package br.com.antifraude.ui.simswap

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.antifraude.R
import br.com.antifraude.model.SimSwapRequest
import br.com.antifraude.ui.score.ScoreActivity
import br.com.antifraude.util.SessaoCliente

class SimSwapActivity : AppCompatActivity() {

    private val viewModel: SimSwapViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simswap)

        val edtTelefone = findViewById<EditText>(R.id.edtTelefoneSimSwap)
        val btnValidar = findViewById<Button>(R.id.btnValidarSimSwap)
        val txtResultado = findViewById<TextView>(R.id.txtResultadoSimSwap)

        btnValidar.setOnClickListener {
            val request = SimSwapRequest(
                cpf = SessaoCliente.cpf,
                telefone = edtTelefone.text.toString()
            )
            viewModel.validarSimSwap(request)
        }

        viewModel.resultado.observe(this) { resultado ->
            txtResultado.text = resultado

            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, ScoreActivity::class.java))
                finish()
            }, 2500)
        }
    }
}




