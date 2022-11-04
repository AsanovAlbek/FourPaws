package epic.legofullstack.fourpaws.feature.detail.data.repository

import android.util.Log
import epic.legofullstack.fourpaws.feature.detail.data.local.DetailsLocalDataSource
import epic.legofullstack.fourpaws.feature.detail.data.model.PetDto
import epic.legofullstack.fourpaws.feature.detail.data.model.ShelterDto
import epic.legofullstack.fourpaws.feature.detail.domain.model.Shelter
import epic.legofullstack.fourpaws.feature.detail.domain.repository.DetailsRepository
import java.net.SocketException

class DetailsRepositoryImpl(
    private val local: DetailsLocalDataSource
): DetailsRepository {
    override suspend fun findPetById(id: Int): PetDto {
        return local.fakePets().first { petDto -> petDto.petId == id }
    }


    /** Добавление [pet] во вкладку "Избранное" */
    override suspend fun addToFavorite(pet: PetDto) {
        local.fakePets().toMutableList().apply {
            // Находим индекс элемента, если такого элемента нет в списке, то добавляем в конец
            val petIndex = indexOf(pet)
            if (petIndex != -1) {
                removeAt(petIndex)
                add(petIndex, pet.addToFavorites())
            }
        }
    }

    /** Удаление [pet] из вкладки "Избранное" */
    override suspend fun removePetFromFavorite(pet: PetDto) {
        local.fakePets().toMutableList().apply {
            // Находим индекс элемента, если такого элемента нет в списке, то добавляем в конец
            val petIndex = indexOf(pet)
            if (petIndex != -1) {
                remove(pet)
                add(petIndex, pet.removeToFavorites())
            }
        }
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