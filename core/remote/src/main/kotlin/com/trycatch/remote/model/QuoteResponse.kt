/*
 * Copyright (c) 2024 trycatch
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.trycatch.remote.model

import com.trycatch.data.model.QuoteEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuoteWrapperResponse(
    val id: Int,
    val name: String,
    val symbol: String,
    val slug: String,
    @SerialName("is_active")
    val isActive: Int,
    @SerialName("is_fiat")
    val isFiat: Int,
    @SerialName("circulating_supply")
    val circulatingSupply: Double,
    @SerialName("total_supply")
    val totalSupply: Double,
    @SerialName("max_supply")
    val maxSupply: Double?,
    @SerialName("date_added")
    val dateAdded: String,
    @SerialName("num_market_pairs")
    val numMarketPairs: Int,
    @SerialName("cmc_rank")
    val cmcRank: Int,
    val tags: List<String>,
    val platform: PlatformResponse?,
    @SerialName("last_updated")
    val lastUpdated: String,
    @SerialName("self_reported_circulating_supply")
    val selfReportedCirculatingSupply: Double?,
    @SerialName("self_reported_market_cap")
    val selfReportedMarketCap: Double?,
    val quote: QuoteResponse?
)

@Serializable
data class PlatformResponse(
    val id: Int,
    val name: String,
    val symbol: String,
    val slug: String,
    @SerialName("token_address")
    val tokenAddress: String
)

@Serializable
data class QuoteResponse(
    val USD: UsdQuote?
) {
    fun toEntity(): QuoteEntity =
        QuoteEntity(
            price = USD?.price ?: 0.0,
            percentChange24h = USD?.percentChange24h ?: 0.0
        )
}

@Serializable
data class UsdQuote(
    val price: Double?,
    @SerialName("volume_24h")
    val volume24h: Double?,
    @SerialName("volume_change_24h")
    val volumeChange24h: Double?,
    @SerialName("percent_change_1h")
    val percentChange1h: Double?,
    @SerialName("percent_change_24h")
    val percentChange24h: Double?,
    @SerialName("percent_change_7d")
    val percentChange7d: Double?,
    @SerialName("percent_change_30d")
    val percentChange30d: Double?,
    @SerialName("market_cap")
    val marketCap: Double?,
    @SerialName("market_cap_dominance")
    val marketCapDominance: Double?,
    @SerialName("fully_diluted_market_cap")
    val fullyDilutedMarketCap: Double?,
    @SerialName("last_updated")
    val lastUpdated: String?
)