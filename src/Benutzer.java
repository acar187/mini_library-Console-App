import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Benutzer implements Serializable{
    private static final long serialVersionUID = 1L;

    private String name;
    private int id;
    List<Buch> ausgelieheneBuecher = new ArrayList<>();

    public Benutzer(String name, int id){
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<Buch> getAusgeliehenBuecher() {
        return ausgelieheneBuecher;
    }

    public void buchAusleihen(Buch b){
        if (!b.isAusgeliehen()) {
            b.setAusgeliehen(true);
            ausgelieheneBuecher.add(b); 
            System.out.println(name + " hat " + b.getTitel() +"ausgeliehen");   
        }
        else{
            System.out.println(b.getTitel() + " ist bereits ausgeliehen.");
        }
    }

    public void buchZurückgeben(Buch b){
        if (ausgelieheneBuecher.contains(b)) {
            b.setAusgeliehen(false);
            ausgelieheneBuecher.remove(b);
            System.out.println(name + " hat " + b.getTitel() +"zurückegeben.");
        }
        else{
            System.out.println(b.getTitel() + " ist nicht vom " + name +" ausgeliehen");
        }
        
    }
    
    @Override
    public String toString() {
        return "Benutzer: " +  name + " (ID: " + id + "), ausgeliehen: " + ausgelieheneBuecher.size(); 
    }


}
