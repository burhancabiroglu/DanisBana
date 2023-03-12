package com.danisbana.danisbanaapp.presentation.screen.home.root


import com.danisbana.danisbanaapp.presentation.components.bottomnav.BottomNavState

class HomeState(
    val isAdmin:Boolean = false,
    val bottomNavState:BottomNavState = BottomNavState()
)

data class HomeActions(
    var routeConsultant: () -> Unit = {},
    var routeLogin: () -> Unit = {},
    var routeConversation: (messageId: String) -> Unit = {}
)
