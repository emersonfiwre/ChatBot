package br.com.emersonfiwre.chatbot.viewmodel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.emersonfiwre.chatbot.infrastructure.ChatBotApi
import br.com.emersonfiwre.chatbot.infrastructure.service.RetrofitClient
import br.com.emersonfiwre.chatbot.repository.ChatBotRepositoryImpl
import br.com.emersonfiwre.chatbot.viewmodel.MainViewModel

class MainViewModelFactory(application: Application) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val apiClient = RetrofitClient.createService(ChatBotApi::class.java)
        val repository = ChatBotRepositoryImpl(apiClient)
        return MainViewModel(repository) as T
    }
}