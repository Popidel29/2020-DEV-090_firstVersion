package com.example.a2020_dev_090.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.a2020_dev_090.R
import com.example.a2020_dev_090.model.Game
import com.example.a2020_dev_090.viewmodel.GameViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private var gameViewModel = GameViewModel(Game("Player One", "Player Two"))
    private lateinit var clickedButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameViewModel.activePlayer.observe(this, Observer {
            if(it != null)
            {
                clickedButton.text = it.mark
                clickedButton.setBackgroundResource(it.backgroundColor)
            }
        })

        gameViewModel.gameResult.observe(this, Observer {
            Toast.makeText(this, "Winner: " + it.toString(), Toast.LENGTH_LONG).show()
        })
    }

    fun btnClick(view: View){
        clickedButton = view as Button

        when (clickedButton) {
            btn1 -> gameViewModel.playGame(0,0)
            btn2 -> gameViewModel.playGame(0,1)
            btn3 -> gameViewModel.playGame(0,2)
            btn4 -> gameViewModel.playGame(1,0)
            btn5 -> gameViewModel.playGame(1,1)
            btn6 -> gameViewModel.playGame(1,2)
            btn7 -> gameViewModel.playGame(2,0)
            btn8 -> gameViewModel.playGame(2,1)
            btn9 -> gameViewModel.playGame(2,2)
        }

        gameViewModel.checkWiwnner()

    }
}
