package br.edu.ifsp.scl.sdm.dicesdm

import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import br.edu.ifsp.scl.sdm.dicesdm.ConfigSingleton.modos.MODO_GRAFICO
import br.edu.ifsp.scl.sdm.dicesdm.ConfigSingleton.modos.MODO_TEXTO
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.startActivity
import android.support.v7.widget.ShareActionProvider


class MainActivity: AppCompatActivity(){

    var compartilharIntent: Intent = Intent(ACTION_SEND)

    init {
        compartilharIntent.type = "text/*"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.app_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val abreFechaToogle: ActionBarDrawerToggle =
                ActionBarDrawerToggle(
                        this,
                        menuLateralDrawerLayout,
                        toolbar,
                        R.string.menu_aberto,
                        R.string.menu_fechado
                )
        menuLateralDrawerLayout.addDrawerListener(abreFechaToogle)

        abreFechaToogle.syncState()

        menuNavigationView.setNavigationItemSelectedListener { onNavigationItemSelected(it) }

        substituiFragment(MODO_GRAFICO)
    }

    fun onNavigationItemSelected(item: MenuItem): Boolean{
        var retorno: Boolean = false
        when(item.itemId){
            R.id.modoTextoMenuItem -> {
                substituiFragment(MODO_TEXTO)
                retorno = true
            }
            R.id.modoGraficoMenuItem -> {
                substituiFragment(MODO_GRAFICO)
                retorno = true
            }
            R.id.sairMenuItem -> {
                finish()
                retorno = true
            }
        }
        menuLateralDrawerLayout.closeDrawer(GravityCompat.START)

        return retorno
    }

    private fun substituiFragment(modo: String){

        val modoJogoFragment = if(modo == MODO_GRAFICO) ModoGraficoFragment() else ModoTextoFragment()

        // Transaction para substituição de fragment
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentJogoFl,  modoJogoFragment)
        fragmentTransaction.commit()

    }

    override fun onBackPressed() {
        if (menuLateralDrawerLayout.isDrawerOpen(GravityCompat.START)){
            menuLateralDrawerLayout.closeDrawer(GravityCompat.START)
        }
        else{
            super.onBackPressed()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val compartilharMenuItem: MenuItem? = menu?.findItem(R.id.compartilharMenuItem)
        val compartilharSnap: ShareActionProvider = MenuItemCompat.getActionProvider(compartilharMenuItem) as ShareActionProvider
        compartilharSnap.setShareIntent(compartilharIntent)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.configuracoesMenuItem ->
                //startActivity(Intent(this, ConfigActivity::class.java))
                startActivity<ConfigActivity>()
        }
        return super.onOptionsItemSelected(item)
    }

}