package org.example

import Evaluation
import evaluateGuess
import java.util.*


val LETTERS = 'A'..'G'
val SECRET_LENGTH = 4


fun main(args: Array<String>) {
    playMastermind()
}

fun playMastermind( secret: String = generateSecret()) {
    val scanner = Scanner(System.`in`)
    var evaluation: Evaluation

    do{
        print("You guess: ")
        var guess = scanner.next()
        while(errors(guess)){
            println("Incorrect input: $guess." +
            "the entered text must consist of 4 letters from A to G!"+
            "Please try again.")
            guess = scanner.next()
        }
        evaluation = evaluateGuess(secret,guess)
        if (evaluation.position == SECRET_LENGTH){
            println("You win!!!")
        }
        else{
            println("Guessed positions: ${evaluation.position}, Guessed letters: ${evaluation.letters}")
        }
    } while (!(evaluation.position == SECRET_LENGTH))
}

fun errors(guess:String): Boolean {
    return guess.length != SECRET_LENGTH || guess.any{it !in LETTERS.toSet()}
}

fun generateSecret(): String {
    val chars = LETTERS.toMutableList()
    val random = Random()
    return buildString {
        for (i in 1..SECRET_LENGTH) {
            val letter = chars[random.nextInt(chars.size)]
            append(letter)
        }
    }
}