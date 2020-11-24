package com.nazar.sobatmasjid.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nazar.sobatmasjid.data.DataRepository
import com.nazar.sobatmasjid.di.Injection
import com.nazar.sobatmasjid.ui.login.LoginViewModel

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
//            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
//                MovieViewModel(dataRepository) as T
//            }
//            modelClass.isAssignableFrom(BookmarkedMovieViewModel::class.java) -> {
//                BookmarkedMovieViewModel(dataRepository) as T
//            }
//            modelClass.isAssignableFrom(TvViewModel::class.java) -> {
//                TvViewModel(dataRepository) as T
//            }
//            modelClass.isAssignableFrom(BookmarkedTvViewModel::class.java) -> {
//                BookmarkedTvViewModel(dataRepository) as T
//            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}
