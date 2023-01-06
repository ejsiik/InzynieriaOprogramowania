# KtoGdzieKiedy backend

Backend dla KtoGdzieKiedy napisany w TypeScript, wykorzystujący jako środowisko NodeJS.

## Uruchomienie projektu:
1. Za pierwszym razem trzeba wpisać `npm install`, żeby zainstalować wymagane biblioteki
2. Następnie wpisujemy `npm start` i projekt się włączy.

# API Endpointy
## `POST /login`
* Dokonuje autoryzacji użytkownika.
* Body zapytania:
  ```json
    {
        "login": "<login:String>",
        "password": "<hasło:String>
    }
  ```
* Odpowiedź 200 - OK:
  ```json
    {
        "token": "<token_jwt_do_autoryzacji:String>"
    }
  ```
* Odpowiedź 400, 401 - Bad Request, Unauthorized:
  ```json
    {
        "error": "Błędne dane."
    }
  ```

## `GET /me`
* Zwróci obecnie zalogowanego użytkownika.
* ***Wymagana autoryzacja***
* Odpowiedź 200 - OK:
  ```json
    {
        "user": {
            "id": "<id:Int>",
            "login": "<login:String>"
        }
    }
  ```
* Odpowiedź 401 - Unauthorized:
    ```json
    {
	    "error": "Operacja wymaga zalogowania."
    }
    ```

## `POST /tasks`
* Doda nowe zadanie do wykonania
* ***Wymagana autoryzacja***
* Body zapytania:
  ```json
    {
        "category": "<kategoria:String>",
        "name": "<nazwa_taska:String>"
    }
  ```
* Odpowiedź 201 - Created:
  ```json
    {
        "task": {
            "name": "<nazwa_taska>:String",
            "category": "<kategoria>:String",
            "userId": "<id_użytkownika:Int>",
            "endTime": null,
            "id": "<id_taska:Int>",
            "createdAt": "<data_i_godzina_w_ISO8601:String>"
        }
    }
  ```
* Odpowiedź 400 - Bad Request:
  ```json
    {
        "error": "Błędne dane."
    }
  ```
* Odpowiedź 401 - Unauthorized:
  ```json
    {
	    "error": "Operacja wymaga zalogowania."
    }
  ```

## `GET /running-tasks`
* Zwróci wszystkie taski aktualne wykonywane przez zalogowanego użytkownika
* ***Wymagana autoryzacja***
* Odpowiedź 200 - OK:
  ```json
    {
        "tasks": [
            {
                "name": "<nazwa_taska>:String",
                "category": "<kategoria>:String",
                "userId": "<id_użytkownika:Int>",
                "endTime": null,
                "id": "<id_taska:Int>",
                "createdAt": "<data_i_godzina_w_ISO8601:String>"
            }
        ]
    }
  ```
* Odpowiedź 401 - Unauthorized:
  ```json
    {
        "error": "Operacja wymaga zalogowania."
    }
  ```

# Autoryzacja
Autoryzacja działa w sposób następujący:
1. Wykonujemy zapytanie POST na login podając login i hasło, otrzymuje token
2. Token przy każdym zapytaniu wymagającym autoryzacji umieszczamy w nagłówku `Authorization: Bearer <token>`:
    ```json
        "Authorization": "Bearer <token>"
    ```

# Wykorzystane biblioteki
* express - biblioteka do łatwiejszej budowy API pod NodeJS
* express-async-errors - łatka na expressa, pozwalająca na łatwiejszą obsługę błędów z funkcji asynchronicznych
* morgan - biblioteka do logowania zapytań docierających na backend
* typeorm - Framework do obsługi baz danych
* sqlite3 - sterownik dla bazy danych SQLite
* class-transformer - biblioteka do transformowania danych, wykluczania właściwości z klas etc.
* bcrypt - biblioteka do hashowania haseł za pomocą algorytmu bcrypt.
* jsonwebtoken - biblioteka do tworzenia tokenów weryfikacji, de facto standard, jeżeli chodzi o autoryzacyjne tokeny.
* reflect-metadata - biblioteka wymaga przez TypeORM
