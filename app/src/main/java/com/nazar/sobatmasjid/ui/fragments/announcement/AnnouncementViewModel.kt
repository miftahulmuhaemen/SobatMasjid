package com.nazar.sobatmasjid.ui.fragments.announcement

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AnnouncementViewModel () : ViewModel() {
    var query: MutableLiveData<String> = MutableLiveData()

    fun setQuery(query: String){
        this.query.value = query
    }
}