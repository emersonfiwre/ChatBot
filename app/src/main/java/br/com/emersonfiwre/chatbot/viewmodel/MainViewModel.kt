package br.com.emersonfiwre.chatbot.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.emersonfiwre.chatbot.repository.ChatBotRepositoryImpl
import br.com.emersonfiwre.chatbot.viewmodel.mapper.MainViewModelMapper.convertToChatBotState
import br.com.emersonfiwre.chatbot.viewmodel.mapper.MainViewModelMapper.convertToChatPersonState
import br.com.emersonfiwre.chatbot.viewmodel.viewstate.ChatViewState
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.cancelChildren
import java.lang.Exception

class MainViewModel(
    private val repository: ChatBotRepositoryImpl
) : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val coroutineContext = Dispatchers.Main + viewModelJob

    private val chatState = MutableLiveData<ChatViewState.ChatSuccess>()
    val chatViewState: LiveData<ChatViewState.ChatSuccess> = chatState

    private val chatLoadingState = MutableLiveData<ChatViewState.ChatLoading>()
    val chatLoadingViewState: LiveData<ChatViewState.ChatLoading> = chatLoadingState

    private val chatErrorState = MutableLiveData<ChatViewState.ChatError>()
    val chatErrorViewState: LiveData<ChatViewState.ChatError> = chatErrorState

    fun getBotAnswer(ask: String?) {
        CoroutineScope(coroutineContext).launch {
            try {
                if (!ask.isNullOrBlank()) {
                    chatState.value = convertToChatPersonState(ask)
                    chatLoadingState.value = ChatViewState.ChatLoading.ShowLoading
                    val result = repository.getBotAnswers(ask)
                    chatState.value = convertToChatBotState(result)
                } else {
                    chatErrorState.value = ChatViewState.ChatError.ShowEmptyAsk
                }
            } catch (exception: Exception) {
                chatErrorState.value = ChatViewState.ChatError.ShowError
            } finally {
                chatLoadingState.value = ChatViewState.ChatLoading.ShowHideLoading
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancelChildren()
    }
}