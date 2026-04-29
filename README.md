# TodoList App

Учебный проект-приложение для управления задачами, построенное по принципам Clean Architecture.

---

## Screenshots

| Список задач | Детальный экран | Добавление задачи | Настройка цвета |
|---|---|---|---|
| <img width="430" height="963" alt="image" src="https://github.com/user-attachments/assets/44699a14-7720-46d7-9f11-36a63d182f70" /> | <img width="438" height="964" alt="image" src="https://github.com/user-attachments/assets/1e65d5c9-cf5d-4ba7-a47d-a223ac91631a" /> | <img width="416" height="919" alt="image" src="https://github.com/user-attachments/assets/9da9e8b5-f514-4fee-a428-896d8988caa1" /> | <img width="427" height="946" alt="image" src="https://github.com/user-attachments/assets/2216281e-d8b4-417b-b8b0-665a1e48af7b" /> | 

---

## Функционал

### Экран списка задач
- Отображение всех задач из локальной БД (Room)
- Чекбокс для отметки задачи как выполненной
- Выполненные задачи меняют цвет фона (настраивается)
- Клик на задачу — переход на детальный экран
- Кнопка добавления новой задачи
- Удаление старой задачи

### Детальный экран
- Отображение заголовка, описания и статуса задачи
- Редактирование задачи
- Кнопка «Назад»

### Настройки
- Switcher в `TopAppBar` в главном списке — включить/выключить цветной фон выполненных задач
- Выбранное значение сохраняется через `DataStore`

---

## Данные
- Используется библиотека-обертка над SQLite — Room
- Поддерживаются операции добавления, редактирования и удаления задач

---

## Технологический стек

| Категория | Технологии |
|---|---|
| Язык | Kotlin |
| UI | Jetpack Compose |
| Навигация | Navigation Compose |
| Архитектура | MVVM, Clean Architecture |
| DI | Koin |
| БД | Room (SQLite) |
| Настройки | DataStore |
| Сборка | Gradle |
