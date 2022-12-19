package UNO;

import java.util.ArrayList;
import java.util.Scanner;

public class Jeu {
    private int nombreDeJoueur = 2;
    ArrayList<Joueur> joueurs = new ArrayList<>();
    Paquet paquet = new Paquet();

    private static Carte table;

    private final static int nombreDeCarteDistribue = 7;

    public Jeu() {
        boolean finPartie = false;/*booleen qui determine si la partie est fini ou pas*/
        initialistationJoueur();/*rentre les noms des joueurs dans le jeu et leur donner une main*/
        //TODO Mettre une carte sur la table
        table = paquet.donneCarte();
        //TODO boucle while qui cherche tant que la partie n'est pas fini, on ne s'arrete pas
        while (!finPartie) {
            for (Joueur joueur : joueurs) {
                tour(joueur);
                finPartie=finPartie(joueur);
                if (finPartie){
                    break;
                }
            }
        }
    }

    public static Carte getTable() {
        return table;
    }

    private static void setTable(Carte table) {
        Jeu.table = table;
    }

    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }

    private void setJoueurs(ArrayList<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    public Paquet getPaquet() {
        return paquet;
    }

    private void setPaquet(Paquet paquet) {
        this.paquet = paquet;
    }


    /**
     * Méthode qui distribue le nombreDeCarteDistribue aux joueurs pour commencer la partie
     */
    private void distribution() {
        for (int i = 0; i < nombreDeCarteDistribue; i++) {
            for (Joueur joueur : joueurs) {
                joueur.getMain().add(paquet.donneCarte());
            }
        }
    }

    /**
     * Méthode qui cherche si un joueur a fini son paquet
     *
     * @return true si un joueur a fini son paquet
     */
    private boolean finPartie(Joueur joueur) {
        boolean retour = false;
        if (joueur.getMain().size() == 0) {
            retour = true;
            System.out.println(joueur + "a gagné la partie");
        }
        return retour;
    }

    /**
     * Méthode qui demande le nom des joueurs et leur distribue une main
     */
    private void initialistationJoueur() {
        for (int i = 0; i < nombreDeJoueur; i++) {
            Scanner input = new Scanner(System.in);
            System.out.print("Nom du joueur " + (i + 1) + "? ");
            joueurs.add(new Joueur(input.next()));
        }
        distribution();
    }

    private void tour(Joueur joueur) {
        //TODO voir si le joueur peut jouer

    }

    private void pioche(Joueur joueur){
        joueur.getMain().add(paquet.donneCarte());
    }

    @Override
    public String toString() {
        return "\nSur la table il y a \n" + table + "\nles paquets des joueurs sont " + joueurs;
    }
}
