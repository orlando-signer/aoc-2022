package aoc

import aoc.util.InputReader

abstract class Day(dayNumber: Int) {

    // lazy delegate ensures the property gets computed only on first access
    protected val inputList: List<String> by lazy { InputReader.getInputAsList(javaClass.packageName.replace(".","/"), dayNumber) }
    protected val inputString: String by lazy { InputReader.getInputAsString(javaClass.packageName.replace(".","/"), dayNumber) }
    protected val isInTest: Boolean by lazy { Thread.currentThread().stackTrace.any{it.className.contains("io.kotest")} }

    abstract fun partOne(): Any

    abstract fun partTwo(): Any
}
