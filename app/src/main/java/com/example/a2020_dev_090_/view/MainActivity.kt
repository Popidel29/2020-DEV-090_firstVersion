package com.example.a2020_dev_090_.view

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.a2020_dev_090_.R
import com.example.a2020_dev_090_.model.GameRepository
import com.example.a2020_dev_090_.viewmodel.GameViewModel
import com.example.a2020_dev_090_.viewmodel.GameViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private val viewModelFactory = GameViewModelFactory(GameRepository("Player One", "Player Two"))
    private lateinit var gameViewModel: GameViewModel
    private lateinit var clickedButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameViewModel = ViewModelProvider(this, viewModelFactory).get(GameViewModel::class.java)
        gameViewModel.activePlayer.observe(this, Observer {
            if (it != null) {
                clickedButton.text = it.mark
                clickedButton.setBackgroundResource(it.backgroundColor)
            }
        })

        gameViewModel.gameResult.observe(this, Observer {
            val alert = AlertDialog.Builder(this).create()
            alert.setTitle("Game Result")
            alert.setMessage("Winner: " + it.toString() + "\n" + "Want to Replay?")
            alert.setButton(AlertDialog.BUTTON_POSITIVE, "Replay")
            { dialog: DialogInterface?, which: Int ->
                resetButtons()
                gameViewModel.resetGame()
            }
            alert.show()
        })
    }

    fun btnClick(view: View) {
        clickedButton = view as Button

        when (clickedButton) {
            btn1 -> gameViewModel.playGame(0, 0)
            btn2 -> gameViewModel.playGame(0, 1)
            btn3 -> gameViewModel.playGame(0, 2)
            btn4 -> gameViewModel.playGame(1, 0)
            btn5 -> gameViewModel.playGame(1, 1)
            btn6 -> gameViewModel.playGame(1, 2)
            btn7 -> gameViewModel.playGame(2, 0)
            btn8 -> gameViewModel.playGame(2, 1)
            btn9 -> gameViewModel.playGame(2, 2)
        }

        gameViewModel.checkWiwnner()
    }

    fun resetButtons() {
        // Clean Butttons and Refresh Board
        btn1.text = ""
        btn2.text = ""
        btn3.text = ""
        btn4.text = ""
        btn5.text = ""
        btn6.text = ""
        btn7.text = ""
        btn8.text = ""
        btn9.text = ""

        btn1.setBackgroundResource(R.color.white)
        btn2.setBackgroundResource(R.color.white)
        btn3.setBackgroundResource(R.color.white)
        btn4.setBackgroundResource(R.color.white)
        btn5.setBackgroundResource(R.color.white)
        btn6.setBackgroundResource(R.color.white)
        btn7.setBackgroundResource(R.color.white)
        btn8.setBackgroundResource(R.color.white)
        btn9.setBackgroundResource(R.color.white)
    }
}
