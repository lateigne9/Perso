package UNO;

import java.util.ArrayList;

public class Paquet {
    private int nombreDeCarte=76;/*nombre de cartes avec des numéros*/
    private int nombreDeMelange=100;

    private ArrayList<Carte> paquet=new ArrayList<>();

    /**
     * Constructeur d'un paquet normal
     */
    public Paquet() {
        //TODO construction et ajout des quatres 0
        for (int i = 0; i < 4; i++) {/*nombre de couleurs*/
            paquet.add(new Carte(choixCouleur(i), 0));
        }
        //TODO construction et ajout des cartes de 1 a 9
        for (int i = 0; i < 4; i++) {/*4 couleurs*/
            for (int j = 1; j <= 9; j++) {/*9 cartes*/
                creerCarteNumeroDouble(j,i);
            }
        }
        melange();
    }

    public ArrayList<Carte> getPaquet() {
        return paquet;
    }

    private void setPaquet(ArrayList<Carte> paquet) {
        this.paquet = paquet;
    }

    private void creerCarteNumeroDouble(int numero, int numeroCouleur){
        for (int i = 0; i < 2; i++) {
            paquet.add(new Carte(choixCouleur(numeroCouleur),numero));
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

    /**Méthode qui donne retire une carte du paquet pour la retourner
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
