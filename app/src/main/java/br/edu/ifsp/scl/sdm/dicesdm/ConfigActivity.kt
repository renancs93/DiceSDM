package br.edu.ifsp.scl.sdm.dicesdm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_config.*

class ConfigActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        //Restauração de configuração
        val selecionado = if (ConfigSingleton.config.numDados == 1) 0 else 1
        numDicesSpinner.setSelection(selecionado)
        numFacesEditText.setText(ConfigSingleton.config.numFaces.toString())

        cancelarBt.setOnClickListener(this)
        salvarBt.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v == salvarBt){
            ConfigSingleton.config.numDados = numDicesSpinner.selectedItem.toString().toInt()
            ConfigSingleton.config.numFaces = numFacesEditText.text.toString().toInt()
        }
        finish()
    }

}
