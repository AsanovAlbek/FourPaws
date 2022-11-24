package epic.legofullstack.fourpaws.feature.home.data.storage

import epic.legofullstack.fourpaws.core.data.model.PetFilterDto
import epic.legofullstack.fourpaws.feature.home.data.mapper.toHome
import epic.legofullstack.fourpaws.feature.home.data.model.HomePetDto
import epic.legofullstack.fourpaws.network.firebase.data.FirebaseDataSource
import epic.legofullstack.fourpaws.network.firebase.data.model.PetDto
import epic.legofullstack.fourpaws.network.firebase.data.model.PetPreviewDto

/**
 * Класс для работы с внутренним хранилищем данных
 * Пока что здесь фейковые данные, в дальнейшем здесь будет работа с базой данных
 *
 * @author Asanov Albek in 16.09.2022
 */
class HomePageDataSource(
    private val remoteSource: FirebaseDataSource
) {
    suspend fun getPetsByArea(areaId: Int): List<HomePetDto> =
        remoteSource.getObjectsByFieldName(
            fieldName = FIELD_AREA_ID,
            value = areaId,
            nameCollection = PET_COLLECTION,
            resultClassName = PetDto::class.java,
        ).map { it.toHome() }

    companion object {
        private const val FIELD_AREA_ID = "areaId"
        private const val PET_COLLECTION = "pet"
    }

    suspend fun getPetsByFilter(areaId: Int, petFilter: PetFilterDto): List<PetPreviewDto> =
        remoteSource.petFilter(areaId = areaId, filter = petFilter)
}