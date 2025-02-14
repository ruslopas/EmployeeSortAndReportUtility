# EmployeeSortandReportUtility

Программа создана в рамках тестового задания для **KoronaTech Shift Courses**.

---

## Технологии
В разработке были использованы:
- **Java 17.0.14**
- Система сборки **Gradle**
- Библиотека **picocli**
  [Ссылка на библиотеку](https://mvnrepository.com/artifact/info.picocli/picocli)

---

## Инструкция по сборке

### Сборка в IntelliJ IDEA

1. **Открытие проекта**
   - Нажмите **Open** (Открыть) на стартовом экране или выберите **File > Open** в меню.
   - Найдите директорию с проектом (`EmployeeSortandReportUtility`), выберите её и нажмите **OK**.
   - IntelliJ IDEA автоматически распознает проект и начнёт его индексацию.
   - Дождитесь завершения синхронизации. В этот момент Gradle загрузит все зависимости, указанные в `build.gradle`.

2. **Сборка JAR-файла**
   - Откройте панель **Gradle** (обычно справа). Если её нет, включите её через **View > Tool Windows > Gradle**.
   - В панели Gradle найдите раздел с именем вашего проекта (например, `EmployeeSortandReportUtility`).
   - Разверните раздел **Tasks > build**.
   - Дважды щёлкните на задачу **jar**. Gradle выполнит сборку JAR-файла.

3. **Открытие JAR-файла**
   - После успешной сборки JAR-файл будет создан в папке `build/libs` внутри вашего проекта.

---

### Запуск JAR-файла

1. Откройте терминал или командную строку.
2. Перейдите в папку, где находится JAR-файл:
   ```bash
   cd path/to/project/build/libs
   
3. Запустите JAR-файл с помощью команды:
  ```bash
     java -jar EmployeeSortandReportUtility-1.0.jar <pathToFile> <options>

