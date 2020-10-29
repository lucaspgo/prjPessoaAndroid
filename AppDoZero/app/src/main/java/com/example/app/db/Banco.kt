package com.example.app.db

//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.example.app.model.Pessoa
//
//@Database(entities = [Pessoa::class], version = 1, exportSchema = false)
//abstract class Banco : RoomDatabase() {
//
//    abstract fun pessoaDao() : PessoaDao
//
//    companion object{
//        @Volatile
//        private var INSTANCE : Banco? = null
//        fun get(conext : Context) : Banco {
//            var instance = INSTANCE
//            if (instance == null){
//                instance = Room.databaseBuilder(
//                    conext.applicationContext,
//                    Banco::class.java,
//                    "banco.db"
//                ).build()
//            }
//            return instance!!
//        }
//    }
//}