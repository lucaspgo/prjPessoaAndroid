package com.example.app.ui

import android.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.example.app.R
import com.example.app.model.Pessoa
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage

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
        holder.nomePessoa.text = pessoa.nome
        holder.cpfPessoa.text = pessoa.cpf

        val storage = FirebaseStorage.getInstance()
        val storageReference = storage.getReference(pessoa.foto)
        storageReference.downloadUrl.addOnSuccessListener { imageURL ->
            Glide.with(activity)
                .load(imageURL)
                .into(holder.fotoPessoa)
        }

        storageReference.downloadUrl.addOnFailureListener {
            Glide.with(activity)
                .load(R.drawable.sem_foto)
                .into(holder.fotoPessoa)
        }

        holder.itemView.setOnClickListener { view ->
            viewModel.pessoa.value = pessoa
            view.findNavController().navigate(R.id.action_lista_para_detalhes_pessoa)
        }

        holder.itemView.setOnLongClickListener { view ->
            view?.let {
                AlertDialog.Builder(activity)
                    .setTitle("Atenção")
                    .setMessage("Deseja exclui a pessoa?")
                    .setPositiveButton("Sim") { dialog, which ->
                        viewModel.repository.excluirPessoa(pessoa.docId)
                    }
                    .setNegativeButton("Não", null)
                    .show()
            }
            true
        }
    }

    override fun getItemCount(): Int = pessoas.size
}