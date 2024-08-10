package com.example.domain.models.coins

data class CoinInMarketUi(
    val id: String = "",
    val symbol: String = "",
    val name: String = "",
    val image: String = "",
    val currentPrice: Double = 0.0,
    val priceChangePercentage24H: Double = 0.0
)