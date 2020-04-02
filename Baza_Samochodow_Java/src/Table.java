import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Table extends JPanel implements ActionListener {

    JTable t1;

    Table(ContainerOfCars ccars) {

        t1 = new JTable();


        String[] cnames = {"numer", "marka", "model", "rok produkcji", "cena", "stan", "przebieg", "typ skrzyni"};

        ccars.cpy_dtm.setColumnIdentifiers(cnames);

        t1.setModel(ccars.cpy_dtm);

        JScrollPane scpane = new JScrollPane(t1);
        scpane.setPreferredSize(new Dimension(660, 456));

        //t1.setSelectionMode(ListSelectionModel.);

        add(scpane);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        t1 = (JTable) e.getSource();

        int row = t1.getSelectedRow();

        System.out.println(row);
    }
}

