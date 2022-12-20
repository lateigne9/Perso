package UNO;

import java.util.ArrayList;

public class Paquet {
    private final int nombreDeCarte=108;
    /*76=nombre de cartes avec des chiffres
    +8 = cartes joker de couleurs
    * 108= nombre de cartes total*/
    private final int nombreDeMelange=100;

    private ArrayList<Carte> paquet=new ArrayList<>();

    /**
     * Constructeur d'un paquet normal
     */
    public Paquet() {
        /*construction et ajout des quatres 0*/
        for (int i = 0; i < 4; i++) {/*nombre de couleurs*/
            paquet.add(new Carte(0,choixCouleur(i)));
        }
        /*construction et ajout des cartes de 1 a 9*/
        for (int i = 0; i < 4; i++) {/*4 couleurs*/
            for (int j = 1; j <= 9; j++) {/*9 cartes*/
                carteNumeroDouble(j,i);
            }
        }
        /*ajout des cartes changements de sens*/
        carteSymboleDouble(Carte.Symbole.CHANGEMENT_DE_SENS);
        /*ajout des cartes interdit de jouer*/
        carteSymboleDouble(Carte.Symbole.INTERDIT_DE_JOUER);
        /*ajout des cartes +2*/
        carteSymboleDouble(Carte.Symbole.PLUS_2);
        /*ajout des cartes +4*/
        carteJoker(Carte.Symbole.PLUS_4);
        /*ajout des cartes changement de couleurs*/
        carteJoker(Carte.Symbole.CHANGEMENT_DE_COULEUR);
        melange();
    }
    public ArrayList<Carte> getPaquet() {
        return paquet;
    }

    private void setPaquet(ArrayList<Carte> paquet) {
        this.paquet = paquet;
    }

    /**Méthode qui ajoute 4 cartes joker au paquet
     * @param symbole Symbole de la carte joker
     */
    private void carteJoker(Carte.Symbole symbole){
        for (int i = 0; i < 4; i++) {
            paquet.add(new Carte(symbole,choixCouleur(i)));
        }
    }

    /**Méthode qui ajoute 2 cartes symboles au paquet
     * @param symbole symbole à ajouter
     */
    private void carteSymboleDouble(Carte.Symbole symbole){
        for (int i = 0; i < 4; i++) {/*4 couleurs*/
            for (int j = 0; j < 2; j++) {
                paquet.add(new Carte(symbole,choixCouleur(i)));
            }
        }
    }

    /**Méthode qui ajoute 2 cartes numérotées au paquet
     * @param numero numéro des cartes à ajouter
     * @param numeroCouleur numero de la couleur à ajouter qui est géré par la méthode choixCouleur
     */
    private void carteNumeroDouble(int numero, int numeroCouleur){
        for (int i = 0; i < 2; i++) {
            paquet.add(new Carte(numero,choixCouleur(numeroCouleur)));
        }
    }

    /**Méthode qui renvoie une couleur de carte
     * @param numero
     * @return une couleur de carte
     */
    private Carte.Couleur choixCouleur(int numero){
        return switch (numero % 4) {
            case 0 -> Carte.Couleur.JAUNE;
            case 1 -> Carte.Couleur.BLEU;
            case 2 -> Carte.Couleur.VERT;
            default -> Carte.Couleur.ROUGE;
        };
    }

    /**
     * Méthode qui mélange le paquet
     */
    private void melange(){
        for (int i = 0; i < nombreDeMelange; i++) {
            int random1=(int) (Math.random()*nombreDeCarte),random2=(int) (Math.random()*nombreDeCarte);
            Carte carteTampon;
            carteTampon=paquet.get(random1);
            paquet.set(random1,paquet.get(random2));
            paquet.set(random2,carteTampon);
        }
    }

    /**Méthode qui retourne la première carte du paquet et l'enlève du paquet
     * @return Carte donnée
     */
    public Carte donneCarte(){
        Carte retour;
        retour=paquet.get(0);
        paquet.remove(0);
        return retour;
    }

    @Override
    public String toString() {
        return "Paquet{" + paquet +'}';
    }
}
