package UNO;

import java.util.Objects;

import static UNO.Carte.superSymbole.SANS_SUPSYMBOLE;
import static UNO.Carte.symbole.SANS_SYMBOLE;
import static UNO.Carte.couleur.NOIR;

public class Carte {

    public enum couleur{
        ROUGE,BLEU,JAUNE,VERT,NOIR
    }
    public enum symbole{
        PLUS_2, INVERSION, PASS_TOUR, SANS_SYMBOLE
    }
    public enum superSymbole{
        CHCOULEUR, PLUS_4,SANS_SUPSYMBOLE
    }
    private String couleur;
    private int numero=-1;
    private String symbole=String.valueOf(SANS_SYMBOLE);
    private String superSymbole=String.valueOf(SANS_SUPSYMBOLE);

    public Carte(symbole symbole, couleur couleur){
        this.symbole= String.valueOf(symbole);
        this.couleur= String.valueOf(couleur);
    }

    public Carte(int numero,couleur couleur){
        this.couleur= String.valueOf(couleur);
        this.numero=numero;
    }
    public Carte(superSymbole superJoker){
        this.superSymbole= String.valueOf(superJoker);
        this.couleur=String.valueOf(NOIR);
    }

    public String getCouleur() {
        return couleur;
    }

    private void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public int getNumero() {
        return numero;
    }

    private void setNumero(int numero) {
        this.numero = numero;
    }

    public String getSymbole() {
        return symbole;
    }

    private void setSymbole(String symbole) {
        this.symbole = symbole;
    }

    public String getSuperSymbole() {
        return superSymbole;
    }

    private void setSuperSymbole(String superSymbole) {
        this.superSymbole = superSymbole;
    }

    /**Méthode qui dit si la carte est jouable sur une autre
     * @param carte Carte deja sur la table
     * @return vrai si la carte est jouable
     */
    public boolean jouable(Carte carte){
        boolean retour=false;
        if (numero== carte.getNumero()){
            retour=true;
        } else if (superSymbole!=null||symbole.equals(carte.getSymbole())) {
            retour= true;
        }
        return retour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Carte carte)) return false;
        return numero == carte.numero && Objects.equals(couleur, carte.couleur) && Objects.equals(symbole, carte.symbole) && Objects.equals(superSymbole, carte.superSymbole);
    }

    @Override
    public String toString() {
        if (numero!=-1){/*carte avec un chiffre*/
            return "["+numero+' '+ couleur+']';
        } else if (!symbole.equals(SANS_SYMBOLE.toString())) {/*carte avec un symbole*/
            return '['+symbole+' '+couleur+']';
        } else if (!superSymbole.equals(SANS_SUPSYMBOLE.toString())) {/*carte super joker*/
            return '['+superSymbole+']';
        }
        return null;
    }
}
