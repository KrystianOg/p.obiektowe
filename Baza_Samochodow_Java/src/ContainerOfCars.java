import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class ContainerOfCars {

    private boolean read=false,saved=false;
    private boolean delete=false;

    //search
        String marka,model;
        private int rokod,rokdo;
        private int cenaod,cenado;
        private int przebiegod,przebiegdo;
    //




    ContainerOfCars(){

    }

    void wczytaj(Vector<Samochod> ccars, DefaultTableModel dtm,String path) throws IOException {

        ccars.clear();

        File file=new File(path);


        //there instead of *this thing* the user could select a file to read from

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

            ccars.add(copy);
        }
        inFile.close();

        showtable(dtm,ccars);

        read=true;
        System.out.println("Wczytano");
    }

    public void zapisz(Vector<Samochod> ccars,String path) throws IOException {
        FileWriter w=new FileWriter(path);
        int i=0;


        while(i<ccars.size()){
            w.write(ccars.get(i).nr +" ");
            w.write(ccars.get(i).marka+" ");
            w.write(ccars.get(i).model+" ");
            w.write(ccars.get(i).rok +" ");
            w.write(ccars.get(i).cena +" ");
            w.write(ccars.get(i).stan +" ");
            w.write(ccars.get(i).przebieg +" ");
            w.write(ccars.get(i).typ_skrzyni +"\n");


            i++;
        }
        w.close();
        saved=true;
        System.out.println("Zapisano");
    }


    public void usun(Vector<Samochod> cars,DefaultTableModel dtm,int row){
        Collections.swap(cars,row,cars.size()-1);
        cars.get(row).nr=row;
        cars.remove(cars.size()-1);
        showtable(dtm,cars);
    }

    public void showtable(DefaultTableModel dtm,Vector<Samochod> ccars){


        while(dtm.getRowCount()>0)
            dtm.removeRow(0);

        for(int i=0;i<ccars.size();i++){

            String nstan=new String();
            String ntyp_s=new String();

            if(ccars.get(i).stan==0)
                nstan="Używany";

            else if(ccars.get(i).stan==1)
                nstan="Nowy";

            if(ccars.get(i).typ_skrzyni==0)
                ntyp_s="Manualna";

            else if(ccars.get(i).typ_skrzyni==1)
                ntyp_s="Automatyczna";


            dtm.addRow(new Object[]{
                    ccars.get(i).nr,
                    ccars.get(i).marka,
                    ccars.get(i).model,
                    ccars.get(i).rok,
                    ccars.get(i).cena,
                    nstan,
                    ccars.get(i).przebieg,
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