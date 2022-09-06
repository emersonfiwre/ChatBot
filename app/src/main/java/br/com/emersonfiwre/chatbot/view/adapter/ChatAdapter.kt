package br.com.emersonfiwre.chatbot.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.emersonfiwre.chatbot.R
import br.com.emersonfiwre.chatbot.model.ChatResultModel
import br.com.emersonfiwre.chatbot.model.ChatTypeEnum
import br.com.emersonfiwre.chatbot.view.viewholder.BotViewHolder
import br.com.emersonfiwre.chatbot.view.viewholder.ErrorViewHolder
import br.com.emersonfiwre.chatbot.view.viewholder.PersonViewHolder

class ChatAdapter(
    private val chat: MutableList<ChatResultModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (ChatTypeEnum.values()[viewType]) {
            ChatTypeEnum.CHAT_BOT -> BotViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.chatbot_robot_holder, parent, false)
            )
            ChatTypeEnum.CHAT_PERSON -> PersonViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.chatbot_person_holder, parent, false)
            )
            ChatTypeEnum.CHAT_LOADING -> ErrorViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.chatbot_loading_holder, parent, false)
            )
            ChatTypeEnum.CHAT_ERROR -> ErrorViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.chatbot_error_holder, parent, false)
            )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val chaType = chat[position]) {
            is ChatResultModel.ChatBot -> {
                (holder as BotViewHolder).bind(chaType.answer)
            }
            is ChatResultModel.ChatPerson -> {
                (holder as PersonViewHolder).bind(chaType.ask)
            }
            ChatResultModel.ChatError -> {
                (holder as ErrorViewHolder)
            }
            ChatResultModel.ChatLoading -> {
                (holder as ErrorViewHolder)
            }
        }
    }

    override fun getItemCount(): Int = chat.size

    override fun getItemViewType(position: Int): Int = chat[position].chatType.ordinal

    fun setupAddAnswer(answer: ChatResultModel.ChatBot) {
        chat.add(answer)
        notifyItemChanged(chat.size)
    }

    fun setupAddAsk(ask: ChatResultModel.ChatPerson) {
        chat.add(ask)
        notifyItemChanged(chat.size)
    }

    fun setupRobotLoading() {
        chat.add(ChatResultModel.ChatLoading)
        notifyItemChanged(chat.size)
    }

    fun setupRemoveLoading() {
        val itemLoading = chat.find { it.chatType == ChatTypeEnum.CHAT_LOADING }
        chat.remove(itemLoading)
        notifyDataSetChanged()
    }

    fun setupRobotError() {
        chat.add(ChatResultModel.ChatError)
        notifyItemChanged(chat.size)
    }
}