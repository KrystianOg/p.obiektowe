import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collections;
import java.util.Vector;

public class Sort  extends JPanel {

    public String[] sort={
            "Numeryczne",
            "Najtańsze",
            "Najdroższe",
            "Najniższy przebieg",
            "Najwyższy przebieg",
            "Najstarszy",
            "Najnowszy"
    };

   private JComboBox box;
   private DefaultTableModel dtm;

Sort(Vector<Samochod> ccars,DefaultTableModel dtmc,ContainerOfCars cpyof_cars){

    dtm=dtmc;

    setBackground(new Color(60,60,60));

        box=new JComboBox(sort);
        box.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                System.out.println(cpyof_cars.getdelete());

                Object item=e.getItem();

                if(ccars.size()==0)
                    return;

                boolean ascending=true;

                if(e.getStateChange()==ItemEvent.SELECTED){
                    int []arr=new int[ccars.size()];

                if(item.toString()==sort[0]){
                    for(int i=0;i<ccars.size();i++)
                        arr[i]=ccars.get(i).nr;
                    ascending=true;
                }

                else if(item.toString()==sort[1]||item.toString()==sort[2]){
                    for(int i=0;i<ccars.size();i++)
                        arr[i]=ccars.get(i).cena;

                    if(item.toString()==sort[1])
                        ascending=true;
                    else
                        ascending=false;

                }

                else if(item.toString()==sort[3]||item.toString()==sort[4]){
                    for(int i=0;i<ccars.size();i++)
                        arr[i]=ccars.get(i).przebieg;

                    if(item.toString()==sort[3])
                        ascending=true;
                    else
                        ascending=false;

                }

                else if(item.toString()==sort[5]||item.toString()==sort[6]){
                    for(int i=0;i<ccars.size();i++)
                        arr[i]=ccars.get(i).rok;

                    if(item.toString()==sort[5])
                        ascending=true;
                    else
                        ascending=false;

                }

                if(ascending==true)
                    quicksort_ascending(arr,ccars,0,ccars.size()-1);
                else
                    quicksort_descending(arr,ccars,0,ccars.size()-1);

                    addtoshow(ccars);
            }
         }
        });

        box.setBackground(Color.white);
        add(box);


    }

        public static void quicksort_ascending(int[] arr,Vector<Samochod> vec, int start, int end){

            int i=start;
            int j=end;
            int x=arr[(start+end)/2];

            do {
                while (arr[i] < x)
                    i++;
                while (arr[j] > x)
                    j--;

                if (i <= j) {
                    Collections.swap(vec, i, j);

                    int temp=arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp;

                    i++;
                    j--;
                }
            }while(i<=j);

                if (start < j)
                    quicksort_ascending(arr, vec, start, j);
                if (end > i)
                    quicksort_ascending(arr, vec, i, end);
        }

    public static void quicksort_descending(int[] arr,Vector<Samochod> vec, int start, int end){

        int i=start;
        int j=end;
        int x=arr[(start+end)/2];

        do {
            while (arr[i] > x)
                i++;
            while (arr[j] < x)
                j--;

            if (i <= j) {
                Collections.swap(vec, i, j);

                int temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;

                i++;
                j--;
            }
        }while(i<=j);

        if (start < j)
            quicksort_descending(arr, vec, start, j);
        if (end > i)
            quicksort_descending(arr, vec, i, end);
    }


        private void addtoshow(Vector<Samochod> ccars){

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


}
