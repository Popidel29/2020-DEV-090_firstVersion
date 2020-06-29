package com.example.a2020_dev_090_

import com.example.a2020_dev_090_.model.GameRepository
import org.junit.Before
import org.junit.Test

class GameTest {
    lateinit var gameRepository: GameRepository

    @Before
    fun setup(){
        gameRepository = GameRepository("P1","P2")
    }

    @Test
    fun testPlayer1WinningHorizontal(){
        gameRepository.playGame(0,0)
        gameRepository.switchPlayer()
        gameRepository.playGame(1,0)
        gameRepository.switchPlayer()
        gameRepository.playGame(0,1)
        gameRepository.switchPlayer()
        gameRepository.playGame(1,1)
        gameRepository.switchPlayer()
        gameRepository.playGame(0,2)
        gameRepository.switchPlayer()
        val winner = gameRepository.checkWinner()
        assert(winner?.name == "P1")
    }

    @Test
    fun testPlayer1WinningVerticalRow1(){
        gameRepository.playGame(0,0)
        gameRepository.switchPlayer()
        gameRepository.playGame(0,1)
        gameRepository.switchPlayer()
        gameRepository.playGame(1,0)
        gameRepository.switchPlayer()
        gameRepository.playGame(1,1)
        gameRepository.switchPlayer()
        gameRepository.playGame(2,0)
        gameRepository.switchPlayer()
        val winner = gameRepository.checkWinner()
        assert(winner?.name == "P1")
    }

    @Test
    fun testPlayer1WinningHorizontalRow2(){
        gameRepository.playGame(1,0)
        gameRepository.switchPlayer()
        gameRepository.playGame(2,0)
        gameRepository.switchPlayer()
        gameRepository.playGame(1,1)
        gameRepository.switchPlayer()
        gameRepository.playGame(2,1)
        gameRepository.switchPlayer()
        gameRepository.playGame(1,2)
        gameRepository.switchPlayer()
        val winner = gameRepository.checkWinner()
        assert(winner?.name == "P1")
    }
    @Test
    fun testPlayer1WinningHorizontalRow3(){
        gameRepository.playGame(2,0)
        gameRepository.switchPlayer()
        gameRepository.playGame(0,0)
        gameRepository.switchPlayer()
        gameRepository.playGame(2,1)
        gameRepository.switchPlayer()
        gameRepository.playGame(0,1)
        gameRepository.switchPlayer()
        gameRepository.playGame(2,2)
        gameRepository.switchPlayer()
        val winner = gameRepository.checkWinner()
        assert(winner?.name == "P1")
    }
}