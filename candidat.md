

Плохое описание задания, в жизни это был бы поход к аналитику или создателю данной задачи.

    ⦁ Возможность запросить список событий с информацией о площадке (Venue).
    ⦁ Возможность запросить список площадок (Venue) с информацией о последних событиях (Events) на этой площадке.
что тут имеется в виду, запросить список, какие параметры запроса?

Возможность создать новое событие, указав `venue-referenceId`, `name`, `startTime` и `endTime` через `mutation`.

    - прям напрашивается вместо `venue-referenceId` - список `venue-referenceId`

у меня наверно проф деформация, нужен контекст:

    - это новое приложение и уже действующие.
    - наличие инструментов на проекте (наличие докера на ранерах гитлаба или что там по CI)

по коду:

    - нет валидации данных
    - нет обработчика ошибок
    - БД плохо нормализована, зачем сложный ключ?
    - много мусорного кода и файлов
    - нету тестов
    - нету анализатора кода
    - просутствуют явные ошибки и бизнес ошибки
    - наличие ApplicationEventPublisher тут явно лишнее, когда есть прямой доступ к сервису

!!! И стоит не заставлять форкать проект, ведь видно же и решения других кандидатов. 