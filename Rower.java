
public class Rower {
    public int rowerId;
    public RowerTyp typ;
    public boolean czyDostepny;
    private static int counter = 1;

    public Rower(RowerTyp typ, boolean czyDostepny) {
        this.rowerId = counter++;
        this.typ = typ;
        this.czyDostepny = czyDostepny;
    }

    // Gettery
    public int getRowerId() {
        return rowerId;
    }

    public RowerTyp getTyp() {
        return typ;
    }

    public boolean isCzyDostepny() {
        return czyDostepny;
    }

    // Settery
    public void setTyp(RowerTyp typ) {
        this.typ = typ;
    }

    public void setCzyDostepny(boolean czyDostepny) {
        this.czyDostepny = czyDostepny;
    }

    // Metody
    public void zablokuj() {
        this.czyDostepny = false;
    }

    public void odblokuj() {
        this.czyDostepny = true;
    }

    @Override
    public String toString() {
        return "Rower #" + rowerId + " | Typ: " + typ + " | Dostepny: " + (czyDostepny ? "Tak" : "Nie");
    }
}