package com.example.quicktools.adapter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.quicktools.databinding.FontLayoutBinding
import com.example.quicktools.model.Font


class FontAdapter(
    private val context: Context,
    private val dataset: ArrayList<Font>,
    onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<FontAdapter.FontHolder>() {
    var preferences: SharedPreferences? = null

    class FontHolder(itemView: FontLayoutBinding) : RecyclerView.ViewHolder(itemView.root) {
        val binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FontHolder {
        val binding = FontLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return FontHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: FontHolder, position: Int) {

        holder.binding.tvStlishText.text = dataset[holder.adapterPosition].fontText

        holder.binding.layoutInsta.setOnClickListener {
            val whatsappIntent = Intent(Intent.ACTION_SEND)
            whatsappIntent.type = "text/plain"
            whatsappIntent.setPackage("com.whatsapp")
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, holder.binding.tvStlishText.text)
            context.startActivity(whatsappIntent)
        }

        holder.binding.ivShare.setOnClickListener {
            val intent2 = Intent()
            intent2.action = Intent.ACTION_SEND
            intent2.type = "text/plain"
            intent2.putExtra(Intent.EXTRA_TEXT, holder.binding.tvStlishText.text)
            context.startActivity(Intent.createChooser(intent2, "Share via"))
        }

        holder.binding.ivCopy.setOnClickListener {
            (holder.itemView.context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager).setPrimaryClip(
                ClipData.newPlainText(
                    "stylish text", holder.binding.tvStlishText.text
                )
            )
            Toast.makeText(holder.itemView.context, "Text Copied", Toast.LENGTH_SHORT).show()
        }

    }
}