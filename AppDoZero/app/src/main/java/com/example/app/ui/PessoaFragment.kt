package com.example.app.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.app.R
import com.example.app.model.Pessoa
import kotlinx.android.synthetic.main.pessoa_fragment.*

class PessoaFragment : Fragment() {

    private lateinit var viewModel: PessoaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.pessoa_fragment, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(PessoaViewModel::class.java)

        viewModel.pessoa.observe(viewLifecycleOwner, { pessoa ->

            txtNome.setText(pessoa.nome)
            txtCpf.setText(pessoa.cpf)
            txtAltura.setText(pessoa.altura.toString())
            txtFoto.setText(pessoa.foto)

            view.findViewById<Button>(R.id.btnSalvar).setOnClickListener {
                var pessoa = Pessoa(
                    docId = pessoa.docId,
                    nome = txtNome.text.toString(),
                    cpf = txtCpf.text.toString(),
                    foto = txtFoto.text.toString(),
                    altura = txtAltura.text.toString().toInt()
                )
                viewModel.repository.salvarPessoa(pessoa)
                findNavController().navigateUp()
            }

        })
        return view
    }
}