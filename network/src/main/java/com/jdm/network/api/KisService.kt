package com.jdm.network.api

import com.jdm.model.resp.KeyResp
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.POST

interface KisService {
    @POST("/oauth2/Approval")
    suspend fun getAccessKey(): ApiResponse<KeyResp>

}