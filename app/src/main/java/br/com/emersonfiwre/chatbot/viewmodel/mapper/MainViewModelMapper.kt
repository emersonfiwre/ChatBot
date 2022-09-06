package br.com.emersonfiwre.chatbot.viewmodel.mapper

import br.com.emersonfiwre.chatbot.model.ChatResultModel
import br.com.emersonfiwre.chatbot.repository.response.BotAnswer
import br.com.emersonfiwre.chatbot.viewmodel.viewstate.ChatViewState

object MainViewModelMapper {

    fun convertToChatPersonState(ask: String) = ChatViewState.ChatSuccess.ShowAsk(
        ChatResultModel.ChatPerson(ask)
    )

    fun convertToChatBotState(botAnswer: BotAnswer) = ChatViewState.ChatSuccess.ShowAnswers(
        ChatResultModel.ChatBot(botAnswer.botAnswer)
    )
}