package epic.legofullstack.fourpaws.feature.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import epic.legofullstack.fourpaws.dispatchers.DispatchersModule
import epic.legofullstack.fourpaws.feature.home.domain.model.Pet
import epic.legofullstack.fourpaws.feature.home.domain.usecase.GetAllPetsUseCase
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val getAllPetsUseCase : GetAllPetsUseCase,
    @DispatchersModule.MainDispatcher private val mainDispatcher: CoroutineDispatcher
    ) : ViewModel() {

    private val _allPets = MutableLiveData<List<Pet>>()
    val allPets : LiveData<List<Pet>> get() = _allPets

    fun getAllPets() {
        viewModelScope.launch {
                val pets = getAllPetsUseCase()
                withContext(mainDispatcher) {
                    _allPets.postValue(pets)
                }
            }
    }
}