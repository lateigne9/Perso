package UNO;

public class TestJeu {
    public static void main(String[] args) {
        Jeu jeu=new Jeu();
        jeu.debutJeu(7,2);
        int joueurQuiCommence=jeu.quiCommence();
        while(!jeu.gagnerPartie(joueurQuiCommence)){
            jeu.deroulementTour(joueurQuiCommence);
        }


    }
}
