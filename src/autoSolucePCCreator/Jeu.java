package autoSolucePCCreator;

import java.util.Scanner;

public class Jeu {
    private int nombreOccurence=6;
    private int nombreDepart,nombreArrivee;
    private String[] tabchiffre=new String[nombreOccurence];

    public Jeu(int nombreDepart, int nombreArrivee) {
        this.nombreDepart = nombreDepart;
        this.nombreArrivee = nombreArrivee;
    }
    public String[] saisieChiffre(){
        Scanner input=new Scanner(System.in);
        for (int i = 0; i < nombreOccurence; i++) {
            System.out.print("chiffre "+(i+1));
            tabchiffre[i]=input.next();
        }
        return tabchiffre;
    }

    public static void main(String[] args) {
        Jeu test=new Jeu(18,16);
        test.saisieChiffre();
    }

}
