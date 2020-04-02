import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Options extends JPanel implements ActionListener {

    static ContainerOfCars ccars;
    private JButton[] button;
    private String[] buttons = {
            "Wczytaj",
            "Zapisz",
            "Dodaj nowy",
            "Usuń",
    };

    Options(ContainerOfCars cars) {

        ccars = cars;

        //set options
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        setBackground(new Color(77, 77, 77));
        setPreferredSize(new Dimension(114, 500));
        //


        //set buttons
        button = new JButton[4];

        for (int i = 0; i < 4; i++) {

            c.gridy = i;
            c.gridx = 0;
            c.insets = new Insets(4, 8, 4, 8);


            button[i] = new JButton(buttons[i]);
            button[i].addActionListener(this);

            button[i].setBackground(Color.white);

            if (i == 3) {
                button[i].setBackground(new Color(150, 150, 150));
                c.insets = new Insets(4, 8, 275, 4);
            }
            //button[i].setPreferredSize(new Dimension(100, 30));
            add(button[i], c);
        }
        //
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();
        if (source == button[0]) {
            new ReadFromFile(ccars, (byte)1);
        } else if (source == button[1]) {

            if (ccars.v_cars.isEmpty()) {
                System.out.println("Empty container.");
                return;
            }

            new ReadFromFile(ccars, (byte)0);

        } else if (source == button[2]) {

            new addNewCar(ccars);
            ccars.setread(false);
        } else if (source == button[3]) {

            new ReadFromFile(ccars, (byte) 2);

            ccars.setsaved(false);
        }

    }

}
