import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Stacja {
    private int stacjaId;
    private String lokalizacja;
    private List<Rower> rowery;
    private static int counter = 1;

    public Stacja(String lokalizacja) {
        this.stacjaId = counter++;
        this.lokalizacja = lokalizacja;
        this.rowery = new ArrayList<>();
    }

    // Gettery
    public int getStacjaId() {
        return stacjaId;
    }

    public String getLokalizacja() {
        return lokalizacja;
    }

    public List<Rower> getRowery() {
        return rowery;
    }

    // Settery
    public void setLokalizacja(String lokalizacja) {
        this.lokalizacja = lokalizacja;
    }

    // Metody
    public void dodanieRoweru(Rower rower) {
        rowery.add(rower);
    }

    public List<Rower> pobierzDostepneRowery() {
        return rowery.stream()
                .filter(Rower::isCzyDostepny)
                .collect(Collectors.toList());
    }

    public boolean czyJestMiejsce() {
        // Załóżmy, że stacja może pomieścić maksymalnie 10 rowerów
        return rowery.size() < 10;
    }

    public boolean usunRower(Rower rower) {
        return rowery.remove(rower);
    }

    @Override
    public String toString() {
        return "Stacja #" + stacjaId + " | Lokalizacja: " + lokalizacja + 
               " | Liczba rowerów: " + rowery.size();
    }
}