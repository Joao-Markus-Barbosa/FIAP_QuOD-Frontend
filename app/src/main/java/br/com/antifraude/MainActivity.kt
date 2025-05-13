package br.com.antifraude

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.antifraude.ui.documentoscopia.DocumentoscopiaActivity
import br.com.antifraude.ui.score.ScoreActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicia a tela de validação de documento
        val intent = Intent(this, ScoreActivity::class.java)
        startActivity(intent)

        // Finaliza a MainActivity para não voltar pra ela
        finish()
    }
}
