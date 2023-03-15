package br.com.slmm.duastelas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var btnGoToTela2 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("Aula","TelaMain...")

        btnGoToTela2 = findViewById(R.id.btnGoToTela1)
        btnGoToTela2.setOnClickListener{

            val intent = Intent(this, tela2::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            intent.putExtra("Chave","Ola tela 2")
            startActivity(intent)

        }


    }
}