import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*

6 - Krypteringsprogram

Relevante temaer

* Funksjoner / metoder
* Enhetstesting
* String

 */

public class Krypteringsprogram extends JPanel implements ActionListener {

    static JFrame myFrame;
    static Krypteringsprogram mittKrypteringsprogram;

    JComboBox<String> algoritmeListe;
    JTextArea myTextArea_1;
    JTextArea myTextArea_2;

    void CreateAndShowPanel() {

        this.setLayout( null );
        this.setPreferredSize(new Dimension(800, 600));  // makes myFrame resize itself to the panel

        JLabel label_1 = new JLabel("Original tekst");
        JLabel label_2 = new JLabel("Kryptert tekst");
        label_1.setBounds(15,25,100,25);
        label_2.setBounds(415,25,100,25);
        this.add( label_1 );
        this.add( label_2 );

        myTextArea_1 = new JTextArea(20,20);
        myTextArea_2 = new JTextArea(20,20);

        myTextArea_1.setLineWrap( true );
        myTextArea_2.setLineWrap( true );

        myTextArea_1.setBounds(10, 50, 380, 530);
        myTextArea_2.setBounds(410, 50, 380, 530);
        myTextArea_2.setEditable(false);
        this.add( myTextArea_1 );
        this.add( myTextArea_2 );

        String[] algoritmer = new String[] {
                "Algoritme 1", "Algoritme 2", "Algoritme 3", "Algoritme 4"
        };

        algoritmeListe = new JComboBox<>(algoritmer);
        algoritmeListe.setBounds(690, 10, 100, 25);
        algoritmeListe.addActionListener( this );
        this.add( algoritmeListe );

        myFrame.add(this, BorderLayout.CENTER);
        myFrame.pack();
        myFrame.setLocationRelativeTo( null );  // Centers the window
        repaint();
    }


    public static void main(String[] args) {

        myFrame = new JFrame();
        myFrame.setTitle("6 - Krypteringsprogram");
        myFrame.setResizable(false);
        myFrame.setVisible(true);
        myFrame.setLayout( new BorderLayout() );
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mittKrypteringsprogram = new Krypteringsprogram();
        mittKrypteringsprogram.CreateAndShowPanel();

    }


    String algoritme2 (String u) {

        String t = u.replace('ø', 'å');
        u = t.replace('æ','ø');
        t = u.replace('y','æ');
        u = t.replace('u','y');
        t = u.replace('o','u');
        u = t.replace('i','o');
        t = u.replace('e','i');
        u = t.replace('a','e');

        return u;
    }


    String algoritme3 (String s) {

        int k;
        String t = "";

        ArrayList<String> myChars = new ArrayList<String>();

        for(int i = 0; i < s.length(); i++) {
            myChars.add( "" + s.charAt( i ));
        }

        for(int i = 0; i < s.length(); i++) {

            k = (int)( Math.random() * myChars.size() );

            t += myChars.get( k );
            myChars.remove( k );

        }

        return t;
    }


    String algoritme4 (String s) {

        String t = "";

        for(int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if(c != ' ') {
                c = (char)(c + 13);

                if(c > 'z') {
                    c = (char)(c-26);
                }
            }
            t += c;
        }

        return t;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(algoritmeListe)) {

            String s = "" + algoritmeListe.getSelectedItem();
            int i = (int)s.charAt(s.length() - 1) - 48;

            switch(i) {

                case 1 :
                    myTextArea_2.setText( myTextArea_1.getText() );
                    break;

                case 2 :
                    myTextArea_2.setText( algoritme2( myTextArea_1.getText() ));
                    break;

                case 3 :
                    myTextArea_2.setText( algoritme3( myTextArea_1.getText() ));
                    break;

                case 4 :
                    myTextArea_2.setText( algoritme4( myTextArea_1.getText() ));
                    break;

                default : break;

            }
        }
    }
}
