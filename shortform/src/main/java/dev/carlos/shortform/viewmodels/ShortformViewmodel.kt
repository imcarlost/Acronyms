package dev.carlos.shortform.viewmodels

import androidx.lifecycle.MutableLiveData
import dev.carlos.core.domain.network.RequestError
import dev.carlos.core.domain.network.RequestState
import dev.carlos.core.viewmodel.RxViewModel
import dev.carlos.shortform.data.models.ShortformModel
import dev.carlos.shortform.domain.GetShortformDefinition
import retrofit2.HttpException
import java.net.UnknownHostException

class ShortformViewmodel(
    private val getAcronymsDefinition: GetShortformDefinition
) : RxViewModel() {

    val acronymDefinition = MutableLiveData<RequestState>()

    fun fetchAcronymDefinition(acronym: String) {
        val disposable = getAcronymsDefinition.getShortformDefinition(acronym)
            .doOnSubscribe { acronymDefinition.postValue(RequestState.Loading) }
            .subscribe(::handleSuccess, ::handleError)
        compositeDisposable.add(disposable)
    }

    private fun handleSuccess(definition: ShortformModel) {
        acronymDefinition.postValue(RequestState.Success(definition))
    }

    private fun handleError(exception: Throwable) {
        when (exception) {
            is NoSuchElementException -> acronymDefinition.postValue(RequestState.Empty)
            is UnknownHostException -> acronymDefinition.postValue(RequestState.Error(RequestError.NO_NETWORK))
            is HttpException -> acronymDefinition.postValue(RequestState.Error(RequestError.BAD_RESPONSE))
            else -> acronymDefinition.postValue(RequestState.Error(RequestError.UNKNOWN_PROBLEM))
        }
    }
}