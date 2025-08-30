import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bibliothek implements Serializable{
    private static final long serialVersionUID = 1L;
    List<Buch> buchListe = new ArrayList<>();
    List<Benutzer> benutzerListe = new ArrayList<>();
    
    //---Bücher Methoden---
    public void buchHinzufügen(Buch buch) {
        buchListe.add(buch);
    }

    public void alleBucherAnzeigen() {
        if (buchListe.isEmpty()) {
            System.out.println("Keine Bücher in der Bibliothek.");            
        }
        else{
            for (int i = 0; i < buchListe.size(); i++) {
                System.out.println(i+1 + "." + buchListe.get(i));                
            }
        }
    }

    public Buch getBuch(int index){
        if (index < 0 || index >= buchListe.size()) {
            return null;
        }
        return buchListe.get(index);
    }
    
    public void buchNachAutor(String autor) {
        for (Buch buch : buchListe) {
            if (buch.getAutor().equalsIgnoreCase(autor)) {
                int index = buchListe.indexOf(buch);
                System.out.println(index+ 1 + ": "+ buch);   
                return;             
            }           
        }
        System.out.println("Es gibt keinen Buch von diesem Autor.");
    }

    public void buchAusleihen(int index) {
        if (index <= 0 || index > buchListe.size()) {
            System.out.println("Es gibt kein Buch mit diesem Index. Ungültige Auswahl.");
            return;            
        }

        Buch buch = buchListe.get(index-1);
        if (buch.isAusgeliehen()) {
            System.out.println("Buch ist bereits ausgeliehen");            
        }
        else{
            buch.setAusgeliehen(true);
            System.out.println("Du hast \" " + buch.getTitel() + "\" ausgeliehen.");
        }
        

    }

    public void buchZurückgeben(int index) {
        if (index <= 0 || index > buchListe.size()) {
            System.out.println("Ungültige Auswahl!!!");
            return;
        }

        Buch buch = buchListe.get(index-1);

        if (!buch.isAusgeliehen()) {
            System.out.println("Buch \"" + buch.getTitel() + "\" war nicht ausgeliehen");
        }
        else{
            buch.setAusgeliehen(false);
            System.out.println("Buch \"" + buch.getTitel() + "\" zurückgegeben.");
        }
    }

    //---Benutzer Methoden---
    public void benutzerHinzufügen(Benutzer b){
        benutzerListe.add(b);
    }

    public void alleBenutzerAnzeigen(){
        if (benutzerListe.isEmpty()) {
            System.out.println("Keine Benutzer registriert.");
        }
        else{
            for (int i = 0; i < benutzerListe.size(); i++) {
                System.out.println(i+1 + ". " + benutzerListe.get(i));
            }
        }
    }
    
    public Benutzer getBenutzer(int index) {
        if (index < 0 || index >= benutzerListe.size()) {
            return null;
        }
        return benutzerListe.get(index);
    }

    //---Ausleihen / Zurückgeben---
    public void buchAusleihenFuerBenutzer(int benutzerIndex, int buchIndex){
        Benutzer user  = getBenutzer(benutzerIndex);
        Buch buch = getBuch(buchIndex);

        if (user != null && buch != null) {
            user.buchAusleihen(buch);    
        }
        else{
            System.out.println("Ungültige Auswahl.");
        }
    }

    public void buchZurueckgebenFuerBenutzer(int benutzerIndex, int buchIndex){
        Benutzer user = getBenutzer(buchIndex);
        Buch buch = getBuch(buchIndex);

        if (user != null && buch != null) { 
            user.buchZurückgeben(buch);
        }
        else{
            System.out.println("Ungültige Auswahl");
        }

    }

    //===Speichern (Binär)===
    public void speichernBinaer(String datei){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(datei));
            oos.writeObject(this);
            System.out.println("Bibliothek gespeichert (Binär).");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //===Laden (Binär)===
    public static Bibliothek ladenBinaer(String datei){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(datei));
            return (Bibliothek) ois.readObject();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("Keine gespeicherte Bibliothek gefunden. Neue wird erstellt.");
            return new Bibliothek();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new Bibliothek();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new Bibliothek();
        }


    }

    //=== Speichern (Text) ===
    public void speichernText(String datei){
        try {
            // File f = new File(datei);
            // f.setWritable(true, false);
            PrintWriter pw = new PrintWriter(new File(datei));
            pw.println("=== Bibliothek ===");
            int count = 1;
            for (Buch buch : buchListe) {
                pw.println(count++ + " " + buch);    
            }
            pw.println("\n=== Benutzer ===");
            for (Benutzer benutzer : benutzerListe) {
                pw.println(benutzer);
                for (Buch buch : benutzer.ausgelieheneBuecher) {
                    pw.println("  -" + buch.getTitel());                   
                }
            }
            pw.close();
            System.out.println("Bibliothek gespeichrt (Text).");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
