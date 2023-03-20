package br.com.slmm.duastelaretorno

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            Log.d("Teste","Retorno")
            Log.d("Teste", result.resultCode.toString())
            if (result.resultCode == Activity.RESULT_OK){
                val data: Intent? = result.data
                var txt = findViewById(R.id.textView) as TextView
                var msg = data?.getData().toString()

                //getStringExtra("ActivityResult")
                txt.setText(msg)
            }

        }

        val btnNext = findViewById(R.id.btnGetValor) as Button
        btnNext.setOnClickListener{
            val intent = Intent(this, TelaValores::class.java)
            launcher.launch(intent)
        }
    }



    //@Deprecated("Decrepted in Java")
    //override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    //    super.onActivityResult(requestCode, resultCode, data)
    //}

}