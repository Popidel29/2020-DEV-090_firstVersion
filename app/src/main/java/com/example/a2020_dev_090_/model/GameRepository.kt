package com.example.a2020_dev_090_.model

import com.example.a2020_dev_090_.R

class GameRepository(playerOneName : String, playerTwoName: String) {

    private val playerOne = Player(playerOneName,"X", R.color.green)
    private var playerTwo =  Player(playerTwoName,"O", R.color.red)
    private var activePlayer = playerOne
    private var moveCount = 0

    private var board = Array(3){Array(3){Player("","", R.color.white)} }

    fun resetGame()
    {
        activePlayer = playerOne
        board = Array(3){Array(3){Player("","", R.color.white)} }
        moveCount = 0
    }

    fun playGame( x : Int,  y: Int) : Player?
    {
        if(board[x][y].name !=  "")
        {
            return null
        }

        moveCount++
        board[x][y] =  activePlayer
        return  activePlayer
    }

     fun switchPlayer(){
         activePlayer = if(activePlayer == playerOne){
             playerTwo
         }else{
             playerOne
         }
     }

    fun isDraw() : Boolean
    {
        return moveCount == 9
    }

    fun checkWinner() : Player? {

        var winner : Player ?= null
        //check row
        for(i in 0..2){
            if(board[i][0] == board[i][1] && board[i][1] == board[i][2]){
                winner = board[i][0]
                break
            }
        }
        //check col
        for(i in 0..2){
            if(board[0][i] == board[1][i] && board[1][i] == board[2][i]){
                winner = board[1][i]
                break
            }
        }
        //check diagonal
        for(i in 1..2){
            if(board[0][0] != board[i][i])
            {
                break
            }
            if (i == 2) {
                winner = board[0][0]
            }
        }
        //check anti- diagonal
        for(i in 1..2){
            if (board[i][2 -i] != board[0][2]) {
                break
            }
            if (i == 2) {
                winner = board[0][2]
            }
        }
        switchPlayer()
        return  winner
    }
}