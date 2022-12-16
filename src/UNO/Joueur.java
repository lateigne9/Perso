package UNO;

import java.util.ArrayList;
import java.util.Scanner;

public class Joueur {
    private String nom;
    private ArrayList<Carte> main= new ArrayList<Carte>();

    public Joueur(String nom, ArrayList<Carte> main) {
        this.nom = nom;
        this.main = main;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Carte> getMain() {
        return main;
    }

    public void setMain(Carte carte) {
        this.main.add(carte);
    }
    public Joueur(){
        Scanner input=new Scanner(System.in);
        System.out.print("Nom du joueur: ");
        this.nom=input.next();
    }

    @Override
    public String toString() {
        return "main de "+ nom + " = {" + main +'}';
    }
}
