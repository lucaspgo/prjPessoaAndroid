package com.example.app.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.app.model.Pessoa
import com.example.app.repository.PessoaRepository

class PessoaViewModel(app: Application) : AndroidViewModel(app) {

    var pessoa = MutableLiveData<Pessoa>()
    var repository = PessoaRepository()
    var listaDePessoas = repository.listaDePessoas

}