package br.com.slmm.duastelas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class tela2 : AppCompatActivity() {

    //lateinit var btnVoltar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela)
        // recebendo valores da tela que chamou

        val string: String? = intent.getStringExtra("Chave")

        val texto = findViewById(R.id.textView2) as TextView
        texto.setText(string)

        val btnVoltar = findViewById(R.id.btnVoltar) as Button
        btnVoltar.setOnClickListener{
            this.finish()
        }

        val btnNext = findViewById(R.id.btnGoTo3) as Button
        btnNext.setOnClickListener{
            val intent = Intent(this, Tela3::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }
    }
}