class Utils {
    companion object {
        fun getInput(filename: String) = Utils::class.java.getResource(filename).readText()
        fun getInputAsList(filename: String): List<String> = getInput(filename).split("\n")
        fun getInputAsIntList(filename: String): List<Int> =
            getInputAsList(filename).map { it.toInt() }

        fun getInputAsDoubleList(filename: String): List<Double> =
            getInputAsList(filename).map { it.toDouble() }
    }
}