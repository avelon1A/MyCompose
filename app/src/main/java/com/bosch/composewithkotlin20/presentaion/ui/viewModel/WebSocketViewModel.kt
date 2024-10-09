package com.bosch.composewithkotlin20.presentaion.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class WebSocketViewModel : ViewModel() {
    private val _messages = MutableStateFlow<List<String>>(emptyList())
    val messages: StateFlow<List<String>> = _messages


    private lateinit var webSocket: WebSocket

    private val client = OkHttpClient()

    fun connect() {
        Log.d(TAG, "Connecting to WebSocket...")

        val request = Request.Builder()
            .url("ws://spring.up.railway.app/hello")
            .build()

        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                Log.d(TAG, "WebSocket connection opened")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                Log.d(TAG, "Message received: $text")
                _messages.value += text
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                Log.d(TAG, "Message received 1: ${bytes.hex()}")
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                Log.d(TAG, "WebSocket closing: $reason")
                webSocket.close(1000, null)
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                Log.d(TAG, "WebSocket closed: $reason")
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                Log.e(TAG, "WebSocket error: ${t.message}", t)
            }
        })
    }

    fun sendMessage(name: String = "android", messageText: String) {
        Log.d(TAG, "Sending message: $messageText from $name")
        val messageObject = ChatMessage(name, messageText)
        val gson = Gson()
        val jsonMessage = gson.toJson(messageObject)
        webSocket.send(jsonMessage)
    }


    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "WebSocketViewModel cleared, closing connection")
        client.dispatcher.executorService.shutdown()
    }

    companion object {
        private const val TAG = "WebSocketViewModel"
    }
}

data class ChatMessage(
    val name: String,
    val message: String,
)