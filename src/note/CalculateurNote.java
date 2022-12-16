package note;

import java.util.Arrays;
import java.util.Scanner;

public class CalculateurNote {
    private Note[] tabnotes;
    private final int MAX=100,NOTE_DE_PASSAGE=60;


    public int demandNbrNote(){
        Scanner input=new Scanner(System.in);
        System.out.print("Nombre de notes: ");
        return input.nextInt();
    }
    public void demandeNotePourcentage(int nombreDeNote){
        double note;
        int pourcent;
        Scanner input=new Scanner(System.in);
        this.tabnotes=new Note[nombreDeNote];
        for (int i = 0; i < nombreDeNote; i++) {
            System.out.print("note "+ (i+1)+ " : ");
            note= input.nextDouble();
            System.out.print("Pourcentage: ");
            pourcent= input.nextInt();
            tabnotes[i]=new Note(note,pourcent);
        }
        System.out.println(Arrays.toString(tabnotes));
    }

    public void resteAvoir(){
        int totpourcentage=0;
        double totNotes=0;
        for (Note note : tabnotes) {
            totNotes+=note.getNote();
            totpourcentage+=note.getPourcentage();
        }
        System.out.println("note actuelle:"+ totNotes+'/'+totpourcentage);
        System.out.println("il vous faut "+ (MAX-totNotes)+ " pour passer le cours");
    }
}
