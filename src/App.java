import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Buch b1 = new Buch("Gucci", "Kevin Costner", "1234");
        Buch b2 = new Buch("Barbie", "Marvel","2222");
        Buch b3 = new Buch("The Barkeeper", "Payman", "3333");
    
        Bibliothek bib = Bibliothek.ladenBinaer("bibliothek.ser");
        // bib.buchHinzufügen(b1);
        // bib.buchHinzufügen(b2);
        // bib.buchHinzufügen(b3); 

        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            System.out.println("\n----Mini-Library----");
            System.out.println("1. Alle Bücher anzeigen");
            System.out.println("2. Buch hinzufügen");
            System.out.println("3. Buch nach Autor suchen");
            System.out.println("4. Buch ausleihen");
            System.out.println("5. Buch zurückgeben");
            System.out.println("6. Beenden");
            System.out.print("Auswahl: ");
            int auswahl = scanner.nextInt();
            scanner.nextLine(); //Puffer leeren
            System.out.println();

            switch (auswahl) {
                case 1:
                    bib.alleBucherAnzeigen();
                    break;
                case 2:
                    System.out.print("Titel: ");
                    String titel = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = scanner.nextLine();
                    bib.buchHinzufügen(new Buch(titel, autor, isbn));
                    break;
                case 3: 
                    System.out.print("Autor eingeben: ");
                    String a = scanner.nextLine();
                    bib.buchNachAutor(a);
                    break;
                case 4:
                    bib.alleBucherAnzeigen();
                    System.out.print("Buchnummer eingeben: ");
                    int buchnr = scanner.nextInt();
                    bib.buchAusleihen(buchnr);
                    break;
                case 5:
                    bib.alleBucherAnzeigen();
                    System.out.print("Buchnummer eingeben: ");
                    int buchnrz = scanner.nextInt();
                    bib.buchZurückgeben(buchnrz);
                    break;
                case 6:
                    //Speichern beim Beenden
                    bib.speichernBinaer("bibliothek.ser");
                    bib.speichernText("bibliothek.txt");
                    System.out.println("Vielen Dank für den Besuch.");
                    running = false;
                    break;
                default:
                    System.out.println("Ungültige Auswahl");
                    break;
            }
        }
        scanner.close();
    }
}
