import org.junit.Test
import org.assertj.core.api.Assertions.*

class Day03Test {

    @Test
    fun part1() {
        assertThat(
            Day03.part1(
                listOf("R8", "U5", "L5", "D3"),
                listOf("U7", "R6", "D4", "L4")
            )
        ).isEqualTo(6)
        assertThat(
            Day03.part1(
                listOf("R75", "D30", "R83", "U83", "L12", "D49", "R71", "U7", "L72"),
                listOf("U62", "R66", "U55", "R34", "D71", "R55", "D58", "R83")
            )
        ).isEqualTo(159)
        assertThat(
            Day03.part1(
                listOf("R98","U47","R26","D63","R33","U87","L62","D20","R33","U53","R51"),
                listOf("U98","R91","D20","R16","D67","R40","U7","R15","U6","R7")
            )
        ).isEqualTo(135)
    }
}