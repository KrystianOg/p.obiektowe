import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Mainframe extends JFrame {

    static public ContainerOfCars ccars = new ContainerOfCars();
    private Wyszukiwanie search;
    private Options options;
    private Sort sort;
    private Table array;

    public Mainframe() {

        //set window
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sch = screenSize.height;
        int scw = screenSize.width;

        setTitle("Baza samochodów");
        setIconImage(Toolkit.getDefaultToolkit().getImage("images/samochody.png"));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window is in the process of being closed.
             * The close operation can be overridden at this point.
             *
             * @param e
             */
            @Override
            public void windowClosing(WindowEvent e) {
                windowlistenerfunc();
            }
        });

        getContentPane().setBackground(new Color(230, 230, 230));
        setSize(scw / 2, sch / 2);
        setLocation((scw / 4), (sch / 4));
        setResizable(false);
        //

        options = new Options(ccars);
        search = new Wyszukiwanie(ccars);
        sort = new Sort(ccars);
        array = new Table(ccars);

        //get
        getContentPane().add(sort, BorderLayout.NORTH);
        getContentPane().add(search, BorderLayout.WEST);
        getContentPane().add(array, BorderLayout.CENTER);
        getContentPane().add(options, BorderLayout.EAST);


        setVisible(true);
    }

    private void windowlistenerfunc() {
        if ((ccars.getsaved() == true) || (ccars.v_cars.size() == 0))
            System.exit(0);

        else {
            String[] ObjButtons = {"Tak", "Nie"};
            UIManager.put("Button.background", Color.white);
            UIManager.put("Button.", Color.white);
            int PromptResult = JOptionPane.showOptionDialog(null, "Dane nie są zapisane czy na pewno chcesz wyjść?", "Potwierdź wyjście", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);

            if (PromptResult == JOptionPane.YES_OPTION)
                System.exit(0);
        }
    }
}



