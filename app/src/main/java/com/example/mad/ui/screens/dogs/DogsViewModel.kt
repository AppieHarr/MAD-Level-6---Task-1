package com.example.mad.ui.screens.dogs

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mad.data.api.util.Resource
import com.example.mad.data.model.Cat
import com.example.mad.data.model.Dog
import com.example.mad.ui.repository.CatsRepository
import com.example.mad.ui.repository.DogsRepository
import kotlinx.coroutines.launch

class DogsViewModel(application: Application) : AndroidViewModel(application)  {
    private val dogsRepository = DogsRepository()

    /**
     * Expose non MutableLiveData via getter
     * errorText can be observed from Activity for error showing
     * Encapsulation :)
     */
    val dogResource: LiveData<Resource<Dog>>
        get() = _dogResource

    //initialize it with an Empty type of Resource
    private val _dogResource: MutableLiveData<Resource<Dog>> = MutableLiveData(Resource.Empty())

    /**
     * The viewModelScope is bound to Dispatchers.Main and will automatically be cancelled when the ViewModel is cleared.
     * Extension method of lifecycle-viewmodel-ktx library
     */
    fun getPictureDog() {
        //set resource type to loading
        _dogResource.value = Resource.Loading()

        viewModelScope.launch {
            _dogResource.value = dogsRepository.getRandomDog()
        }
    }
}