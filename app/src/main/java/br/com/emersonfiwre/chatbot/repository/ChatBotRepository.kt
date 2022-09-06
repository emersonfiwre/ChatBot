package br.com.emersonfiwre.chatbot.repository

import br.com.emersonfiwre.chatbot.repository.response.BotAnswer

interface ChatBotRepository {
    suspend fun getBotAnswers(ask: String) : BotAnswer
}