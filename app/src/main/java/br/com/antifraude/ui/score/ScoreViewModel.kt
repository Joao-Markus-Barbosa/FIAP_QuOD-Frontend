package br.com.antifraude.ui.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.antifraude.model.ScoreRequest
import br.com.antifraude.network.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class ScoreViewModel : ViewModel() {

    private val _resultado = MutableLiveData<String>()
    val resultado: LiveData<String> = _resultado

    fun gerarScore(request: ScoreRequest) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.gerarScore(request)
                val body: String? = response.body()?.string()


                if (response.isSuccessful && !body.isNullOrBlank()) {
                    _resultado.postValue("✅ $body")
                } else {
                    _resultado.postValue("❌ ${body ?: "Erro ao gerar score antifraude."}")
                }

            } catch (e: IOException) {
                _resultado.postValue("❌ Erro de conexão: ${e.message}")
            } catch (e: HttpException) {
                _resultado.postValue("❌ Erro HTTP: ${e.message}")
            } catch (e: Exception) {
                _resultado.postValue("❌ Erro inesperado: ${e.message}")
            }
        }
    }
}


