package br.com.emersonfiwre.chatbot.view.viewholder

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import br.com.emersonfiwre.chatbot.R

class BotViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    private val txtRobotMessage: AppCompatTextView

    init {
        txtRobotMessage = itemView.findViewById(R.id.id_txt_robot_message)
    }

    fun bind(answer: String?) {
        txtRobotMessage.text = answer
    }
}