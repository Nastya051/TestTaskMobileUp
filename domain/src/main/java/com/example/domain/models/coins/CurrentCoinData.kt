package com.example.domain.models.coins

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/***
 * Увидела, что в большом количестве случаев выбрасывается JsonConvertException из-за того,
 * что не хватает "contract_address".
 * Его нет в описании структуры ответа на сайте, но он приходит во многих ответах.
 * Поэтому решила сделать две по сути одинаковые модели с разницей в одном этом поле
 * и принимать 2 разных успешных ответа
 */

@Serializable
data class CurrentCoinData(
    @SerialName("id")
    val id: String,
    @SerialName("symbol")
    val symbol: String,
    @SerialName("name")
    val name: String,
    @SerialName("web_slug")
    val webSlug: String?,
    @SerialName("asset_platform_id")
    val assetPlatformID: String? = null,
    @SerialName("platforms")
    val platforms: JsonElement?,
    @SerialName("detail_platforms")
    val detailPlatforms: JsonElement?,
    @SerialName("block_time_in_minutes")
    val blockTimeInMinutes: Long?,
    @SerialName("hashing_algorithm")
    val hashingAlgorithm: String?,
    @SerialName("categories")
    val categories: List<String>?,
    @SerialName("preview_listing")
    val previewListing: Boolean?,
    @SerialName("public_notice")
    val publicNotice: String? = null,
    @SerialName("additional_notices")
    val additionalNotices: List<String>?,
    @SerialName("localization")
    val localization: Tion,
    @SerialName("description")
    val description: Tion,
    @SerialName("links")
    val links: CoinLinks?,
    @SerialName("image")
    val image: Image,
    @SerialName("country_origin")
    val countryOrigin: String?,
    @SerialName("genesis_date")
    val genesisDate: String?,
    @SerialName("sentiment_votes_up_percentage")
    val sentimentVotesUpPercentage: Double?,
    @SerialName("sentiment_votes_down_percentage")
    val sentimentVotesDownPercentage: Double?,
    @SerialName("ico_data")
    val icoData: IcoData? = null,
    @SerialName("watchlist_portfolio_users")
    val watchlistPortfolioUsers: Long?,
    @SerialName("market_cap_rank")
    val marketCapRank: Long?,
    @SerialName("market_data")
    val marketData: MarketData?,
    @SerialName("community_data")
    val communityData: CommunityData?,
    @SerialName("developer_data")
    val developerData: DeveloperData?,
    @SerialName("status_updates")
    val statusUpdates: List<StatusUpdate>? = null,
    @SerialName("last_updated")
    val lastUpdated: String?,
    @SerialName("tickers")
    val tickers: List<Ticker>?
) {
    fun toCurrentCoinDataUi(): CurrentCoinDataUi {
        return CurrentCoinDataUi(
            id = id,
            name = name,
            image = image.large ?: "",
            categories = categories ?: emptyList(),
            description = description.en
        )
    }
}

