import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadFromFile extends JFrame implements ActionListener {

    static ContainerOfCars cars;
    private JLabel label;
    private JLabel pickpath;
    private JLabel txt;
    private JTextField txtf;
    private JButton button;
    private String path = " ";
    private byte read;

    ReadFromFile(ContainerOfCars ccars, byte cread) {

        read = cread;
        cars = ccars;

        if (read == 1)
            setTitle("Wczytaj z pliku");
        else if(read==0)
            setTitle("Zapisz do pliku");
        else if(read==2)
            setTitle("Usuń");

        setIconImage(Toolkit.getDefaultToolkit().getImage("images/samochody.png"));

        //szerokosc i wysokosc ekranu w px
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sch = screenSize.height;
        int scw = screenSize.width;
        //

        //set square
        int w = 350, h = 200;

        //set new jframe
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(60, 60, 60));
        setSize(w, h);
        setLocation((scw - w) / 2, ((sch - h) / 2));
        setResizable(false);
        //

        //
        button = new JButton("Akceptuj");
        txtf = new JTextField();

        if(read==2){
            label=new JLabel("nr:");
        }

        pickpath = new JLabel("Podaj numer samochodu do usunięcia.");

        if(read!=2) {
            label = new JLabel("bases /");
            txt = new JLabel(".txt");
            pickpath = new JLabel("Wprowadź ścieżkę:");
        }
        //

        GridBagConstraints c = new GridBagConstraints();
        //set default spacing
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(3, 3, 20, 10); //set default spacing


        pickpath.setForeground(Color.white);
        add(pickpath, c);

        c.insets = new Insets(3, 3, 3, 3);

        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(3, 3, 3, 1);
        label.setForeground(Color.white);
        add(label, c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        txtf.setPreferredSize(new Dimension(60, 18));
        if(read==2) {
            c.gridwidth = 3;
            txtf.setPreferredSize(new Dimension(50, 18));
            c.insets = new Insets(3, 4, 2, 20);
        }

        add(txtf, c);

        c.gridx = 3;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(3, 1, 3, 3);

        if(read!=2) {
            txt.setForeground(Color.white);
            add(txt, c);
        }

        c.gridx = 2;
        c.gridy = 3;
        c.gridwidth = 2;
        c.insets = new Insets(18, 15, 3, 40);
        button.setBackground(Color.white);
        button.addActionListener(this);
        add(button, c);

        setVisible(true);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        action_end();

    }


    public void action_end() {
        path = txtf.getText();
        if (read == 1) {
            try {
                cars.wczytaj("bases/" + getpath() + ".txt");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if(read==0) {
            try {
                cars.zapisz("bases/" + getpath() + ".txt");
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else if(read==2){
            cars.usun(Integer.parseInt(getpath()));
        }


        if (read == 1)
            System.out.println("Wczytano");
        else if(read==0)
            System.out.println("Zapisano");
        else if(read==2) {
            System.out.println("Usunięto samochoód nr: " + getpath());
        }
        dispose();
    }


    public String getpath() {
        return this.path;
    }
}


