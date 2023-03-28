package br.com.slmm.listabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import org.xmlpull.v1.XmlPullParser

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arrayAdapter : ArrayAdapter<*>
        val usuarios = arrayOf("São Paulo", "Minas Gerais", "Rio de Janeiro", "Parana",
                               "Espirito Santo","Bahia")
        // accessa a  lista apartir de um arquivo xml
        var mListView = findViewById<ListView>(R.id.userlist)
        // cria o adapter
        arrayAdapter = ArrayAdapter(this,
        android.R.layout.simple_list_item_1, usuarios)
        mListView.adapter = arrayAdapter

        var lista = mutableListOf<Cores>()
        var text: String = ""
        var valor: String = ""
        val context = this.applicationContext
        val res = context.resources
        val xmlStr = res.getXml(R.xml.nomes)
        var eventType = xmlStr.eventType
        while (eventType != XmlPullParser.END_DOCUMENT){
            val tagname = xmlStr.name
            when (eventType){
                XmlPullParser.START_TAG -> if (tagname.equals("cor", ignoreCase = true)){

                    val attr = xmlStr.getAttributeName(0)
                    valor = xmlStr.getAttributeValue(0).toString()

                }

                XmlPullParser.TEXT ->  {
                    text = xmlStr.text
                    var cor = Cores(valor, text)
                    lista.add(cor)
                }
                XmlPullParser.END_TAG ->if (tagname.equals("cor", ignoreCase = true)) {

                }

                else -> {
                }
            }
            eventType = xmlStr.next()
        }


        Log.d("Teste",text + valor)
        lista.forEach{
            Log.d("TESTE", it.nome)
        }

    }
}