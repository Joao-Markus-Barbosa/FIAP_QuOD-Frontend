package br.com.antifraude.ui.resultado

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.antifraude.R
import br.com.antifraude.util.SessaoCliente

class ResultadoActivity : AppCompatActivity() {

    private val viewModel: ResultadoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        val txtEtapas = findViewById<TextView>(R.id.txtEtapas)
        val txtStatusFinal = findViewById<TextView>(R.id.txtStatusFinal)
        val btnFinalizar = findViewById<Button>(R.id.btnFinalizar)

        viewModel.consultarResultado(SessaoCliente.cpf)

        viewModel.resultado.observe(this) { resultado ->
            resultado?.let {
                val statusFacial = it.statusBiometriaFacial
                val statusDigital = it.statusBiometriaDigital
                val statusDoc = it.statusDocumento
                val statusSim = it.statusSimSwap
                val score = it.score

                txtEtapas.text = """
                    ▶ Biometria Facial: $statusFacial
                    ▶ Biometria Digital: $statusDigital
                    ▶ Documentoscopia: $statusDoc
                    ▶ SIM Swap: $statusSim
                    ▶ Score: $score
                """.trimIndent()

                txtStatusFinal.text = if (
                    statusFacial == "SUCESSO" &&
                    statusDigital == "SUCESSO" &&
                    statusDoc == "SUCESSO" &&
                    statusSim == "SUCESSO"
                ) {
                    "✅ Análise antifraude concluída com sucesso!"
                } else {
                    "❌ Análise com falhas. Verifique os resultados."
                }
            } ?: run {
                txtStatusFinal.text = "❌ Não foi possível obter os dados do resultado."
            }
        }

        viewModel.erro.observe(this) {
            txtStatusFinal.text = it
        }

        btnFinalizar.setOnClickListener {
            finishAffinity()
        }
    }
}









