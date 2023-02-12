package com.danisbana.danisbanaapp.presentation.screen.home.conversation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.toMutableStateList
import com.danisbana.danisbanaapp.R
import com.danisbana.danisbanaapp.core.model.MessageModel


/**
 * UI State that represents ConversationScreen
 **/
class ConversationState(
    val channelName: String,
    val channelMembers: Int,
    initialMessages: List<MessageModel>
) {
    private val _messages: MutableList<MessageModel> = initialMessages.toMutableStateList()
    val messages: List<MessageModel> = _messages

    fun addMessage(msg: MessageModel) {
        _messages.add(0, msg) // Add to the beginning of the list
    }
}

/**
 * Conversation Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class ConversationActions(
    val onClick: () -> Unit = {}
)

/**
 * Compose Utility to retrieve actions from nested components
 **/
val LocalConversationActions = staticCompositionLocalOf<ConversationActions> {
    error("{NAME} Actions Were not provided, make sure ProvideConversationActions is called")
}

@Composable
fun ProvideConversationActions(actions: ConversationActions, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalConversationActions provides actions) {
        content.invoke()
    }
}


val _initialMessages = listOf(
    MessageModel(
        "me",
        "Check it out!",
        "8:07 PM"
    ),
    MessageModel(
        "me",
        "Thank you!",
        "8:06 PM",
        R.drawable.sticker
    ),
    MessageModel(
        "Taylor Brooks",
        "You can use all the same stuff",
        "8:05 PM"
    ),
    MessageModel(
        "Taylor Brooks",
        "@aliconors Take a look at the `Flow.collectAsStateWithLifecycle()` APIs",
        "8:05 PM"
    ),
    MessageModel(
        "John Glenn",
        "Compose newbie as well, have you looked at the JetNews sample? Most blog posts end up " +
                "out of date pretty fast but this sample is always up to date and deals with async " +
                "data loading (it's faked but the same idea applies) \uD83D\uDC49" +
                "https://github.com/android/compose-samples/tree/master/JetNews",
        "8:04 PM"
    ),
    MessageModel(
        "me",
        "Compose newbie: I’ve scourged the internet for tutorials about async data loading " +
                "but haven’t found any good ones. What’s the recommended way to load async " +
                "data and emit composable widgets?",
        "8:03 PM"
    )
)