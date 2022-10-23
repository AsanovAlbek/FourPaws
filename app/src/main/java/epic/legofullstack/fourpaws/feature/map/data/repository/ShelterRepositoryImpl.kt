package epic.legofullstack.fourpaws.feature.map.data.repository

import epic.legofullstack.fourpaws.core.domain.model.Area
import epic.legofullstack.fourpaws.feature.map.data.model.ShelterDto
import epic.legofullstack.fourpaws.feature.map.domain.repository.ShelterRepository

//TODO получать данные извне
class ShelterRepositoryImpl: ShelterRepository {
    override suspend fun getShelterByAreaId(areaId: Int): List<ShelterDto> {
        return listOf(
            ShelterDto(1, "Краснодог", "г. Краснодар, ул. Куйбышева, 2", Area(1, "Краснодарский край"), "+7-988-505-2-505", "krasnodog_official@mail.ru", 45.030330f, 39.009754f),
            ShelterDto(2, "Ключ Добра", "г. Горячий Ключ 353292, ул. Ленина, 37", Area(1, "Краснодарский край"), "+7-991-076-94-04", "fond2019@rambler.ru", 44.627600f, 39.107195f),
            ShelterDto(3, "Зоогеленджик", "г. Геленджик улица Лютенко, 31", Area(1, "Краснодарский край"), "+7 (918) 067-90-66", null, 44.565244f, 38.100974f)
        )
    }
}