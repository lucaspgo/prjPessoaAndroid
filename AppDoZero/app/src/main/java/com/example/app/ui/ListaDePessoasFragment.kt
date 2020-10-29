package com.example.app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.app.R
import com.example.app.model.Pessoa
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListaDePessoasFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.lista_de_pessoas, container, false)
        var recycler = view.findViewById<RecyclerView>(R.id.list)
        var viewModel = ViewModelProvider(requireActivity()).get(PessoaViewModel::class.java)

        viewModel.listaDePessoas.observe(requireActivity(), { pessoas ->
            with(recycler) {
                adapter = PessoaAdapter(requireActivity(), viewModel, pessoas)
            }
        })

        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            viewModel.pessoa.value = Pessoa()
            findNavController().navigate(R.id.action_lista_para_detalhes_pessoa)
        }
        return view
    }
}