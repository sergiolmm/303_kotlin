package br.com.slmm.permissoes1

import android.Manifest.permission.CAMERA
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import br.com.slmm.permissoes1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val permissionActResult = registerForActivityResult(
        ActivityResultContracts.RequestPermission()){
        var msg: String = ""
        when (it) {
            true -> { msg = "Permissao aceita"}
            false -> { msg = "Permissao negada"}
        }
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.textView.text="Ola"

        binding.btnPermi.setOnClickListener {
            binding.textView.text = "clicou"
            if (checkPermision()) {
                permissionActResult.launch(CAMERA)
            }
            else {
                println("pode chamar algo...")
            }
        }


    }
    /* Cria uma função para verificar se a permissao está ativa ou não*/
    private fun checkPermision(): Boolean{
        val permission = ContextCompat.checkSelfPermission(
            this, CAMERA)

        return permission != PackageManager.PERMISSION_GRANTED
    }


}