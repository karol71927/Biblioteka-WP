import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class Konto {
    private int StatusKonta;
    private int WypozyczoneKsiazki;
    private int ZarezerwowaneKsiazki;

    public Konto(String imie, String nazwisko){
        this.WypozyczoneKsiazki = sprawdzWypozyczone(imie,nazwisko);
        this.ZarezerwowaneKsiazki = sprawdzRezerwacje(imie, nazwisko);
    }

    public int sprawdzWypozyczone(String imie, String nazwisko) {
        BazaKsiazek bazaKsiazek = new BazaKsiazek();
        ArrayList<String> lista = new ArrayList<>();
        int ileKsiazek = 0;
        lista = bazaKsiazek.wyszukaj(imie, nazwisko, 0);
        for(int i = 0; i < lista.size(); i+=4) {
            ileKsiazek++;
        }
        for(int i = 0; i < lista.size(); i+=4) {
            for(int j = 0; j < 4; j++) {
                System.out.print(lista.get(j) + " ");
            }
            System.out.println();
        }
        return ileKsiazek;
    }

    public int sprawdzRezerwacje(String imie, String nazwisko) {
        BazaKsiazek bazaKsiazek = new BazaKsiazek();
        ArrayList<String> lista = new ArrayList<>();
        int ileKsiazek = 0;
        lista = bazaKsiazek.wyszukaj(imie, nazwisko, 2);
        for(int i = 0; i < lista.size(); i+=4) {
            ileKsiazek++;
        }
        for(int i = 0; i < lista.size(); i+=4) {
            for(int j = 0; j < 4; j++) {
                System.out.print(lista.get(j) + " ");
            }
            System.out.println();
        }
        return ileKsiazek;
    }

    public void przedluzWaznoscKonta(String imie, String nazwisko) {
        if(StatusKonta == 0) {
            try {
                StatusKonta = 1;
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotekadb",
                        "root",
                        "");
                Statement statement = con.createStatement();
                statement.executeUpdate("UPDATE Uzytkownik SET Uzytkownik.StatusKonta = 1 WHERE Uzytkownik.imie = '" + imie + "' AND Uzytkownik.nazwisko = '" + nazwisko + "' ;");
            } catch(Exception e) {
                System.out.println(e.toString());
            }
        }
    }

}