import Day04.decreasing
import Day04.hasDouble
import Day04.hasExactDouble
import org.junit.Test
import org.assertj.core.api.Assertions.*

class Day06Test {

    @Test
    fun part1() {
        assertThat(111111.toString().let { hasDouble(it) && decreasing(it) }).isTrue()
        assertThat(123789.toString().let { hasDouble(it) && decreasing(it) }).isFalse()
        assertThat(223450.toString().let { hasDouble(it) && decreasing(it) }).isFalse()

        assertThat(112233.toString().let { hasExactDouble(it) && decreasing(it) }).isTrue()
        assertThat(111122.toString().let { hasExactDouble(it) && decreasing(it) }).isTrue()
        assertThat(123444.toString().let { hasExactDouble(it) && decreasing(it) }).isFalse()
    }
}