# 🚀 Инструкция по запуску проекта

## **🔹 Клонирование репозитория**
Скачайте проект из GitHub:  
```sh
git clone https://github.com/MarinaSolovyeva/cs.xlsx_max
cd cs.xlsx_max
``` 
## Сборка проекта с помощью Maven
 ``` 
mvn clean package
```
# Запуск через консоль
Перейдите в директорию target, выполните команду
```
mvn spring-boot:run
```

# Запуск через docker

## Настройка docker-compose.yml
Откройте файл docker-compose.yml в текстовом редакторе и укажите путь к папке, где хранятся .xlsx файлы.
Найдите строку volumes и замените путь на ваш:
```  
volumes:
  - "D:/мой_каталог_с_файлами:/app/files"
```
## Запуск проекта в Docker
docker-compose up -d

# После запуска приложения перейдите в браузере:
👉 http://localhost:8001/swagger-ui/index.html
