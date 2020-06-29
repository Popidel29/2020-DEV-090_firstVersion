package com.example.a2020_dev_090_.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a2020_dev_090_.model.GameRepository
import com.example.a2020_dev_090_.model.Player

class GameViewModel(private val gameRepository: GameRepository): ViewModel() {

    val activePlayer : LiveData<Player>
        get() = mActivePlayer
    private var mActivePlayer = MutableLiveData<Player>()

    val  gameResult : LiveData<String>
        get() = mGameResult
    private var mGameResult = MutableLiveData<String>()

    fun playGame( x: Int,  y: Int)
    {
        mActivePlayer.postValue(gameRepository.playGame(x,y))

    }

    fun checkWiwnner()
    {
        var winnerPlayer : Player? = gameRepository.checkWinner()

        if(winnerPlayer == null && gameRepository.isDraw())
        {
            mGameResult.postValue("Draw")
            return
        }

        if(winnerPlayer!= null)
        {
            mGameResult.postValue(winnerPlayer.name)
            return
        }
    }

    fun resetGame()
    {
        gameRepository.resetGame()
    }

    fun switchPlayer() {
        gameRepository.switchPlayer()
    }
}