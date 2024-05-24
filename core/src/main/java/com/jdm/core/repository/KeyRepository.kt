package com.jdm.core.repository

interface KeyRepository {
    fun getAccessKey(onSuccess: (String) -> Unit, onError: (String) -> Unit)
}