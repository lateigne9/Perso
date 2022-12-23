package UNO;

import java.util.*;

/**
 * @author La_teigne
 * 20/12/2022
 */
public class Jeu {
    private int nombreDeJoueur;
    private ArrayList<Joueur> joueurs = new ArrayList<>();
    private Paquet paquet = new Paquet();

    private static Carte table;

    private final static int nombreDeCarteDistribue = 7;
    private boolean joueurSuivantPeutJouer=true,changerSens=false;

    public Jeu(int nombreDeJoueurs) {
        this.nombreDeJoueur=nombreDeJoueurs;
        boolean fin = false;/*booleen qui determine si la partie est fini ou pas*/
        initialistationJoueur();/*rentre les noms des joueurs dans le jeu et leur donner une main*/
        //Mettre une carte sur la table
        table = paquet.donneCarte();
        //Boucle while qui cherche tant que la partie n'est pas fini, on ne s'arrete pas
        while (!fin) {
            for (Joueur joueur : joueurs) {
                tour(joueur,joueurSuivantPeutJouer);
                fin = finPartie(joueur);
                if (changerSens){/*si le sens est changé, on remet le sens normal mais on a deja reorganiser la liste de joueurs*/
                    changerSens=false;
                    break;
                }
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
            System.out.println(joueur.getNom() + " a gagné la partie");
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
    private void tour(Joueur joueur,boolean peutJouer) {
        if (!peutJouer){/*si le joueur est interdit de jouer*/
            System.out.println(joueur.getNom()+" ne peut pas jouer car il a reçu "+table);
            joueurSuivantPeutJouer=true;
        }else{
            Carte cartePosee=null;
            if (peutJouer(joueur)){/*si le joueur peut jouer*/
                System.out.println(descriptionTour(joueur));
                cartePosee= joueCarte(joueur,demandeUtilisateurPLaceCarte(joueur));
            }else {
                pioche(joueur);
            }
            if (cartePosee!=null){/*verifie si une carte a été posée*/
                //TODO remplacer les else-if par un switch
                switch (cartePosee.getSymbole()){
                    case PLUS_4:plus4(joueur);
                    break;
                    case PLUS_2:plus2(joueur);
                    break;
                    case INTERDIT_DE_JOUER:joueurSuivantPeutJouer=false;
                    break;
                    case CHANGEMENT_DE_SENS:changeSens(joueur);
                    break;
                    case CHANGEMENT_DE_COULEUR:table.setCouleur(choixCouleur(joueur));
                    break;
                    default:
                        break;
                }
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
                "\n\nAu tour de "+joueur.getNom()+" de jouer avec la main: "+joueur.getMain();
    }

    /**
     * Méthode qui joue une carte de la main du joueur et l'enlève de sa main
     * @param joueur     joueur qui joue
     * @param placeCarte place de la carte à jouer dans la main du joueur
     * @return la carte jouée pour decider de ce qu'il se passe après la pose de celle-ci
     */
    private Carte joueCarte(Joueur joueur, int placeCarte) {
        table = joueur.getMain().get(placeCarte);
        System.out.println('\n'+joueur.getNom() + " a joué " + table);
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
            if (placeCarteMain-1>=joueur.getMain().size()||placeCarteMain<=0){
                placeCarteMain=1;
            }
        }while (!joueur.getMain().get(placeCarteMain-1).estjouable(table));
        return placeCarteMain-1;
    }

    /**Methode qui fait piocher le joueur
     * @param joueur joueur qui pioche
     */
    private void pioche(Joueur joueur) {
        verifiePioche(1);
        joueur.getMain().add(paquet.donneCarte());
        int dernierecarte=joueur.getMain().size()-1;
        System.out.println(joueur.getNom()+" a pioché "+joueur.getMain().get(dernierecarte));
    }

    /**Méthode qui donne 2 cartes de la pioche au joueur suivant
     * @param joueur joueur qui met le +2
     */
    private void plus2(Joueur joueur){
        Carte[] deuxCarte=new Carte[2];
        jokerDonneCarte(joueurSuivant(joueur), deuxCarte);
    }

    /**Méthode qui donne 4 cartes de la pioche au joueur suivant et met à jour la couleur de la
     * carte +4 pour que la carte suivante soit de la couleur demandée
     * @param joueur joueur qui met le +4
     */
    private void plus4(Joueur joueur){
        Carte[] listeCarte= new Carte[4];
        jokerDonneCarte(joueurSuivant(joueur), listeCarte);
        table.setCouleur(choixCouleur(joueur));

    }

    private void jokerDonneCarte(Joueur joueur, Carte[] listeCarte) {
        verifiePioche(listeCarte.length);
        for (int i = 0; i < listeCarte.length; i++) {
            listeCarte[i]=paquet.donneCarte();
            joueur.getMain().add(listeCarte[i]);
        }
        System.out.println('\n'+joueur.getNom()+" a reçu une attaque\nIl reçoit : "+ Arrays.toString(listeCarte));
        joueurSuivantPeutJouer=false;
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

    private Carte.Couleur choixCouleur(Joueur joueur){
        Scanner input=new Scanner(System.in);
        int chiffreDonne;
        do {
            System.out.println(joueur.getNom()+ ", quelle couleur veux tu ?\n1 = Rouge\n2 = Bleu\n3 = Vert\n4 = Jaune");
            chiffreDonne= input.nextInt();
        }while (!verifieChiffreCouleur(chiffreDonne));
        return switch (chiffreDonne){
          case 1-> Carte.Couleur.ROUGE;
          case 2-> Carte.Couleur.BLEU;
          case 3-> Carte.Couleur.VERT;
          case 4-> Carte.Couleur.JAUNE;
          default -> Carte.Couleur.NOIR;
        };
    }

    /**Méthode qui vérifie si le chiffre rentré est bon ou pas
     * @param chiffre chiffre testé
     * @return vrai si le chiffre correspond à une couleur
     */
    private boolean verifieChiffreCouleur(int chiffre){
        return switch (chiffre) {
            case 1, 2, 3, 4 -> true;
            default -> false;
        };
    }
    /**Méthode qui change l'ordre des joueurs pour simuler le fait que l'on change de sens
     * @param joueur joueur qui joue la carte de changement de sens
     */
    private void changeSens(Joueur joueur){
        changerSens=true;
        int placeJoueur=0;
        ArrayList<Joueur> copie=new ArrayList<>();
        for (int i = 0; i < nombreDeJoueur; i++) {
            if (joueurs.get(i).equals(joueur)){
                placeJoueur=i;
                break;
            }
        }
        for (int i = placeJoueur; i <nombreDeJoueur ; i++) {
            copie.add(joueurs.get(i));
        }
        for (int i = 0; i < placeJoueur; i++) {
            copie.add(joueurs.get(i));
        }
        Collections.reverse(copie);
        joueurs=copie;
    }

    /**Méthode qui vérifie s'il y a assez de carte dans la pioche pour pouvoir piocher dedans si ce n'est pas le cas, la pioche se régénère
     * @param nombreDeCarte nombre de cartes qu'il faudra donner
     */
    private void verifiePioche(int nombreDeCarte){
        if (nombreDeCarte>paquet.getPaquet().size()){
            paquet=new Paquet();
        }
    }

    @Override
    public String toString() {
        return "\nSur la table il y a \n" + table + "\nles paquets des joueurs sont " + joueurs;
    }

    public static void main(String[] args) {
        new Jeu(2);
    }
}