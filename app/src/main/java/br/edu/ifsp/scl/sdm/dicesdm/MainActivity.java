package br.edu.ifsp.scl.sdm.dicesdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // Gerador de números randômicos usado para simular o lançamento do dado
    private Random geradorRandomico;

    // Referência para resultadoTextView do arquivo de layout
    private TextView resultadoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Após a criação da tela
        geradorRandomico = new Random(System.currentTimeMillis());

        // Recuperando referência para o resultadoTextView do arquivo de layout
        resultadoTextView = findViewById(R.id.resultadoTextView);
    }

    public void jogarDado(View view){
        if (view.getId() == R.id.jogarDadoButton) {
            int resultado = geradorRandomico.nextInt(6) + 1;

            resultadoTextView.setText(getString(R.string.a_face_sorteada_foi) + resultado);
        }
    }
}
