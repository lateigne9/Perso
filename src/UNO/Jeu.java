package UNO;

import java.util.ArrayList;
import java.util.Scanner;

public class Jeu {
    private final ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
    private ArrayList<Carte> pioche=new ArrayList<Carte>();
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
     * @return Joueur qui commence le jeu
     */
    public Joueur debutJeu(int nombreDeCarte, int nombreDeJoueur) {
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

        return quiCommence();
    }

    /**
     * Méthode qui demande à l'utilisateur le joueur qui commence à jouer
     */
    private Joueur quiCommence() {
        Scanner input=new Scanner(System.in);
        String nom;
        do {
            System.out.print("Saissisez le nom du joueur qui commence: ");
            nom=input.next();
        }while (!valideJoueur(nom));
        System.out.println("c'est "+nom+ " qui commence");
        return joueurs.get(retrouveIndex(nom));
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
     * @param joueur joueur qui doit jouer
     * @return joueur suivant
     */
    public Joueur deroulementTour(Joueur joueur){
        System.out.println("Au tour de "+ joueur.getNom());
        System.out.println("sur la table il y a :\n\n"+table+"\n");
        if (table.getSymbole()!=null)/*si il y a une carte joker sur la table*/{
            if (table.getSymbole().equals(Carte.symbole.PLUS_2.toString())){
                piochejeu(joueur,2);
            } else if (table.getSymbole().equals(Carte.symbole.PASS_TOUR.toString())) {
                //le programme n'execute rien et passe au joueur suivant
                System.out.println(joueur.getNom()+ " passe son tour");
            } else if (table.getSymbole().equals(Carte.symbole.INVERSION.toString())) {
                //Methode qui changera le sens du jeu
            }
        } else if (!table.getSuperSymbole().equals(Carte.superSymbole.SANS_SUPSYMBOLE.toString()))/*si il y a un super joker sur la table*/ {
            piochejeu(joueur,4);
        }else /*s'il y a juste une carte avec un chiffre dessus*/{
            int placeCarte= jouer(joueur);
            if (placeCarte!=-1)/*si le joueur joue*/{
                joue(joueur.getMain().get(placeCarte));
            }else {
                piochejeu(joueur,1);
                Carte derniereCarte=joueur.getMain().get(joueur.getMain().size());
                if (table.jouable(derniereCarte)){
                    joue(derniereCarte);
                }
            }
        }
        //Determinaison du joueur suivant
        /*1- voir dans quel sens on joue
        2- si c'est dans le sens horaire => si c'est le 1=>2, le 2=>3... dernier=>premier
        si c'est dans le sens anti-horaire => si c'est le dernier=> avant... 1er=> dernier
        */
        return null;
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
    private void joue(Carte carteJoue){
        table=carteJoue;
    }

    private void piochejeu(Joueur joueur, int nombredeCarte){
        if(pioche.size()<nombredeCarte){
            //à coder plus tard pour créer un nouveau paquet et le mettre à la place de la pioche
        }else {
            for (int i = 0; i < nombredeCarte; i++) {
                joueur.getMain().add(pioche.get(0));
                pioche.remove(0);
            }
        }
    }



}
