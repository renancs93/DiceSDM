package br.edu.ifsp.scl.sdm.dicesdm

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity: AppCompatActivity(){

    val geradorRandomico: Random
    init {
        geradorRandomico = Random(System.currentTimeMillis())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun jogarDado(view: View){
        if (view == jogarDadoButton){
            val numDices: Int = numDicesSpinner.selectedItem.toString().toInt()
            val numFaces = numFacesEditText.text.toString().toInt()

            if (numFaces > 6) {
                resultadoImageView.visibility = View.GONE
                resultado2ImageView.visibility = View.GONE
            }
            else {
                resultadoImageView.visibility = View.VISIBLE
                resultado2ImageView.visibility = if (numDices == 2) View.VISIBLE else View.GONE
            }

            var resultadoText = ""

            for (i in 1..numDices){
                val resultado = geradorRandomico.nextInt( numFaces ) +1

                resultadoText = "$resultadoText $resultado"

                val imageView: ImageView = if (i==1) resultadoImageView else resultado2ImageView
                val resourceName: String = "dice_${resultado}"
                imageView.setImageResource(
                        resources.getIdentifier(resourceName, "drawable", packageName)
                )

            }

            resultadoTextView.text = "A face sorteada foi: $resultadoText"
        }
    }

}