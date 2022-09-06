package br.com.emersonfiwre.chatbot.repository

import android.app.Application
import br.com.emersonfiwre.chatbot.infrastructure.ChatBotApi
import br.com.emersonfiwre.chatbot.repository.response.BotAnswer
import retrofit2.await

class ChatBotRepositoryImpl(private val apiClient: ChatBotApi) :
    ChatBotRepository {
    override suspend fun getBotAnswers(ask: String): BotAnswer {
        return apiClient.getBotAnswers(ask).await()
/*        Em caso de pegar um mock do assets
        return if (isMock) {
            Gson().fromJson(
                BufferedReader(
                    InputStreamReader(application.assets.open("mock/chatbot_mock.json"))
                ), BotAnswer::class.java
            )
        } else {
            apiClient.getBotAnswers(ask).await()
        }*/
    }
}