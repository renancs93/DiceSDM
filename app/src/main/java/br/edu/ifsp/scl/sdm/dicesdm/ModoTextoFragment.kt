package br.edu.ifsp.scl.sdm.dicesdm

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_modo_texto.*
import kotlinx.android.synthetic.main.toolbar.*

class ModoTextoFragment: ModoJogoFragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val layoutFragment = inflater.inflate(R.layout.fragment_modo_texto, null)

        activity?.toolbar?.subtitle = "Modo Texto"

        return layoutFragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        jogarDadoButton.setOnClickListener{ jogarDado(it) }
    }

    override fun jogarDado(view: View) {

        val numDices: Int = ConfigSingleton.config.numDados
        val numFaces = ConfigSingleton.config.numFaces

        var resultadoText = ""
        for (i in 1..numDices){
            val resultado = geradorRandomico.nextInt(numFaces) + 1

            resultadoText = "$resultadoText $resultado"
        }

        resultadoTextView.text = "A face sorteada foi: $resultadoText"
        (activity as MainActivity).compartilharIntent.putExtra(Intent.EXTRA_TEXT, resultadoTextView.text.toString())
    }

}