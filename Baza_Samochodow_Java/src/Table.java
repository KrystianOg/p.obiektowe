import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class Table  extends JPanel {

    JTable t1;

    Table(ContainerOfCars ccars) {

        t1 = new JTable();

        String[] cnames = {"numer", "marka", "model", "rok produkcji", "cena", "stan", "przebieg", "typ skrzyni"};
        int i = 0;

        ccars.dtm.setColumnIdentifiers(cnames);

        t1.setModel(ccars.dtm);

        JScrollPane scpane = new JScrollPane(t1);
        scpane.setPreferredSize(new Dimension(660, 456));

        t1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override

            public void valueChanged(ListSelectionEvent e) {

                //tutaj będzie usuwanie

                //będzie, naprawdę

                int selected = t1.getSelectedRow();
                    if(selected>=0&&ccars.getdelete())
                    ccars.usun(selected);
                    else if(selected==-1)
                        return;
            }
        });

        add(scpane);
    }
}

