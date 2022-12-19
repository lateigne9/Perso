package UNO;

public class TestCarte {
    public static void main(String[] args) {
        Carte carteTest=new Carte();
        Carte carteTestCouleur=new Carte(Carte.Couleur.BLEU,2);
        Carte carteTest1=new Carte(Carte.Couleur.VERT,7);
        Carte cartetest2=new Carte(Carte.Couleur.ROUGE,3);
        Carte carteTest3=new Carte(Carte.Couleur.JAUNE, -1);
        Carte carteTest4=new Carte(Carte.Couleur.VERT,10);
        Carte carteTest5=new Carte(Carte.Couleur.VERT,2);
        Carte carteTest6=new Carte(Carte.Couleur.JAUNE,6);
        Carte carteTest7=new Carte(Carte.Couleur.ROUGE,8);



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

        System.out.println("test est jouable");
        System.out.println("Doit est false : " +carteTest.estjouable(carteTestCouleur));
        System.out.println("Doit etre true : "+carteTest3.estjouable(carteTest));
        System.out.println("Doit etre false : "+carteTest1.estjouable(carteTest6));
        System.out.println("Doit etre true : "+carteTest7.estjouable(cartetest2));
    }

}
