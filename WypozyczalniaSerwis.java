import java.util.*;

public class WypozyczalniaSerwis {

    //singleton
    private static WypozyczalniaSerwis instancja;
        
    private List<Stacja> stacje = new ArrayList<>();
    private List<Wypozyczenie> wypozyczenia = new ArrayList<>();
        
    private WypozyczalniaSerwis() {}
    public static WypozyczalniaSerwis getInstance() {
        if (instancja == null) {
            instancja = new WypozyczalniaSerwis();
        }
        return instancja;
    }
    
    public List<Stacja> getStacje() {
    return stacje;
    }


    public boolean wypozyczRower(Uzytkownik uzytkownik, Stacja stacja, Rower rower){
        if (!rower.isCzyDostepny()) {
            System.out.println("Rower jest już wypożyczony, nie można go wypożyczyć.");
            return false;
        }
    
        rower.zablokuj();
        Wypozyczenie wypozyczenie = new Wypozyczenie(rower, stacja, uzytkownik);
        wypozyczenia.add(wypozyczenie);
        uzytkownik.dodajDoHistorii(wypozyczenie);
        return true;
        }
}

