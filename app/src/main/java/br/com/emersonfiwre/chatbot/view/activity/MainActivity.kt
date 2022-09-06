package br.com.emersonfiwre.chatbot.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.emersonfiwre.chatbot.R
import br.com.emersonfiwre.chatbot.model.ChatResultModel
import br.com.emersonfiwre.chatbot.view.adapter.ChatAdapter
import br.com.emersonfiwre.chatbot.viewmodel.MainViewModel
import br.com.emersonfiwre.chatbot.viewmodel.factory.MainViewModelFactory
import br.com.emersonfiwre.chatbot.viewmodel.viewstate.ChatViewState
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerChat: RecyclerView
    private lateinit var buttonSend: FloatingActionButton
    private lateinit var editMessage: AppCompatEditText

    private lateinit var chatAdapter: ChatAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setupViewModel()
        setupViews()
        setupRecycler()
        setupListeners()
        setupObserver()
        setupObserverLoading()
        setupObserverError()
    }

    private fun setupViewModel() {
        val factory = MainViewModelFactory(this.application)
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    private fun setupViews() {
        recyclerChat = findViewById(R.id.id_recycler_chat)
        buttonSend = findViewById(R.id.id_btn_send_ask)
        editMessage = findViewById(R.id.id_edit_message)
    }

    private fun setupRecycler() {
        chatAdapter = ChatAdapter(mutableListOf())
        val linearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerChat.apply {
            layoutManager = linearLayoutManager
            itemAnimator = DefaultItemAnimator()
            adapter = chatAdapter
        }
    }

    private fun setupListeners() {
        buttonSend.setOnClickListener {
            setupSend()
        }
        editMessage.setOnEditorActionListener { v, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    setupSend()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun setupSend() {
        viewModel.getBotAnswer(editMessage.text.toString())
        editMessage.text?.clear()
    }

    private fun setupObserver() {
        viewModel.chatViewState.observe(this, { state ->
            when (state) {
                is ChatViewState.ChatSuccess.ShowAnswers -> {
                    chat.add(state.answer)
                    chatAdapter.setupAddAnswer(state.answer)
                    recyclerChat.scrollToPosition(chat.size - 1)
                }
                is ChatViewState.ChatSuccess.ShowAsk -> {
                    chat.add(state.ask)
                    chatAdapter.setupAddAsk(state.ask)
                    recyclerChat.scrollToPosition(chat.size - 1)
                }
            }
        })
    }

    private fun setupObserverLoading() {
        viewModel.chatLoadingViewState.observe(this, { state ->
            when (state) {
                ChatViewState.ChatLoading.ShowHideLoading -> {
                    chatAdapter.setupRemoveLoading()
                }
                ChatViewState.ChatLoading.ShowLoading -> {
                    chatAdapter.setupRobotLoading()
                }
            }
        })
    }

    private fun setupObserverError() {
        viewModel.chatErrorViewState.observe(this, { state ->
            when (state) {
                ChatViewState.ChatError.ShowEmptyAsk -> {
                    //Toast.makeText(this, "Show Empty Ask", Toast.LENGTH_SHORT).show()
                    //chatAdapter.setupRobotError()
                }
                ChatViewState.ChatError.ShowError -> {
                    //Toast.makeText(this, "Show Error", Toast.LENGTH_SHORT).show()
                    chatAdapter.setupRobotError()
                }
            }
        })
    }

    companion object {
        private val chat = mutableListOf<ChatResultModel>()

        @JvmStatic
        fun newInstance(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}