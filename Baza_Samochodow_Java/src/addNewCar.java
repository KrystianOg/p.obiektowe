import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class addNewCar extends JFrame implements ActionListener{

    private String[] labelname={
        "Marka:",
        "Model:",
        "Rok produkcji:",
        "Przebieg:",
        "Cena:",
        "Stan:",
        "Typ skrzyni:"
    };

    private JLabel[] label;
    private JTextField[] txtf;

    private ButtonGroup stan;
    private JRadioButton []stanb;

    private ButtonGroup typ_skrzyni;
    private JRadioButton[] typ_skrzynib;

    private JButton addandexit ;

    DefaultTableModel dtm;

    Vector<Samochod> ccars;

    addNewCar(Vector<Samochod> cars,DefaultTableModel dtmc){

        ccars=cars;

       dtm=dtmc;
        setLayout(new GridBagLayout());
        setTitle("Dodaj nowy samochód");
        setIconImage(Toolkit.getDefaultToolkit().getImage("images/samochody.png"));

     //szerokosc i wysokosc ekranu w px
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sch = screenSize.height;
        int scw = screenSize.width;
     //

     //set square
        int w=400,h=400;

     //set new jframe
        getContentPane().setBackground(new Color(60, 60, 60));
        setSize(w, h);
        setLocation((scw-w)/2, ((sch-h) / 2));
        setResizable(false);
     //

    //set labels and text
        label=new JLabel[7];
        txtf=new JTextField[5];

        //set radio buttons
            stan=new ButtonGroup();
            stanb=new JRadioButton[2];
            stanb[0]=new JRadioButton("Nowy",false);
            stanb[0].setBounds(50,150,100,30);
            stanb[1]=new JRadioButton("Używany",false);
            stanb[1].setBounds(50,150,100,30);
            stan.add(stanb[0]);
            stan.add(stanb[1]);

            typ_skrzyni=new ButtonGroup();
            typ_skrzynib=new JRadioButton[2];
            typ_skrzynib[0]=new JRadioButton("Automatyczna",false);
            typ_skrzynib[0].setBounds(50,150,100,30);
            typ_skrzynib[1]=new JRadioButton("Manualna",false);
            typ_skrzynib[1].setBounds(50,150,100,30);
            typ_skrzyni.add(typ_skrzynib[0]);
            typ_skrzyni.add(typ_skrzynib[1]);
        //
    //

        GridBagConstraints c=new GridBagConstraints();
        c.insets=new Insets(3,3,3,3); //set default spacing
        c.fill = GridBagConstraints.HORIZONTAL;


        for(int i=0;i<9;i++){
            c.gridy=i;

            c.gridx=0;

            if(i<7) {
                label[i] = new JLabel(labelname[i]);
                label[i].setForeground(Color.white);
                add(label[i], c);
            }

            c.gridx=1;
            if(i<5){
                txtf[i] = new JTextField();
                txtf[i].setPreferredSize(new Dimension(65, 18));
                c.gridwidth = 2;
                add(txtf[i],c);
                c.gridwidth=1;
            }
            else if(i==5) {
                add(stanb[0], c);
            }
            else if(i==6) {
                add(typ_skrzynib[0], c);
            }

            c.gridx=2;
            if(i==5) {
                add(stanb[1], c);
            }
            else if(i==6) {
                add(typ_skrzynib[1], c);
            }

        }

        c.gridx=1;
        c.gridy=7;
        c.insets=new Insets(40,3,3,3); //set default spacing
        addandexit=new JButton("Dodaj");
        addandexit.addActionListener((ActionListener) this);
        addandexit.setBackground(Color.white);
        addandexit.setPreferredSize(new Dimension(100,30));

        add(addandexit,c);

        setVisible(true);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source =e.getSource();
        if(source==addandexit) {

            int i=ccars.size();
            Samochod copy=new Samochod();

            //dodanie nowego do vectora
            copy.nr=i+1;
            copy.marka=txtf[0].getText();
            copy.model=txtf[1].getText();
            copy.rok= Integer.parseInt(txtf[2].getText()); //convert s to i
            copy.cena=Integer.parseInt(txtf[4].getText()); //convert s to i
            copy.stan=0;
            copy.przebieg=Integer.parseInt(txtf[3].getText()); //convert s to i
            copy.typ_skrzyni=0;

            ccars.add(copy);

            //dodanie nowego do wyswietlania

            String nstan;
            String ntyp_s;

            if(stanb[1].isSelected())
                nstan="Używany";

            else
                nstan="Nowy";

            if(typ_skrzynib[1].isSelected())
                ntyp_s="Manualna";

            else
                ntyp_s="Automatyczna";

            dtm.addRow(new Object[]{
                    copy.nr,
                    copy.marka,
                    copy.model,
                    copy.rok,
                    copy.cena,
                    nstan,
                    copy.przebieg,
                    ntyp_s
             });

            System.out.println("Dodano");

            dispose();
        }
    }
}
