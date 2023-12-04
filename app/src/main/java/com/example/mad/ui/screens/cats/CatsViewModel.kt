package com.example.mad.ui.screens.cats

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mad.data.api.util.Resource
import com.example.mad.data.model.Cat
import com.example.mad.ui.repository.CatsRepository
import kotlinx.coroutines.launch

class CatsViewModel(application: Application) : AndroidViewModel(application) {
    private val catsRepository = CatsRepository()

    /**
     * Expose non MutableLiveData via getter
     * errorText can be observed from Activity for error showing
     * Encapsulation :)
     */
    val catResource: LiveData<Resource<Cat>>
        get() = _catResource

    //initialize it with an Empty type of Resource
    private val _catResource: MutableLiveData<Resource<Cat>> = MutableLiveData(Resource.Empty())

    /**
     * The viewModelScope is bound to Dispatchers.Main and will automatically be cancelled when the ViewModel is cleared.
     * Extension method of lifecycle-viewmodel-ktx library
     */
    fun getPictures() {

        _catResource.value = Resource.Loading()

        viewModelScope.launch {
            _catResource.value = catsRepository.getRandomCat()
        }
    }


}