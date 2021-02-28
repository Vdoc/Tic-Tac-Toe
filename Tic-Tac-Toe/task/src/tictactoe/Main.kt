package tictactoe

import java.util.*

fun main() {
    val matrix = Matrix(3,3)
    matrix.printFields()
    while (!matrix.isGameEnds) {
        matrix.readCoordinates()
        matrix.changePlayer()
        matrix.printFields()
        matrix.checkState()
    }
    print(matrix.getState())
}

class Matrix {
    var x: Int = 0
    var y: Int = 0
    val m: Array<IntArray>
    var player = 1
    var straightLine = 0

    var isWinsX = false
    var isWinsO = false
    var hasEmptyCells = false
    var isItImposible = false

    var isGameEnds = false

    fun checkState() {
        sumMatrix()
        checkRows()
        checkColumns()
        checkAscDiagonal()
        checkDescDiagonal()
        if (isItImposible || isWinsX || isWinsO || !hasEmptyCells) isGameEnds = true
    }

    fun getState(): String {
        if (isItImposible) return "Impossible"
        if (isWinsX) return "X wins"
        if (isWinsO) return "O wins"
        if (!hasEmptyCells) return "Draw"
        return "Game not finished"
    }

    fun readCoordinates() {
        var x = 0
        var y = 0
        while (true) {
            print("Enter the coordinates: ")
            val scanner = Scanner(System.`in`)
            val input = scanner.nextLine().split(" ")

            if (input.size != 2) print("You should enter numbers!\n")
            else if (input[0].length == 1 && input[1].length == 1) {
                if (input[0].first().isDigit() && input[1].first().isDigit()) {
                    x = input[1].toInt()
                    y = input[0].toInt()
                    if (x in 1..3 && y in 1..3) break
                    print("Coordinates should be from 1 to 3!\n")
                    continue
                }
            }
        }
        addMove(x - 1, y - 1)
    }

    private fun addMove(x: Int, y: Int) {
        if (getElenent(y,x) == 0) m[y][x] = player
        else {
            print("This cell is occupied! Choose another one!\n")
            readCoordinates()
        }
    }

    fun changePlayer() {
        player = if (player == 1) -1 else 1
    }

    fun sumMatrix() {
        hasEmptyCells = false
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

    fun checkRows() {
        for (i in 0 until x) {
            straightLine = checkRow(i)
            if (straightLine == 3) isWinsX = true
            if (straightLine == -3) isWinsO = true
        }
    }

    fun checkColumns() {
        for (i in 0 until y) {
            straightLine = checkColumn(i)
            if (straightLine == 3) isWinsX = true
            if (straightLine == -3) isWinsO = true
        }
    }

    fun checkAscDiagonal() {
        straightLine = getElenent(2, 0) + getElenent(1, 1) + getElenent(0, 2)
        if (straightLine == 3) isWinsX = true
        if (straightLine == -3) isWinsO = true
    }

    fun checkDescDiagonal() {
        straightLine = getElenent(0, 0) + getElenent(1, 1) + getElenent(2, 2)
        if (straightLine == 3) isWinsX = true
        if (straightLine == -3) isWinsO = true
    }

    fun checkRow(x: Int) = getElenent(x, 0) + getElenent(x, 1) + getElenent(x, 2)

    fun checkColumn(y: Int) = getElenent(0, y) + getElenent(1, y) + getElenent(2, y)

    fun getElenent(row: Int, column: Int) = m[row][column]

    fun printFields() {
        var output = "---------\n"
        for (y in 0..2){
            output += "| "
            for (x in 0..2) {
                output += when (getElenent(y, x)) {
                    1 -> "X "
                    -1 -> "O "
                    else -> "  "
                }
            }
            output += "|\n"
        }
        output += "---------\n"
        print(output)
    }

    constructor (x: Int, y: Int) {
        this.x = x
        this.y = y
        m = Array(x) { IntArray(y) }
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




/*
Stage 3/5: What's up on the field?
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
        checkAscDiagonal()Stage 3/5: What's up on the field
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
 */




/*
Stage 4/5: First move!

fun main() {
    val matrix = readInput()
    matrix.printFields()
//    print(matrix.state())
    matrix.readTurn(1)
    matrix.printFields()
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

    fun readTurn(player: Int) {
        var x = 0
        var y = 0
        while (true) {
            print("Enter the coordinates: ")
            val scanner = Scanner(System.`in`)
            val input = scanner.nextLine().split(" ")

            if (input.size == 2) {
                if (input[0].length == 1 && input[1].length == 1) {
                    if (input[0].first().isDigit() && input[1].first().isDigit()) {
                        x = input[1].toInt()
                        y = input[0].toInt()
                        if (y in 1..3 && x in 1..3) break
                        print("Coordinates should be from 1 to 3!\n")
                        continue
                    }
                }
            }
            print("You should enter numbers!\n")
        }
        addMove(x - 1, y - 1, player)
    }

    private fun addMove(x: Int, y: Int, player: Int) {
        if (getElenent(y,x) == 0) m[y][x] = player
        else {
            print("This cell is occupied! Choose another one!\n")
            readTurn(player)
        }
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

    fun checkAscDiagonal() {
        val whoseDiagonal = getElenent(2, 0) + getElenent(1, 1) + getElenent(0, 2)
        if (whoseDiagonal == 3) isWinsX = true
        if (whoseDiagonal == -3) isWinsO = true
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
                    else -> "  "
                }
            }
            output += "|\n"
        }
        output += "---------\n"
        print(output)
    }

    constructor (x: Int, y: Int) {
        this.x = x
        this.y = y
        m = Array(x) { IntArray(y) }
    }
}
*/