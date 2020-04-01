public class Samochod {

    int nr;
    String marka;
    String model;
    int rok;
    int cena;
    byte stan;
    int przebieg;
    byte typ_skrzyni;

    public Samochod(){

   }

    public 	Samochod(int nr,String marka, String model, int rok, int cena, byte stan, int przebieg, byte typ_skrzyni){
        this.nr = nr;
        this.marka = marka;
        this.model = model;
        this.rok = rok;
        this.cena = cena;
        this.stan = stan;
        this.przebieg = przebieg;
        this.typ_skrzyni = typ_skrzyni;
    }
    
}
