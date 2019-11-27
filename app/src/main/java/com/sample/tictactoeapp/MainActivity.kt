package com.sample.tictactoeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btnClick(v: View){
        val btnSelected = v as Button
        var cellID = 0
        when(btnSelected.id){
            R.id.bnt1 -> cellID = 1
            R.id.bnt2 -> cellID = 2
            R.id.bnt3 -> cellID = 3
            R.id.bnt4 -> cellID = 4
            R.id.bnt5 -> cellID = 5
            R.id.bnt6 -> cellID = 6
            R.id.bnt7 -> cellID = 7
            R.id.bnt8 -> cellID = 8
            R.id.bnt9 -> cellID = 9
        }

        Log.d("btnClicked", cellID.toString() )

        playGame(cellID, btnSelected)

        autoPlay()
    }

    var activePlayer = 1
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    fun playGame(cellID:Int, btnSelected:Button){
        if (activePlayer == 1){
            btnSelected.text = "X"
            btnSelected.setBackgroundResource(R.color.blue)
            player1.add(cellID)
            activePlayer = 2
        }else{
            btnSelected.text = "O"
            btnSelected.setBackgroundResource(R.color.green)
            player2.add(cellID)
            activePlayer = 1
        }

        btnSelected.isEnabled = true

        checkWinner()

    }

    fun checkWinner(){
        var winner = -1
        //row
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }
        //row
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }
        //row
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }
        //cloum
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }
        //cloum
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }
        //cloum
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }
        //sp
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
        }
        //sp
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
        }


        if (winner == 1){
            player1Count += 1
            Toast.makeText(this, "Player 1 win the game", Toast.LENGTH_LONG).show()
            restartGame()

        }else if(winner == 2){
            Toast.makeText(this, "Player 2 win the game", Toast.LENGTH_LONG).show()
            player2Count += 1
            restartGame()
        }
    }

    fun autoPlay(){
        var emptyCell = ArrayList<Int>()
        for (cellId in  1..9){
            if (!(player1.contains(cellId)||player2.contains(cellId))){
                emptyCell.add(cellId)
            }
        }
        val r = Random
        val rIndex = r.nextInt(emptyCell.size)
        val cellId = emptyCell[rIndex]
        var btnSelected: Button?
        btnSelected = when(cellId) {
            1 -> bnt1
            2 -> bnt2
            3 -> bnt3
            4 -> bnt4
            5 -> bnt5
            6 -> bnt6
            7 -> bnt7
            8 -> bnt8
            9 -> bnt9
            else -> {
                bnt1
            }
        }
        playGame(cellId, btnSelected)

        if (emptyCell.size == 0){
            restartGame()
        }
    }


    var player1Count = 0
    var player2Count = 0
    fun restartGame(){
        activePlayer = 1
        player1.clear()
        player2.clear()
        for (cellId in 1..9){
            var btnSelected: Button?
            btnSelected = when(cellId) {
                1 -> bnt1
                2 -> bnt2
                3 -> bnt3
                4 -> bnt4
                5 -> bnt5
                6 -> bnt6
                7 -> bnt7
                8 -> bnt8
                9 -> bnt9
                else -> {
                    bnt1
                }
            }
            btnSelected!!.text = ""
            btnSelected!!.setBackgroundResource(R.color.btn)
            btnSelected!!.isEnabled = true
        }

        Toast.makeText(this, "Player1: $player1Count \t Player2: $player2Count", Toast.LENGTH_LONG).show()

    }

}
