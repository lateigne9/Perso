package UNO;

import java.util.ArrayList;
import java.util.Scanner;

public class Jeu {
    private final ArrayList<Joueur> joueurs = new ArrayList<>();
    private ArrayList<Carte> pioche=new ArrayList<>();
    private final Paquet paquet = new Paquet();
    private Carte table;/*carte qui représente la dernière carte posée par le joueur*/
    public static sens sens= Jeu.sens.horaire;
    private enum sens{
        horaire,antiHoraire
    }

    /**
     * Méthode qui demande le nom des joueurs et leur distribue par la suite le nombre de cartes une par une.
     * @param nombreDeCarte  nombre de cartes que le jeu donne aux joueurs
     * @param nombreDeJoueur nombre de joueurs jouant au jeu
     */
    public void debutJeu(int nombreDeCarte, int nombreDeJoueur) {
        for (int i = 0; i < nombreDeJoueur; i++) {
            joueurs.add(new Joueur());
        }
        System.out.println(paquet);
        /*prend le joueur 1 et lui donne une carte
         * prend le joueur 2 et lui donne une carte....*/
        for (int i = 0; i < nombreDeCarte; i++) {
            for (int j = 0; j < nombreDeJoueur; j++) {
                joueurs.get(j).setMain(paquet.distribue());
            }
        }
        System.out.println(joueurs);
        pioche=paquet.initialisePioche(nombreDeCarte,nombreDeJoueur);

//        System.out.println("Pioche avant depot de la premiere carte");
//        System.out.println(pioche);

        //initialisation de la table
        initialiseTable();

//        System.out.println(table);
//        System.out.println("pioche apres le depot de la premiere carte");
//        System.out.println(pioche);
    }

    /**
     * Méthode qui demande à l'utilisateur le joueur qui commence à jouer
     * @return l'entier de la position du joueur qui commence
     */
    public int quiCommence() {
        Scanner input=new Scanner(System.in);
        String nom;
        do {
            System.out.print("Saissisez le nom du joueur qui commence: ");
            nom=input.next();
        }while (!valideJoueur(nom));
        System.out.println("c'est "+nom+ " qui commence");
        return retrouveIndex(nom);
    }

    /**Méthode qui valide le nom du joueur qui commence
     * @param nom chaine de charactère du nom du joueur
     * @return booléen, vrai si le nom du joueur est dans la liste des joueurs
     */
    private boolean valideJoueur(String nom){
        boolean retour=false;
        for (Joueur joueur : joueurs) {
            if (joueur.getNom().equals(nom)) {
                retour = true;
                break;
            }
        }
        return retour;
    }

    /**Méthode qui cherche l'index du nom dans la liste de joueur
     * @param nom nom du joueur
     * @return index du nom dans la liste
     */
    private int retrouveIndex(String nom){
        int retour=-1;
        for (int i = 0; i < joueurs.size(); i++) {
            if (joueurs.get(i).getNom().equals(nom)){
                retour= i;
            }
        }
        return retour;
    }

    /**
     * Méthode qui initialise la première carte de la table
     */
    private void initialiseTable(){
        table=pioche.get(0);
        pioche.remove(0);
    }

