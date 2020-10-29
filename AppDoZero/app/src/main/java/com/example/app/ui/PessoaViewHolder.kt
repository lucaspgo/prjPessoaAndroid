package com.example.app.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R

class PessoaViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    val nomePessoa: TextView = view.findViewById(R.id.texto_nome)
    val cpfPessoa: TextView = view.findViewById(R.id.texto_cpf)
    val fotoPessoa: ImageView = view.findViewById(R.id.imgFoto)

    override fun toString(): String {
        return super.toString() + " '" + nomePessoa.text + "'"
    }
}