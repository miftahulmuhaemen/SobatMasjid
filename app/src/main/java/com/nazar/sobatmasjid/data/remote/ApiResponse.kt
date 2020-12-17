package com.nazar.sobatmasjid.data.remote

import com.nazar.sobatmasjid.data.remote.StatusResponse.*

class ApiResponse<T>(val status: StatusResponse, val body: T?, val message: String?) {
    companion object {
        fun <T> success(body: T): ApiResponse<T> = ApiResponse(SUCCESS, body, null)

        fun <T> empty(msg: String): ApiResponse<T> = ApiResponse(EMPTY, null, msg)

        fun <T> error(msg: String): ApiResponse<T> = ApiResponse(ERROR, null, msg)
    }
}