    /**Méthode qui gère le tour d'un joueur
     * regarde en premier le carte qui est sur la table et fait les actions en conséquence
     * @param numeroDuJoueur joueur qui doit jouer
     * @return joueur suivant
     */
    public int deroulementTour(int numeroDuJoueur){
        System.out.println("Au tour de "+ joueurs.get(numeroDuJoueur).getNom());
        System.out.println("sur la table il y a :\n\n"+table+"\n");
        if (table.getSymbole()!=null)/*si il y a une carte joker sur la table*/{
            if (table.getSymbole().equals(Carte.symbole.PLUS_2.toString())){
                piochejeu(joueurs.get(numeroDuJoueur),2);
            } else if (table.getSymbole().equals(Carte.symbole.PASS_TOUR.toString())) {
                //le programme n'execute rien et passe au joueur suivant
                System.out.println(joueurs.get(numeroDuJoueur).getNom()+ " passe son tour");
            } else if (table.getSymbole().equals(Carte.symbole.INVERSION.toString())) {
                //Methode qui changera le sens du jeu
                changementDeSens();
            }
        } else if (!table.getSuperSymbole().equals(Carte.superSymbole.SANS_SUPSYMBOLE.toString()))/*si il y a un super joker sur la table*/ {
            piochejeu(joueurs.get(numeroDuJoueur),4);
        }else /*s'il y a juste une carte avec un chiffre dessus*/{
            int placeCarte= jouer(joueurs.get(numeroDuJoueur));
            if (placeCarte!=-1)/*si le joueur joue*/{
                joue(joueurs.get(numeroDuJoueur).getMain().get(placeCarte),placeCarte,joueurs.get(numeroDuJoueur));
            }else {
                piochejeu(joueurs.get(numeroDuJoueur),1);
                Carte derniereCarte=joueurs.get(numeroDuJoueur).getMain().get(joueurs.get(numeroDuJoueur).getMain().size()-1);
                if (table.jouable(derniereCarte)){
                    joue(derniereCarte,joueurs.get(numeroDuJoueur).getMain().size()-1,joueurs.get(numeroDuJoueur));
                }
            }
        }
        gagnerPartie(numeroDuJoueur);
        //TODO Determinaison du joueur suivant
        int joueurSuivant=-1;
        if (sens== Jeu.sens.horaire){
            if (joueurs.get(numeroDuJoueur).equals(joueurs.get(joueurs.size()-1))){
                joueurSuivant=0;
            }else {
                joueurSuivant=numeroDuJoueur+1;
            }
        } else if (sens== Jeu.sens.antiHoraire) {
            if (joueurs.get(numeroDuJoueur).equals(joueurs.get(0))){
                joueurSuivant=joueurs.size()-1;
            }else {
                joueurSuivant=numeroDuJoueur-1;
            }
        }
        return joueurSuivant;
    }

    /**Méthode qui gère la carte à jouer dans la main du joueur
     * @param joueur joueur qui doit jouer
     * @return l'index de la liste où se trouve la carte
     */
    private int jouer(Joueur joueur){
        Scanner input=new Scanner(System.in);
        int retour;
        System.out.println(joueur.getNom()+ " que veux tu jouer ?\n"+joueur.getMain());
        do {
            retour=input.nextInt();
        }while (table.jouable(joueur.getMain().get(retour))||retour==-1);
        return retour;
    }

    /**
     * Méthode qui remplace la carte de la table par la carte jouée
     */
    private void joue(Carte carteJoue,int placeCarteJoue,Joueur joueur){
        table=carteJoue;
        joueur.getMain().remove(placeCarteJoue);
    }

    /**Méthode qui fait piocher le joueur
     * @param joueur joueur qui pioche
     * @param nombredeCarte nombre de carte qu'il pioche
     */
    private void piochejeu(Joueur joueur, int nombredeCarte){
        if (pioche.size() >= nombredeCarte) {
            for (int i = 0; i < nombredeCarte; i++) {
                joueur.getMain().add(pioche.get(0));
                pioche.remove(0);
            }
        }  //à coder plus tard pour créer un nouveau paquet et le mettre à la place de la pioche

    }

    /**
     * Méthode qui change le sens du jeu
     */
    private void changementDeSens(){
        if (sens== Jeu.sens.antiHoraire){
            sens= Jeu.sens.horaire;
        }else {
            sens= Jeu.sens.antiHoraire;
        }
    }

    public boolean gagnerPartie(int joueur){
        //TODO verifier a chaque fois si la main est vide
        return joueurs.get(joueur).getMain().size() == 0;
    }


}
