package dev.carlos.core.domain.network

import androidx.annotation.StringRes
import dev.carlos.core.R

enum class RequestError(@StringRes val message: Int) {
    NO_NETWORK(R.string.network_error_no_network),
    BAD_RESPONSE(R.string.network_error_bad_response),
    UNKNOWN_PROBLEM(R.string.network_error_unknown)
}
