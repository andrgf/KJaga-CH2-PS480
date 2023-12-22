package com.example.kjaga.data.auth

data class Register(
    val name: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)
