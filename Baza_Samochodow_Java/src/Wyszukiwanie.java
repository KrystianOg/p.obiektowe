import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Wyszukiwanie extends JPanel implements ActionListener{

    private String[] labels={
            "Marka:",
            "Model:",
            "Rok produkcji",
            "Cena",
            "Przebieg",
    };

    private JLabel [] type;
    private JTextField[] txtf;
    private JButton confirm;
    //dodac do wyszukiwania checkboxy
    //private JCheckBox stan,typ_skrzyni;

    private ContainerOfCars cars;

    public Wyszukiwanie(ContainerOfCars ccars){

        cars=ccars;

        //set wyszukiwanie
            int width=160,height=440;
            setLayout(new GridBagLayout());
            GridBagConstraints c=new GridBagConstraints();

            c.insets=new Insets(3,3,3,3); //set default spacing

            setBackground(new Color(77,77,77));
            setPreferredSize(new Dimension(width,height));
        //

        //set textfields and labels
            type=new JLabel[5];

            txtf =new JTextField[8];

            int k=0,i=0,j=0;

            while(k<11){

                if(k<3||k==5||k==8) {
                    type[i] = new JLabel(labels[i]);
                    type[i].setForeground(Color.white);

                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 0;
                    c.gridy = k;


                    if (i % 3 == 2) {
                        c.weightx = 1.0;
                        c.insets = new Insets(13, 4, 3, 2); //set top spacing
                    }

                    add(type[i], c);
                    c.insets = new Insets(3, 3, 3, 3); //set top spacing to default
                    i++;
                }

                if(k<11&&k!=2&&k!=5&&k!=8) {
                    txtf[j] = new JTextField();
                    txtf[j].setPreferredSize(new Dimension(60, 18));
                   // txtf[j].getDocument().addDocumentListener(new Docu);

                    c.gridx = 1;
                    c.gridy=k;
                    c.weightx=1;


                    add(txtf[j],c);
                    j++;
                }

                //dodac stan
                //dodac typ skrzyni
                k++;
            }

            c.gridx=0;
            c.gridy=12;
            c.insets=new Insets(30,18,100,18); //set default spacing
            c.gridwidth=2;

            confirm=new JButton("Zatwierdź");
            confirm.addActionListener((ActionListener) this);
            //confirm.setPreferredSize(new Dimension(60, 18));
            confirm.setForeground(Color.black);
            confirm.setBackground(Color.white);


            add(confirm,c);

        //
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source =e.getSource();
        if(cars.getread()==false)
            return;

        if(source==confirm) {

            String marka=txtf[0].getText();
            String model=txtf[1].getText();

            int rokod=Integer.parseInt(txtf[2].getText()+0);
            int rokdo=Integer.parseInt(txtf[3].getText()+0);
            System.out.println(rokdo);
            int cenaod=Integer.parseInt(txtf[4].getText());
            int cenado=Integer.parseInt(txtf[5].getText());
            int przebiegod=Integer.parseInt(txtf[6].getText());
            int przebiegdo=Integer.parseInt(txtf[7].getText());

            cars.setsearch(marka,model,rokod,rokdo,cenaod,cenado,przebiegod,przebiegdo);

            cars.showtable();

        }

    }
}
