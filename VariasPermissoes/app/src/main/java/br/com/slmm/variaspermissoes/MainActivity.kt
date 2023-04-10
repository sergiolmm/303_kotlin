package br.com.slmm.variaspermissoes

import android.Manifest.permission.CAMERA
import android.Manifest.permission.RECORD_AUDIO
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import br.com.slmm.variaspermissoes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        var PERMISSIONS = arrayOf(
            CAMERA,
            RECORD_AUDIO
        )
    }
// referencia
    // https://mobologicplus.com/request-permission-launcher-with-kotlin-in-android/

    private fun hasPermission(context: Context, permissions: Array<String>):
            Boolean = permissions.all{
                ActivityCompat.checkSelfPermission(context,it) ==
                        PackageManager.PERMISSION_GRANTED
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}