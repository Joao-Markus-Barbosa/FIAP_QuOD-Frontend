package br.com.antifraude.ui.score

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.antifraude.R
import br.com.antifraude.model.ScoreRequest
import br.com.antifraude.ui.resultado.ResultadoActivity
import br.com.antifraude.util.SessaoCliente

class ScoreActivity : AppCompatActivity() {

    private val viewModel: ScoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val btnGerarScore = findViewById<Button>(R.id.btnGerarScore)
        val txtResultado = findViewById<TextView>(R.id.txtScoreResultado)

        btnGerarScore.setOnClickListener {
            val request = ScoreRequest(
                cpf = SessaoCliente.cpf
            )
            viewModel.gerarScore(request)
        }

        viewModel.resultado.observe(this) { resultado ->
            txtResultado.text = resultado

            // Ir para o resultado consolidado, independentemente do texto
            startActivity(Intent(this, ResultadoActivity::class.java))
            finish()
        }
    }
}



