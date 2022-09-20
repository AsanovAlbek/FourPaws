package epic.legofullstack.fourpaws.feature.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import epic.legofullstack.fourpaws.feature.home.domain.model.Pet
import epic.legofullstack.fourpaws.feature.home.domain.usecase.GetAllPetsUseCase
import javax.inject.Inject
import kotlinx.coroutines.launch

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