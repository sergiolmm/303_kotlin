package br.com.slmm.lista1item

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import org.xmlpull.v1.XmlPullParser

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter : Adapter
        var arrayCoresList: ArrayList<Cores> = ArrayList()

        // codigo para ler o xml
        var lista = mutableListOf<Cores>()
        var text: String = ""
        var valor: String = ""
        val context = this.applicationContext
        val res = context.resources
        val xmlStr = res.getXml(R.xml.nomes)
        var eventType = xmlStr.eventType
        // le os dados xml e preenche a lista.
        while (eventType != XmlPullParser.END_DOCUMENT){
            when (eventType){
                XmlPullParser.START_TAG -> if (xmlStr.name.equals("cor", ignoreCase = true)){
                    valor = xmlStr.getAttributeValue(0).toString()
                }
                XmlPullParser.TEXT ->  {
                    text = xmlStr.text
                    var cor = Cores(valor, text)
                    lista.add(cor)
                }
                else -> {
                }
            }
            eventType = xmlStr.next()
        }
        // criamos o adapter e o conectamos ao componente de UI
        // Cria Adapter
        // Bond variavel local com XML
        // configura o adapter

        lista.forEach{
            arrayCoresList.add(it)
        }
        adapter = MyAdapter(this, arrayCoresList)
        var mListView = findViewById<ListView>(R.id.coresList)
        mListView.adapter = adapter

    }
}

class MyAdapter(private val context: Context,
                private val arrayList: ArrayList<Cores>): BaseAdapter(){
    private lateinit var texto1: TextView
    private lateinit var texto2: TextView
    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var convertView = LayoutInflater.from(context).inflate(
            //android.R.layout.simple_list_item_2,
            R.layout.cust_layout,
            parent,
            false
        )
        texto1 = convertView.findViewById(R.id.nome)     //text1)
        texto2 = convertView.findViewById(R.id.valor)     //text2)
        var img: ImageView = convertView.findViewById(R.id.icon)

        if (position%2==0){
            img.setImageResource(R.drawable.tools)
        }

        texto1.text = arrayList[position].nome
        texto2.text = arrayList[position].valor
        return convertView
    }


}