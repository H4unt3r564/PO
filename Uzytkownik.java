import java.util.*;

public class Uzytkownik{
    int userId;
    String imie;
    String nazwisko;
    String email;
    String haslo;
    List<Wypozyczenie> historia;

    //gettery
    public int getUserId(){
        return  userId;
    }
    public String getImie(){
        return imie;
    }
    public String getNazwisko(){
        return nazwisko;
    }
    public String getEmail(){
        return email;
    }
    public String getHaslo(){
        return haslo;
    }

    public List<Wypozyczenie> getHistoria(){
        return historia;
    }

    //settery
    public void setImie(String imie){
        this.imie = imie;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }
    public void setNazwisko(String nazwisko){
        this.nazwisko = nazwisko;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setHaslo(String haslo){
        this.haslo = haslo;
    }
    public void setHistoria(List<Wypozyczenie> historia){
        this.historia = historia;
    }

    //konstruktor
    public Uzytkownik(int userId, String imie, String nazwisko, String email, String haslo) {
    // Walidacja email. Nie mozna bez postaci cos@cos.cos (wszystkie litery i ich ilosc dozwolone oprzocz znakow typu spacja, kropka itp)
    //Czyli e@d.c przejdzie ale np e@d."spacja" juz nie
    // jak jest zle to wywali blad (ewentualnie do poprawy/zmiany)
    if (email == null || !email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9.-]+$")) {
        throw new IllegalArgumentException("Nieprawidłowy adres e-mail.");
    }

    this.userId = userId;
    this.imie = imie;
    this.nazwisko = nazwisko;
    this.email = email;
    this.haslo = haslo;
    this.historia = new ArrayList<>();
    }

    //wypisanie historii
    //poprawiona metoda wypisywania historii usera
    public void wypisanieHistorii(){
        if (historia.isEmpty()){
            System.out.println("Brak historii wypozyczen.");
        }
        else{
            for (Wypozyczenie h: historia){
                    System.out.println(h);
            }
        }
    }
    
    //wypozyczenie 
    public void wypozyczRower(Rower rower, Stacja stacja, WypozyczalniaSerwis serwis){
        serwis.wypozyczRower(this, stacja, rower);
    }


    public Wypozyczenie getAktywneWypozyczenie() {
        for (Wypozyczenie w : historia) {
            if (w.getCzasKoniec() == null) {
                return w;
            }
        }
        return null;
    }

    //zwrot
    public void zwrocRower(Stacja stacja, WypozyczalniaSerwis serwis) {
        Wypozyczenie aktywne = getAktywneWypozyczenie();
        if (aktywne != null) {
            Rower rower = aktywne.getRower();
            serwis.zwrocRower(this, stacja, rower);
        } else {
            System.out.println("Brak aktywnego wypożyczenia.");
        }
    }


    public void dodajDoHistorii(Wypozyczenie wypozyczenie) {
        historia.add(wypozyczenie);
    }

}
