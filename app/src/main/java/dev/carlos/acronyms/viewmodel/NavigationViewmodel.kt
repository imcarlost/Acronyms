package dev.carlos.acronyms.viewmodel

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.carlos.core.navigation.NavigationEvent

class NavigationViewmodel : ViewModel() {

    val navigate = MutableLiveData<Pair<@IdRes Int, Bundle>>()

    fun onNavigationEvent(event: NavigationEvent) {
        when (event) {
            else -> throw NoWhenBranchMatchedException("Undefined navigation event parent")
        }
    }
}
