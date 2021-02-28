package tictactoe

import java.util.*
/*
Stage 3/5: What's up on the field?
*/
fun main() {
    val matrix = readInput()
    matrix.printFields()
    print(matrix.state())
}

fun readInput(): Matrix {
    print("Enter cells: ")
    val scanner = Scanner(System.`in`)
    val input = scanner.next().split("")

    val matrix = Matrix(3,3)
    var n = 1
    for (y in 0..2){
        for (x in 0..2) {
            matrix.m[y][x] = when (input[n++]) {
                "X" -> 1
                "O" -> -1
                else -> 0
            }
        }
    }
    return matrix
}

class Matrix {
    var x: Int = 0
    var y: Int = 0
    val m: Array<IntArray>

    var isItImposible = false
    var isWinsX = false
    var isWinsO = false
    var hasEmptyCells = false

    fun state(): String {
        sumMatrix()
        checkRows()
        checkColumns()
        checkAscDiagonal()
        checkDescDiagonal()
        checkDoubleWiners()
        if (isItImposible) return "Impossible"
        if (isWinsX) return "X wins"
        if (isWinsO) return "O wins"
        if (!hasEmptyCells) return "Draw"
        if (hasEmptyCells) return "Game not finished"
        return "Impossible @*&%#!!!"
    }
    fun sumMatrix() {
        var sum = 0
        var currentElement = 0
        for (i in 0 until y) {
            for (j in 0 until x) {
                currentElement = getElenent(i, j)
                sum += currentElement
                if (currentElement == 0) hasEmptyCells = true
            }
        }
        if (sum > 1 || sum < -1) isItImposible = true
    }
    fun getElenent(row: Int, column: Int) = m[row][column]
    fun checkRow(x: Int) = getElenent(x, 0) + getElenent(x, 1) + getElenent(x, 2)
    fun checkColumn(y: Int) = getElenent(0, y) + getElenent(1, y) + getElenent(2, y)
    fun checkRows() {
        var whoseRow = 0
        for (i in 0 until x) {
            whoseRow = checkRow(i)
            if (whoseRow == 3) isWinsX = true
            if (whoseRow == -3) isWinsO = true
        }
    }
    fun checkColumns() {
        var whoseColumn = 0
        for (i in 0 until y) {
            whoseColumn = checkColumn(i)
            if (whoseColumn == 3) isWinsX = true
            if (whoseColumn == -3) isWinsO = true
        }
    }
    /*
        XXX
        OXO
        OOX
    */
    fun checkAscDiagonal() {
        val whoseDiagonal = getElenent(2, 0) + getElenent(1, 1) + getElenent(0, 2)
        if (whoseDiagonal == 3 /*&& !isWinsX*/) isWinsX = true
        if (whoseDiagonal == -3/* && !isWinsO*/) isWinsO = true
    }
    fun checkDescDiagonal() {
        val whoseDiagonal = getElenent(0, 0) + getElenent(1, 1) + getElenent(2, 2)
        if (whoseDiagonal == 3 /*&& !isWinsX*/) isWinsX = true
        if (whoseDiagonal == -3 /*&& !isWinsO*/) isWinsO = true
    }
    private fun checkDoubleWiners() {
        if (isWinsX && isWinsO) isItImposible = true
    }
    fun printFields() {
        var output = "---------\n"
        for (y in 0..2){
            output += "| "
            for (x in 0..2) {
                output += when (getElenent(y, x)) {
                    1 -> "X "
                    -1 -> "O "
                    else -> "_ "
                }
            }
            output += "|\n"
        }
        output += "---------\n"
        print(output)
    }
    constructor (
            x: Int,
            y: Int
    ) {
        this.x = x
        this.y = y
        m = Array(x) { IntArray(y) }
        for (i in 0 until x) {
            for (j in 0 until y) m[i][j] = 0
        }
    }
}
/*    Stage 1-5 Welcome to the battlefield
    print("X O X\n" +
            "O X O\n" +
            "X X O ")
*/
/*    Stage 2/5: The user is the gamemaster
val scanner = Scanner(System.`in`)
val input = scanner.next().split("")
val matrix = Matrix(3,3)
var n = 1
for (y in 0..2){
    for (x in 0..2) {
        matrix.m[x][y] = when (input[n++]) {
            "X" -> 1
            "O" -> 2
            else -> 0
        }
    }
}
var output = "---------\n"
for (y in 0..2){
    output += "| "
    for (x in 0..2) {
        output += when (matrix.m[x][y]) {
            1 -> "X "
            2 -> "O "
            else -> "_ "
        }
    }
    output += "|\n"
}
output += "---------"
print(output)
}
class Matrix {
    var x: Int = 0
    var y: Int = 0
    val m: Array<IntArray>
    constructor(
            x: Int,
            y: Int
    ) {
        this.x = x
        this.y = y
        m = Array(x) { IntArray(y) }
        for (i in 0 until x) {
            for (j in 0 until y)
                m[i][j] = 0
        }
    }
}
*/