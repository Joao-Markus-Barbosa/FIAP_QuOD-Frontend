package br.com.antifraude.ui.biometria

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.antifraude.R
import br.com.antifraude.model.BiometriaDigitalRequest
import br.com.antifraude.ui.documentoscopia.DocumentoscopiaActivity
import br.com.antifraude.util.SessaoCliente

class BiometriaDigitalActivity : AppCompatActivity() {

    private val viewModel: BiometriaDigitalViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biometria_digital)

        val btnValidar = findViewById<Button>(R.id.btnValidarBiometriaDigital)
        val txtResultado = findViewById<TextView>(R.id.txtResultadoBiometriaDigital)

        btnValidar.setOnClickListener {
            val request = BiometriaDigitalRequest(
                cpf = SessaoCliente.cpf,
                imagemBase64 = "imagem_base64_digital_simulada"
            )
            viewModel.validarBiometria(request)
        }

        viewModel.resultado.observe(this) { resultado ->
            txtResultado.text = resultado

            // Delay de 2.5 segundos antes de ir para a próxima tela
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, DocumentoscopiaActivity::class.java))
                finish()
            }, 2500)
        }
    }
}

