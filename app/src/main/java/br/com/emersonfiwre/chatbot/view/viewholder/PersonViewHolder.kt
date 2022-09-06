package br.com.emersonfiwre.chatbot.view.viewholder

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import br.com.emersonfiwre.chatbot.R

class PersonViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    private val txtPersonMessage: AppCompatTextView

    init {
        txtPersonMessage = itemView.findViewById(R.id.id_txt_person_message)
    }

    fun bind(ask: String?) {
        txtPersonMessage.text = ask
    }
}