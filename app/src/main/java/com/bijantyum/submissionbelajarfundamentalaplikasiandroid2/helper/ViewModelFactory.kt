package com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.helper

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.menu.settings.SettingPreferences
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.menu.settings.SettingViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory private constructor(private val mApplication: Application) :
    ViewModelProvider.NewInstanceFactory() {

    private var preferences: SettingPreferences? = null

    constructor(preferences: SettingPreferences) : this(Application()) {
        this.preferences = preferences
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SettingViewModel::class.java) -> {
                preferences?.let { SettingViewModel(it) } as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(application)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}