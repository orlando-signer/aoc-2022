package aoc._2024

import aoc.Day

class Day17 : Day(17) {

    override fun partOne(): Any {
        val regValues = inputList.take(3)
            .map { it.split(":")[1] }
            .map { it.trim().toLong() }
        val registers = Registers(regValues[0], regValues[1], regValues[2], 0)

        return -1
    }

    fun execute(opcode: Long, operand: Long, registers: Registers) {
        when (opcode) {
            0L -> { // adv: A division
                val comboOperand = getComboOperand(operand, registers)
                registers.regA = registers.regA / (2L.shl(comboOperand.toInt()))
            }

            1L -> { // bxl: B XOR literal
                registers.regB = registers.regB.xor(operand)
            }

            2L -> { // bst: B mod 8
                val comboOperand = getComboOperand(operand, registers)

                registers.regB = comboOperand.mod(8L)
            }

            3L -> { // jnz: Jump if A NOT zero

            }

            4L -> { // bxc: B XOR c
                registers.regB = registers.regB.xor(registers.regC)
            }

            5L -> { // out: returns the comboOp modulo 8
                val comboOperand = getComboOperand(operand, registers)
                print(comboOperand.mod(8))
            }

            6L -> { // bdv: same as adv but store in B
                val comboOperand = getComboOperand(operand, registers)

                registers.regB = registers.regA / (2L.shl(comboOperand.toInt()))
            }

            7L -> { // cdv: same as adv but store in B
                val comboOperand = getComboOperand(operand, registers)
                registers.regC = registers.regA / (2L.shl(comboOperand.toInt()))
            }

            else -> throw Exception("Invalid Operation")
        }
    }

    private fun getComboOperand(operand: Long, registers: Registers): Long {
        val comboOperand = when (operand) {
            0L, 1L, 2L, 3L -> operand
            4L -> registers.regA
            5L -> registers.regB
            6L -> registers.regC
            else -> throw Exception("Invalid operand $operand")
        }
        return comboOperand
    }


    override fun partTwo(): Any {
        return -2
    }

    class Registers(var regA: Long, var regB: Long, var regC: Long, pc: Long)
}
