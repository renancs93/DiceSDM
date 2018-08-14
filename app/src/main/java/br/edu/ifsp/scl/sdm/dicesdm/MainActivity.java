package br.edu.ifsp.scl.sdm.dicesdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // Gerador de números randômicos usado para simular o lançamento do dado
    private Random geradorRandomico;

    // Referências para as Views do arquivo de layout
    private TextView resultadoTextView;
    private ImageView resultadoImageView;
    private Spinner numDicesSpinner;
    private ImageView resultado2ImageView;
    private EditText numFacesEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Após a criação da tela
        geradorRandomico = new Random(System.currentTimeMillis());

        // Recuperando referência para as Views do arquivo de layout
        resultadoTextView = findViewById(R.id.resultadoTextView);
        resultadoImageView = findViewById(R.id.resultadoImageView);
        numDicesSpinner = findViewById(R.id.numDicesSpinner);
        resultado2ImageView = findViewById(R.id.resultado2imageView);
        numFacesEditText = findViewById(R.id.numFacesEditText);
    }

    public void jogarDado(View view){
        if (view.getId() == R.id.jogarDadoButton) {
            int numDices = Integer.parseInt(numDicesSpinner.getSelectedItem().toString());

            int numFaces = Integer.parseInt(numFacesEditText.getText().toString());
            if (numFaces > 6) {
                resultadoImageView.setVisibility(View.GONE);
                resultado2ImageView.setVisibility(View.GONE);
            }
            else {
                resultadoImageView.setVisibility(View.VISIBLE);
                if (numDices == 2) {
                    resultado2ImageView.setVisibility(View.VISIBLE);
                }
                else{
                    resultado2ImageView.setVisibility(View.GONE);
                }
            }

            String resultadoText = "";
            for (int i = 1; i <= numDices; i++) {
                int resultado = geradorRandomico.nextInt( numFaces ) + 1;

                resultadoText += resultado + " ";

                ImageView imageView = i == 1? resultadoImageView : resultado2ImageView;
                switch (resultado) {
                    case 1:
                        imageView.setImageResource(R.drawable.dice_1);
                        break;
                    case 2:
                        imageView.setImageResource(R.drawable.dice_2);
                        break;
                    case 3:
                        imageView.setImageResource(R.drawable.dice_3);
                        break;
                    case 4:
                        imageView.setImageResource(R.drawable.dice_4);
                        break;
                    case 5:
                        imageView.setImageResource(R.drawable.dice_5);
                        break;
                    case 6:
                        imageView.setImageResource(R.drawable.dice_6);
                        break;
                    default:
                        break;
                }
            }

            resultadoTextView.setText(getString(R.string.a_face_sorteada_foi) + resultadoText);
        }
    }
}
