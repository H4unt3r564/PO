import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WypozyczalniaSerwis {

    //singleton
    private static WypozyczalniaSerwis instancja;
        
    private List<Stacja> stacje = new ArrayList<>();
    private List<Wypozyczenie> wypozyczenia = new ArrayList<>();
    private List<Uzytkownik> uzytkownicy = new ArrayList<>(); //lista uzytkownikow potrzebna do logowania (aby leciec po liscie tak jak w zwrocRower)
        
    private WypozyczalniaSerwis() {}
    public static WypozyczalniaSerwis getInstance() {
        if (instancja == null) {
            instancja = new WypozyczalniaSerwis();
        }
        return instancja;
    }
    
    //dodanie usera do listy userow
    public void dodajUzytkownika(Uzytkownik user){
        uzytkownicy.add(user);
    }

    public Uzytkownik zaloguj(String email, String haslo){
        for (Uzytkownik u : uzytkownicy) {
            if(email.equals(u.getEmail()) && haslo.equals(u.getHaslo())){
                System.out.println("Pomyslnie zalogowano!");
                return u;
            }
        }
        System.out.println("Nie udalo sie zalogowac. Sprobuj ponownie.");
        return null;
    }

    public List<Stacja> getStacje() {
        return stacje;
    }


    public void wypozyczRower(Uzytkownik uzytkownik, Stacja stacja, Rower rower){
        if (!rower.isCzyDostepny()) {
            System.out.println("Rower jest już wypożyczony, nie można go wypożyczyć.");
        }

        rower.zablokuj();
        stacja.usunRower(rower);
        Wypozyczenie wypozyczenie = new Wypozyczenie(rower, stacja, uzytkownik);
        wypozyczenia.add(wypozyczenie);
        uzytkownik.dodajDoHistorii(wypozyczenie);
        }
        
    // public void ZwrocRower(Uzytkownik uzytkownik, Stacja stacja, Rower rower){
    //     rower.odblokuj();
    //     Wypozyczenie ZakonczWypozyczenie = new ZakonczWypozyczenie(stacja);
    //     Wypozyczenie.ZakonczWypozyczenie(stacja);
    //     uzytkownik.dodajDoHistorii(ZakonczWypozyczenie);
        
    // }

    public void zwrocRower(Uzytkownik uzytkownik, Stacja stacja, Rower rower) {
        // Znajdź aktywne wypożyczenie tego roweru przez tego użytkownika
        for (Wypozyczenie wyp : wypozyczenia) {
            if (wyp.getUzytkownik().getUserId() == uzytkownik.getUserId() && wyp.getRower().getRowerId() == rower.getRowerId() && wyp.getCzasKoniec() == null) {
                wyp.ZakonczWypozyczenie(stacja);
                rower.odblokuj(); // rower dostępny z powrotem
                stacja.dodanieRoweru(rower); // opcjonalnie, jeśli Stacja ma listę rowerów
                return;
            }
        }
        System.out.println("Nie znaleziono aktywnego wypożyczenia dla podanego roweru i użytkownika.");
    }
    
    public void zaladujDanePoczatkowe() {
        zaladujStacje();
        zaladujRowery();
        zaladujUzytkownikow();
    }

    private void zaladujStacje() {
        try (BufferedReader br = new BufferedReader(new FileReader("stacje.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                stacje.add(new Stacja(line.trim()));
            }
        } catch (IOException e) {
            // Domyślne stacje jeśli plik nie istnieje
            stacje.add(new Stacja("Centrum"));
            stacje.add(new Stacja("Dworzec Główny"));
            stacje.add(new Stacja("Park Miejski"));
        }
    }

    private void zaladujRowery() {
        try (BufferedReader br = new BufferedReader(new FileReader("rowery.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    int stacjaId = Integer.parseInt(parts[0].trim());
                    RowerTyp typ = RowerTyp.valueOf(parts[1].trim().toUpperCase());
                    boolean dostepny = Boolean.parseBoolean(parts[2].trim());
                    
                    Rower rower = new Rower(typ, dostepny);
                    Stacja stacja = znajdzStacjePoId(stacjaId);
                    if (stacja != null) {
                        stacja.dodanieRoweru(rower);
                    }
                }
            }
        } catch (IOException e) {
            // Domyślne rowery jeśli plik nie istnieje
            if (!stacje.isEmpty()) {
                stacje.get(0).dodanieRoweru(new Rower(RowerTyp.STANDARD, true));
                stacje.get(0).dodanieRoweru(new Rower(RowerTyp.ELEKTRYCZNY, true));
                stacje.get(1).dodanieRoweru(new Rower(RowerTyp.STANDARD, true));
                stacje.get(1).dodanieRoweru(new Rower(RowerTyp.CARGO, true));
            }
        }
    }

    private void zaladujUzytkownikow() {
        try (BufferedReader br = new BufferedReader(new FileReader("uzytkownicy.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    int userId = Integer.parseInt(parts[0].trim());
                    String imie = parts[1].trim();
                    String nazwisko = parts[2].trim();
                    String email = parts[3].trim();
                    String haslo = parts[4].trim();
                    
                    uzytkownicy.add(new Uzytkownik(userId, imie, nazwisko, email, haslo));
                }
            }
        } catch (IOException e) {
            // Domyślny użytkownik jeśli plik nie istnieje
            uzytkownicy.add(new Uzytkownik(1, "Jan", "Kowalski", "jan@kowalski.pl", "haslo123"));
        }
    }

    private Stacja znajdzStacjePoId(int stacjaId) {
        for (Stacja s : stacje) {
            if (s.getStacjaId() == stacjaId) {
                return s;
            }
        }
        return null;
    }
}



