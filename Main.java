import java.util.*;

public class Main {
    public static void main(String[] args) {

       
        WypozyczalniaSerwis serwis = WypozyczalniaSerwis.getInstance();
        WypozyczalniaSerwis serwis2 = WypozyczalniaSerwis.getInstance();
        serwis.zaladujDanePoczatkowe();
        
        // Stacja stacja1 = new Stacja("dupa");
        // Rower rower1 = new Rower(RowerTyp.STANDARD, true);
        // Rower rower2 = new Rower(RowerTyp.STANDARD, true);
        // stacja1.dodanieRoweru(rower1);
        // stacja1.dodanieRoweru(rower2);
        // Stacja stacja2 = new Stacja("dupa2");
        // Stacja stacja3 = new Stacja("dupa3");
    
        // serwis.getStacje().add(stacja1);
        // serwis.getStacje().add(stacja2);
        // serwis.getStacje().add(stacja3);
    

        //"nowy" od testow i dodanie do listy uzytkownikow 
        // Uzytkownik nowy = new Uzytkownik(0, "juan", "pablo", "a@b.c", "1234");
        // serwis.dodajUzytkownika(nowy);
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
                        System.out.println("Brak dostepnych rowerow w stacji");
                        break;
                    }
                    System.out.println("Dostepne rowery:");
                     for (Rower r : dostepneRowery) {
                        System.out.println("ID: " + r.getRowerId() + " | Typ: " + r.getTyp());
                    }
                    System.out.println("Podaj ID rowerów do wypozyczenia (oddzielone przecinkami, np. 1,3,4):");
                    String[] idWprowadzone = skaner.nextLine().split(",");
                    List<Rower> wypozyczone = new ArrayList<>();

                    for (String idStr : idWprowadzone) {
                        try {
                            int idRoweru = Integer.parseInt(idStr.trim());
                            Rower wybranyRower = null;
                            for (Rower r : dostepneRowery) {
                                if (r.getRowerId() == idRoweru) {
                                    wybranyRower = r;
                                    break;
                                }
                            }

                            if (wybranyRower != null) {
                                serwis.wypozyczRower(zalogowany, wybranaStacja, wybranyRower);
                                wypozyczone.add(wybranyRower);
                            } else {
                                System.out.println("Rower o ID " + idRoweru + " nie istnieje lub jest niedostepny.");
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("Nieprawidlowy format ID: " + idStr);
                        }
                    }

                    if (!wypozyczone.isEmpty()) {
                        System.out.println("Wypozyczyles nastepujace rowery:");
                        for (Rower r : wypozyczone) {
                            System.out.println("ID: " + r.getRowerId() + " | Typ: " + r.getTyp());
                        }
                    } else {
                        System.out.println("Nie wypozyczono zadnych rowerow.");
                    }

                    break;
                                    
                case 4:
                    // zwracanie wybranych rowerów
                    List<Wypozyczenie> aktywne = new ArrayList<>();
                    for (Wypozyczenie w : zalogowany.getHistoria()) {
                        if (w.getCzasKoniec() == null) {
                            aktywne.add(w);
                        }
                    }

                    if (aktywne.isEmpty()) {
                        System.out.println("Brak aktywnych wypożyczeń.");
                        break;
                    }

                    System.out.println("Aktywne wypożyczenia:");
                    for (Wypozyczenie w : aktywne) {
                        System.out.println("ID: " + w.getRower().getRowerId() + " | Typ: " + w.getRower().getTyp() + " | Stacja początkowa: " + w.getStacjaPoczatkowa().getLokalizacja());
                    }

                    System.out.println("Podaj ID rowerów do zwrotu (oddzielone przecinkami):");
                    String[] doZwrotu = skaner.nextLine().split(",");

                    System.out.println("Wybierz stację zwrotu:");
                    for (Stacja s : serwis.getStacje()) {
                        System.out.println(s.getLokalizacja());
                    }

                    String lokalizacjaZwrotu = skaner.nextLine();
                    Stacja stacjaZwrotu = null;
                    for (Stacja s : serwis.getStacje()) {
                        if (s.getLokalizacja().equalsIgnoreCase(lokalizacjaZwrotu)) {
                            stacjaZwrotu = s;
                            break;
                        }
                    }

                    if (stacjaZwrotu == null) {
                        System.out.println("Nie znaleziono stacji o podanej lokalizacji.");
                        break;
                    }

                    for (String idStr : doZwrotu) {
                        try {
                            int id = Integer.parseInt(idStr.trim());
                            // reczne wyszukiwanie konkretnego aktywnego wypozyczenia po rowerId
                            Wypozyczenie doZwrotuW = null;
                            for (Wypozyczenie w : aktywne) {
                                if (w.getRower().getRowerId() == id) {
                                    doZwrotuW = w;
                                    break;
                                }
                            }

                            if (doZwrotuW != null) {
                                serwis.zwrocRower(zalogowany, stacjaZwrotu, doZwrotuW.getRower());
                                System.out.println("Zwrócono rower ID: " + id);
                            } else {
                                System.out.println("Nie masz aktywnego wypożyczenia z rowerem ID: " + id);
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("Nieprawidłowy format ID: " + idStr);
                        }
                    }
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
