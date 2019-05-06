import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*

5 - Tripp-trapp-tresko

Relevante temaer

* GUI
* Funksjoner/Metoder
* Programkontroll (løkker)

 */

public class ThreeRow extends JPanel implements ActionListener {

    static JFrame myFrame;
    static ThreeRow myThreeRow;

    static JMenuItem filSpillX = new JMenuItem("Spill kryss");
    static JMenuItem filSlett = new JMenuItem("Slett");
    static JMenuItem filVunnet = new JMenuItem("Hvem har vunnet");

    JTextField[] myTextField = new JTextField[9];

    void CreateAndShowPanel() {

        this.setPreferredSize(new Dimension(550, 550));  // makes myFrame resize itself to the panel

        JMenuBar menyRad = new JMenuBar();  // Add the Menu
        JMenu filMeny = new JMenu("Fil");

        menyRad.add(filMeny);
        filMeny.add(filSpillX);
        filMeny.add(filSlett);
        filMeny.add(filVunnet);

        myFrame.setJMenuBar(menyRad);

        filSpillX.addActionListener( this );
        filSlett.addActionListener( this );
        filVunnet.addActionListener( this );

        this.setLayout( new GridLayout(3,3) );
        Font f = new Font("Sans Serif", Font.BOLD, 96);

        for(int i = 0; i < 9; i++) {
            myTextField[i] = new JTextField();
            myTextField[i].setFont( f );
            this.add( myTextField[i] );
        }

        myFrame.add(this, BorderLayout.CENTER);
        myFrame.pack();
        myFrame.setLocationRelativeTo( null );  // centers the window
    }


    public static void main(String[] args) {

        myFrame = new JFrame();
        myFrame.setTitle("5 - Tripp-trapp-tresko");
        myFrame.setResizable(false);
        myFrame.setVisible(true);
        myFrame.setLayout( new BorderLayout() );
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myThreeRow = new ThreeRow();
        myThreeRow.CreateAndShowPanel();
    }


    String hvaSpilt(int i) {

        String s = myTextField[i].getText();

        switch (s) {

            case "x" : return "X";
            case "X" : return "X";
            case "o" : return "O";
            case "O" : return "O";
            default : return "-";

        }
    }

    void spillKryss() {

        ArrayList<String> availableSlots = new ArrayList<String>();

        for(int i = 0; i < 9; i++) {
            if( hvaSpilt(i).equals("-") ) {
                availableSlots.add("" + i);
            }
        }

        if( availableSlots.size() > 0) {

            int k = (int)( Math.random() * (availableSlots.size() - 1) );
            myTextField[ Integer.parseInt( availableSlots.get(k) ) ].setText("X");

        }
        else {
            System.out.println("Ingen mulige trekk!");
        }
    }


    void spillVunnet() {

        int counter_o, counter_x, m, n;
        boolean o_vant = false, x_vant = false;

        // Horisontalt/Vertikalt

        for(int k = 0; k < 2; k++) {

            for(int i = 0; i < 3; i++) {

                counter_o = 0;
                counter_x = 0;

                for(int j = 0; j < 3; j++) {

                    if(k == 0) m = j + i*3;  // Bytte på rader og kolonner ettersom hva k er.
                    else m = i + j*3;

                    if( hvaSpilt( m ).equals("X"))
                        counter_x++;

                    if( hvaSpilt( m ).equals("O"))
                        counter_o++;
                }

                if( counter_o == 3) o_vant = true;
                if( counter_x == 3) x_vant = true;
            }
        }

        for(int k = 0; k < 2; k++) {

            if(k == 0) { m = 4; n = 0; }
            else { m = 2; n = 2; }

            if( hvaSpilt(n).equals("O") && hvaSpilt(n+m ).equals("O") && hvaSpilt(n + 2*m ).equals("O"))
                o_vant = true;

            if( hvaSpilt(n).equals("X") && hvaSpilt(n+m ).equals("X") && hvaSpilt(n + 2*m ).equals("X"))
                x_vant = true;
        }

        String s = "\n";

        if(o_vant) s += "O vant\n";
        if(x_vant) s += "X vant\n";
        if(!o_vant && !x_vant) s+= "Ingen har vunnet\n";

        JOptionPane.showMessageDialog( this, s);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(filSlett)) {
            for(int i = 0; i < 9; i++) {
                myTextField[i].setText("");
            }
        }

        if(e.getSource().equals(filSpillX)) {
            spillKryss();
        }

        if(e.getSource().equals(filVunnet)) {
            spillVunnet();
        }
    }
}
