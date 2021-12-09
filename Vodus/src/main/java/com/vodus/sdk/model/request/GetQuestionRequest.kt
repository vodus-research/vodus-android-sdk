package com.vodus.sdk.model.request

import kotlinx.serialization.Serializable

/**
 *
 * Properties to be used for Vodus CC requests
 *
 * partnerCode: Vodus partner code for publisher
 * containerWidth: Display width or width to be set
 * containerBackgroundColor: Background color for the survey container in hex code, eg: #000000
 *
 */
@Serializable
data class GetQuestionRequest(
    val partnerCode: String,
    val deviceId: String,
    val containerWidth: Int,
    val containerBackgroundColor: String
)
