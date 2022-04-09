package aoc2018

import java.io.File

object Day02 {
    fun stringCount(s: String): MutableMap<Int, Int> {
        var mutableList: MutableMap<Int, Int> = mutableMapOf()
        var mutableMal: MutableMap<Char, Int> = mutableMapOf()
        s.forEach { c ->
            mutableMal[c] = mutableMal[c]?.inc() ?: 1
        }
        mutableMal.forEach {
            _, i ->
            if (i > 1 && i < 4) mutableList[i] = 1
        }
        return mutableList
    }

    fun countStuff(input: List<String>): Int {
        var res = mutableMapOf<Int, Int>()
        input.forEach { s ->
            stringCount(s).forEach { i, n ->
                //                res[i] = res[i]?.let { it + n } ?: n
                res[i] = res[i]?.inc() ?: 1
            }
        }
        return res.values.fold(1) {
            acc, d ->
            d * acc
        }
    }

    fun part2(input: List<String>): String {
        val mm = input.sorted()
        mm.forEachIndexed { i, s1 ->
            if (i < mm.size - 1) {
                val s2: String = mm[i + 1]
                val diff1: MutableMap<Char, Int> = mutableMapOf()
                val diff2: MutableMap<Char, Int> = mutableMapOf()

                for (j in 0..s1.length - 1) {
                    diff1[s1[j]] = diff1[s1[j]]?.inc() ?: 1
                    diff2[s2[j]] = diff2[s2[j]]?.inc() ?: 1
                }
                var res: MutableMap<Char, Int> = mutableMapOf()
                diff1.forEach { (k, v) ->
                    res[k] = v
                }
                diff2.forEach { (k, v) ->
                    res[k] = res[k]?.let { it - v } ?: -v
                }
                res = res.filter<Char, Int> { (_, v) -> v != 0 } as MutableMap<Char, Int>
                if (res.size == 2) {
                    var result = "???"
                    res.forEach { k, v -> if (v == 1) result = s1.replaceFirst(k.toString(), "") }
                    return result
                }
            }
        }
        return "?"
    }
}


fun main(args: Array<String>) {

    val imputFile = Day01::class.java.classLoader.getResource("aoc2018/day02")
    val input = File(imputFile.path).readLines()
    println("Part1: " + Day02.countStuff(input))
    println("Part2: " + Day02.part2(input))
}