package br.com.antifraude.model

import com.google.gson.annotations.SerializedName

data class ResultadoConsolidadoResponse(
    val cpf: String,

    @SerializedName("biometriaFacial")
    val statusBiometriaFacial: String,

    @SerializedName("biometriaDigital")
    val statusBiometriaDigital: String,

    @SerializedName("documentoscopia")
    val statusDocumento: String,

    @SerializedName("simSwap")
    val statusSimSwap: String,

    val score: Int
)

