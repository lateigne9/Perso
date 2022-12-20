package UNO;


import java.util.ArrayList;

public class TestJoueur {
    public static void main(String[] args) {
        Paquet paquet1=new Paquet();
//        System.out.println(paquet1);
        ArrayList<Carte> mainRIRI=new ArrayList<>();
        Carte carteTest=new Carte();
        Carte carteTestCouleur=new Carte(2,Carte.Couleur.BLEU);
        Carte carteTest1=new Carte(7,Carte.Couleur.VERT);
        Carte cartetest2=new Carte(3,Carte.Couleur.ROUGE);
        Carte carteTest3=new Carte(-1,Carte.Couleur.JAUNE);
        Carte carteTest4=new Carte(10,Carte.Couleur.VERT);
        Carte carteTest5=new Carte(2,Carte.Couleur.VERT);
        Carte carteTest6=new Carte(6,Carte.Couleur.JAUNE);
        Carte carteTest7=new Carte(8,Carte.Couleur.ROUGE);
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
