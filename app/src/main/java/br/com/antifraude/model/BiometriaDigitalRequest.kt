package br.com.antifraude.model

data class BiometriaDigitalRequest(
    val cpf: String,
    val imagemBase64: String
)
