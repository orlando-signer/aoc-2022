package aoc.util

import java.math.BigDecimal
import java.math.RoundingMode

class Matrix(val values: List<List<BigDecimal>>) {

    companion object {
        fun ofLong(values: List<List<Long>>): Matrix {
            val mapped = values.map { row -> row.map { BigDecimal.valueOf(it) } }
            return Matrix(mapped)
        }

        fun ofDouble(values: List<List<Double>>): Matrix {
            val mapped = values.map { row -> row.map { BigDecimal.valueOf(it) } }
            return Matrix(mapped)
        }

        fun ofString(values: List<String>): Matrix {
            val mapped =
                values.map { row -> row.split(" ").filter { it.isNotBlank() }.map { BigDecimal(it) } }
            return Matrix(mapped)
        }
    }

    fun transpose(): Matrix {
        return Matrix((this.values[0].indices).map { i -> (this.values.indices).map { j -> this.values[j][i] } })
    }

    fun solveLinearEquation(b: List<BigDecimal>): List<BigDecimal> {
        val (A, p) = lupDecompose()
        val n = A.values.size
        val x = MutableList<BigDecimal>(n) { BigDecimal.ZERO }
        if (A.values.size != b.size) {
            throw IllegalStateException("Dimensions dont match: Matrix is $n*$n, b is ${b.size}")
        }
        for (i in 0..<n) {
            x[i] = b[p[i]]
            for (k in 0..<i) {
                x[i] -= A.values[i][k] * x[k]
            }
        }
        for (i in n - 1 downTo 0) {
            for (k in i + 1..<n) {
                x[i] = x[i] - A.values[i][k] * x[k]
            }
            x[i] = x[i].divide(A.values[i][i], RoundingMode.HALF_EVEN)
        }
        stripTrailingZeroes(x)
        return x.toList()
    }


    /**
     * LUP-Decomposition according to 'Introduction to Algorithms', Chapter 28.1
     */
    private fun lupDecompose(): Lup {
        val n = this.values.size
        if (values.any { it.size != n }) {
            throw IllegalStateException("Matrix is not $n * $n")
        }
        val A = values.map { it.map { it.setScale(10) }.toMutableList() }.toMutableList()
        val pi = MutableList(n) { it }

        for (k in 0..<n) {
            var p = BigDecimal.ZERO
            var kTmp = 0
            for (i in k..<n) {
                if (A[i][k].abs() > p) {
                    p = A[i][k].abs()
                    kTmp = i
                }
            }
            if (p == BigDecimal.ZERO) {
                throw IllegalStateException("Singular matrix")
            }
            swap(pi, k, kTmp)

            for (i in 0..<n) {
                val tmp = A[k][i]
                A[k][i] = A[kTmp][i]
                A[kTmp][i] = tmp
            }

            for (i in k + 1..<n) {
                A[i][k] = A[i][k].divide(A[k][k], RoundingMode.HALF_EVEN)
                for (j in k + 1..<n) {
                    A[i][j] = A[i][j] - (A[i][k] * A[k][j])
                }
            }
        }
        return Lup(Matrix(A), pi)
    }

    override fun toString(): String {
        return values.joinToString(separator = "\n") {
            it.joinToString(separator = "") {
                String.format("%6s", it.toString())
            }
        }
    }

    data class Lup(val lu: Matrix, val p: List<Int>)

    private fun swap(arr: MutableList<Int>, i: Int, j: Int) {
        val tmp = arr[i]
        arr[i] = arr[j]
        arr[j] = tmp
    }

    private fun stripTrailingZeroes(a: MutableList<BigDecimal>) {
        for (i in 0..<a.size) {
            a[i] = a[i].setScale(5, RoundingMode.HALF_EVEN).stripTrailingZeros()
        }
    }
}