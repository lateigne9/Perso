package UNO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Paquet {
    private final int nbreCrt = 108,
            couleursCarte = 4,
            nbrMelange = 200,
            nbreCrtSuperJoker = 4;

    private static int compteurCartePaquet = 0, curseurCarteDistribue =0;

    private Carte[] paquet = new Carte[nbreCrt];

    public Paquet() {
        /*insere en premier les zero de chaques couleur (4)*/
        for (int i = 0; i < 4; i++) {
            paquet[compteurCartePaquet] = new Carte(0, choixColori(i));
            compteurCartePaquet++;
        }
        /*insertion des chiffres de 1 à 9*/
        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < couleursCarte; j++) { /*4 couleurs*/
                for (int k = 0; k < 2; k++) {/* 2x */
                    paquet[compteurCartePaquet] = new Carte(i, choixColori(j));
                    compteurCartePaquet++;
                }
            }
        }
        /*insertion des plus_2*/
        generateCarteSymbole(Carte.symbole.PLUS_2);

        /*insertion des changements de sens*/
        generateCarteSymbole(Carte.symbole.INVERSION);

        /*insertion cartes pass*/
        generateCarteSymbole(Carte.symbole.PASS_TOUR);

        /*isertion de 4 changements de couleurs*/
        generateCarteJoker(Carte.superSymbole.CHCOULEUR);

        /*insertion cartes 4 +4*/
        generateCarteJoker(Carte.superSymbole.PLUS_4);
        melange();
    }

    /**
     * Méthode qui génère les 4 cartes super joker du type "changement couleurs" et "+4"
     *
     * @param joker le super joker
     */
    private void generateCarteJoker(Carte.superSymbole joker) {
        for (int i = 0; i < nbreCrtSuperJoker; i++) {
            paquet[compteurCartePaquet] = new Carte(joker);
            compteurCartePaquet++;
        }
    }

    /**
     * Méthode qui génère 8 cartes avec le symbole dessus (2 rouges, 2 jaunes, 2 vertes, 2 bleues)
     *
     * @param symbole symbole qui doit figurer sur la carte
     */
    private void generateCarteSymbole(Carte.symbole symbole) {
        for (int i = 0; i < couleursCarte; i++) {
            for (int j = 0; j < 2; j++) {
                paquet[compteurCartePaquet] = new Carte(symbole, choixColori(i));
                compteurCartePaquet++;
            }
        }
    }

    /**
     * Méthode qui retourne un des couleurs du jeu
     *
     * @param numero le numero corrspondant au choix de la couleur
     * @return un enum de la couleur de carte
     */
    private Carte.couleur choixColori(int numero) {
        return switch (numero) {
            case 0 -> Carte.couleur.BLEU;
            case 1 -> Carte.couleur.JAUNE;
            case 2 -> Carte.couleur.VERT;
            default -> Carte.couleur.ROUGE;
        };
    }

    /**
     * Méthode qui mélange le paquet de carte
     */
    private void melange() {
        for (int i = 0; i < nbrMelange; i++) {
            Carte carteTampon = new Carte(0, Carte.couleur.BLEU);/*copie de la carte a la position random 1*/
            int random1 = randomiser(), random2 = randomiser();
            if (paquet[random1].getCouleur().equals(Carte.couleur.NOIR.toString())) {/*si c'est une carte super joker*/
                if (Objects.equals(paquet[random1].getSuperSymbole(), Carte.superSymbole.PLUS_4)) {
                    carteTampon = new Carte(Carte.superSymbole.PLUS_4);
                } else {
                    carteTampon = new Carte(Carte.superSymbole.CHCOULEUR);
                }
            } else if (paquet[random1].getNumero() == -1) {/*carte joker*/
                carteTampon = new Carte(symbole(String.valueOf(paquet[random1].getSymbole())), couleur(String.valueOf(paquet[random1].getCouleur())));
            } else if (!paquet[random1].getCouleur().equals(Carte.couleur.NOIR.toString())) {/*carte numero + couleur*/
                carteTampon = new Carte(paquet[random1].getNumero(), couleur(String.valueOf(paquet[random1].getCouleur())));
            }
            paquet[random1] = paquet[random2];
            paquet[random2] = carteTampon;
        }
    }

    /**
     * Méthode qui transfome une chaine de chatactère de couleur en couleur de carte sous le format de Carte.couleur
     *
     * @param couleur la couleur en string
     * @return la couleur en format Carte.couleur
     */
    private Carte.couleur couleur(String couleur) {
        if (couleur.equals(String.valueOf(Carte.couleur.BLEU))) {
            return Carte.couleur.BLEU;
        } else if (couleur.equals(String.valueOf(Carte.couleur.ROUGE))) {
            return Carte.couleur.ROUGE;
        } else if (couleur.equals(String.valueOf(Carte.couleur.JAUNE))) {
            return Carte.couleur.JAUNE;
        } else if (couleur.equals(String.valueOf(Carte.couleur.VERT))) {
            return Carte.couleur.VERT;
        }
        return null;
    }

    /**
     * Méthode qui transforme une chaine de charactères de symbole en symbole de carte sour le format Carte.symbole
     *
     * @param symbole le format en string
     * @return le symbole en format Carte.symbole
     */
    private Carte.symbole symbole(String symbole) {
        if (symbole.equals(String.valueOf(Carte.symbole.PLUS_2))) {
            return Carte.symbole.PLUS_2;
        } else if (symbole.equals(String.valueOf(Carte.symbole.PASS_TOUR))) {
            return Carte.symbole.PASS_TOUR;
        } else if (symbole.equals(String.valueOf(Carte.symbole.INVERSION))) {
            return Carte.symbole.INVERSION;
        }
        return null;
    }

    /**
     * @return un entier entre 0 et le nombre de cartes du paquet
     */
    private int randomiser() {
        return (int) (Math.random() * nbreCrt);
    }

    /**Méthode qui distribue une carte du paquet
     * @return une carte du paquet en partant de la première
     */
    public Carte distribue(){
        Carte distribue=paquet[curseurCarteDistribue];
        paquet[curseurCarteDistribue]=null;
        curseurCarteDistribue++;
        return distribue;
    }

    public ArrayList<Carte> initialisePioche(int nombreDeCarte, int nombreDeJoueurs){
        int nombreCarteDist=nombreDeCarte*nombreDeJoueurs;
        return new ArrayList<>(Arrays.asList(paquet).subList(nombreCarteDist, nbreCrt));
    }


    @Override
    public String toString() {
        return "Paquet{" +
                "paquet=" + Arrays.toString(paquet) +
                '}';
    }
}

