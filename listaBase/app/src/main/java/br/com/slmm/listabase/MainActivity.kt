package br.com.slmm.listabase

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
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
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, usuarios)
        mListView.adapter = arrayAdapter

        var c1 : Cores = Cores("a33","222")
        var num : Int = 0
        num = c1.teste()
        Log.d("Teste", num.toString())


        var arrayList: ArrayList<Cores> = ArrayList()
        var lista = mutableListOf<Cores>()

        var text: String = ""
        var cor: String = ""
        var valor: String = ""

        // obtem o contexto da aplicação
        val context = this.applicationContext
        // obtenho acesso aos recursos da aplicação
        val res = context.resources
        // com base no acesso leio o arquivo xml da aplicação
        val xmlStr = res.getXml(R.xml.nomes)

        var eventType = xmlStr.eventType

        while (eventType != XmlPullParser.END_DOCUMENT){
            val tagname = xmlStr.name
            when (eventType){
                XmlPullParser.START_TAG -> if (tagname.equals("cor", ignoreCase = true)){
                    cor = xmlStr.getAttributeValue(0).toString()
                }

                XmlPullParser.TEXT ->  {
                    valor = xmlStr.text
                    var cor = Cores(cor, valor)
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
            arrayList.add(it)
        }

        //arrayList.add(MyData(1, " Mashu", "987576443"))
        //arrayList.add(MyData(2, " Azhar", "8787576768"))
        //arrayList.add(MyData(3, " Niyaz", "65757657657"))
        //arrayAdapter =

 //       mListView.adapter = MyAdapter(this, arrayList)

    }
}

class MyAdapter(private val context: Context,
                private val arrayList: ArrayList<Cores>) : BaseAdapter() {
    private lateinit var t1: TextView

    private lateinit var t2: TextView

    override fun getCount(): Int {
        return arrayList.size
    }
    override fun getItem(position: Int): Any {
        return position
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false)

        t1 = convertView.findViewById(android.R.id.text1)
        t2 = convertView.findViewById(android.R.id.text2)
        t1.text = arrayList[position].nome
        t2.text = arrayList[position].valor
        return convertView
    }
}
