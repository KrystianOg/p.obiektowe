import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class ContainerOfCars {

    private boolean read;
    private boolean saved;
    private boolean delete;

    public Vector<Samochod> v_cars =new Vector<>();
    DefaultTableModel dtm=new DefaultTableModel(0,0);

    //search
        String marka,model;
        private int rokod,rokdo;
        private int cenaod,cenado;
        private int przebiegod,przebiegdo;
    //

    ContainerOfCars(){

        read=false;
        saved=false;
        delete=false;
    }

    void wczytaj(String path) throws IOException {

        v_cars.clear();

        File file=new File(path);

        BufferedReader inFile = new BufferedReader(new FileReader(path));
        Scanner in =new Scanner(inFile);

        while(in.hasNextLine()){
            Samochod copy = new Samochod();
            copy.nr=in.nextInt();
            copy.marka=in.next();
            copy.model=in.next();
            copy.rok=in.nextInt();
            copy.cena=in.nextInt();
            copy.stan=in.nextByte();
            copy.przebieg=in.nextInt();
            copy.typ_skrzyni=in.nextByte();

            v_cars.add(copy);
        }
        inFile.close();

        showtable();

        read=true;
        System.out.println("Wczytano");
    }

    public void zapisz(String path) throws IOException {
        FileWriter w=new FileWriter(path);
        int i=0;

        while(i<v_cars.size()){
            w.write(v_cars.get(i).nr +" ");
            w.write(v_cars.get(i).marka+" ");
            w.write(v_cars.get(i).model+" ");
            w.write(v_cars.get(i).rok +" ");
            w.write(v_cars.get(i).cena +" ");
            w.write(v_cars.get(i).stan +" ");
            w.write(v_cars.get(i).przebieg +" ");
            w.write(v_cars.get(i).typ_skrzyni +"\n");

            i++;
        }
        w.close();
        saved=true;
        System.out.println("Zapisano");
    }

    public void usun(int row){
        Collections.swap(v_cars,row,v_cars.size()-1);
        v_cars.get(row).nr=row;
        v_cars.remove(v_cars.size()-1);
        showtable();
    }

    public void showtable(){

        while(dtm.getRowCount()>0)
            dtm.removeRow(0);

        for(int i=0;i<v_cars.size();i++){

            String nstan=new String();
            String ntyp_s=new String();

            if(v_cars.get(i).stan==0)
                nstan="Używany";

            else if(v_cars.get(i).stan==1)
                nstan="Nowy";

            if(v_cars.get(i).typ_skrzyni==0)
                ntyp_s="Manualna";

            else if(v_cars.get(i).typ_skrzyni==1)
                ntyp_s="Automatyczna";


            dtm.addRow(new Object[]{
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

        /*
    public void changerow(int newrow){
        row=newrow;

    }
        */

    public void setread(boolean read){
        this.read=read;
    }

    public boolean getread(){
        return this.read;
    }

    public boolean getsaved(){
        return this.saved;
    }

    public void setdelete(boolean deleting){
        this.delete=deleting;
    }

    public boolean getdelete(){
        return this.delete;
    }

    public void setsearch(String marka,String model,int rokod,int rokdo,int cenaod,int cenado,int przebiegod,int przebiegdo){
        this.marka=marka;
        this.model=model;
        this.rokod=rokod;
        this.rokdo=rokdo;
        this.cenaod=cenaod;
        this.cenado=cenado;
        this.przebiegod=przebiegod;
        this.przebiegdo=przebiegdo;
    }
}