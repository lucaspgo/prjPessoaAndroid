package com.example.appdozero.ui

import android.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.example.appdozero.R
import com.example.appdozero.model.Pessoa

class PessoaAdapter(
    private val activity: FragmentActivity,
    private val viewModel: PessoaViewModel,
    private val pessoas: List<Pessoa>
) : RecyclerView.Adapter<PessoaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PessoaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pessoa, parent, false)
        return PessoaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PessoaViewHolder, position: Int) {

        val pessoa = pessoas[position]
        holder.idPessoa.text = pessoa.id.toString()
        holder.nomePessoa.text = pessoa.nome


        holder.itemView.setOnClickListener { view ->
            viewModel.pessoa.value = pessoa
            view.findNavController().navigate(R.id.action_lista_para_detalhes_pessoa)
        }

        holder.itemView.setOnLongClickListener { view ->
            view?.let{
                AlertDialog.Builder(activity)
                .setTitle("Atenção")
                .setMessage("Deseja excluir a pessoa?")
                    .setPositiveButton("sim"){ dialog, which ->
                        viewModel.excluirPessoa(pessoa.id)
                    }
                    .setNegativeButton("nao", null)
                    .show()
            }
            true
        }
    }

    override fun getItemCount(): Int = pessoas.size

}