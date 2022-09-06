package br.com.emersonfiwre.chatbot.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.emersonfiwre.chatbot.infrastructure.Constants.SPLASH_DURATION
import br.com.emersonfiwre.chatbot.viewmodel.viewstate.ChatSplashViewState
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay

class ChatSplashViewModel() : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val coroutineContext = Dispatchers.Main + viewModelJob

    private val chatSplashState = MutableLiveData<ChatSplashViewState>()
    val chatSplashViewState: LiveData<ChatSplashViewState> = chatSplashState

    fun setupDelaySplashScreen() {
        CoroutineScope(coroutineContext).launch {
            delay(SPLASH_DURATION)
            chatSplashState.value = ChatSplashViewState.ShowChat
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancelChildren()
    }
}