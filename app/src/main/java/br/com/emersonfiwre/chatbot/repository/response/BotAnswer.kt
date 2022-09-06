package br.com.emersonfiwre.chatbot.repository.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BotAnswer(
    @SerializedName("answer")
    val botAnswer: String? = null
) : Parcelable
