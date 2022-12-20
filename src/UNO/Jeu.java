package UNO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Jeu {
    private int nombreDeJoueur;
    private ArrayList<Joueur> joueurs = new ArrayList<>();
    private Paquet paquet = new Paquet();

    private static Carte table;

    private final static int nombreDeCarteDistribue = 7;

    public Jeu(int nombreDeJoueurs) {
        this.nombreDeJoueur=nombreDeJoueurs;
        boolean fin = false;/*booleen qui determine si la partie est fini ou pas*/
        initialistationJoueur();/*rentre les noms des joueurs dans le jeu et leur donner une main*/
        //Mettre une carte sur la table
        table = paquet.donneCarte();
        //Boucle while qui cherche tant que la partie n'est pas fini, on ne s'arrete pas
        while (!fin) {
            for (Joueur joueur : joueurs) {
                tour(joueur);
                fin = finPartie(joueur);
                if (fin) {
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
        Carte cartePosee=null;
        if (peutJouer(joueur)){/*si le joueur peut jouer*/
            cartePosee= joueCarte(joueur,demandeUtilisateurPLaceCarte(joueur));
        }else {
            pioche(joueur);
        }
        if (cartePosee!=null){/*verifie si une carte a été posée*/
            //TODO remplacer les else-if par un switch
//            switch (cartePosee.getSymbole()){}
            if (cartePosee.getSymbole().equals(Carte.Symbole.PLUS_4)){
                //TODO coder ce qu'il se passe dans ce cas

            } else if (cartePosee.getSymbole().equals(Carte.Symbole.PLUS_2)) {
                //TODO coder ce qu'il se passe dans ce cas
                plus2(joueurSuivant(joueur));

            } else if (cartePosee.getSymbole().equals(Carte.Symbole.INTERDIT_DE_JOUER)) {
                //TODO coder ce qu'il se passe dans ce cas

            } else if (cartePosee.getSymbole().equals(Carte.Symbole.CHANGEMENT_DE_SENS)) {
                //TODO coder ce qu'il se passe dans ce cas

            } else if (cartePosee.getSymbole().equals(Carte.Symbole.CHANGEMENT_DE_COULEUR)) {
                //TODO coder ce qu'il se passe dans ce cas

            } else if (cartePosee.getSymbole().equals(Carte.Symbole.SANS_SYMBOLE)) {/*si c'est une carte chiffre*/
                //TODO coder ce qu'il se passe dans ce cas

            }
        }
    }

    /**Méthode qui décrit la table ainsi que la main du joueur qui joue ce tour
     * @param joueur joueur qui joue
     * @return chaine de charactères formatée
     */
    private String descriptionTour(Joueur joueur){
        return "\nSur la table il y a \n"+
                table+
                "\nAu tour de "+joueur.getNom().toUpperCase()+" de jouer avec la main: "+joueur.getMain()+'\n';
    }

    /**
     * Méthode qui joue une carte de la main du joueur et l'enlève de sa main
     * @param joueur     joueur qui joue
     * @param placeCarte place de la carte à jouer dans la main du joueur
     * @return la carte jouée pour decider de ce qu'il se passe après la pose de celle-ci
     */
    private Carte joueCarte(Joueur joueur, int placeCarte) {
        table = joueur.getMain().get(placeCarte);
        System.out.println(joueur.getNom() + " a joué " + table);
        joueur.getMain().remove(placeCarte);
        return table;
    }

    /**Méthode qui demande de rentrer la position de la carte dans la main pour la jouer
     * @param joueur joueur qui joue
     * @return position de la carte dans la main
     */
    private int demandeUtilisateurPLaceCarte(Joueur joueur){
        int placeCarteMain;
        Scanner input= new Scanner(System.in);
        do {
            System.out.print("Position de la carte à jouer : ");
            placeCarteMain=input.nextInt();
            if (placeCarteMain>joueur.getMain().size()){
                placeCarteMain=0;
            }
        }while (!joueur.getMain().get(placeCarteMain).estjouable(table));
        return placeCarteMain;
    }

    /**Methode qui fait piocher le joueur
     * @param joueur joueur qui pioche
     */
    private void pioche(Joueur joueur) {
        joueur.getMain().add(paquet.donneCarte());
        int dernierecarte=joueur.getMain().size()-1;
        System.out.println(joueur.getNom()+" a pioché "+joueur.getMain().get(dernierecarte));
    }

    /**Méthode qui donne 2 cartes de la pioche au joueur
     * @param joueur joueur qui subit le +2
     */
    private void plus2(Joueur joueur){
        //TODO changer la methode
        Carte[] deuxCarte=new Carte[2];
        for (int i = 0; i < deuxCarte.length; i++) {
            deuxCarte[i]=paquet.donneCarte();
            joueur.getMain().add(deuxCarte[i]);
        }
        System.out.println(joueur.getNom().toUpperCase()+" a reçu un +2\nIl reçoit : "+ Arrays.toString(deuxCarte));
        //TODO le joueur suivant doit se recevoir un faux interdit de jouer pour passer son tour

    }

    /**Méthode qui donne 4 cartes de la pioche au joueur
     * @param joueur joueur qui subit le +4
     */
    private Carte.Couleur plus4(Joueur joueur){
        //TODO changer la methode
        Carte[] listeCarte= new Carte[4];
        for (int i = 0; i < 4; i++) {

        }
        return null;
    }

    /**Méthode qui vérifie si un joueur peut jouer sur la carte de la table
     * @param joueur joueur qui joue
     * @return true si le joueur peut jouer
     */
    private boolean peutJouer(Joueur joueur){
        boolean retour=false;
        for (Carte carte : joueur.getMain()) {
            if (carte.estjouable(table)){
                retour=true;
                break;
            }
        }
        return retour;
    }

    /**Méthode qui renvoie le joueur suivant
     * @param joueur joueur actuel
     * @return joueur suivant
     */
    private Joueur joueurSuivant(Joueur joueur){
        Joueur suivant;
        int positionJoueurActuel=0;
        for (int i = 0; i < joueurs.size(); i++) {
            if (joueurs.get(i).equals(joueur)){
                positionJoueurActuel=i;
            }
        }
        if (positionJoueurActuel==joueurs.size()-1){
            suivant=joueurs.get(0);
        }else {
            suivant=joueurs.get(positionJoueurActuel+1);
        }
        return suivant;
    }

    @Override
    public String toString() {
        return "\nSur la table il y a \n" + table + "\nles paquets des joueurs sont " + joueurs;
    }
}