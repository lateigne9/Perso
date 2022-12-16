package note;

public class Note {
    private double note;
    private int pourcentage;

    public Note(double note, int pourcentage) {
        this.note = note;
        setPourcentage(pourcentage);
    }

    public double getNote() {
        return note;
    }

    private void setNote(double note) {
        this.note = note;
    }

    public int getPourcentage() {
        return pourcentage;
    }

    private void setPourcentage(int pourcentage) {
        if (pourcentage<=100&&pourcentage>0){
            if (note <= pourcentage) {
                this.pourcentage = pourcentage;
            } else {
                System.out.println("pourcentage plus petit que la note");
                this.pourcentage= Integer.parseInt(null);
            }
        }
    }

    @Override
    public String toString() {
        return "Note{" +
                "note=" + note +
                ", pourcentage=" + pourcentage +
                '}';
    }
}
