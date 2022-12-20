package UNO;

import java.util.ArrayList;

public class Joueur {
    private String nom="INCONNU";
    private ArrayList<Carte> main=new ArrayList<>();

    public Joueur(String nom) {
        this.nom = nom.toUpperCase();
    }

    public String getNom() {
        return nom;
    }

    private void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Carte> getMain() {
        return main;
    }

    private void setMain(ArrayList<Carte> main) {
        this.main = main;
    }

    @Override
    public String toString() {
        return nom + " a la main : " + main;
    }
}
