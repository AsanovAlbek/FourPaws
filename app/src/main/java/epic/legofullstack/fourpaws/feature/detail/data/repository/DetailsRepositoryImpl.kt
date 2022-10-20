package epic.legofullstack.fourpaws.feature.detail.data.repository

import android.util.Log
import epic.legofullstack.fourpaws.feature.detail.data.model.PetDto
import epic.legofullstack.fourpaws.feature.detail.data.model.ShelterDto
import epic.legofullstack.fourpaws.feature.detail.domain.model.Shelter
import epic.legofullstack.fourpaws.feature.detail.domain.repository.DetailsRepository

class DetailsRepositoryImpl: DetailsRepository {
    /** Добавление [pet] во вкладку "Избранное" */
    override suspend fun addToFavorite(pet: PetDto): PetDto
    {
        Log.i("detail", "${pet.name} добавлен в избранное")
        return pet.addToFavorites()
    }

    /** Удаление [pet] из вкладки "Избранное" */
    override suspend fun removePetFromFavorite(pet: PetDto): PetDto {
        return pet.removeToFavorites()
    }

    /** Звонок в приют */
    override suspend fun call(shelter: ShelterDto) {
        Log.i("detail", "Звоним на номер ${shelter.phone}")
    }

    /** Отправка письма в приют */
    override suspend fun sendMale(shelter: ShelterDto) {
        Log.i("detail", "Пишем сообщение на почту ${shelter.email}")
    }

    /** Поделиться [pet] */
    override suspend fun sharePet(pet: PetDto) {
        Log.i("detail", "Делимся ${pet.name} с друзьями")
    }
}