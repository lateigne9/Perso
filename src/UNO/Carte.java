package UNO;

public class Carte {
    enum Couleur {
        ROUGE,BLEU,VERT,JAUNE, NOIR
    }
    enum Symbole{
        SANS_SYMBOLE, PLUS_2, PLUS_4,CHANGEMENT_DE_SENS,CHANGEMENT_DE_COULEUR,INTERDIT_DE_JOUER
    }
    private Couleur couleur=Couleur.ROUGE;
    private int numero=-1;

    private Symbole symbole=Symbole.SANS_SYMBOLE;
    public Carte() {}

    /**Constructeur de carte normale (chiffe et couleur)
     * @param couleur couleur de la carte
     * @param numero chiffre sur la carte
     */
    public Carte(int numero,Couleur couleur) {
        this.couleur = couleur;
        setNumero(numero);
    }

    /**Constructeur de carte joker
     * @param symbole symbole de la carte
     * @param couleur couleur de la carte
     */
    public Carte(Symbole symbole,Couleur couleur){
        this.couleur=couleur;
        setSymbole(symbole);
    }

    public Symbole getSymbole() {
        return symbole;
    }

    private void setSymbole(Symbole symbole) {
        if (symbole.equals(Symbole.PLUS_4)||symbole.equals(Symbole.CHANGEMENT_DE_COULEUR)){
            this.couleur=Couleur.NOIR;
        }
        this.symbole=symbole;
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
        } else if (autreCarte.numero==numero&&numero!=-1) {/*voir si la carte a le meme numéro*/
            retour=true;
        } else if (autreCarte.symbole.equals(symbole)&&numero==-1) {/*voir si la carte a le meme symbole*/
            retour=true;
        }
        return retour;
    }

    @Override
    public String toString() {
        if (numero==-1){/*c'est une carte joker*/
            if (symbole.equals(Symbole.PLUS_4)||symbole.equals(Symbole.CHANGEMENT_DE_COULEUR)){
                return "["+symbole+']';
            }else {
                return "["+symbole+' '+couleur+']';
            }
        }else {
            return "["+numero+' '+couleur+']';
        }
    }

}
