package br.com.antifraude.ui.documentoscopia

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
import br.com.antifraude.model.DocumentoRequest
import br.com.antifraude.ui.simswap.SimSwapActivity
import br.com.antifraude.util.SessaoCliente

class DocumentoscopiaActivity : AppCompatActivity() {

    private val viewModel: DocumentoscopiaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_documentoscopia)

        val btnValidar = findViewById<Button>(R.id.btnValidarDocumentoscopia)
        val txtResultado = findViewById<TextView>(R.id.txtResultadoDocumentoscopia)
        val imgDocumento = findViewById<ImageView>(R.id.imgDocumento)

        btnValidar.setOnClickListener {
            val request = DocumentoRequest(
                cpf = SessaoCliente.cpf,
                imagemDocumentoBase64 = "imagem_base64_documento_simulada",
                imagemSelfieBase64 = "imagem_base64_selfie_simulada"
            )

            viewModel.validarDocumento(request)
        }

        viewModel.resultado.observe(this) { resultado ->
            txtResultado.text = resultado

            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, SimSwapActivity::class.java))
                finish()
            }, 2500)
        }
    }
}



