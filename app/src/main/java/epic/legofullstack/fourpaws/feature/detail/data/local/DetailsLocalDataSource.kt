package epic.legofullstack.fourpaws.feature.detail.data.local

import epic.legofullstack.fourpaws.core.domain.model.Area
import epic.legofullstack.fourpaws.feature.detail.data.model.PetDto
import epic.legofullstack.fourpaws.feature.detail.data.model.ShelterDto

class DetailsLocalDataSource {
    suspend fun fakePets() = fakeList
    private val fakeList = listOf(
        PetDto(
            petId = 1,
            name = "Мурка",
            city = "Москва",
            shelter = ShelterDto(
                id = 1,
                name = "Котопёс",
                address = "Москва, ул. Московская, дом 7",
                phone = "88005553535",
                email = "kotopes@mail.ru",
                area = Area(
                    id = 1,
                    title = "Москва"
                )
            ),
            isFavorite = false
        ),
        PetDto(
            petId = 2,
            name = "Тузик",
            city = "Нальчик",
            shelter = ShelterDto(
                id = 1,
                name = "Котопёс",
                address = "Нальчик, ул. Московская, дом 7",
                phone = "88005553535",
                email = "kotopes@mail.ru",
                area = Area(
                    id = 2,
                    title = "Нальчик"
                )
            ),
            isFavorite = false
        ),
        PetDto(
            petId = 3,
            name = "Мася",
            city = "Саратов",
            shelter = ShelterDto(
                id = 1,
                name = "Котопёс",
                address = "Саратов, ул. Московская, дом 7",
                phone = "88005553535",
                email = "kotopes@mail.ru",
                area = Area(
                    id = 3,
                    title = "Саратов"
                )
            ),
            isFavorite = false
        ),
        PetDto(
            petId = 4,
            name = "Рекс",
            city = "Ростов",
            shelter = ShelterDto(
                id = 1,
                name = "Котопёс",
                address = "Ростов, ул. Московская, дом 7",
                phone = "88005553535",
                email = "kotopes@mail.ru",
                area = Area(
                    id = 4,
                    title = "Ростов"
                )
            ),
            isFavorite = false
        ),
        PetDto(
            petId = 5,
            name = "Борис",
            city = "Краснодар",
            shelter = ShelterDto(
                id = 1,
                name = "Котопёс",
                address = "Краснодар, ул. Московская, дом 7",
                phone = "88005553535",
                email = "kotopes@mail.ru",
                area = Area(
                    id = 5,
                    title = "Краснодар"
                )
            ),
            isFavorite = false
        ),
        PetDto(
            petId = 6,
            name = "Симба",
            city = "Краснодар",
            shelter = ShelterDto(
                id = 1,
                name = "Котопёс",
                address = "Краснодар, ул. Московская, дом 7",
                phone = "88005553535",
                email = "kotopes@mail.ru",
                area = Area(
                    id = 5,
                    title = "Краснодар"
                )
            ),
            isFavorite = false
        ),
        PetDto(
            petId = 7,
            name = "Цезарь",
            city = "Краснодар",
            shelter = ShelterDto(
                id = 1,
                name = "Котопёс",
                address = "Краснодар, ул. Московская, дом 7",
                phone = "88005553535",
                email = "kotopes@mail.ru",
                area = Area(
                    id = 5,
                    title = "Краснодар"
                )
            ),
            isFavorite = false
        ),
        PetDto(
            petId = 8,
            name = "Граф",
            city = "Краснодар",
            shelter = ShelterDto(
                id = 1,
                name = "Котопёс",
                address = "Краснодар, ул. Московская, дом 7",
                phone = "88005553535",
                email = "kotopes@mail.ru",
                area = Area(
                    id = 5,
                    title = "Краснодар"
                )
            ),
            isFavorite = false
        )
    )
}