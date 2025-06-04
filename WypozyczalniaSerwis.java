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
}