@Serializable
data class CurrentCoinDataWithContracts(
    @SerialName("id")
    val id: String,
    @SerialName("symbol")
    val symbol: String,
    @SerialName("name")
    val name: String,
    @SerialName("web_slug")
    val webSlug: String?,
    @SerialName("asset_platform_id")
    val assetPlatformID: String? = null,
    @SerialName("platforms")
    val platforms: JsonElement?,
    @SerialName("detail_platforms")
    val detailPlatforms: JsonElement?,
    @SerialName("block_time_in_minutes")
    val blockTimeInMinutes: Long?,
    @SerialName("hashing_algorithm")
    val hashingAlgorithm: String?,
    @SerialName("categories")
    val categories: List<String>?,
    @SerialName("preview_listing")
    val previewListing: Boolean?,
    @SerialName("public_notice")
    val publicNotice: String? = null,
    @SerialName("additional_notices")
    val additionalNotices: List<String>?,
    @SerialName("localization")
    val localization: Tion,
    @SerialName("description")
    val description: Tion,
    @SerialName("links")
    val links: CoinLinks?,
    @SerialName("image")
    val image: Image,
    @SerialName("country_origin")
    val countryOrigin: String?,
    @SerialName("genesis_date")
    val genesisDate: String?,
    @SerialName("contract_address")
    val contractAddress: String?,
    @SerialName("sentiment_votes_up_percentage")
    val sentimentVotesUpPercentage: Double?,
    @SerialName("sentiment_votes_down_percentage")
    val sentimentVotesDownPercentage: Double?,
    @SerialName("ico_data")
    val icoData: IcoData? = null,
    @SerialName("watchlist_portfolio_users")
    val watchlistPortfolioUsers: Long?,
    @SerialName("market_cap_rank")
    val marketCapRank: Long?,
    @SerialName("market_data")
    val marketData: MarketData?,
    @SerialName("community_data")
    val communityData: CommunityData?,
    @SerialName("developer_data")
    val developerData: DeveloperData?,
    @SerialName("status_updates")
    val statusUpdates: List<StatusUpdate>? = null,
    @SerialName("last_updated")
    val lastUpdated: String?,
    @SerialName("tickers")
    val tickers: List<Ticker>?
) {
    fun toCurrentCoinDataUi(): CurrentCoinDataUi {
        return CurrentCoinDataUi(
            id = id,
            name = name,
            image = image.large ?: "",
            categories = categories ?: emptyList(),
            description = description.en
        )
    }
}

@Serializable
data class CommunityData(
    @SerialName("facebook_likes")
    val facebookLikes: Long? = null,
    @SerialName("twitter_followers")
    val twitterFollowers: Long?,
    @SerialName("reddit_average_posts_48h")
    val redditAveragePosts48H: Double?,
    @SerialName("reddit_average_comments_48h")
    val redditAverageComments48H: Double?,
    @SerialName("reddit_subscribers")
    val redditSubscribers: Long?,
    @SerialName("reddit_accounts_active_48h")
    val redditAccountsActive48H: Long?,
    @SerialName("telegram_channel_user_count")
    val telegramChannelUserCount: Long? = null
)

@Serializable
data class Tion(
    @SerialName("en")
    val en: String,
    @SerialName("de")
    val de: String,
    @SerialName("es")
    val es: String,
    @SerialName("fr")
    val fr: String,
    @SerialName("it")
    val it: String,
    @SerialName("pl")
    val pl: String,
    @SerialName("ro")
    val ro: String,
    @SerialName("hu")
    val hu: String,
    @SerialName("nl")
    val nl: String,
    @SerialName("pt")
    val pt: String,
    @SerialName("sv")
    val sv: String,
    @SerialName("vi")
    val vi: String,
    @SerialName("tr")
    val tr: String,
    @SerialName("ru")
    val ru: String,
    @SerialName("ja")
    val ja: String,
    @SerialName("zh")
    val zh: String,
    @SerialName("zh-tw")
    val zhTw: String,
    @SerialName("ko")
    val ko: String,
    @SerialName("ar")
    val ar: String,
    @SerialName("th")
    val th: String,
    @SerialName("id")
    val id: String,
    @SerialName("cs")
    val cs: String,
    @SerialName("da")
    val da: String,
    @SerialName("el")
    val el: String,
    @SerialName("hi")
    val hi: String,
    @SerialName("no")
    val no: String,
    @SerialName("sk")
    val sk: String,
    @SerialName("uk")
    val uk: String,
    @SerialName("he")
    val he: String,
    @SerialName("fi")
    val fi: String,
    @SerialName("bg")
    val bg: String,
    @SerialName("hr")
    val hr: String,
    @SerialName("lt")
    val lt: String,
    @SerialName("sl")
    val sl: String
)


