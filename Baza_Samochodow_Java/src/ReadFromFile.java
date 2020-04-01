import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

public class ReadFromFile extends JFrame implements ActionListener {

    private JLabel label;
    private JLabel pickpath;
    private JTextField txtf;
    private JButton button;

    private String path=" ";

    ContainerOfCars cars;
    Vector<Samochod> samochody;
    DefaultTableModel dtm;

    private boolean read;

    ReadFromFile( ContainerOfCars ccars, Vector<Samochod> cv_cars ,DefaultTableModel dtmc,boolean cread){


        read=cread;
        cars=ccars;
        samochody=cv_cars;
        dtm=dtmc;

        if(read==true)
            setTitle("Wczytaj z pliku");
        else
            setTitle("Zapisz do pliku");

        setIconImage(Toolkit.getDefaultToolkit().getImage("images/samochody.png"));

        //szerokosc i wysokosc ekranu w px
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sch = screenSize.height;
        int scw = screenSize.width;
        //

        //set square
        int w=350,h=200;

        //set new jframe
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(60, 60, 60));
        setSize(w, h);
        setLocation((scw-w)/2, ((sch-h) / 2));
        setResizable(false);
        //

        //
        button=new JButton("Akceptuj");
        txtf=new JTextField();
        label=new JLabel("bases/");
        pickpath=new JLabel("Wprowadź ścieżkę:");
        //

        GridBagConstraints c=new GridBagConstraints();
         //set default spacing
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx=0;
        c.gridy=0;
        c.gridwidth=2;
        c.insets=new Insets(3,3,20,3); //set default spacing
        pickpath.setForeground(Color.white);
        add(pickpath,c);

        c.insets=new Insets(3,3,3,3);

        c.gridwidth=1;
        c.gridx=0;
        c.gridy=1;

        label.setForeground(Color.white);
        add(label,c);

        c.gridx=1;
        c.gridy=1;
        txtf.setPreferredSize(new Dimension(60, 18));
        add(txtf,c);

        c.gridx=0;
        c.gridy=2;
        c.gridwidth=2;

        c.insets=new Insets(7,15,3,15);
        button.setBackground(Color.white);
        button.addActionListener((ActionListener) this);
        add(button,c);

        setVisible(true);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e){

        path=txtf.getText();
        if(read==true) {
            try {
                cars.wczytaj(samochody, dtm, "bases/" + getpath()+".txt");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else {
            try {
                cars.zapisz(samochody,"bases/"+getpath()+".txt");
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        if(read==true)
            System.out.println("Wczytano");
        else
            System.out.println("Zapisano");

        dispose();
    }

    public String getpath(){
        return this.path;
    }
}
