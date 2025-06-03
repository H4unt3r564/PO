public class Main {
    public static void main(String[] args) {

       
        WypozyczalniaSerwis serwis = WypozyczalniaSerwis.getInstance();
        
        Stacja stacja1 = new Stacja("dupa");
        Rower rower1 = new Rower(RowerTyp.STANDARD, true);
        stacja1.dodanieRoweru(rower1);

        
        Uzytkownik user = new Uzytkownik(1, "Jan", "Kowalski", "e@d.c", "lol");

        
        serwis.getStacje().add(stacja1);

        
        System.out.println("Dostepne rowery:");
        for (Rower r : stacja1.pobierzDostepneRowery()) {
            System.out.println(r);
        }

        
        user.wypozyczRower(rower1, stacja1, serwis);

       
        user.getHistoria().get(0).cofnijCzasStart(30);

       
        user.getHistoria().get(0).zakonczWypozyczenie();

        
        System.out.println("\nHistoria:");
        for (Wypozyczenie w : user.getHistoria()) {
            System.out.println(w);
        }
    }
}
