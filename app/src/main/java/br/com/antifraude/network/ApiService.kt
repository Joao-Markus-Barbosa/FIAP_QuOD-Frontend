package br.com.antifraude.network

import br.com.antifraude.model.*
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("/api/autenticacao/cadastro")
    suspend fun autenticarCliente(@Body request: ClienteRequest): Response<ResponseBody>

    @POST("/api/documento/validar")
    suspend fun validarDocumento(@Body documento: DocumentoRequest): Response<ResponseBody>

    @POST("/api/simswap/validar")
    suspend fun validarSimSwap(@Body request: SimSwapRequest): Response<ResponseBody>

    @POST("/api/biometria/digital/validar")
    suspend fun validarBiometriaDigital(@Body request: BiometriaDigitalRequest): Response<ResponseBody>

    @POST("/api/score/gerar")
    suspend fun gerarScore(@Body request: ScoreRequest): Response<ResponseBody>

    @POST("/api/biometria/facial/validar")
    suspend fun validarBiometriaFacial(@Body request: BiometriaFacialRequest): Response<ResponseBody>

    @GET("/api/resultado/consolidado/{cpf}")
    suspend fun obterResultado(@Path("cpf") cpf: String): Response<ResultadoConsolidadoResponse>
}





