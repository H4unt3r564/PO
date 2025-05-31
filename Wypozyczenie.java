import java.util.*;
import java.util.concurrent.TimeUnit;

public class Wypozyczenie{

    private static int counter = 1; //automatyczne ID (static sprawia, ze kazde wypozyczenie robi wlasny counter)

    private int wypozyczenieId;
    private Rower rower;
    // private String rower;
    // private Uzytkownik uzytkownik;
    private String uzytkownik;
    // private Stacja stacjaStart;
    private String stacjaStart;
    // private Stacja stacjaKoniec;
    private String stacjaKoniec;
    private Date czasStart;
    private Date czasKoniec;
    private double cena;

    // public Wypozyczenie(Rower rowerWypozyczenie, Stacja stacjaStartWypozyczenie, Uzytkownik uzytkownikWypozyczenie){
    //     this.wypozyczenieId = counter++;
    //     this.rower = rowerWypozyczenie;
    //     this.stacjaStart = stacjaStartWypozyczenie;
    //     this.uzytkownik = uzytkownikWypozyczenie;
    //     this.czasStart = new Date();
    //     this.cena = 0.0;
    // }

    public Wypozyczenie(Rower rowerWypozyczenie, String stacjaStartWypozyczenie, String uzytkownikWypozyczenie){
        this.wypozyczenieId = counter++;
        this.rower = rowerWypozyczenie;
        this.stacjaStart = stacjaStartWypozyczenie;
        this.uzytkownik = uzytkownikWypozyczenie;
        this.czasStart = new Date();
        this.cena = 0.0;
    }

    public double obliczanieCeny(){
        long czasWMilisekundach = (this.czasKoniec.getTime() - this.czasStart.getTime());
        long czasWMinutach = TimeUnit.MILLISECONDS.toMinutes(czasWMilisekundach);
        switch(rower.typ){
            case STANDARD:
                return czasWMinutach * 5.0;
            case ELEKTRYCZNY:
                return czasWMinutach * 8.0;
            case CARGO:
                return czasWMinutach * 10.0;
            default:
                return czasWMinutach * 2.0;
        }
        
        // return czasWMinutach * 5.0;
    }

    public void zakonczWypozyczenie(){
        // this.stacjaKoniec = stacjaKoniec;
        this.stacjaKoniec = "koniec";
        this.czasKoniec = new Date();
        this.cena = obliczanieCeny();
        this.rower.czyDostepny = true;
    }

    @Override
    public String toString(){ //wypisywanie wypozyczenia (testy)
        return "Wypożyczenie #" + wypozyczenieId +
           " | Rower: " + rower.rowerId +
           " | Od: " + stacjaStart +
           " | Do: " + stacjaKoniec +
           " | Start: " + czasStart +
           " | Koniec: " + czasKoniec +
           " | Cena: " + cena + " zł";
    }

    public void cofnijCzasStart(int minutes) { //testowanie zmiana czasu
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.czasStart);
        cal.add(Calendar.MINUTE, -minutes);
        this.czasStart = cal.getTime();
    }


}
