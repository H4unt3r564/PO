import java.util.*;

public class Main {
    public static void main(String[] args) {

       
        WypozyczalniaSerwis serwis = WypozyczalniaSerwis.getInstance();
        WypozyczalniaSerwis serwis2 = WypozyczalniaSerwis.getInstance();
        
        Stacja stacja1 = new Stacja("dupa");
        Rower rower1 = new Rower(RowerTyp.STANDARD, true);
        stacja1.dodanieRoweru(rower1);
        Stacja stacja2 = new Stacja("dupa2");
        Stacja stacja3 = new Stacja("dupa3");
        
        // stacja2.pobierzDostepneRowery();
        
        Uzytkownik user = new Uzytkownik(1, "Jan", "Kowalski", "e@d.c", "lol");

        
        serwis.getStacje().add(stacja1);
        serwis.getStacje().add(stacja2);
        serwis.getStacje().add(stacja3);
        
        System.out.println("Dostepne stacje:");
        for (Stacja s : serwis.getStacje()) {
            System.out.println(s);
        }
        
        System.out.println("Dostepne rowery STACJA 2:");
        for (Rower r : stacja2.pobierzDostepneRowery()) {
            System.out.println(r);
        }

        System.out.println("Dostepne rowery STACJA 1:");
        for (Rower r : stacja1.pobierzDostepneRowery()) {
            System.out.println(r);
        }

        
        user.wypozyczRower(rower1, stacja1, serwis);

       
        user.getHistoria().get(0).cofnijCzasStart(30);

       
        // user.getHistoria().get(0).ZakonczWypozyczenie(stacja1);

        
        System.out.println("\nHistoria:");
        for (Wypozyczenie w : user.getHistoria()) {
            System.out.println(w);
        }

        user.zwrocRower(stacja2, serwis);

        System.out.println("\nHistoria:");
        for (Wypozyczenie w : user.getHistoria()) {
            System.out.println(w);
        }

        //"nowy" od testow i dodanie do listy uzytkownikow 
        Uzytkownik nowy = new Uzytkownik(0, "juan", "pablo", "a@b.c", "1234");
        serwis.dodajUzytkownika(nowy);
        //skaner do pobierania z kalwiatury maila i hasla, czyZalogowany puste bo while nie dziala bez pustej zmiennej
        Scanner skaner = new Scanner(System.in);
        Uzytkownik zalogowany;
        //do-while z podawaniem maila i hasla i wywolujacy metode zaloguj z wypozyczalniaserwis; jesli jest zle to bedzie null i petla sie powtorzy
        do{
            System.out.println("LOGOWANIE");
            System.out.println("EMAIL: ");
            String email = skaner.nextLine();
            System.out.println("HASLO: ");
            String haslo = skaner.nextLine();
            zalogowany = serwis.zaloguj(email, haslo);
        }while(zalogowany == null);

        int opcja;
        do{
            System.out.println("MENU");
            System.out.println("1 - wypisz stacje");
            System.out.println("2 - wypisz rowery w konkretnej stacji");
            System.out.println("3 - wypozycz rower");
            System.out.println("4 - zwroc rower");
            System.out.println("5 - wypisz swoja historie");
            System.out.println("6 - skok w przyszlosc (testowanie czasu)");
            System.out.println("0 - wyjdz");

            opcja = skaner.nextInt();
            skaner.nextLine(); //usuwa znak nowej linii
            switch(opcja){
                case 1:
                    for (Stacja s: serwis.getStacje()){
                        System.out.println(s);
                    }
                    break;
                case 2:
                // narazie tak zostawie ale imo najlepiej dac to jako jedna opcja
                    for (Stacja s: serwis.getStacje()){
                        System.out.println(s + ": " + s.getRowery());
                    }
                    break;
                case 3:
                //wypozyczanie roweru. trzeba bedzie zrobic ze wybiera sobie jaki rower z jakiej stacji // zrobione
                    System.out.println("Wybierz stacje (podaj lokalizacje):");
                    for (Stacja s : serwis.getStacje()) {
                        System.out.println(s.getLokalizacja()); 
                    }

                    Stacja wybranaStacja = null;
                    String stacjaWybor = skaner.nextLine();
                    for (Stacja s : serwis.getStacje()) {
                        if (s.getLokalizacja().equalsIgnoreCase(stacjaWybor)) {
                            wybranaStacja = s;
                            break; 
                            }
                        }

                    if (wybranaStacja == null) {
                        System.out.println("Nie znaleziono stacji o podanej lokalizacji.");
                        break;
                    }
                    List<Rower> dostepneRowery = wybranaStacja.pobierzDostepneRowery();
                    if (dostepneRowery.isEmpty()){
                        System.out.println("Brak dostepnych rowerww w stacji");
                        break;
                    }
                    System.out.println("Dostepne rowery:");
                     for (Rower r : dostepneRowery) {
                        System.out.println("ID: " + r.getRowerId() + " | Typ: " + r.getTyp());
                    }
                    System.out.println("Podaj ID roweru, który chcesz wypozyczyc:");
                    int idRoweru = Integer.parseInt(skaner.nextLine());
                    Rower wybranyRower = null;

                    for (Rower r : dostepneRowery) {
                        if (r.getRowerId() == idRoweru) {
                            wybranyRower = r;
                            break;
                        }
                    }

                    if (wybranyRower == null) {
                        System.out.println("Nie znaleziono roweru o podanym ID.");
                        break;
                    }

                    serwis.wypozyczRower(zalogowany, wybranaStacja, wybranyRower);
                    System.out.println("Wypozyczyles rower o ID: " + wybranyRower.getRowerId());
                    break;
                                    
                case 4:
                //zwracanie roweru
                    zalogowany.zwrocRower(stacja3, serwis2);
                    break;
                case 5:
                //wypisywanie historii
                    zalogowany.wypisanieHistorii();
                    break;
                case 6:
                // przeskoczenie czasu (testowanie)
                //pobiera ostatnie nieskonczone wypozyczenie i przesuwa czas o godzine do przodu (czyli ostatnie w liscie)
                // if sprawdza czy historia jest pusta czy nie (moze sie wykrzaczyc)
                    if(!zalogowany.getHistoria().isEmpty()){
                        Wypozyczenie ostatnie = zalogowany.getHistoria().get(zalogowany.getHistoria().size() - 1);
                        ostatnie.cofnijCzasStart(60);
                    }
                    break;
                case 0:
                //ustawia zalogowanego na null i wychodzi z menu
                    zalogowany = null;
                    break;
                default:
                    System.out.println("Nieprawidlowa opcja. Sprobuj ponownie.");
            }
        }while(opcja != 0);
    }
}
