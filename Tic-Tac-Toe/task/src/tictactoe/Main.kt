package tictactoe

import java.util.*

fun main() {
    // write your code here
/*    Stage 1-5 Welcome to the battlefield
    print("X O X\n" +
            "O X O\n" +
            "X X O ")
*/

/*    Stage 2/5: The user is the gamemaster
*/
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