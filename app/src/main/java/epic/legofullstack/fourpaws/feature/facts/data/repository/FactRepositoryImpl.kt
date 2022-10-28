package epic.legofullstack.fourpaws.feature.facts.data.repository

import epic.legofullstack.fourpaws.feature.facts.data.model.FactDto
import epic.legofullstack.fourpaws.feature.facts.data.model.FactPreviewDto
import epic.legofullstack.fourpaws.feature.facts.domain.repository.FactRepository

//TODO получать данные извне
class FactRepositoryImpl : FactRepository {
    override suspend fun getFacts(): List<FactPreviewDto> {
        return listOf(
            FactPreviewDto(
                111,
                "https://travelask.ru/system/images/files/000/156/181/wysiwyg/3.jpg?1486469640",
                "fact 1"
            ),
            FactPreviewDto(
                222,
                "https://travelask.ru/system/images/files/000/156/181/wysiwyg/3.jpg?1486469640",
                "fact 2"
            ),
            FactPreviewDto(
                333,
                "https://travelask.ru/system/images/files/000/156/190/wysiwyg/11.jpg?1486469653",
                "fact 3"
            ),
            FactPreviewDto(
                444,
                "https://images11.domashnyochag.ru/upload/img_cache/c57/c57c3a443ef426d5f61b1dadc3f7ea5a_cropped_1182x1182.jpg",
                "fact 4"
            )
        )
    }

    override suspend fun getFactById(factId: Int): FactDto {
        return FactDto(
            111,
            "https://travelask.ru/system/images/files/000/156/181/wysiwyg/3.jpg?1486469640",
            "fact 1",
            """
                Многие хозяева уверены, что их четвероногим питомцам требуется кальций, который они получат в молоке. Это заблуждение, 
                т.к. доказано, что у этих животных непереносимость лактозы, которая в последующем приведет к проблемам кишечника и диарее.
                 Для получения кальция лучше давать кисломолочные продукты, и то не часто.
                Рецепторы языка кошки не чувствуют сладкого вкуса.
                Почки животных устроены так, что они могут употреблять для питья соленую морскую воду.
                Кошки чистоплотные животные, которые тратят четверть своего времени по уходу за собой.
                При попадании в легкие сигаретного дыма животное может заболеть серьезным заболеванием, от астмы до рака органов.
                С помощью усов кошки определяют чье-либо движение уже на расстоянии.
                С помощью определенных звуков животное проявляет свои чувства и эмоции. Например, при рокоте оно показывает просьбу, 
                при урчании ­– свое подчинение и довольство, шипением предупреждает агрессию и показывает враждебность, пронзительные вопли означают боль.
                
                СОБАКИ
                Нюх собаки развит так, что она может учуять запах за несколько сотен метров.
                Оказывается, при объятиях с хозяином собаки чувствуют доминирование над собой, а не проявление теплых отношений.
                Собаки, как кошки, могут проявлять свои чувства и эмоции с помощью звука. При хриплых стонах она чувствует опасность или испытывает боль, громкий лай означает хорошее настроение и довольство.
                Четвероногим питомцам категорически запрещено давать шоколад. Содержащийся в составе сладости теобромин представляет для животного опасность, так как считается ядом.
            """.trimIndent()
        )
    }
}