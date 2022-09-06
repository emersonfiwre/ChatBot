package br.com.emersonfiwre.chatbot.infrastructure

import br.com.emersonfiwre.chatbot.infrastructure.Constants.URL_BOT
import br.com.emersonfiwre.chatbot.repository.response.BotAnswer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ChatBotApi {
    @GET(URL_BOT)
    fun getBotAnswers(@Query("ask") ask: String): Call<BotAnswer>
}