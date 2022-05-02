package com.meu.qualeacapital

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.meu.qualeacapital.databinding.ActivityQuestionBinding
import com.meu.qualeacapital.databinding.CorrectDialogLayoutBinding
import com.meu.qualeacapital.databinding.WrongDialogLayoutBinding

class QuestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionBinding
    private lateinit var bindingResultCorrect: CorrectDialogLayoutBinding
    private lateinit var bindingResultWrong: WrongDialogLayoutBinding
    private var questionList: ArrayList<Question>? = null
    private val position = MainActivity.mixPosition[sum -1]

    companion object{
        var sum: Int = 1
        var A:Boolean = false
        var B:Boolean = false
        var C:Boolean = false
        var D:Boolean = false
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        questionList = Constants.getQuestions()
        savingAnswer()

        val timer = countDownTimer()

        binding.tvOptionOne.setOnClickListener {
            timer!!.cancel()
            equal()
            correction(A)
            if (A){
                binding.tvOptionOne.setBackgroundColor(resources.getColor(R.color.green_500))
            } else {
                binding.tvOptionOne.setBackgroundColor(resources.getColor(R.color.red_500))
            }
        }
        binding.tvOptionTwo.setOnClickListener {
            timer!!.cancel()
            equal()
            correction(B)
            if (B){
                binding.tvOptionTwo.setBackgroundColor(resources.getColor(R.color.green_500))
            } else {
                binding.tvOptionTwo.setBackgroundColor(resources.getColor(R.color.red_500))
            }
        }
        binding.tvOptionThree.setOnClickListener {
            timer!!.cancel()
            equal()
            correction(C)
            if (C){
                binding.tvOptionThree.setBackgroundColor(resources.getColor(R.color.green_500))
            } else {
                binding.tvOptionThree.setBackgroundColor(resources.getColor(R.color.red_500))
            }
        }
        binding.tvOptionFour.setOnClickListener {
            timer!!.cancel()
            equal()
            correction(D)
            if (D){
                binding.tvOptionFour.setBackgroundColor(resources.getColor(R.color.green_500))
            } else {
                binding.tvOptionFour.setBackgroundColor(resources.getColor(R.color.red_500))
            }
        }

        binding.points.text = "$sum|${questionList!!.size}"
        binding.progressBar.progress = (sum * 100 / questionList!!.size )

    }

    private fun correction(answer:Boolean) {
        if (answer) {
            openDialogCorrect()
            sum++
        } else {
            openDialogWrong()
            sum = 1
        }
    }

    private fun countDownTimer(): CountDownTimer? {
        val timer = object : CountDownTimer(16000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                binding.timer.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                openDialogWrong()
                sum = 1
            }
        }.start()
        return timer
    }

    private fun savingAnswer(){
        val question = questionList?.get(position)
        binding.tvCountry.text = question?.question
        binding.tvOptionOne.text = question?.optionOne
        binding.tvOptionTwo.text = question?.optionTwo
        binding.tvOptionThree.text = question?.optionThree
        binding.tvOptionFour.text = question?.optionFour
    }

    private fun openDialogCorrect() {
        //Fazendo o binding do layout e ndicando que o dialog irá abrir nesta Activity
        bindingResultCorrect = CorrectDialogLayoutBinding.bind(View.inflate(this, R.layout.correct_dialog_layout, null))
        val builder = AlertDialog.Builder(this)
        builder.setView(bindingResultCorrect.root)

        //Mostrando o dialog na tela
        val dialog =builder.create()
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCanceledOnTouchOutside(false)

        bindingResultCorrect.btnOK.setOnClickListener {
            dialog.dismiss()
            finish()
            val intent = Intent(this, QuestionActivity::class.java)
            startActivity(intent)

            if (sum > questionList!!.size){
                val intentMain = Intent(this, MainActivity::class.java)
                startActivity(intentMain)
            }
        }

        return dialog.show()
    }

    private fun openDialogWrong(){
        //Fazendo o binding do layout e indicando que o dialog irá abrir nesta Activity
        bindingResultWrong = WrongDialogLayoutBinding.bind(View.inflate(this, R.layout.wrong_dialog_layout, null))
        val builder = AlertDialog.Builder(this)
        builder.setView(bindingResultWrong.root)

        //Mostrando o dialog na tela
        val dialog =builder.create()
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCanceledOnTouchOutside(false)
        bindingResultWrong.btnOK.setOnClickListener {
            dialog.dismiss()
            finish()
        }
        return dialog.show()
    }

    private fun equal(): Boolean {
        val answer = questionList!![position].correctOption
        val a = binding.tvOptionOne.text
        val b = binding.tvOptionTwo.text
        val c = binding.tvOptionThree.text
        val d = binding.tvOptionFour.text

        A = answer == a
        B = answer == b
        C = answer == c
        D = answer == d
        return A || B || C || D
    }

    //Função desabilita função de voltar.
    override fun onBackPressed() {}
}