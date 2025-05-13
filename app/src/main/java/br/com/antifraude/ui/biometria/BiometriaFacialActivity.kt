package br.com.antifraude.ui.biometria

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.antifraude.R
import br.com.antifraude.model.BiometriaFacialRequest
import br.com.antifraude.ui.biometria.BiometriaDigitalActivity
import br.com.antifraude.util.SessaoCliente

class BiometriaFacialActivity : AppCompatActivity() {

    private val viewModel: BiometriaFacialViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biometria_facial)

        val btnValidarFacial = findViewById<Button>(R.id.btnValidarBiometriaFacial)
        val txtResultado = findViewById<TextView>(R.id.txtResultadoBiometriaFacial)
        val imgFoto = findViewById<ImageView>(R.id.imgFotoFacial)

        btnValidarFacial.setOnClickListener {
            val request = BiometriaFacialRequest(
                cpf = SessaoCliente.cpf,
                imagemBase64 = "imagem_base64_facial_simulada"
            )
            viewModel.validarBiometria(request)
        }

        viewModel.resultado.observe(this) { resultado ->
            txtResultado.text = resultado

            // Aguarda 2.5 segundos e avança para a próxima etapa
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, BiometriaDigitalActivity::class.java))
                finish()
            }, 2500)
        }
    }
}





