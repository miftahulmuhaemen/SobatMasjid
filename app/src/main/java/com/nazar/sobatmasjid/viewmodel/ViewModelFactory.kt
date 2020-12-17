package com.nazar.sobatmasjid.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nazar.sobatmasjid.data.DataRepository
import com.nazar.sobatmasjid.di.Injection
import com.nazar.sobatmasjid.ui.fragments.adhan.AdhanViewModel
import com.nazar.sobatmasjid.ui.fragments.announcement.AnnouncementViewModel
import com.nazar.sobatmasjid.ui.fragments.announcement.detail.AnnouncementDetailViewModel
import com.nazar.sobatmasjid.ui.fragments.announcement.list.AnnouncementListViewModel
import com.nazar.sobatmasjid.ui.fragments.home.HomeViewModel
import com.nazar.sobatmasjid.ui.fragments.location.LocationViewModel
import com.nazar.sobatmasjid.ui.fragments.login.LoginViewModel
import com.nazar.sobatmasjid.ui.fragments.mosque.MosqueViewModel
import com.nazar.sobatmasjid.ui.fragments.mosque.detail.MosqueDetailViewModel
import com.nazar.sobatmasjid.ui.fragments.mosque.list.MosqueListViewModel
import com.nazar.sobatmasjid.ui.fragments.research.ResearchViewModel
import com.nazar.sobatmasjid.ui.fragments.research.detail.ResearchDetailViewModel
import com.nazar.sobatmasjid.ui.fragments.research.list.ResearchListViewModel

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
            modelClass.isAssignableFrom(AdhanViewModel::class.java) -> {
                AdhanViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(MosqueViewModel::class.java) -> {
                MosqueViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(MosqueListViewModel::class.java) -> {
                MosqueListViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(MosqueDetailViewModel::class.java) -> {
                MosqueDetailViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(ResearchViewModel::class.java) -> {
                ResearchViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(ResearchListViewModel::class.java) -> {
                ResearchListViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(ResearchDetailViewModel::class.java) -> {
                ResearchDetailViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(AnnouncementViewModel::class.java) -> {
                AnnouncementViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(AnnouncementListViewModel::class.java) -> {
                AnnouncementListViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(AnnouncementDetailViewModel::class.java) -> {
                AnnouncementDetailViewModel(dataRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}
