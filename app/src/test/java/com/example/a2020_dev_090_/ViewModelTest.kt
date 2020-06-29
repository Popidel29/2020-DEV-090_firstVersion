package com.example.a2020_dev_090_

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.a2020_dev_090_.model.GameRepository
import com.example.a2020_dev_090_.model.Player
import com.example.a2020_dev_090_.viewmodel.GameViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    private lateinit var gameViewModel: GameViewModel

    @MockK
    private lateinit var gameRepository: GameRepository

    @SpyK
    val activePlayerResponseObserver = Observer<Player> {}

    @SpyK
    val gameResultResponseObserver = Observer<String> {}

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        gameViewModel = spyk((GameViewModel(gameRepository)))
        gameViewModel.activePlayer.observeForever(activePlayerResponseObserver)
        gameViewModel.gameResult.observeForever(gameResultResponseObserver)
    }

    @Test
    fun `Testing Active Player`() {

        //Setup
        val result = Player("PlayerOne", "X", R.color.green)

        //given
        coEvery {
            gameRepository.playGame(0,1)
        } returns result

        //when
        gameViewModel.playGame(0,1)

        //Then
        verify { activePlayerResponseObserver.onChanged(result)}
    }

    @Test
    fun `Testing Win Result`() {

        //Setup
        val result = Player("PlayerOne", "X", R.color.green)

        //given
        coEvery {
            gameRepository.checkWinner()
        } returns result

        //when
        gameViewModel.checkWiwnner()

        //Then
        verify { gameResultResponseObserver.onChanged(result.name)}
    }

    @Test
    fun `Testing Draw Result`() {

        val result = "Draw"

        //given
        coEvery {
            gameRepository.checkWinner()
        } returns null

        coEvery {
            gameRepository.isDraw()
        } returns true

        //when
        gameViewModel.checkWiwnner()

        //Then
        verify { gameResultResponseObserver.onChanged(result)}
    }
}