package br.com.antifraude.model

data class DocumentoRequest(
    val cpf: String,
    val imagemDocumentoBase64: String,
    val imagemSelfieBase64: String
)
