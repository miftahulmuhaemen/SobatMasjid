package com.nazar.sobatmasjid.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nazar.sobatmasjid.data.DataRepository
import com.nazar.sobatmasjid.di.Injection
import com.nazar.sobatmasjid.ui.fragments.home.HomeViewModel
import com.nazar.sobatmasjid.ui.fragments.location.LocationViewModel
import com.nazar.sobatmasjid.ui.fragments.login.LoginViewModel
import com.nazar.sobatmasjid.ui.fragments.mosque.MosqueViewModel
import com.nazar.sobatmasjid.ui.fragments.mosque.list.MosqueListViewModel

class ViewModelFactory  private constructor(private val dataRepository: DataRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(LocationViewModel::class.java) -> {
                LocationViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(MosqueViewModel::class.java) -> {
                MosqueViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(MosqueListViewModel::class.java) -> {
                MosqueListViewModel(dataRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}
