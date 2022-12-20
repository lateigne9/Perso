package UNO;

public class TestCarte {
    public static void main(String[] args) {
        Carte carteTest=new Carte();
        Carte carteTestCouleur=new Carte(2,Carte.Couleur.BLEU);
        Carte carteTest1=new Carte(7,Carte.Couleur.VERT);
        Carte cartetest2=new Carte(3,Carte.Couleur.ROUGE);
        Carte carteTest3=new Carte(-1,Carte.Couleur.JAUNE);
        Carte carteTest4=new Carte(10,Carte.Couleur.VERT);
        Carte carteTest5=new Carte(2,Carte.Couleur.VERT);
        Carte carteTest6=new Carte(6,Carte.Couleur.JAUNE);
        Carte carteTest7=new Carte(8,Carte.Couleur.ROUGE);


        System.out.println("Sortie de cartes numéros/couleurs");
        System.out.println(carteTest);
        System.out.println(carteTestCouleur);
        System.out.println(carteTest1);
        System.out.println(cartetest2);
        System.out.println(carteTest3);
        System.out.println(carteTest4);
        System.out.println(carteTest5);
        System.out.println(carteTest6);
        System.out.println(carteTest7);
        System.out.println();

        System.out.println("===Test est jouable===");
        System.out.println("Doit est false : " +carteTest.estjouable(carteTestCouleur));
        System.out.println("Doit etre true : "+carteTest3.estjouable(carteTest));
        System.out.println("Doit etre false : "+carteTest1.estjouable(carteTest6));
        System.out.println("Doit etre true : "+carteTest7.estjouable(cartetest2));
        System.out.println();

        System.out.println("===test carte jokers===");
        Carte carteJ1=new Carte(Carte.Symbole.PLUS_2, Carte.Couleur.BLEU);
        Carte carteJ2=new Carte(Carte.Symbole.CHANGEMENT_DE_SENS, Carte.Couleur.ROUGE);
        Carte carteJ3=new Carte(Carte.Symbole.PLUS_4, Carte.Couleur.VERT);/*on s'en fiche de la couleur puisqu'elle deviendra automatiquement noire*/
        Carte carteJ4=new Carte(Carte.Symbole.CHANGEMENT_DE_COULEUR, Carte.Couleur.JAUNE);/*pareil*/
        Carte carteJ5=new Carte(Carte.Symbole.SANS_SYMBOLE, Carte.Couleur.VERT);/*doit creer un 0 vert*/
        Carte carteJ6=new Carte(Carte.Symbole.INTERDIT_DE_JOUER, Carte.Couleur.BLEU);
        Carte carteJ7=new Carte(Carte.Symbole.INTERDIT_DE_JOUER, Carte.Couleur.ROUGE);

        System.out.println(carteJ1);
        System.out.println(carteJ2);
        System.out.println(carteJ3);
        System.out.println(carteJ4);
        System.out.println(carteJ5);
        System.out.println(carteJ6);
        System.out.println(carteJ7);
    }
}
