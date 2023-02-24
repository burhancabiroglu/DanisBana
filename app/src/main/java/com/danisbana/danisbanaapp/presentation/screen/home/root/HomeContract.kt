package com.danisbana.danisbanaapp.presentation.screen.home.root


class HomeState

data class HomeActions(
    var routeConsultant: () -> Unit = {},
    var routeLogin: () -> Unit = {}
)
