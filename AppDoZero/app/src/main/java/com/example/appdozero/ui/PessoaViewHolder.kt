package com.example.appdozero.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appdozero.R

class PessoaViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    val idPessoa: TextView = view.findViewById(R.id.id_pessoa)
    val nomePessoa: TextView = view.findViewById(R.id.nome_pessoa)

    override fun toString(): String {
        return super.toString() + " '" + nomePessoa.text + "'"
    }
}


