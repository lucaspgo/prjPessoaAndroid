package com.example.appdozero.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.appdozero.R
import kotlinx.android.synthetic.main.pessoa_fragment.*

class PessoaFragment : Fragment() {

    private lateinit var viewModel: PessoaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var view = inflater.inflate(R.layout.pessoa_fragment, container, false)
        viewModel = ViewModelProvider(this).get(PessoaViewModel::class.java)

        viewModel.pessoa.observe(viewLifecycleOwner, { pessoa ->
            txtNome.setText(pessoa.nome)
            txtCPF.setText(pessoa.cpf)
            txtAltura.setText(pessoa.altura.toString())

            view.findViewById<Button>(R.id.btnSalvar).setOnClickListener {
                val nome = txtNome.text.toString()
                val cpf = txtCPF.text.toString()
                val altura = txtAltura.textString().toDouble()

                viewModel.salvarPessoa(Pessoa(id = pessoa.id, nome = nome, cpf = cpf, altura = altura))
                findNavController().navigateUp()
            }
        })
        return view
    }
}