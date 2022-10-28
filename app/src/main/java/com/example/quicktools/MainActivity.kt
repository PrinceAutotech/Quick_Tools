package com.example.quicktools

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.AdapterView.OnItemClickListener
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quicktools.adapter.FontAdapter
import com.example.quicktools.databinding.ActivityMainBinding
import com.example.quicktools.model.C
import com.example.quicktools.model.Font
import java.util.Locale


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var fontArrayList: ArrayList<Font>? = null
    var fontText: String? = null
    var recyclerView: RecyclerView? = null
    var editText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        fontArrayList = ArrayList()
        editText = findViewById(R.id.et_text)
        recyclerView = findViewById(R.id.rv_fonts)

        binding.etText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                this@MainActivity.makeStylishOf(charSequence)
            }

            override fun afterTextChanged(editable: Editable) {}
        })
    }

    private fun makeStylishOf(charSequence: CharSequence) {
        val charArray = charSequence.toString().lowercase(Locale.getDefault()).toCharArray()
        val strArr = arrayOfNulls<String>(44)
        for (i in 0..43) {
            strArr[i] = applyStyle(charArray, C.strings[i])
        }
        styleTheFont(strArr)
    }


    private fun styleTheFont(strArr: Array<String?>) {
        fontArrayList!!.clear()
        fontText = editText!!.text.toString().trim { it <= ' ' }
        if (fontText!!.isNotEmpty()) {
            for (i in 0..43) {
                val font = Font()
                font.fontText = strArr[i]!!
                fontArrayList!!.add(font)
            }
            recyclerView!!.layoutManager =
                LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            recyclerView!!.adapter = FontAdapter(this, fontArrayList!!,
                OnItemClickListener { adapterView, view, i, j -> })
        }
    }


    private fun applyStyle(charArray: CharArray, string: Array<String>): String {
        val stringBuffer = StringBuffer()
        for (i in charArray.indices) {
            if (charArray[i].code - 'a'.code < 0 || charArray[i].code - 'a'.code > 25) {
                stringBuffer.append(charArray[i])
            } else {
                stringBuffer.append(string[charArray[i].code - 'a'.code])
            }
        }
        return stringBuffer.toString()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId === 16908332) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}