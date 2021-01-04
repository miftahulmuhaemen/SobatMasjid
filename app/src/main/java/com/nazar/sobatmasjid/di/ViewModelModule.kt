package com.nazar.sobatmasjid.di

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
import com.nazar.sobatmasjid.ui.fragments.profile.ProfileViewModel
import com.nazar.sobatmasjid.ui.fragments.profile.followed.FollowedMosqueViewModel
import com.nazar.sobatmasjid.ui.fragments.research.ResearchViewModel
import com.nazar.sobatmasjid.ui.fragments.research.detail.ResearchDetailViewModel
import com.nazar.sobatmasjid.ui.fragments.research.list.ResearchListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { LocationViewModel(get()) }
    viewModel { AdhanViewModel(get()) }
    viewModel { MosqueViewModel(get()) }
    viewModel { MosqueListViewModel(get()) }
    viewModel { MosqueDetailViewModel(get()) }
    viewModel { ResearchViewModel(get()) }
    viewModel { ResearchListViewModel(get()) }
    viewModel { ResearchDetailViewModel(get()) }
    viewModel { AnnouncementViewModel() }
    viewModel { AnnouncementListViewModel(get()) }
    viewModel { AnnouncementDetailViewModel(get()) }
    viewModel { FollowedMosqueViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}