@Serializable
data class Platforms(
    @SerialName("ethereum")
    val ethereum: Avalanche?,
    @SerialName("avalanche")
    val avalanche: Avalanche?,
    @SerialName("tron")
    val tron: Avalanche?,
    @SerialName("near-protocol")
    val nearProtocol: Avalanche?,
    @SerialName("solana")
    val solana: Avalanche?,
    @SerialName("the-open-network")
    val theOpenNetwork: Avalanche?,
    @SerialName("kava")
    val kava: Avalanche?,
    @SerialName("celo")
    val celo: Avalanche?
)

@Serializable
data class Avalanche(
    @SerialName("decimal_place")
    val decimalPlace: Long?,
    @SerialName("contract_address")
    val contractAddress: String?
)

@Serializable
data class Empty(
    @SerialName("decimal_place")
    val decimalPlace: Long? = null,
    @SerialName("contract_address")
    val contractAddress: String?
)

@Serializable
data class DeveloperData(
    @SerialName("forks")
    val forks: Long?,
    @SerialName("stars")
    val stars: Long?,
    @SerialName("subscribers")
    val subscribers: Long?,
    @SerialName("total_issues")
    val totalIssues: Long?,
    @SerialName("closed_issues")
    val closedIssues: Long?,
    @SerialName("pull_requests_merged")
    val pullRequestsMerged: Long?,
    @SerialName("pull_request_contributors")
    val pullRequestContributors: Long?,
    @SerialName("code_additions_deletions_4_weeks")
    val codeAdditionsDeletions4Weeks: CodeAdditionsDeletions4Weeks?,
    @SerialName("commit_count_4_weeks")
    val commitCount4Weeks: Long?,
    @SerialName("last_4_weeks_commit_activity_series")
    val last4WeeksCommitActivitySeries: List<Long>?
)

@Serializable
data class CodeAdditionsDeletions4Weeks(
    @SerialName("additions")
    val additions: Long?,
    @SerialName("deletions")
    val deletions: Long?
)

@Serializable
data class IcoData(
    @SerialName("ico_start_date")
    val icoStartDate: String?,
    @SerialName("ico_end_date")
    val icoEndDate: String?,
    @SerialName("short_desc")
    val shortDesc: String?,
    @SerialName("description")
    val description: String? = null,
    @SerialName("links")
    val links: JsonElement?,
    @SerialName("softcap_currency")
    val softcapCurrency: String?,
    @SerialName("hardcap_currency")
    val hardcapCurrency: String,
    @SerialName("total_raised_currency")
    val totalRaisedCurrency: String?,
    @SerialName("softcap_amount")
    val softcapAmount: JsonElement? = null,
    @SerialName("hardcap_amount")
    val hardcapAmount: JsonElement? = null,
    @SerialName("total_raised")
    val totalRaised: JsonElement? = null,
    @SerialName("quote_pre_sale_currency")
    val quotePreSaleCurrency: String?,
    @SerialName("base_pre_sale_amount")
    val basePreSaleAmount: JsonElement? = null,
    @SerialName("quote_pre_sale_amount")
    val quotePreSaleAmount: JsonElement? = null,
    @SerialName("quote_public_sale_currency")
    val quotePublicSaleCurrency: String?,
    @SerialName("base_public_sale_amount")
    val basePublicSaleAmount: Double?,
    @SerialName("quote_public_sale_amount")
    val quotePublicSaleAmount: Double?,
    @SerialName("accepting_currencies")
    val acceptingCurrencies: String?,
    @SerialName("country_origin")
    val countryOrigin: String?,
    @SerialName("pre_sale_start_date")
    val preSaleStartDate: JsonElement? = null,
    @SerialName("pre_sale_end_date")
    val preSaleEndDate: JsonElement? = null,
    @SerialName("whitelist_url")
    val whitelistURL: String?,
    @SerialName("whitelist_start_date")
    val whitelistStartDate: JsonElement? = null,
    @SerialName("whitelist_end_date")
    val whitelistEndDate: JsonElement? = null,
    @SerialName("bounty_detail_url")
    val bountyDetailURL: String?,
    @SerialName("amount_for_sale")
    val amountForSale: JsonElement? = null,
    @SerialName("kyc_required")
    val kycRequired: Boolean?,
    @SerialName("whitelist_available")
    val whitelistAvailable: JsonElement? = null,
    @SerialName("pre_sale_available")
    val preSaleAvailable: JsonElement? = null,
    @SerialName("pre_sale_ended")
    val preSaleEnded: Boolean?
)

