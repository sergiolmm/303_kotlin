package br.com.slmm.listacustom

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import org.xmlpull.v1.XmlPullParser

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter : Adapter
        var arrayList: ArrayList<Cores> = ArrayList()

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
            arrayList.add(it)
        }

        adapter = MyAdapter(this, arrayList)
        var mListView = findViewById<ListView>(R.id.userlist)
        mListView.adapter = adapter
    }
}
class MyAdapter(private val context: Context, private val arrayList: java.util.ArrayList<Cores>) : BaseAdapter() {

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
        //convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false)
        convertView = LayoutInflater.from(context).inflate(R.layout.teste, parent, false)

        //t1 = convertView.findViewById(android.R.id.text1)
        //t2 = convertView.findViewById(android.R.id.text2)

        t1 = convertView.findViewById(R.id.title)
        t2 = convertView.findViewById(R.id.description)

        t1.text = arrayList[position].nome
        t2.text = arrayList[position].valor
        return convertView
    }
}