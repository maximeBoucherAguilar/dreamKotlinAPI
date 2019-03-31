package fr.maxba.dreamkotlinapi.ui

import fr.maxba.dreamkotlinapi.ui.peoples.create.CreatePeopleViewModel
import fr.maxba.dreamkotlinapi.ui.peoples.detail.DetailPeopleViewModel
import fr.maxba.dreamkotlinapi.ui.peoples.list.PeoplesViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModule = module {

    viewModel { PeoplesViewModel(androidApplication()) }

    viewModel { CreatePeopleViewModel(androidApplication()) }

    viewModel { DetailPeopleViewModel(androidApplication()) }

}