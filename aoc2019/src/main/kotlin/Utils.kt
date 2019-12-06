class Utils {
    companion object {
        fun getInput(filename: String) = Utils::class.java.getResource(filename).readText()
        fun getInputAsList(filename: String, delim: String = "\n"): List<String> =
            getInput(filename).split(delim)

        fun getInputAsIntList(filename: String, delim: String = "\n"): List<Int> =
            getInputAsList(filename, delim).map { it.toInt() }

        fun getInputAsDoubleList(filename: String): List<Double> =
            getInputAsList(filename).map { it.toDouble() }

        fun getInputAsStringList(filename: String, delim: String) =
            getInputAsList(filename).map { it.split(delim) }
    }
}