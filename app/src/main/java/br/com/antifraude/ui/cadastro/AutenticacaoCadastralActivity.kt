package br.com.antifraude.ui.cadastro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.antifraude.R
import br.com.antifraude.model.ClienteRequest
import br.com.antifraude.ui.biometria.BiometriaFacialActivity
import br.com.antifraude.util.SessaoCliente

class AutenticacaoCadastralActivity : AppCompatActivity() {

    private val viewModel: AutenticacaoCadastralViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autenticacao)

        val edtCpf = findViewById<EditText>(R.id.edtCpf)
        val edtNome = findViewById<EditText>(R.id.edtNome)
        val edtEndereco = findViewById<EditText>(R.id.edtEndereco)
        val edtTelefone = findViewById<EditText>(R.id.edtTelefone)
        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val btnCadastrar = findViewById<Button>(R.id.btnCadastrar)
        val txtResultadoCadastro = findViewById<TextView>(R.id.txtResultadoCadastro)

        btnCadastrar.setOnClickListener {
            val cliente = ClienteRequest(
                cpf = edtCpf.text.toString(),
                nomeCompleto = edtNome.text.toString(),
                endereco = edtEndereco.text.toString(),
                telefone = edtTelefone.text.toString(),
                email = edtEmail.text.toString()
            )

            SessaoCliente.cpf = cliente.cpf
            viewModel.autenticar(cliente)
        }

        viewModel.resultado.observe(this) { mensagem ->
            txtResultadoCadastro.text = mensagem
            if (mensagem.contains("sucesso", ignoreCase = true)) {
                startActivity(Intent(this, BiometriaFacialActivity::class.java))
                finish()
            }
        }
    }
}




