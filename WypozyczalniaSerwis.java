import java.util.*;

public class WypozyczalniaSerwis {
    private List<Stacja> stacje = new ArrayList<>();
    private List<Wypozyczenie> wypozyczenia = new ArrayList<>();

    public void wypozyczRower(Uzytkownik uzytkownik, Stacja stacja, Rower rower){
        rower.zablokuj(); // nie wiem czy podaawac jaki rower

        wypozyczenie wypozyczenie = new Wypozyczenie(rower, stacja, uzytkownik);
        wypozyczenia.add(wypozyczenie);

        uzytkownik.dodajDoHistorii(wypozyczenie);

        //zwrot to chuj wie jak zrobic loool, nie chce mi sie teraz kminic
    }
}
