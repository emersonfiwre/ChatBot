package br.com.emersonfiwre.chatbot.model

sealed class ChatResultModel(val chatType: ChatTypeEnum) {
    data class ChatPerson(val ask: String?) : ChatResultModel(ChatTypeEnum.CHAT_PERSON)
    data class ChatBot(val answer: String?) : ChatResultModel(ChatTypeEnum.CHAT_BOT)
    object ChatLoading : ChatResultModel(ChatTypeEnum.CHAT_LOADING)
    object ChatError : ChatResultModel(ChatTypeEnum.CHAT_ERROR)
}
