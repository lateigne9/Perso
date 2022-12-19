package UNO;

public class Carte {
    enum Couleur {
        ROUGE,BLEU,VERT,JAUNE
    }
    private Couleur couleur=Couleur.ROUGE;
    private int numero=0;
    public Carte() {}

    public Carte(Couleur couleur, int numero) {
        this.couleur = couleur;
        setNumero(numero);
    }

    public Couleur getCouleur() {
        return couleur;
    }
    private void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }
    public int getNumero() {
        return numero;
    }
    private void setNumero(int numero) {
        if (numero>=0&&numero<=9){
            this.numero=numero;
        }
    }

    /**Vérifie si une carte est jouable par sur une autre une autre
     * @param autreCarte la carte avec laquelle on teste si elle est jouable
     * @return retourne vrai si la carte est jouable sur celle passée en paramètre
     */
    public boolean estjouable(Carte autreCarte){
        boolean retour=false;
        if (autreCarte.couleur.equals(couleur)){/*voir si la carte est de la meme couleur*/
            retour=true;
        } else if (autreCarte.numero==numero) {/*voir si la carte a le meme numéro*/
            retour=true;
        }
        return retour;
    }

    @Override
    public String toString() {
        return "["+numero+' '+couleur+']';
    }

}
