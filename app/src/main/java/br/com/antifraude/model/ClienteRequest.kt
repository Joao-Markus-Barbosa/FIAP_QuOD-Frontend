package br.com.antifraude.model

data class ClienteRequest(
    val cpf: String,
    val nomeCompleto: String,
    val endereco: String,
    val telefone: String,
    val email: String
)
