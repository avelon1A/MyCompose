package com.bosch.composewithkotlin20.presentaion.ui.viewModel

import android.util.Log
import com.bosch.composewithkotlin20.data.model.data.Message
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.ws
import io.ktor.http.HttpMethod
import io.ktor.websocket.CloseReason
import io.ktor.websocket.DefaultWebSocketSession
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.runBlocking

class WebSocketManager(uri: String) {
    private val client = HttpClient {
        install(WebSockets)
    }

    private var webSocketSession: DefaultWebSocketSession? = null
    private val messageListener = mutableListOf<(Message) -> Unit>()

    init {
        connect(uri)
    }

    private fun connect(uri: String) {
        runBlocking {
            client.ws(method = HttpMethod.Get, host = uri) {
                webSocketSession = this
                Log.d("WebSocket", "Connected")

                // Listen for incoming messages
                for (message in incoming) {
                    when (message) {
                        is Frame.Text -> {
                            val messageData =
                                Gson().fromJson(message.readText(), Message::class.java)
                            messageListener.forEach { it(messageData) }
                        }
                        // Handle other frame types if needed
                        else -> Log.d("WebSocket", "Received a non-text message: $message")
                    }
                }
            }
        }
    }

    suspend fun sendMessage(name: String, message: String) {
        val messageObject = Message(name, message)
        webSocketSession?.send(Frame.Text(Gson().toJson(messageObject)))
            ?: Log.e("WebSocket", "WebSocket not initialized. Cannot send message.")
    }

    fun addMessageListener(listener: (Message) -> Unit) {
        messageListener.add(listener)
    }

    fun close() {
        runBlocking {
            webSocketSession?.close(CloseReason(CloseReason.Codes.NORMAL, "Client closed"))
            client.close()
        }
    }
}
