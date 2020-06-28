package com.example.a2020_dev_090.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a2020_dev_090.model.Game
import com.example.a2020_dev_090.model.Player

class GameViewModel(private val game: Game): ViewModel() {

    val activePlayer : LiveData<Player>
        get() = mActivePlayer
    private var mActivePlayer = MutableLiveData<Player>()

    val  gameResult : LiveData<String>
        get() = mgameResult
    private var mgameResult = MutableLiveData<String>()

    fun playGame( x: Int,  y: Int)
    {
        mActivePlayer.postValue(game.playGame(x,y))

    }

    fun checkWiwnner()
    {
        var winnerPlayer : Player? = game.checkWinner()

        if(winnerPlayer == null && game.isDraw())
        {
            mgameResult.postValue("Draw")
            return
        }

        if(winnerPlayer!= null)
        {
            mgameResult.postValue(winnerPlayer.name)
            return
        }
    }
}