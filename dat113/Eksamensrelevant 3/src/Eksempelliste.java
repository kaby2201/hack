import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;

/*

3 - Eksempelliste

Relevante temaer

* Collections
* Filer

*/

public class Eksempelliste extends JPanel implements ActionListener {

    static JFrame myFrame;
    static Eksempelliste minEksempelliste;

    JList myList;
    JButton myButton1;
    JButton myButton2;

    JLabel myLabel;
    JTextArea myTextArea;

//    ArrayList<String> URLList = new ArrayList<String>();

    // Using HashSet instead of ArrayList keeps the list unique (no duplicate entries)
    HashSet<String> URLList = new HashSet<String>();


    void CreateAndShowPanel() {

        this.setPreferredSize(new Dimension(1024, 768));  // makes myFrame resize itself to the panel

        myList = new JList();
        this.add( myList );

        myButton1 = new JButton("Legg til en ny Javafil");
        myButton2 = new JButton("Last ned alle Javafilene");
        myButton1.addActionListener( this );
        myButton2.addActionListener( this );
        this.add( myButton1 );
        this.add( myButton2 );

        myLabel = new JLabel("Programtekst");
        this.add(myLabel);

        myTextArea = new JTextArea(20,60);
        this.add(myTextArea);

        myFrame.add(this, BorderLayout.CENTER);
        myFrame.pack();
        myFrame.setLocationRelativeTo( null );  // centers the window
    }


    public static void main(String[] args) {

        myFrame = new JFrame();
        myFrame.setTitle("3 - Eksempelliste");
        myFrame.setResizable(false);
        myFrame.setVisible(true);
        myFrame.setLayout( new BorderLayout() );
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        minEksempelliste = new Eksempelliste();
        minEksempelliste.CreateAndShowPanel();

    }


    void updateList(String myURL) {

        DefaultListModel m = new DefaultListModel();

        URLList.add( myURL );

        for(Object s : URLList ) {
            m.addElement(s);
        }

        myList.setModel(m);
    }


    /*
http://mortengoodwin.net/dat113/javafiler/oppgave1.java
http://mortengoodwin.net/dat113/javafiler/oppgave2.java
    */


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(myButton1)) {  //  Legg til ny Javafil

            //  Add URL files, and update the HashSet for each file so it will display in the GUI.

            String javafil = "";
            boolean cancelAction = false;

            try {
                while( javafil.equals("") )
                    javafil = JOptionPane.showInputDialog("Legg til en ny Javafil.");

            } catch(NullPointerException n) {
                cancelAction = true;
            }

            if(!cancelAction) {
                updateList( javafil );
            }
        }

        if(e.getSource().equals(myButton2)) {

            for(int i = 0; i < myList.getModel().getSize(); i++) {

                //  If there are files in the HashSet, show a dialog box with its name and then download
                //  the file and display its contents in the text area.

                JOptionPane.showMessageDialog(null, "Fil: " + myList.getModel().getElementAt(i));

                try {

                    URL u = new URL( myList.getModel().getElementAt(i).toString() );
                    InputStreamReader isr = new InputStreamReader(u.openStream() );
                    BufferedReader br = new BufferedReader(isr);

                    String linje = br.readLine();
                    String programText = "";

                    while( linje != null ) {

                        programText += linje + "\n";
                        linje = br.readLine();

                    }

                    //  Instead of opening up a lot of dialog boxes showing each line, put the entire
                    //  text into a text area instead.

                    myLabel.setText( "Programtekst: " + myList.getModel().getElementAt(i) );
                    myTextArea.setText( programText );

                    br.close();
                    isr.close();

                } catch (MalformedURLException m) {
                    m.printStackTrace();
                } catch (IOException m) {
                    m.printStackTrace();
                }
            }
        }
    }
}
