package br.com.antifraude.model

data class BiometriaFacialRequest(
    val cpf: String,
    val imagemBase64: String
)
