package epic.legofullstack.fourpaws.feature_home_page.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import epic.legofullstack.fourpaws.feature_home_page.domain.model.Pet
import epic.legofullstack.fourpaws.feature_home_page.domain.usecase.GetAllPetsUseCase
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val getAllPetsUseCase : GetAllPetsUseCase
    ) : ViewModel() {

    private val _allPets = MutableLiveData<List<Pet>>()
    val allPets : LiveData<List<Pet>> get() = _allPets

    fun getAllPets() {
        viewModelScope.launch {
                val pets = getAllPetsUseCase()
                _allPets.postValue(pets)
            }
    }
}