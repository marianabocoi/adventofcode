import org.junit.Test
import org.assertj.core.api.Assertions.*

class Day01Test {

    @Test
    fun part2() {
        // mass=14 fuel=2, further fuel = 0
        assertThat(Day01.calculateFuelRecursively(14.0)).isEqualTo(2.0)

        // mass 1969 requires 654 fuel. total fuel 654 + 216 + 70 + 21 + 5 = 966
        assertThat(Day01.calculateFuelRecursively(1969.0)).isEqualTo(966.0)

        // mass 100756 fuel is: 33583 + 11192 + 3728 + 1240 + 411 + 135 + 43 + 12 + 2 = 50346
        assertThat(Day01.calculateFuelRecursively(100756.0)).isEqualTo(50346.0)
    }
}