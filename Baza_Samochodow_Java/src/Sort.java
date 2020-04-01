import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collections;

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

   static ContainerOfCars ccars;

Sort(ContainerOfCars cars){

    ccars=cars;

    setBackground(new Color(60,60,60));

        box=new JComboBox(sort);
        box.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                Object item=e.getItem();

                if(ccars.v_cars.size()==0)
                    return;

                boolean ascending=true;

                if(e.getStateChange()==ItemEvent.SELECTED){
                    int []arr=new int[ccars.v_cars.size()];

                if(item.toString().equals(sort[0])){
                    for(int i=0;i<ccars.v_cars.size();i++)
                        arr[i]=ccars.v_cars.get(i).nr;
                    ascending=true;
                }

                else if(item.toString()==sort[1]||item.toString()==sort[2]){
                    for(int i=0;i<ccars.v_cars.size();i++)
                        arr[i]=ccars.v_cars.get(i).cena;

                    if(item.toString()==sort[1])
                        ascending=true;
                    else
                        ascending=false;

                }

                else if(item.toString()==sort[3]||item.toString()==sort[4]){
                    for(int i=0;i<ccars.v_cars.size();i++)
                        arr[i]=ccars.v_cars.get(i).przebieg;

                    if(item.toString()==sort[3])
                        ascending=true;
                    else
                        ascending=false;

                }

                else if(item.toString()==sort[5]||item.toString()==sort[6]){
                    for(int i=0;i<ccars.v_cars.size();i++)
                        arr[i]=ccars.v_cars.get(i).rok;

                    if(item.toString()==sort[5])
                        ascending=true;
                    else
                        ascending=false;

                }

                if(ascending==true)
                    quicksort_ascending(arr,0,ccars.v_cars.size()-1);
                else
                    quicksort_descending(arr,0,ccars.v_cars.size()-1);

                    addtoshow();
            }
         }
        });

        box.setBackground(Color.white);
        add(box);
    }

        public static void quicksort_ascending(int[] arr, int start, int end){

            int i=start;
            int j=end;
            int x=arr[(start+end)/2];

            do {
                while (arr[i] < x)
                    i++;
                while (arr[j] > x)
                    j--;

                if (i <= j) {
                    Collections.swap(ccars.v_cars, i, j);

                    int temp=arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp;

                    i++;
                    j--;
                }
            }while(i<=j);

                if (start < j)
                    quicksort_ascending(arr, start, j);
                if (end > i)
                    quicksort_ascending(arr, i, end);
        }

    public static void quicksort_descending(int[] arr, int start, int end){

        int i=start;
        int j=end;
        int x=arr[(start+end)/2];

        do {
            while (arr[i] > x)
                i++;
            while (arr[j] < x)
                j--;

            if (i <= j) {
                Collections.swap(ccars.v_cars, i, j);

                int temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;

                i++;
                j--;
            }
        }while(i<=j);

        if (start < j)
            quicksort_descending(arr, start, j);
        if (end > i)
            quicksort_descending(arr, i, end);
    }


        private void addtoshow(){

            while(ccars.dtm.getRowCount()>0)
                ccars.dtm.removeRow(0);

            for(int i=0;i<ccars.v_cars.size();i++){

                String nstan=new String();
                String ntyp_s=new String();

                if(ccars.v_cars.get(i).stan==0)
                    nstan="Używany";

                else if(ccars.v_cars.get(i).stan==1)
                    nstan="Nowy";

                if(ccars.v_cars.get(i).typ_skrzyni==0)
                    ntyp_s="Manualna";

                else if(ccars.v_cars.get(i).typ_skrzyni==1)
                    ntyp_s="Automatyczna";

                ccars.dtm.addRow(new Object[]{
                        ccars.v_cars.get(i).nr,
                        ccars.v_cars.get(i).marka,
                        ccars.v_cars.get(i).model,
                        ccars.v_cars.get(i).rok,
                        ccars.v_cars.get(i).cena,
                        nstan,
                        ccars.v_cars.get(i).przebieg,
                        ntyp_s,
                });

            }
        }


}
