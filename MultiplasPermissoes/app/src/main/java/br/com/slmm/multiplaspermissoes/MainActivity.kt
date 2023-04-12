package br.com.slmm.multiplaspermissoes

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import br.com.slmm.multiplaspermissoes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    companion object{
        var PERMISSIONS = arrayOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.RECORD_AUDIO
        )
    }
    private lateinit var valores: Array<String>
    private fun hasPermission(context: Context, permissions: Array<String>):
            Boolean = permissions.all {
                ActivityCompat.checkSelfPermission(context, it) ==
                        PackageManager.PERMISSION_GRANTED
    }

    private fun hasPermission2(context: Context, permissions: Array<String>):
            Array<String> =
        permissions.filter {
            ActivityCompat.checkSelfPermission(context, it) ==
                    PackageManager.PERMISSION_DENIED
        }.toTypedArray()


    private val requestMultiplePermissions =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions())
        {  permissions ->
            permissions.entries.forEach {
                Log.d("PERMISSAO", "${it.key} = ${it.value}")
                binding.btnGravar.isEnabled = it.value
                if (!it.value){
                    Log.e("ERR perm","${it.key} sem permissao")
                    return@forEach // cai fora do for each
                }

            }

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnPermissoes.setOnClickListener {
            binding.txtStatus.text = "OLA ALUNOS"
            var checkPermission: Boolean = hasPermission(this, PERMISSIONS)
            Log.d("TESTE HAS", checkPermission.toString())
            if (!checkPermission){
                var lista = hasPermission2(this, PERMISSIONS)
                requestMultiplePermissions.launch(lista)
            }

        }

    }
}