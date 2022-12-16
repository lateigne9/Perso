package UNO;

public class TestCarte {
    public static void main(String[] args) {
        Carte carteJOKER1=new Carte(Carte.symbole.PLUS_2, Carte.couleur.BLEU);

        Carte carteSuperJOKER1=new Carte(Carte.superSymbole.CHCOULEUR);
        Carte carteSuperJOKER2=new Carte(Carte.superSymbole.PLUS_4);
        Carte carteNum1=new Carte(2, Carte.couleur.ROUGE);


        System.out.println(carteJOKER1);
        System.out.println(carteSuperJOKER1);
        System.out.println(carteSuperJOKER2);
        System.out.println(carteNum1);
    }
}
