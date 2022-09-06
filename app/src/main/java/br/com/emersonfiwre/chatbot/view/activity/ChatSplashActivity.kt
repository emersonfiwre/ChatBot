package br.com.emersonfiwre.chatbot.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import br.com.emersonfiwre.chatbot.R
import br.com.emersonfiwre.chatbot.viewmodel.ChatSplashViewModel
import br.com.emersonfiwre.chatbot.viewmodel.viewstate.ChatSplashViewState
import com.airbnb.lottie.LottieAnimationView

class ChatSplashActivity : AppCompatActivity() {

    private lateinit var lottieAnimation: LottieAnimationView
    private lateinit var viewModel: ChatSplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupViewModel()
        setupObservers()
        viewModel.setupDelaySplashScreen()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(ChatSplashViewModel::class.java)
    }

    private fun setupObservers() {
        viewModel.chatSplashViewState.observe(this, { state ->
            when (state) {
                ChatSplashViewState.ShowChat -> {
                    MainActivity.newInstance(this)
                    finish()
                }
            }
        })
    }
}