package br.com.antifraude.ui.resultado

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.antifraude.model.ResultadoConsolidadoResponse
import br.com.antifraude.network.RetrofitInstance
import kotlinx.coroutines.launch

class ResultadoViewModel : ViewModel() {

    private val _resultado = MutableLiveData<ResultadoConsolidadoResponse?>()
    val resultado: LiveData<ResultadoConsolidadoResponse?> = _resultado

    private val _erro = MutableLiveData<String>()
    val erro: LiveData<String> = _erro

    fun consultarResultado(cpf: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.obterResultado(cpf)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        _resultado.postValue(body)
                    } else {
                        _erro.postValue("❌ Resposta da API vazia.")
                    }
                } else {
                    _erro.postValue("❌ Erro HTTP: ${response.code()}")
                }
            } catch (e: Exception) {
                _erro.postValue("❌ Erro de conexão: ${e.message}")
            }
        }
    }
}





