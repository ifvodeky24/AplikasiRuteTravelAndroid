package com.example.aplikasirutetravel.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aplikasirutetravel.data.TravelRepository
import com.example.aplikasirutetravel.di.Injection

class ViewModelFactory private constructor(private val travelRepository: TravelRepository) :
    ViewModelProvider.NewInstanceFactory() {

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
            modelClass.isAssignableFrom(PerusahaanViewModel::class.java) -> {
                PerusahaanViewModel(travelRepository) as T
            }
            modelClass.isAssignableFrom(KondisiJalanViewModel::class.java) -> {
                KondisiJalanViewModel(travelRepository) as T
            }
            modelClass.isAssignableFrom(TrayekViewModel::class.java) -> {
                TrayekViewModel(travelRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}
