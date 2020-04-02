import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Wyszukiwanie extends JPanel implements ActionListener {

    private String[] labels = {
            "Marka:",
            "Model:",
            "Rok produkcji:",
            "Cena:",
            "Przebieg:",
    };

    private JLabel[] type;
    private JTextField[] txtf;
    private JLabel[] od_;
    private JLabel[] do_;

    private JButton confirm, reset;
    //dodac do wyszukiwania checkboxy
    //private JCheckBox stan,typ_skrzyni;

    private ContainerOfCars ccars;

    public Wyszukiwanie(ContainerOfCars cars) {

        ccars = cars;

        //set wyszukiwanie
        int width = 160, height = 440;
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(3, 3, 3, 3); //set default spacing

        setBackground(new Color(77, 77, 77));
        setPreferredSize(new Dimension(width, height));
        //

        //set textfields and labels
        type = new JLabel[5];
        od_ = new JLabel[3];
        do_ = new JLabel[3];
        txtf = new JTextField[8];

        for (int i = 0; i < 3; i++) {
            od_[i] = new JLabel("od:");
            do_[i] = new JLabel("do:");
        }

        int k = 0, i = 0, j = 0;

        while (k < 11) {

            c.insets = new Insets(3, 3, 3, 3);

            if (k < 3 || k == 5 || k == 8) {
                type[i] = new JLabel(labels[i]);
                type[i].setForeground(Color.white);

                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 0;
                c.gridy = k;

                if (i % 3 == 2) {
                    c.gridwidth = 4;
                }

                c.insets = new Insets(12, 5, 3, 3);

                if (k < 3)
                    c.insets = new Insets(3, 3, 3, 3);

                add(type[i], c);
                i++;
            }

            if (k < 11 && k != 2 && k != 5 && k != 8) {
                txtf[j] = new JTextField();
                txtf[j].setPreferredSize(new Dimension(90, 18));
                // txtf[j].getDocument().addDocumentListener(new Docu);
                c.insets = new Insets(3, 0, 3, 8);
                c.gridx = 3;
                c.gridy = k;
                c.weightx = 2;
                add(txtf[j], c);

                if (k != 0 && k != 1) {
                    c.gridx = 0;
                    c.gridy = k;
                    c.weightx = 1;

                    if (k % 3 == 0) {
                        c.insets = new Insets(3, 25, 3, 0);
                        od_[k / 3 - 1].setForeground(Color.white);
                        add(od_[k / 3 - 1], c);
                    } else if (k % 3 == 1) {
                        c.insets = new Insets(3, 25, 3, 0);
                        do_[k / 3 - 1].setForeground(Color.white);
                        add(do_[k / 3 - 1], c);
                    }

                }

                j++;
            }

            //dodac stan
            //dodac typ skrzyni
            k++;
        }

        c.gridx = 0;
        c.gridy = 12;
        c.insets = new Insets(30, 8, 0, 8); //set default spacing
        c.gridwidth = 4;

        confirm = new JButton("Zatwierdź");
        confirm.addActionListener(this);
        //confirm.setPreferredSize(new Dimension(60, 18));
        confirm.setForeground(Color.black);
        confirm.setBackground(Color.white);


        add(confirm, c);

        c.gridx = 0;
        c.gridy = 13;
        c.insets = new Insets(6, 8, 40, 8); //set default spacing
        c.gridwidth = 4;

        reset = new JButton("Resetuj");
        reset.addActionListener(this);
        //confirm.setPreferredSize(new Dimension(60, 18));
        reset.setForeground(Color.black);
        reset.setBackground(Color.white);

        add(reset, c);

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
        if (ccars.v_cars.size()==0)
            return;

        if (source == confirm) {

            if (!txtf[0].getText().equals("")) {
                ccars.spomarka = txtf[0].getText();
                ccars.smarka = true;
            }
            if (!txtf[1].getText().equals("")) {
                ccars.spomodel = txtf[1].getText();
                ccars.smodel = true;
            }

            if (!txtf[2].getText().equals("") && Integer.parseInt(txtf[2].getText()) >= 0) {
                ccars.sporokod = Integer.parseInt(txtf[2].getText());
                ccars.srokod = true;
            }
            if (!txtf[3].getText().equals("") && Integer.parseInt(txtf[3].getText()) >= 0) {
                ccars.sporokdo = Integer.parseInt(txtf[3].getText());
                ccars.srokdo = true;
            }
            if (!txtf[4].getText().equals("") && Integer.parseInt(txtf[4].getText()) >= 0) {
                ccars.spocenaod = Integer.parseInt(txtf[4].getText());
                ccars.scenaod = true;
            }
            if (!txtf[5].getText().equals("") && Integer.parseInt(txtf[5].getText()) >= 0) {
                ccars.spocenado = Integer.parseInt(txtf[5].getText());
                ccars.scenado = true;
            }
            if (!txtf[6].getText().equals("") && Integer.parseInt(txtf[6].getText()) >= 0) {
                ccars.spoprzebiegod = Integer.parseInt(txtf[6].getText());
                ccars.sprzebiegod = true;
            }
            if (!txtf[7].getText().equals("") && Integer.parseInt(txtf[7].getText()) >= 0) {
                ccars.spoprzebiegdo = Integer.parseInt(txtf[7].getText());
                ccars.sprzebiegdo = true;
            }

            ccars.setsearch(ccars.spomarka, ccars.spomodel, ccars.sporokod, ccars.sporokdo, ccars.spocenaod, ccars.spocenado, ccars.spoprzebiegod, ccars.spoprzebiegdo);
        } else if (source == reset) {
            ccars.resetsearch("", "", -1, -1, -1, -1, -1, -1);
        }
        ccars.showtable();
    }
}
