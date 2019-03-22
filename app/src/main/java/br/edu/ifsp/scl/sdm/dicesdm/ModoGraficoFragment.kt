package br.edu.ifsp.scl.sdm.dicesdm

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.fragment_modo_grafico.*
import kotlinx.android.synthetic.main.toolbar.*

class ModoGraficoFragment: ModoJogoFragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val layoutFragment = inflater.inflate(R.layout.fragment_modo_grafico, null)

        activity?.toolbar?.subtitle = "Modo Grafico"

        return layoutFragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        jogarDadoButton.setOnClickListener{ jogarDado(it) }
    }


    override fun jogarDado(view: View) {

        val numDices: Int = ConfigSingleton.config.numDados
        val numFaces = ConfigSingleton.config.numFaces

        if (numFaces > 6) {
            resultadoImageView.visibility = View.GONE
            resultado2ImageView.visibility = View.GONE
        }
        else {
            resultadoImageView.visibility = View.VISIBLE
            resultado2ImageView.visibility = if (numDices == 2) View.VISIBLE else View.GONE
        }

        var resultadoText = ""

        var textoCompartilhar = "Face sorteada: "
        for (i in 1..numDices){
            val resultado = geradorRandomico.nextInt( numFaces ) +1

            textoCompartilhar = "$resultadoText $resultado"

            val imageView: ImageView = if (i==1) resultadoImageView else resultado2ImageView
            val resourceName: String = "dice_${resultado}"
            imageView.setImageResource(
                    resources.getIdentifier(resourceName, "drawable", activity?.packageName )
            )
        }

        (activity as MainActivity).compartilharIntent.putExtra(Intent.EXTRA_TEXT, textoCompartilhar)
    }

}