package com.example.domain.models.coins

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinInMarket (
    @SerialName("id")
    val id: String,
    @SerialName("symbol")
    val symbol: String,
    @SerialName("name")
    val name: String,
    @SerialName("image")
    val image: String,
    @SerialName("current_price")
    val currentPrice: Double,
    @SerialName("market_cap")
    val marketCap: Long,
    @SerialName("market_cap_rank")
    val marketCapRank: Long,
    @SerialName("fully_diluted_valuation")
    val fullyDilutedValuation: Long? = null,
    @SerialName("total_volume")
    val totalVolume: Long,
    @SerialName("high_24h")
    val high24H: Double,
    @SerialName("low_24h")
    val low24H: Double,
    @SerialName("price_change_24h")
    val priceChange24H: Double,
    @SerialName("price_change_percentage_24h")
    val priceChangePercentage24H: Double,
    @SerialName("market_cap_change_24h")
    val marketCapChange24H: Double,
    @SerialName("market_cap_change_percentage_24h")
    val marketCapChangePercentage24H: Double,
    @SerialName("circulating_supply")
    val circulatingSupply: Double,
    @SerialName("total_supply")
    val totalSupply: Double,
    @SerialName("max_supply")
    val maxSupply: Double? = null,
    @SerialName("ath")
    val ath: Double,
    @SerialName("ath_change_percentage")
    val athChangePercentage: Double,
    @SerialName("ath_date")
    val athDate: String,
    @SerialName("atl")
    val atl: Double,
    @SerialName("atl_change_percentage")
    val atlChangePercentage: Double,
    @SerialName("atl_date")
    val atlDate: String,
    @SerialName("roi")
    val roi: Roi? = null,
    @SerialName("last_updated")
    val lastUpdated: String
) {
    fun toCoinInMarketUi(): CoinInMarketUi {
        return CoinInMarketUi(
            id = id,
            symbol = symbol,
            name = name,
            image = image,
            currentPrice = currentPrice,
            priceChangePercentage24H = priceChangePercentage24H
        )
    }
}

@Serializable
data class Roi(
    @SerialName("times")
    val times: Double,
    @SerialName("currency")
    val currency: String,
    @SerialName("percentage")
    val percentage: Double
)