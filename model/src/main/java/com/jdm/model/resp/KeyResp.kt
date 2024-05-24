package com.jdm.model.resp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KeyResp (
    @SerialName("approval_key")
    val approvalKey: String
)