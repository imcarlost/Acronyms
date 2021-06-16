package dev.carlos.core.navigation

class NavigationRouter : NavigationController {
    private var onNavigationEvent: (NavigationEvent) -> Unit = {}

    override fun sendNavigation(event: NavigationEvent) {
        onNavigationEvent(event)
    }

    fun setOnNavigationEvent(listener: (NavigationEvent) -> Unit) {
        onNavigationEvent = listener
    }
}
