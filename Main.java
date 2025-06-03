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
    }
}
