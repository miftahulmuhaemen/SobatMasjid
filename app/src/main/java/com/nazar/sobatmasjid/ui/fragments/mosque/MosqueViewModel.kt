package com.nazar.sobatmasjid.ui.fragments.mosque

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nazar.sobatmasjid.data.DataRepository

class MosqueViewModel(private val dataRepository: DataRepository) : ViewModel() {
    var query: MutableLiveData<String> = MutableLiveData()
    var classification: MutableLiveData<List<String>> = MutableLiveData()

    fun setQuery(query: String){
        this.query.value = query
    }

    fun setClassification(classification: List<String>){
        this.classification.value = classification
    }
}