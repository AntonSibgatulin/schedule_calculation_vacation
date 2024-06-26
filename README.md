# Калькулятор отпускных

Это приложение представляет собой микросервис на базе SpringBoot и Java 11, который предоставляет API для расчета отпускных.

## Endpoints

### GET /api/v1/calculate

#### Описание

Этот эндпоинт принимает параметры для расчета отпускных и возвращает объект с информацией о сумме, которую получит сотрудник.

#### Параметры запроса

- averageSalary (необязательно): средняя зарплата за 12 месяцев (тип: число, формат: число с плавающей запятой, по умолчанию: 1000000.0)
- numberOfDays (необязательно): количество дней отпуска (тип: целое число)

#### Ответ

- averageSalary: средняя зарплата за 12 месяцев
- vacationAllowance: сумма отпускных, которую получит сотрудник
- numberOfDays: количество дней отпуска

#### Примеры

Запрос:

```http request
GET /api/v1/calculate?averageSalary=5000&numberOfDays=20
```


Ответ:

```json
{
"averageSalary": 5000.0,
"vacationAllowance": 833.33,
"numberOfDays": 20
}
```


### GET /api/v1/calculate/date

#### Описание

Этот эндпоинт принимает параметры для расчета отпускных с учетом указанных дат начала и окончания отпуска и возвращает объект с информацией о сумме, которую получит сотрудник.

#### Параметры запроса

- averageSalary (необязательно): средняя зарплата за 12 месяцев (тип: число, формат: число с плавающей запятой, по умолчанию: 1000000.0)
- vacationDates (необязательно): перечисление дат начала и окончания отпуска через запятую в формате "ГГГГ-ММ-ДД" (тип: строковый)

#### Ответ

- averageSalary: средняя зарплата за 12 месяцев
- vacationDates: перечисление дат начала и окончания отпуска
- vacationAllowance: сумма отпускных, которую получит сотрудник
- numberOfDay: количество дней отпуска

#### Примеры

Запрос:

```http request
GET /api/v1/calculate/date?averageSalary=5000&vacationDates=2024-07-01,2024-07-15
```


Ответ:

```json
{
"averageSalary": 5000.0,
"vacationDates": [
"2022-07-01",
"2022-07-15"
],
"vacationAllowance": 705.56,
"numberOfDay": 15
}
```


## Установка и запуск

1. Склонируйте репозиторий на свой локальный компьютер.
2. Откройте проект в вашей среде разработки.
3. Убедитесь, что у вас установлен Java 11 и Gradle.
4. Запустите приложение.

```shell
$ gradle bootRun
```


Приложение будет запущено на http://localhost:8080.

