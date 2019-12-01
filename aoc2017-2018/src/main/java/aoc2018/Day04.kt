package aoc2018

import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

object Day04 {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    private fun extractDate(s: String): LocalDateTime? {
        val timeRegex = """\[(\d+-\d+-\d+ \d+:\d+)].*""".toRegex()
        timeRegex.matchEntire(s)?.groups?.let { input ->
            val details = input.map { i -> i?.value }.filterNotNull()
            if (details.isNotEmpty()) {
                return LocalDateTime.parse(details[1], formatter)
            }
        }
        return null
    }

    fun extractShiftStart(dateTime: LocalDateTime): LocalDateTime {
        val day = dateTime.plus(5, ChronoUnit.HOURS).truncatedTo(ChronoUnit.DAYS)
        if (dateTime.isBefore(day)) {
            return day
        }
        return dateTime
    }

    fun extractShiftEnd(dateTime: LocalDateTime): LocalDateTime {
        val shiftEnd = dateTime.truncatedTo(ChronoUnit.DAYS).plusMinutes(59)
        if (dateTime.isAfter(shiftEnd)) {
            return shiftEnd
        }
        return dateTime
    }

    private fun getGuardsWithSleepIntervals(guardsWithSleepIntervals: List<Guard>): List<Guard> {
        val map = guardsWithSleepIntervals.groupBy { it.id }.map { (id, guardDays) ->
            val allSleepIntervals = guardDays.flatMap { it.sleepIntervals }
            Guard(id, guardDays[0].shiftStart, allSleepIntervals)
        }
        return map
    }

    private fun getSleepIntervals(input: List<String>): Map<String, List<Interval>> {
        val startTimes = input.filter { x -> x.contains("falls asleep") }
            .mapNotNull { s -> extractDate(s) }
        val endTimes = input.filter { x -> x.contains("wakes up") }
            .mapNotNull { s -> extractDate(s) }
        val intervals = startTimes.mapIndexed { index, startTime ->
            Interval(extractShiftStart(startTime), extractShiftEnd(endTimes[index]))
        }.groupBy { it.getDateKey() }
        return intervals
    }

    private fun getGuardsWithSleepIntervals(
        guards: List<Guard>,
        intervals: Map<String, List<Interval>>
    ): List<Guard> {
        return guards.map { guard ->
            val sleepIntervals = intervals[guard.getDateKey()] ?: emptyList()
            Guard(guard.id, guard.shiftStart, sleepIntervals)
        }
    }

    private fun getConsolidatedGuardList(input: List<String>): List<Guard> {
        val guards = input.map { s -> Guard.parseGuard(s) }.filterNotNull()
        val intervals = getSleepIntervals(input)
        val guardsWithSleepIntervals = getGuardsWithSleepIntervals(guards, intervals)
        return getGuardsWithSleepIntervals(guardsWithSleepIntervals)
    }

    fun part1(input: List<String>): Int {
        val consolidatedGuards = getConsolidatedGuardList(input)
        val theGuard = consolidatedGuards.maxBy { it.getSeepTime() }
        return theGuard!!.id * theGuard.getMaxIntervalMinute()!! // not 14990 96793 too high, 111124 not right
    }

    fun part2(input: List<String>): Int {
        val consolidatedGuards = getConsolidatedGuardList(input)
        val theGuard = consolidatedGuards.maxBy { g -> g.getMaxIntervalMinuteCount() ?: 0 }!!
        return theGuard.id * theGuard.getMaxIntervalMinute()!! // not 14990 96793 too high, 111124 not right
    }
}

data class Interval(val startTime: LocalDateTime, val endTime: LocalDateTime) {
    fun getMinuteDuration(): Int =
        endTime.minute - startTime.minute - 1 //because the end minute is awake!

    fun getDateKey(): String = "${endTime.year}-${endTime.monthValue}-${endTime.dayOfMonth}"

    companion object {
        fun addUpDurations(list: List<Interval>): Int =
            list.fold(0) { acc, interval -> acc + interval.getMinuteDuration() }
    }
}

data class Guard(
    val id: Int,
    val shiftStart: LocalDateTime,
    val sleepIntervals: List<Interval> = emptyList()
) {
    fun getDateKey(): String =
        "${shiftStart.plusHours(1).year}-" +
            "${shiftStart.plusHours(1).monthValue}-" +
            "${shiftStart.plusHours(1).dayOfMonth}"

    fun getSeepTime(): Int = Interval.addUpDurations(sleepIntervals)

    fun getMaxIntervalMinute() = IntArray(59) { it }.maxBy { i -> napsPerMinute(i) }
    fun getMaxIntervalMinuteCount() = IntArray(59) { it }.map { i -> napsPerMinute(i) }.max()

    private fun napsPerMinute(i: Int) =
        sleepIntervals.count { interval -> minuteInInterval(i, interval) }

    private fun minuteInInterval(i: Int, interval: Interval): Boolean {
        val timeAtMinute = interval.endTime.truncatedTo(ChronoUnit.DAYS).plusMinutes(i.toLong())
        return (interval.startTime.isBefore(timeAtMinute) || interval.startTime.isEqual(timeAtMinute))
            && interval.endTime.isAfter(timeAtMinute)
    }

    companion object {
        val guardRegex = """\[(\d+-\d+-\d+ \d+:\d+)] Guard #(\d+) begins shift""".toRegex()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        fun parseGuard(line: String): Guard? {
            guardRegex.matchEntire(line)?.groups?.let { input ->
                val details = input.map { i -> i?.value }.filterNotNull()
                if (details.isNotEmpty()) {
                    return Guard(details[2].toInt(), LocalDateTime.parse(details[1], formatter))
                }
            }
            return null
        }
    }
}

fun main(args: Array<String>) {

    val inputFile = Day04::class.java.classLoader.getResource("aoc2018/day04")
    val input = File(inputFile.path).readLines().sorted()
    println("Part1: " + Day04.part1(input))
    println("Part2: " + Day04.part2(input))
}
