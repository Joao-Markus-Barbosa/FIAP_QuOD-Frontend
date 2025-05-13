package br.com.antifraude.model

data class ApiResponse<T>(
    val success: Boolean,
    val mensagem: String,
    val data: T
)



