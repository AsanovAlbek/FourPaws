package epic.legofullstack.fourpaws.feature_home_page.data.storage

import epic.legofullstack.fourpaws.feature_home_page.data.model.PetDto

/**
 * Класс для работы с внутренним хранилищем данных
 * Пока что здесь фейковые данные, в дальнейшем здесь будет работа с базой данных
 *
 * @author Asanov Albek in 16.09.2022
 */
class LocalDataSource {
    /** Метод для получения фейковых данных в виде флоу */
    suspend fun fakeData() = fakeList

    // Какие то фейковые данные
    private val fakeList = listOf(
        PetDto(1, "Мурка", false),
        PetDto(2, "Тузик"),
        PetDto(3, "Мася"),
        PetDto(4, "Рекс"),
        PetDto(5, "Борис")
    )
}