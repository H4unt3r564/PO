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
    this.userId = userId;
    this.imie = imie;
    this.nazwisko = nazwisko;
    this.email = email;
    this.haslo = haslo;
    this.historia = new ArrayList<>();
    }

    //wypisanie historii
    public void wypisanieHistorii(){
        if (historia.isEmpty()){
            System.out.println("Brak historii wypozyczen.");
        }
        else{
            for(int i = 0; i < historia.size(); i++ ){
                System.out.print("Wypozyczenie #" + (i+1) );
            }
        }
    }
    //wypozyczenie 
    public void wypozyczRower(Rower rower, Stacja stacja, WypozyczalniaSerwis serwis){
        serwis.wypozyczRower(this, stacja, rower);
    }
    //zwrot
    public void zwrocRower(Stacja stacja, WypozyczalniaSerwis serwis) {
        serwis.zwrocRower(this, stacja);
    }

    public void dodajDoHistorii(Wypozyczenie wypozyczenie) {
        historia.add(wypozyczenie);
    }

}