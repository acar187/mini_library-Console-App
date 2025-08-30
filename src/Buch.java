import java.io.Serializable;

public class Buch implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String titel;
    private String autor;
    private String isbn;
    private boolean ausgeliehen;
    
    public Buch(String titel, String autor, String isbn){
        this.titel = titel;
        this.autor = autor;
        this.isbn = isbn;
        this.ausgeliehen = false;
    }

    public String getTitel() {
        return titel;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAusgeliehen() {
        return ausgeliehen;
    }

    public void setAusgeliehen(boolean ausgeliehen) {
        this.ausgeliehen = ausgeliehen;
    }

    @Override
    public String toString() {
        return "Titel: " + titel +" , Autor: " + autor +", ISBN: " + isbn + " (Ausleihbar " + (ausgeliehen ? "Nein" : "Ja" ) +")";
    }
}
