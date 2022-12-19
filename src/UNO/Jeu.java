package UNO;

import java.util.ArrayList;
import java.util.Scanner;

public class Jeu {
    private int nombreDeJoueur;
    private ArrayList<Joueur> joueurs = new ArrayList<>();
    private Paquet paquet = new Paquet();

    private static Carte table;

    private final static int nombreDeCarteDistribue = 7;

    public Jeu(int nombreDeJoueurs) {
        this.nombreDeJoueur=nombreDeJoueurs;
        boolean finPartie = false;/*booleen qui determine si la partie est fini ou pas*/
        initialistationJoueur();/*rentre les noms des joueurs dans le jeu et leur donner une main*/
        //TODO Mettre une carte sur la table
        table = paquet.donneCarte();
        //TODO boucle while qui cherche tant que la partie n'est pas fini, on ne s'arrete pas
        while (!finPartie) {
            for (Joueur joueur : joueurs) {
                tour(joueur);
                finPartie = finPartie(joueur);
                if (finPartie) {
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

    /**Méthode qui simule le tour d'un joueur
     * @param joueur joueur qui joue
     */
    private void tour(Joueur joueur) {
        System.out.println(descriptionTour(joueur));
        //TODO voir si le joueur peut jouer
        int TMJoueurDebut=joueur.getMain().size();
        for (int i = 0; i < TMJoueurDebut; i++) {
            if (joueur.getMain().get(i).estjouable(table)) {/*parcours la main du joueur à la recherche de la première carte jouable et la joue*/
                joueCarte(joueur, i);
                break;
            }
        }
        if (TMJoueurDebut==joueur.getMain().size()){
            pioche(joueur);
        }
    }

    /**Méthode qui décrit la table ainsi que la main du joueur qui joue ce tour
     * @param joueur joueur qui joue
     * @return chaine de charactères formatée
     */
    private String descriptionTour(Joueur joueur){
        return "\nSur la table il y a \n"+
                table+
                "\nAu tour de "+joueur.getNom()+" de jouer avec la main: "+joueur.getMain()+'\n';
    }

    /**
     * Méthode qui joue une carte de la main du joueur et l'enlève de sa main
     * @param joueur     joueur qui joue
     * @param placeCarte place de la carte à jouer dans la main du joueur
     */
    private void joueCarte(Joueur joueur, int placeCarte) {
        table = joueur.getMain().get(placeCarte);
        System.out.println(joueur.getNom() + " a joué " + table);
        joueur.getMain().remove(placeCarte);
    }

    /**Methode qui fait piocher le joueur
     * @param joueur joueur qui pioche
     */
    private void pioche(Joueur joueur) {
        joueur.getMain().add(paquet.donneCarte());
        int dernierecarte=joueur.getMain().size();
        System.out.println(joueur.getNom()+"a pioché "+joueur.getMain().get(dernierecarte-1));
    }

    @Override
    public String toString() {
        return "\nSur la table il y a \n" + table + "\nles paquets des joueurs sont " + joueurs;
    }
}