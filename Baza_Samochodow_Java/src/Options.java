import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

public class Options extends JPanel implements ActionListener{

    public Vector<Samochod> samochody=new Vector<Samochod>();

    private JButton[]button;

    private ReadFromFile read;

    private String []buttons={
            "Wczytaj",
            "Zapisz",
            "Dodaj nowy",
            "Usuń",
    };

    DefaultTableModel dtm;

    ContainerOfCars cars;

    Options(DefaultTableModel dtmc,ContainerOfCars ccars){

       cars=ccars;

             dtm=dtmc;



        //set options
            setBackground(new Color(77,77,77));
            setPreferredSize(new Dimension(114,500));
        //


        //set buttons
            button=new JButton[4];

            for(int i=0;i<4;i++){
               button[i]=new JButton(buttons[i]);
               button[i].addActionListener(this);
               button[i].setBackground(Color.white);
               if(i==3)
                   button[i].setBackground(new Color(150,150,150));
               button[i].setPreferredSize(new Dimension(100,30));
               add(button[i]);
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

        Object source =e.getSource();
        if(source==button[0]) {

            new ReadFromFile(cars,samochody,dtm,true);

        }

        else if(source==button[1]) {

            if(samochody.isEmpty()){
                System.out.println("Empty container.");
                return;
            }

            new ReadFromFile(cars,samochody,dtm,false);


        }

        else if(source==button[2]) {

            new addNewCar(samochody,dtm);
                cars.setread(false);
        }

        else if(source==button[3]) {
            /*
            if(cars.getdelete()==false) {
                cars.setdelete(true);
                button[3].setBackground(new Color(179,179,179));
            }
            else {
                cars.setdelete(false);
                button[3].setBackground(Color.white);
            }

             */
        }

    }
}
