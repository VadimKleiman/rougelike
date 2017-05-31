# Документация
### Сведения об игре
Игра представляет собой RPG с видом сверху вниз и управлением одним персонажем.
### Основные правила
Основное действие игры происходит на одной локации. Игрок управляет одним игровым персонажем способным перемещаться по локации. Персонаж передвигается при помощи стрелочных клавиш. Для захвата руны используется клавиша `A`.

Задача игрока - очистить локацию от вражеских мобов. Каждый моб атакует всякого, кто встает у него на пути. Игрок может упростить битвы с врагами и подбирать магические артефакты (руны).

Игра заканчивается либо победой главного героя, либо его смертью.
### Базовые понятия
#### Локация
Локация генерируется случайно. Представляет собой прямоугольное поле на котором расположены разлиные объекты. Центр вида обычно находится там, где стоит главный герой.

Локация состоит из тайлов и объектов.
**Тайл** - это 1 символ в терминале. Тайлы образуют сетку. Между собой никак не взаимодействуют.
Тип тайла:
* Стена
* Проход

**Объект** - это активные объекты, которые раполагаются в произвольном месте на локации. Объекты делятся на два типа:
* Статические(руны)
* Динамические(игрок и враги)

#### Объекты
##### Игрок
**Изображение:** @

**Цвет**: белый

**Атака:** 20

**Защита:** 5

**Здоровье:** 100

**Описание:** Вооружен магическим артефактом. У артефакта есть 10 слотов для вставки магических рун. В каждый слот можно вставить только одну руну, после чего достать ее уже невозможно. Показатели всех рун суммируются. Способен пробивать стены.
##### Орк
**Изображение:** o

**Цвет:** зеленый

**Атака:** 40

**Защита:** 20

**Здоровье:** 200

**Описание:** Способен пробивать стены.
##### Троль
**Изображение:** t

**Цвет:** синий

**Атака:** 20

**Защита:** 15

**Здоровье:** 70

**Описание:** Особых способностей нет.
##### Зомби
**Изображение:** z

**Цвет:** серый

**Атака:** 10

**Защита:** 5

**Здоровье:** 60

**Описание:** Особых способностей нет.
##### Вампир
**Изображение:** v

**Цвет:** красный

**Атака:** 70

**Защита:** 50

**Здоровье:** 500

**Описание:** Восстанавливает часть здоровья, пропорционально нанесенному урону. Повышенная скорость перемешения.
##### Руны
Руны делятся на три типа:
* Руна силы
* Руна защиты
* Руна здоровья

Характеристики каждой руны выбираются случайно.
#### Правила боя
Бой между двумя объектами происходит в ситуации когда оба пытаются занять одну и ту же позицию на локации. Нанесенный урон вычисляется по формуле:

урон = макс{0, атака - защита_соперника} * случайный_коэффициент + 1
#### Пользователи
1. Иванов Иван
* Возраст: 35
* Пол: мужской
* Образование: ПТУ
* Особенности использования игры: скачал игру, поиграл 5 минут, удалил игру, пошел работать на завод.
2. Смирнова Надежда
* Возраст: 10
* Пол: женский
* Образование: -
* Особенности использования игры: возможно запустит игру пару раз. Особой тяги возвращаться в игровой мир у нее нет.
3. Чижиков Антон
* Возраст: 20
* Пол: мужской
* Образование: среднее
* Особенности использования игры: запускает игру на парах. Вне университета про нее даже не вспоминает.

Для них будут полезны следующие фишки:
* Более красочный графический интерфейс
* Система рейтингов
* Возможность сохранения игрового процесса
####Architectural drivers
Класс ApplicationMain - точка входа в программу
Интерфейс Screen - содержит методы для отрисовки сцены и обработки нажатия клавиш.
Класс StartScreen - описывает стартовый экран игры, наследуется от интерфейса Screen.
Класс LoseScreen - описывает экран в случае смерти персонажа. Наследуется от интерфейса Screen.
Класс WinScreen - описывает экран в случае выйгрыша персонажа. Наследуется от интерфейса Screen.
Класс PlayScrenn - описывает экран во время игрового процесса. Наследуется от интерфейса Screen.
Класс Attributes - содержит характеристики моба и игрового персонажа.
Класс Point - содержит местоположение объектов на карте.
Класс Mob - абстрактный класс, описывающий базовые реализации методов, общие для всех персонажей.
Класс Player - описывает игрового персонажа. Наследуется от абстрактного класса Mob.
Класс Orc, Troll, Zombie, Vampire - описывает поведение мобов управляемых компьютером. Наследуются от абстрактного класса Mob.
Класс Tile - описывает ячейку на карте. Может быть либо проходом, либо стеной.
Класс World - класс описывающий игровую локацию. Знает о всех игровых объектах. Определяет коллизии.
Класс WorldBuilder - класс конструирующий игровой мир. Реализует паттерн Builder.
Класс MobFactory - фабрика для содания мобов. Реализует паттерн Factory.
Класс ItemFactory - фабрика для создания вещей. Реализует паттерн Factory.
Класс Item - описывает item.
Класс Inventory - описывает инвентарь.
