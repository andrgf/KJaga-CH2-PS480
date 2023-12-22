package com.example.kjaga.ui.home

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kjaga.domain.repo.MainRepository
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import retrofit2.http.Url
import java.io.File

class HomeViewModel(private val repository: MainRepository) : ViewModel() {

    fun getSignedLink(token:String, mime_type: String) = repository.getSignedLink(token,mime_type)

    fun getHistory(date:String, token:String) = repository.getHistory(date, token)

    fun uploadFile(url: String, file: File) = repository.uploadImages(url,file)

    fun userByToken(token: String) = repository.getUserByToken(token)

}