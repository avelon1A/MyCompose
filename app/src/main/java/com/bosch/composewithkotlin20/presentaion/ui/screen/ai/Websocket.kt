import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bosch.composewithkotlin20.presentaion.ui.viewModel.WebSocketViewModel
import kotlinx.serialization.Serializable

@Composable
fun Websocket(viewModel: WebSocketViewModel) {
    var text by remember { mutableStateOf("") }
    val messages by viewModel.messages.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("WebSocket Messages", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(messages) { message ->
                Text(message, modifier = Modifier.padding(vertical = 4.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Enter message") },
                modifier = Modifier.weight(2f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (text.isNotBlank()) {
                    viewModel.sendMessage(messageText = text)
                    text = ""
                }
            }) {
                Text("Send")
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
    }

    LaunchedEffect(Unit) {
        viewModel.connect()
    }
}


@Serializable
object Websocket