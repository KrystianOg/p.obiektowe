import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Collections;

public class Sort extends JPanel {

    static ContainerOfCars ccars;
    public String[] sort = {
            "Numeryczne",
            "Najtańsze",
            "Najdroższe",
            "Najniższy przebieg",
            "Najwyższy przebieg",
            "Najstarszy",
            "Najnowszy"
    };

    Sort(ContainerOfCars cars) {

        ccars = cars;

        setBackground(new Color(60, 60, 60));

        JComboBox box = new JComboBox(sort);
        box.addItemListener(e -> {

            Object item = e.getItem();

            if (ccars.v_cars.size() == 0)
                return;

            boolean ascending = true;

            if (e.getStateChange() == ItemEvent.SELECTED) {
                int[] arr = new int[ccars.v_cars.size()];

                if (item.toString().equals(sort[0])) {
                    for (int i = 0; i < ccars.v_cars.size(); i++)
                        arr[i] = ccars.v_cars.get(i).nr;
                    ascending = true;
                } else if (item.toString().equals(sort[1]) || item.toString().equals(sort[2])) {
                    for (int i = 0; i < ccars.v_cars.size(); i++)
                        arr[i] = ccars.v_cars.get(i).cena;

                    ascending = item.toString().equals(sort[1]);

                } else if (item.toString().equals(sort[3]) || item.toString().equals(sort[4])) {
                    for (int i = 0; i < ccars.v_cars.size(); i++)
                        arr[i] = ccars.v_cars.get(i).przebieg;

                    ascending = item.toString().equals(sort[3]);

                } else if (item.toString().equals(sort[5]) || item.toString().equals(sort[6])) {
                    for (int i = 0; i < ccars.v_cars.size(); i++)
                        arr[i] = ccars.v_cars.get(i).rok;

                    ascending = item.toString().equals(sort[5]);

                }

                if (ascending)
                    quicksort_ascending(arr, 0, ccars.v_cars.size() - 1);
                else
                    quicksort_descending(arr, 0, ccars.v_cars.size() - 1);

                addtoshow();
            }
        });

        box.setBackground(Color.white);
        add(box);
    }

    public static void quicksort_ascending(int[] arr, int start, int end) {

        int i = start;
        int j = end;
        int x = arr[(start + end) / 2];

        do {
            while (arr[i] < x)
                i++;
            while (arr[j] > x)
                j--;

            if (i <= j) {
                Collections.swap(ccars.v_cars, i, j);

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

                i++;
                j--;
            }
        } while (i <= j);

        if (start < j)
            quicksort_ascending(arr, start, j);
        if (end > i)
            quicksort_ascending(arr, i, end);
    }

    public static void quicksort_descending(int[] arr, int start, int end) {

        int i = start;
        int j = end;
        int x = arr[(start + end) / 2];

        do {
            while (arr[i] > x)
                i++;
            while (arr[j] < x)
                j--;

            if (i <= j) {
                Collections.swap(ccars.v_cars, i, j);

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

                i++;
                j--;
            }
        } while (i <= j);

        if (start < j)
            quicksort_descending(arr, start, j);
        if (end > i)
            quicksort_descending(arr, i, end);
    }


    private void addtoshow() {

        while (ccars.cpy_dtm.getRowCount() > 0)
            ccars.cpy_dtm.removeRow(0);

        for (int i = 0; i < ccars.v_cars.size(); i++) {

            String nstan = "";
            String ntyp_s = "";

            if (ccars.v_cars.get(i).stan == 0)
                nstan = "Używany";

            else if (ccars.v_cars.get(i).stan == 1)
                nstan = "Nowy";

            if (ccars.v_cars.get(i).typ_skrzyni == 0)
                ntyp_s = "Manualna";

            else if (ccars.v_cars.get(i).typ_skrzyni == 1)
                ntyp_s = "Automatyczna";

            ccars.cpy_dtm.addRow(new Object[]{
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
