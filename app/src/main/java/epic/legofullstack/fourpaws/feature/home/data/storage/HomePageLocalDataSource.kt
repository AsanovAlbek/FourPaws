package epic.legofullstack.fourpaws.feature.home.data.storage

import epic.legofullstack.fourpaws.feature.home.data.model.PetDto

/**
 * Класс для работы с внутренним хранилищем данных
 * Пока что здесь фейковые данные, в дальнейшем здесь будет работа с базой данных
 *
 * @author Asanov Albek in 16.09.2022
 */
class HomePageLocalDataSource {
    /** Метод для получения фейковых данных в виде флоу */
    suspend fun fakeData() : List<PetDto> = fakeList

    // Какие то фейковые данные
    private val fakeList = listOf(
        PetDto(1, "Мурка", city = "Москва"),
        PetDto(2, "Тузик", city = "Нальчик"),
        PetDto(3, "Мася", city = "Саратов"),
        PetDto(4, "Рекс", city = "Ростов"),
        PetDto(5, "Борис", city = "Краснодар"),
        PetDto(6, "Симба", city = "Краснодар"),
        PetDto(7, "Цезарь", city = "Краснодар"),
        PetDto(8, "Граф", city = "Краснодар")
    )
}