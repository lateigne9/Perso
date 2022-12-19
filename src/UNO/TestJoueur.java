package UNO;


import java.util.ArrayList;

public class TestJoueur {
    public static void main(String[] args) {
        Paquet paquet1=new Paquet();
//        System.out.println(paquet1);
        ArrayList<Carte> mainRIRI=new ArrayList<>();
        Carte carteTest=new Carte();
        Carte carteTestCouleur=new Carte(Carte.Couleur.BLEU,2);
        Carte carteTest1=new Carte(Carte.Couleur.VERT,7);
        Carte cartetest2=new Carte(Carte.Couleur.ROUGE,3);
        Carte carteTest3=new Carte(Carte.Couleur.JAUNE, -1);
        Carte carteTest4=new Carte(Carte.Couleur.VERT,10);
        Carte carteTest5=new Carte(Carte.Couleur.VERT,2);
        Carte carteTest6=new Carte(Carte.Couleur.JAUNE,6);
        Carte carteTest7=new Carte(Carte.Couleur.ROUGE,8);
        mainRIRI.add(carteTest1);
        mainRIRI.add(cartetest2);
        mainRIRI.add(carteTest3);
        mainRIRI.add(carteTest4);
        mainRIRI.add(carteTest5);
        mainRIRI.add(carteTest6);
        mainRIRI.add(carteTest7);
        mainRIRI.add(carteTest);

        Joueur joueur1=new Joueur("riri");
        System.out.println(joueur1);

        System.out.println();
        System.out.println("===getter main===");
        System.out.println(joueur1.getMain());
        System.out.println("\n===getter main position===");
        for (int i = 0; i < joueur1.getMain().size(); i++) {
            System.out.println("carte a la position "+i+ "="+joueur1.getMain().get(i));
        }

        System.out.println("\n===getter nom===");
        System.out.println(joueur1.getNom());

        System.out.println("\n===setter main===");

    }
}