@Serializable
data class Image(
    @SerialName("thumb")
    val thumb: String?,
    @SerialName("small")
    val small: String?,
    @SerialName("large")
    val large: String?
)

@Serializable
data class CoinLinks(
    @SerialName("homepage")
    val homepage: List<String>?,
    @SerialName("whitepaper")
    val whitepaper: String?,
    @SerialName("blockchain_site")
    val blockchainSite: List<String>?,
    @SerialName("official_forum_url")
    val officialForumURL: List<String>?,
    @SerialName("chat_url")
    val chatURL: List<String>?,
    @SerialName("announcement_url")
    val announcementURL: List<String>?,
    @SerialName("twitter_screen_name")
    val twitterScreenName: String?,
    @SerialName("facebook_username")
    val facebookUsername: String?,
    @SerialName("bitcointalk_thread_identifier")
    val bitcointalkThreadIdentifier: JsonElement? = null,
    @SerialName("telegram_channel_identifier")
    val telegramChannelIdentifier: String?,
    @SerialName("subreddit_url")
    val subredditURL: String?,
    @SerialName("repos_url")
    val reposURL: ReposURL?
)

@Serializable
data class ReposURL(
    @SerialName("github")
    val github: List<String>?,
    @SerialName("bitbucket")
    val bitbucket: List<String>?
)

@Serializable
data class MarketData(
    @SerialName("current_price")
    val currentPrice: Map<String, Double>?,
    @SerialName("total_value_locked")
    val totalValueLocked: JsonElement? = null,
    @SerialName("mcap_to_tvl_ratio")
    val mcapToTvlRatio: JsonElement? = null,
    @SerialName("fdv_to_tvl_ratio")
    val fdvToTvlRatio: JsonElement? = null,
    @SerialName("roi")
    val roi: Roi? = null,
    @SerialName("ath")
    val ath: Map<String, Double>?,
    @SerialName("ath_change_percentage")
    val athChangePercentage: Map<String, Double>?,
    @SerialName("ath_date")
    val athDate: Map<String, String>?,
    @SerialName("atl")
    val atl: Map<String, Double>?,
    @SerialName("atl_change_percentage")
    val atlChangePercentage: Map<String, Double>?,
    @SerialName("atl_date")
    val atlDate: Map<String, String>?,
    @SerialName("market_cap")
    val marketCap: Map<String, Double>?,
    @SerialName("market_cap_rank")
    val marketCapRank: Long?,
    @SerialName("fully_diluted_valuation")
    val fullyDilutedValuation: Map<String, Double>?,
    @SerialName("market_cap_fdv_ratio")
    val marketCapFdvRatio: Double?,
    @SerialName("total_volume")
    val totalVolume: Map<String, Double>?,
    @SerialName("high_24h")
    val high24H: Map<String, Double>?,
    @SerialName("low_24h")
    val low24H: Map<String, Double>?,
    @SerialName("price_change_24h")
    val priceChange24H: Double?,
    @SerialName("price_change_percentage_24h")
    val priceChangePercentage24H: Double?,
    @SerialName("price_change_percentage_7d")
    val priceChangePercentage7D: Double?,
    @SerialName("price_change_percentage_14d")
    val priceChangePercentage14D: Double?,
    @SerialName("price_change_percentage_30d")
    val priceChangePercentage30D: Double?,
    @SerialName("price_change_percentage_60d")
    val priceChangePercentage60D: Double?,
    @SerialName("price_change_percentage_200d")
    val priceChangePercentage200D: Double?,
    @SerialName("price_change_percentage_1y")
    val priceChangePercentage1Y: Double?,
    @SerialName("market_cap_change_24h")
    val marketCapChange24H: Double?,
    @SerialName("market_cap_change_percentage_24h")
    val marketCapChangePercentage24H: Double?,
    @SerialName("price_change_24h_in_currency")
    val priceChange24HInCurrency: Map<String, Double>?,
    @SerialName("price_change_percentage_1h_in_currency")
    val priceChangePercentage1HInCurrency: Map<String, Double>?,
    @SerialName("price_change_percentage_24h_in_currency")
    val priceChangePercentage24HInCurrency: Map<String, Double>?,
    @SerialName("price_change_percentage_7d_in_currency")
    val priceChangePercentage7DInCurrency: Map<String, Double>?,
    @SerialName("price_change_percentage_14d_in_currency")
    val priceChangePercentage14DInCurrency: Map<String, Double>?,
    @SerialName("price_change_percentage_30d_in_currency")
    val priceChangePercentage30DInCurrency: Map<String, Double>?,
    @SerialName("price_change_percentage_60d_in_currency")
    val priceChangePercentage60DInCurrency: Map<String, Double>?,
    @SerialName("price_change_percentage_200d_in_currency")
    val priceChangePercentage200DInCurrency: Map<String, Double>?,
    @SerialName("price_change_percentage_1y_in_currency")
    val priceChangePercentage1YInCurrency: Map<String, Double>?,
    @SerialName("market_cap_change_24h_in_currency")
    val marketCapChange24HInCurrency: Map<String, Double>?,
    @SerialName("market_cap_change_percentage_24h_in_currency")
    val marketCapChangePercentage24HInCurrency: Map<String, Double>?,
    @SerialName("total_supply")
    val totalSupply: Double?,
    @SerialName("max_supply")
    val maxSupply: JsonElement? = null,
    @SerialName("circulating_supply")
    val circulatingSupply: Double?,
    @SerialName("last_updated")
    val lastUpdated: String?
)

