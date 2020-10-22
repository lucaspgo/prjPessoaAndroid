package com.example.appdozero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
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
        navigationView.setNavigationItemSelectedListener { item ->
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
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.ItemSobre -> mostrarActivitySobre()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun mostrarActivitySobre(): Boolean {
        var intent = Intent(this, SobreActivity::class.java)
        startActivity(intent)
        return true
    }
}