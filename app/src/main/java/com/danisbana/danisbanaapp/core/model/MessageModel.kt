package com.danisbana.danisbanaapp.core.model

import com.danisbana.danisbanaapp.R
import javax.annotation.concurrent.Immutable

@Immutable
data class MessageModel(
    val author: String,
    val content: String,
    val timestamp: String,
    val image: Int? = null,
    val authorImage: Int = if (author == "me") R.drawable.ali else R.drawable.someone_else
)