System Wypożyczania Rowerów
===========================

1. Opis projektu

Projekt przedstawia prosty system wypożyczalni rowerów napisany w języku Java, z wykorzystaniem zasad programowania obiektowego.

2. Funkcjonalności:
- Rejestracja i zarządzanie użytkownikami
- Dodawanie rowerów do stacji
- Wypożyczanie i zwracanie rowerów
- Obliczanie kosztów wypożyczenia
- Śledzenie historii wypożyczeń

3. Struktura klas
- `Rower`: zawiera pola `id`, `typ`, `dostepny` oraz metody pomocnicze
- `Stacja`: przechowuje listę rowerów, umożliwia dodawanie i pobieranie rowerów
- `Uzytkownik`: reprezentuje użytkownika, jego dane i historię wypożyczeń
- `Wypozyczenie`: przechowuje czas rozpoczęcia i zakończenia oraz koszt
- `WypozyczalniaSerwis`: logika aplikacji, zarządzanie wypożyczeniami
- `Main`: klasa główna uruchamiająca aplikację z przykładowymi danymi

4. Struktura plików

Wszystkie pliki powinny znajdować się w tym samym katalogu. Nie należy używać podfolderów ani pakietów.
Lista wymaganych plików:

- Main.java
- Rower.java
- RowerTyp.java
- Stacja.java
- WypozyczalniaSerwis.java
- Wypozyczenie.java
- Uzytkownik.java
- rowery.txt
- stacje.txt
- użytkownicy.txt

5. Jak uruchomić

- Wejdź do katalogu z plikami projektu,
- W terminalu wpisz:
	"javac *.java" w celu skompilowania wszystkich plików
	"Java Main" w celu uruchomienia programu

Program powinien wystartować i wczytać dane z plików tekstowych, a następnie powinien pojawić się proces logowania.


