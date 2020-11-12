package com.nazar.sobatmasjid.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.nazar.sobatmasjid.data.remote.ApiResponse
import com.nazar.sobatmasjid.data.remote.StatusResponse
import com.nazar.sobatmasjid.utils.AppExecutors
import com.nazar.sobatmasjid.vo.Resource

abstract class NetworkOutboundResource<RequestType>(private val mExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<RequestType>>()

    init {
        result.value = Resource.loading(null)
        fetchFromNetwork()
    }

    protected open fun onFetchFailed() {}

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    private fun fetchFromNetwork() {

        val apiResponse = createCall()

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            when (response.status) {
                StatusResponse.SUCCESS ->
                    mExecutors.mainThread().execute {
                        result.value = Resource.success(null)
                    }
                StatusResponse.EMPTY ->
                    mExecutors.mainThread().execute {
                        result.value = Resource.success(null)
                    }
                StatusResponse.ERROR -> {
                    onFetchFailed()
                    result.value = Resource.error(response.message, null)
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<RequestType>> = result

}