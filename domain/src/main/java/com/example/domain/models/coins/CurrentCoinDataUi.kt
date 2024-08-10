package com.example.domain.models.coins

data class CurrentCoinDataUi(
    val id: String = "",
    val name: String,
    val image: String = "",
    val categories: List<String> = emptyList(),
    val description: String = ""
)