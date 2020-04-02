import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class ContainerOfCars {

    public Vector<Samochod> v_cars = new Vector<>();
    DefaultTableModel dtm = new DefaultTableModel(0, 0);
    DefaultTableModel cpy_dtm = new DefaultTableModel(0, 0);

    boolean smarka=false, smodel=false;
    boolean srokod=false, srokdo=false;
    boolean scenaod=false, scenado=false;
    boolean sprzebiegod=false, sprzebiegdo=false;

    String spomarka = "", spomodel = "";
    int sporokod = -1, sporokdo = -1;
    int spocenaod = -1, spocenado = -1;
    int spoprzebiegod = -1, spoprzebiegdo = -1;

    private boolean read;
    private boolean saved;

    ContainerOfCars() {
        read = false;
        saved = false;
    }

    void wczytaj(String path) throws IOException {

        v_cars.clear();

        new File(path);
        BufferedReader inFile = new BufferedReader(new FileReader(path));
        Scanner in = new Scanner(inFile);

        while (in.hasNextLine()) {
            Samochod copy = new Samochod();
            copy.nr = in.nextInt();
            copy.marka = in.next();
            copy.model = in.next();
            copy.rok = in.nextInt();
            copy.cena = in.nextInt();
            copy.stan = in.nextByte();
            copy.przebieg = in.nextInt();
            copy.typ_skrzyni = in.nextByte();

            v_cars.add(copy);
        }
        inFile.close();

        setdtm();

        showtable();

        read = true;
        System.out.println("Wczytano");
    }

    public void zapisz(String path) throws IOException {
        FileWriter w = new FileWriter(path);
        int i = 0;

        while (i < v_cars.size()) {

            w.write(v_cars.get(i).nr + " ");
            w.write(v_cars.get(i).marka + " ");
            w.write(v_cars.get(i).model + " ");
            w.write(v_cars.get(i).rok + " ");
            w.write(v_cars.get(i).cena + " ");
            w.write(v_cars.get(i).stan + " ");
            w.write(v_cars.get(i).przebieg + " ");
            w.write(v_cars.get(i).typ_skrzyni + "\n");

            i++;
        }
        w.close();
        saved = true;
        System.out.println("Zapisano");
    }

    //usuwanie: zamiana ostatniego z wybranym i wtedy usuniecie ostatniego

    public void usun(int row) {

        int i;

        for(i=0;i<v_cars.size();i++){
            if(v_cars.get(i).nr==row)
                break;
        }


        v_cars.get(v_cars.size()-1).nr=v_cars.get(i).nr;

        if(v_cars.size()!=i) {
            Collections.swap(v_cars, i , v_cars.size() - 1);
            v_cars.remove(v_cars.size()-1);
        }
        else
            v_cars.remove(i);

        showtable();
    }

    //

    public void showtable() {

        while (cpy_dtm.getRowCount() > 0)
            cpy_dtm.removeRow(0);

        for (int i = 0; i < v_cars.size(); i++) {

            String nstan = "";
            String ntyp_s = "";

            if (v_cars.get(i).stan == 0)
                nstan = "Używany";

            else if (v_cars.get(i).stan == 1)
                nstan = "Nowy";

            if (v_cars.get(i).typ_skrzyni == 0)
                ntyp_s = "Manualna";

            else if (v_cars.get(i).typ_skrzyni == 1)
                ntyp_s = "Automatyczna";

            if (smarka) {
                if (!spomarka.equals(getmarka(i)))
                    continue;
            }
            if (smodel) {
                if (!spomodel.equals(getmodel(i)))
                    continue;
            }
            if (scenaod) {
                if (spocenaod > v_cars.get(i).cena)
                    continue;
            }
            if (scenado) {
                if (sporokod < v_cars.get(i).cena)
                    continue;
            }
            if (srokod) {
                if (sporokod > v_cars.get(i).rok)
                    continue;
            }
            if (srokdo) {
                if (sporokdo < v_cars.get(i).rok)
                    continue;
            }

            if (sprzebiegod) {
                if (spoprzebiegod > v_cars.get(i).przebieg)
                    continue;
            }

            if (sprzebiegdo) {
                if (spoprzebiegdo < v_cars.get(i).przebieg)
                    continue;
            }

            cpy_dtm.addRow(new Object[]{
                    v_cars.get(i).nr,
                    v_cars.get(i).marka,
                    v_cars.get(i).model,
                    v_cars.get(i).rok,
                    v_cars.get(i).cena,
                    nstan,
                    v_cars.get(i).przebieg,
                    ntyp_s,
            });
        }
    }

    public void setread(boolean read) {
        this.read = read;
    }

    public void setsaved(boolean saved) {
        this.read = saved;
    }

    public boolean getread() {
        return this.read;
    }

    public boolean getsaved() {
        return this.saved;
    }

    public void resetsearch(String marka, String model, int rokod, int rokdo, int cenaod, int cenado, int przebiegod, int przebiegdo) {
        this.spomarka = marka;
        this.spomodel = model;
        this.sporokod = rokod;
        this.sporokdo = rokdo;
        this.spocenaod = cenaod;
        this.spocenado = cenado;
        this.spoprzebiegod = przebiegod;
        this.spoprzebiegdo = przebiegdo;

        this.smarka = false;
        this.smodel = false;
        this.srokod = false;
        this.srokdo = false;
        this.sprzebiegod = false;
        this.sprzebiegdo = false;
        this.scenaod = false;
        this.scenado = false;
    }

    public void setsearch(String marka, String model, int rokod, int rokdo, int cenaod, int cenado, int przebiegod, int przebiegdo) {
        this.spomarka = marka;
        this.spomodel = model;
        this.sporokod = rokod;
        this.sporokdo = rokdo;
        this.spocenaod = cenaod;
        this.spocenado = cenado;
        this.spoprzebiegod = przebiegod;
        this.spoprzebiegdo = przebiegdo;
    }

    public void setdtm() {
        while (dtm.getRowCount() > 0)
            dtm.removeRow(0);

        for (Samochod v_car : v_cars) {

            String nstan = "";
            String ntyp_s = "";

            if (v_car.stan == 0)
                nstan = "Używany";

            else if (v_car.stan == 1)
                nstan = "Nowy";

            if (v_car.typ_skrzyni == 0)
                ntyp_s = "Manualna";

            else if (v_car.typ_skrzyni == 1)
                ntyp_s = "Automatyczna";


            dtm.addRow(new Object[]{
                    v_car.nr,
                    v_car.marka,
                    v_car.model,
                    v_car.rok,
                    v_car.cena,
                    nstan,
                    v_car.przebieg,
                    ntyp_s,
            });
        }
    }

    private String getmarka(int i) {
        return this.v_cars.get(i).marka;

    }

    private String getmodel(int i) {
        return this.v_cars.get(i).model;

    }
}