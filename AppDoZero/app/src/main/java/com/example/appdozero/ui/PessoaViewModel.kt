package com.example.appdozero.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdozero.db.Banco
import com.example.appdozero.model.Pessoa
import kotlinx.coroutines.launch

class PessoaViewModel(app: Application) : AndroidViewModel(app) {

    var pessoa = MutableLiveData<Pessoa>()
    val pessoaDao = Banco.get(app).pessoaDao()
    var listaDePessoas = pessoaDao.listarTodas()

    fun salvarPessoa(pessoa : Pessoa) = viewModelScope.launch {
        if(pessoa.id == 0){
            pessoaDao.inserir(pessoa)
        } else {
            pessoaDao.atualizar(pessoa)
        }
    }

    fun excluirPessoa(id : Int) = viewModelScope.launch {
        pessoaDao.apagar(id)
    }
}