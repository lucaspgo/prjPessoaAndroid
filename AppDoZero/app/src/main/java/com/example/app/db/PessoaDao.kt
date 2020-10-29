package com.example.app.db
//
//import androidx.lifecycle.LiveData
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.Query
//import androidx.room.Update
//import com.example.app.model.Pessoa
//
//@Dao
//interface PessoaDao {
//
//    @Query("SELECT * FROM pessoas")
//    fun listarTodas() : LiveData<List<Pessoa>>
//
//    @Insert
//    suspend fun inserir(pessoa : Pessoa)
//
//    @Update
//    suspend fun atualizar(pessao : Pessoa)
//
//    @Query("DELETE FROM pessoas WHERE id = (:id)")
//    suspend fun apagar(id : Int)
//
//    @Query("DELETE FROM pessoas")
//    suspend fun apagarTodos()
//
//}