package com.example.kjaga.domain.repo

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kjaga.di.DI
import com.example.kjaga.ui.addfood.AddFoodViewModel
import com.example.kjaga.ui.auth.login.LoginViewModel
import com.example.kjaga.ui.auth.register.RegisterViewModel
import com.example.kjaga.ui.history.HistoryViewModel
import com.example.kjaga.ui.history.food.HistoryFoodViewModel
import com.example.kjaga.ui.home.HomeViewModel
import com.example.kjaga.ui.profile.ProfileViewModel
import com.example.kjaga.ui.result.ResultScanViewModel
import com.example.kjaga.ui.search.SearchViewModel

class ViewModelFactory private constructor(
    private val authRepository: AuthRepository,
    private val mainRepository: MainRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(authRepository) as T
        }
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(authRepository) as T
        }
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(mainRepository) as T
        }
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            return HistoryViewModel(mainRepository) as T
        }
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(mainRepository) as T
        }
        if (modelClass.isAssignableFrom(ResultScanViewModel::class.java)) {
            return ResultScanViewModel(mainRepository) as T
        }
        if (modelClass.isAssignableFrom(AddFoodViewModel::class.java)) {
            return AddFoodViewModel(mainRepository) as T
        }
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(mainRepository) as T
        }
        if (modelClass.isAssignableFrom(HistoryFoodViewModel::class.java)) {
            return HistoryFoodViewModel(mainRepository) as T
        }


        throw IllegalAccessException("Unknown viewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    DI.provideAuthRepository(context),
                    DI.provideMainRepository(context)
                )
            }.also { instance = it }
    }

}