@Serializable
data class Ticker(
    @SerialName("base")
    val base: String?,
    @SerialName("target")
    val target: String?,
    @SerialName("market")
    val market: Market?,
    @SerialName("last")
    val last: Double?,
    @SerialName("volume")
    val volume: Double?,
    @SerialName("converted_last")
    val convertedLast: Map<String, Double>?,
    @SerialName("converted_volume")
    val convertedVolume: Map<String, Double>?,
    @SerialName("trust_score")
    val trustScore: String?,
    @SerialName("bid_ask_spread_percentage")
    val bidAskSpreadPercentage: Double?,
    @SerialName("timestamp")
    val timestamp: String?,
    @SerialName("last_traded_at")
    val lastTradedAt: String?,
    @SerialName("last_fetch_at")
    val lastFetchAt: String?,
    @SerialName("is_anomaly")
    val isAnomaly: Boolean?,
    @SerialName("is_stale")
    val isStale: Boolean?,
    @SerialName("trade_url")
    val tradeURL: String? = null,
    @SerialName("token_info_url")
    val tokenInfoURL: JsonElement? = null,
    @SerialName("coin_id")
    val coinID: String?,
    @SerialName("target_coin_id")
    val targetCoinID: String? = null
)

@Serializable
data class Market(
    @SerialName("name")
    val name: String?,
    @SerialName("identifier")
    val identifier: String?,
    @SerialName("has_trading_incentive")
    val hasTradingIncentive: Boolean?
)

@Serializable
data class StatusUpdate(
    @SerialName("description")
    val description: String?,
    @SerialName("category")
    val category: String?,
    @SerialName("created_at")
    val createdAt: String?,
    @SerialName("user")
    val user: String?,
    @SerialName("user_title")
    val userTitle: String?,
    @SerialName("pin")
    val pin: Boolean?,
    @SerialName("project")
    val project: Project?
)

@Serializable
data class Project(
    @SerialName("type")
    val type: String?,
    @SerialName("id")
    val id: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("symbol")
    val symbol: String?,
    @SerialName("image")
    val image: Image?
)