package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import com.example.app.model.Pessoa
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)
        setSupportActionBar(toolbar)

        var drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        var btnToggle = ActionBarDrawerToggle(
            this, findViewById(R.id.drawerLayout), toolbar,
            R.string.abrir_drawer, R.string.fechar_drawer
        )
        drawerLayout.addDrawerListener(btnToggle)
        btnToggle.syncState()

        var navController = findNavController(R.id.nav_host_fragment)
        navigationView.setNavigationItemSelectedListener{ item ->
            when (item.itemId){
                R.id.itemLista ->
                    navController.navigate(R.id.listaDePessoasFragment)
                R.id.itemUm ->
                    navController.navigate(R.id.fragmento1)
                R.id.itemDois ->
                    navController.navigate(R.id.fragmento2)
                R.id.itemTres ->
                    navController.navigate(R.id.fragmento3)
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        //uploadNoFireStore()

    }

    fun uploadNoFireStore() {

        var famosos = "[\n" +
                " {\"cpf\":\"123456789\", \"nome\":\"Anne Hathaway\",     \"foto\":\"anne_hathaway.jpg\",     \"altura\": 177},\n" +
                " {\"cpf\":\"234567891\", \"nome\":\"Barack Obama\",      \"foto\":\"barack_obama.jpg\",      \"altura\": 178},\n" +
                " {\"cpf\":\"345678912\", \"nome\":\"Caetano Velozo\",    \"foto\":\"caetano_velozo.jpg\",    \"altura\": 181},\n" +
                " {\"cpf\":\"456789123\", \"nome\":\"Eddie Murphy\",      \"foto\":\"eddie_murphy.jpg\",      \"altura\": 171},\n" +
                " {\"cpf\":\"567891234\", \"nome\":\"Elton Jhon\",        \"foto\":\"elton_jhon.jpg\",        \"altura\": 167},\n" +
                " {\"cpf\":\"678912345\", \"nome\":\"Fábio Junior\",      \"foto\":\"fabio_junior.jpg\",      \"altura\": 182},\n" +
                " {\"cpf\":\"789123456\", \"nome\":\"Fidel Castro\",      \"foto\":\"fidel_castro.jpg\",      \"altura\": 183},\n" +
                " {\"cpf\":\"891234567\", \"nome\":\"Gisele Bundchen\",   \"foto\":\"gisele_bundchen.jpg\",   \"altura\": 182},\n" +
                " {\"cpf\":\"912345678\", \"nome\":\"Jennifer Connelly\", \"foto\":\"jennifer_connelly.jpg\", \"altura\": 176},\n" +
                " {\"cpf\":\"987654321\", \"nome\":\"Jô Soares\",         \"foto\":\"jo_soares.jpg\",         \"altura\": 170},\n" +
                " {\"cpf\":\"876543219\", \"nome\":\"José Serra\",        \"foto\":\"jose_serra.jpg\",        \"altura\": 171},\n" +
                " {\"cpf\":\"765432198\", \"nome\":\"Julia Roberts\",     \"foto\":\"julia_roberts.jpg\",     \"altura\": 179},\n" +
                " {\"cpf\":\"654321987\", \"nome\":\"Michael Jackson\",   \"foto\":\"michael_jackson.jpg\",   \"altura\": 169},\n" +
                " {\"cpf\":\"543219876\", \"nome\":\"Mr Bean\",           \"foto\":\"mr_bean.jpg\",           \"altura\": 167},\n" +
                " {\"cpf\":\"432198765\", \"nome\":\"Neimar Junior\",     \"foto\":\"neimar_junior.jpg\",     \"altura\": 170},\n" +
                " {\"cpf\":\"321987654\", \"nome\":\"Roberto Carlos\",    \"foto\":\"roberto_carlos.jpg\",    \"altura\": 167},\n" +
                " {\"cpf\":\"219876543\", \"nome\":\"Ronaldinho Gaucho\", \"foto\":\"ronaldinho_gaucho.jpg\", \"altura\": 166},\n" +
                " {\"cpf\":\"198765432\", \"nome\":\"Ronaldo\",           \"foto\":\"ronaldo.jpg\",           \"altura\": 175},\n" +
                " {\"cpf\":\"105654321\", \"nome\":\"Vladimir Putin\",    \"foto\":\"vladimir_putin.jpg\",    \"altura\": 179},\n" +
                " {\"cpf\":\"903254321\", \"nome\":\"Xuxa Meneghel\",     \"foto\":\"xuxa_meneghel.jpg\",     \"altura\": 180}\n" +
                "]"

        var moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, Pessoa::class.java)
        var adapter: JsonAdapter<List<Pessoa>> = moshi.adapter(type)
        var pessoas = adapter.fromJson(famosos)

        val db = FirebaseFirestore.getInstance()

        for (p in pessoas ?: emptyList()){
            Log.i("JSON", "$p.nome $p.foto")
            val pessoa = Pessoa(
                docId = String(),
                nome = p.nome,
                cpf = p.cpf,
                foto = p.foto,
                altura = p.altura
            )
            var doc = db.collection("pessoas").document()
            pessoa.docId = doc.id
            doc.set(pessoa)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.itemSobre -> mostrarActivitySobre()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun mostrarActivitySobre(): Boolean {
        var intent = Intent(this, SobreActivity::class.java)
        startActivity(intent)
        return true
    }
}