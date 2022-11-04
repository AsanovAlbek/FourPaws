package epic.legofullstack.fourpaws.feature.detail.domain.usecase

import epic.legofullstack.fourpaws.core.di.DispatchersModule
import epic.legofullstack.fourpaws.feature.detail.data.mapper.toPetDto
import epic.legofullstack.fourpaws.feature.detail.data.mapper.toShelterDto
import epic.legofullstack.fourpaws.feature.detail.domain.model.Pet
import epic.legofullstack.fourpaws.feature.detail.domain.model.Shelter
import epic.legofullstack.fourpaws.feature.detail.domain.repository.DetailsRepository
import epic.legofullstack.fourpaws.network.errorhandle.safeCall
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/** UseCase для работы с deep link*/
class DeeplinkUseCase @Inject constructor(
    private val repository: DetailsRepository,
    @DispatchersModule.IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun callToShelter(shelter: Shelter) = withContext(ioDispatcher) {
        return@withContext safeCall { repository.call(shelter.toShelterDto()) }
    }

    suspend fun sendMailToShelter(shelter: Shelter) = withContext(ioDispatcher) {
        return@withContext safeCall { repository.sendMale(shelter.toShelterDto()) }
    }

    suspend fun sharePetInSocial(pet: Pet) = withContext(ioDispatcher) {
        return@withContext safeCall { repository.sharePet(pet.toPetDto()) }
    }
}