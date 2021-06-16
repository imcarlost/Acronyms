package dev.carlos.core.navigation

interface NavigationEvent

interface NavigationController {
    fun sendNavigation(event: NavigationEvent)
}
