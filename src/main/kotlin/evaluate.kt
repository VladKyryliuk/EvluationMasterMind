import javax.swing.text.Position

data class Evaluation(val position: Int, val letters: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation{
    val positions = getGuessedRightPositions(secret,guess)
    val letters = getGuessedWrongPositions(secret,guess)

    return Evaluation(positions,letters)
}

private fun getGuessedRightPositions(secret: String, guess: String): Int {
    var positions = 0
    for (i in 0 until secret.length){
        if (guess[i] == secret[i]){
            positions++
        }
    }
    return positions
}
private fun getGuessedWrongPositions(secret: String, guess: String): Int {
    var newSecret = ""
    var newGuess = ""
    var rightLetters = 0

    for (i in 0 until secret.length) {
        if (secret[i] != guess[i]){
            newGuess += guess[i]
            newSecret += secret[i]
        }
    }

    val evaluatedChars = mutableListOf<Char>()
    if (!newSecret.isEmpty()){
        for (i in 0 until guess.length){
            val letter = guess[i]
            if (!evaluatedChars.contains(letter)){
                val quantityInSecret = count(newSecret,letter)
                val quantityInGuess = count(newGuess,letter)
                rightLetters += if (quantityInGuess == quantityInSecret || quantityInSecret > quantityInGuess) quantityInGuess
                else quantityInSecret

                evaluatedChars.add(letter)
            }

        }
    }

    return rightLetters
}

fun count(letters: String, letter: Char ): Int{
    var howMany = 0
    for (i in 0 until letters.length){
        if (letter == letters[i]){
            howMany++
        }
    }
    return howMany
}

