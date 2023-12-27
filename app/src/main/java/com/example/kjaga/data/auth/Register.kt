package com.example.kjaga.data.auth

data class Register(
    val email: String,
    val name: String,
    val password: String,
    val confirmPassword: String
)
