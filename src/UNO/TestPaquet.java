package UNO;

public class TestPaquet {
    public static void main(String[] args) {
        Paquet paquetTest=new Paquet();
        System.out.println(paquetTest);

        for (int i = 0; i < 10; i++) {
            System.out.println(paquetTest.donneCarte());
        }
        System.out.println(paquetTest);
    }
}
