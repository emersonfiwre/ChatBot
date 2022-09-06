package br.com.emersonfiwre.chatbot.viewmodel.viewstate

import br.com.emersonfiwre.chatbot.model.ChatResultModel

sealed class ChatViewState {

    sealed class ChatSuccess {
        data class ShowAnswers(val answer: ChatResultModel.ChatBot) : ChatSuccess()
        data class ShowAsk(val ask: ChatResultModel.ChatPerson) : ChatSuccess()
    }

    sealed class ChatLoading {
        object ShowLoading : ChatLoading()
        object ShowHideLoading : ChatLoading()
    }

    sealed class ChatError {
        object ShowError : ChatError()
        object ShowEmptyAsk : ChatError()
    }
}
