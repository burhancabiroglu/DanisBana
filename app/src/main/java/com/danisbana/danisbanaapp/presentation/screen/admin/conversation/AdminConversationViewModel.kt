package com.danisbana.danisbanaapp.presentation.screen.admin.conversation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danisbana.danisbanaapp.core.model.message.Answer
import com.danisbana.danisbanaapp.core.model.message.MessageEntity
import com.danisbana.danisbanaapp.core.model.message.MessageStatus
import com.danisbana.danisbanaapp.domain.repo.AdminRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AdminConversationViewModel @Inject constructor(
    private val adminRepo: AdminRepo,
): ViewModel() {
    private val _stateFlow: MutableStateFlow<AdminConversationState> = MutableStateFlow(AdminConversationState())
    val stateFlow: StateFlow<AdminConversationState> = _stateFlow.asStateFlow()

    fun setStateArgs(args: AdminConversationArgs?) {
        args?:return
        viewModelScope.launch {
            _stateFlow.value.loadingState.show()
            val result  = reloadMessage(args.messageId).getOrNull()
            _stateFlow.value.channelName = result?.title.toString()
            _stateFlow.value.message = result
            _stateFlow.value.loadingState.hide()
        }

    }


    fun rejectMessage(function: ()->Unit) {
        viewModelScope.launch {
            val message = _stateFlow.value.message?: return@launch
            _stateFlow.value.loadingState.show()
            adminRepo.updateMessageStatusAsync(message.id,MessageStatus.REJECTED).await()
            _stateFlow.value.message = reloadMessage(messageId = message.id).getOrNull()
            _stateFlow.value.loadingState.hide()
            delay(400)
            function.invoke()
        }
    }

    fun confirmMessage(function: ()->Unit) {
        viewModelScope.launch {
            val message = _stateFlow.value.message?: return@launch
            _stateFlow.value.loadingState.show()
            adminRepo.updateMessageStatusAsync(message.id,MessageStatus.ACCEPTED).await()
            _stateFlow.value.message = reloadMessage(messageId = message.id).getOrNull()
            _stateFlow.value.loadingState.hide()
            delay(400)
            function.invoke()

        }
    }

    fun answerMessage(text:String) {
        viewModelScope.launch {
            val message = _stateFlow.value.message?: return@launch
            val date = Date()
            val answer = Answer(
                timestamp = date.time,
                content = text
            )
            adminRepo.updateMessageStatusAsync(message.id,MessageStatus.ANSWERED).await()
            _stateFlow.value.message?.answer = answer
            _stateFlow.value.message?.statusOrdinal = MessageStatus.ANSWERED.ordinal
            adminRepo.answerMessageAsync(message.id,answer).await()
            _stateFlow.value.message = reloadMessage(messageId = message.id).getOrNull()
        }
    }

    private suspend fun reloadMessage(messageId:String): Result<MessageEntity> {
        return adminRepo.reloadMessageAsync(messageId).await()
    }
}