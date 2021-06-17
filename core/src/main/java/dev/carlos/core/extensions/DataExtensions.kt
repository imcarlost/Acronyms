package dev.carlos.core.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeNonNull(owner: LifecycleOwner, func: (T) -> Unit) {
    observe(owner, {
        it?.let {
            func(it)
        }
    })
}

fun Int.wasUpdated() = this > 0
