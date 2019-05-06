import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Bibliotek extends JPanel implements ActionListener {

    static JFrame myFrame;
    static Bibliotek mittBibliotek;

    DefaultListModel<Bok> model = new DefaultListModel<Bok>();
    JList myList;
    JButton myButton1;
    JButton myButton2;
    JButton myButton3;
    JButton myButton4;
    JButton myButton5;
    JTextArea myTextArea;

    void CreateAndShowPanel() {

        this.setBackground(Color.magenta);
        this.setPreferredSize(new Dimension(800, 300));  // makes myFrame resize itself to the panel

        myList = new JList();
        myList.setModel( model );

        this.add( myList );

        myButton1 = new JButton("Legg til en ny bok");
        myButton2 = new JButton("Fjern bok");
        myButton3 = new JButton("Lagre");
        myButton4 = new JButton("Åpne");
        myButton5 = new JButton("Se innhold");

        myButton1.addActionListener( this );
        myButton2.addActionListener( this );
        myButton3.addActionListener( this );
        myButton4.addActionListener( this );
        myButton5.addActionListener( this );

        this.add( myButton1 );
        this.add( myButton2 );
        this.add( myButton3 );
        this.add( myButton4 );
        this.add( myButton5 );

        myTextArea = new JTextArea(10,60);
        this.add( myTextArea );

        myFrame.add(this, BorderLayout.CENTER);
        myFrame.pack();
        myFrame.setLocationRelativeTo( null );  // centers the window

    }


    public static void main(String[] args) {

        myFrame = new JFrame();
        myFrame.setTitle("2 - Bibliotek over bøker");
        myFrame.setResizable(false);
        myFrame.setVisible(true);
        myFrame.setLayout( new BorderLayout() );
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mittBibliotek = new Bibliotek();
        mittBibliotek.CreateAndShowPanel();

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        int index = myList.getSelectedIndex();

        //  Legg til en ny bok
        if(e.getSource().equals(myButton1)) {

            String forfatter = JOptionPane.showInputDialog(this, "Forfatter");
            String tittel = JOptionPane.showInputDialog(this, "Tittel");
            int antallSider = Integer.parseInt( JOptionPane.showInputDialog(this, "Antall sider") );

            Bok b = new Bok(forfatter, antallSider, tittel);
            model.addElement(b);

        }

        //  Fjern bok
        if(e.getSource().equals(myButton2)) {

            if(index > -1)
                model.remove( index );

        }

        //  Lagre
        if(e.getSource().equals(myButton3)) {

            try {

                FileOutputStream fileOut = new FileOutputStream("data");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject( model );
                out.close();
                fileOut.close();

            } catch (FileNotFoundException m) {
                m.printStackTrace();
            } catch (IOException m) {
                m.printStackTrace();
            }
        }

        //  Åpne
        if(e.getSource().equals(myButton4)) {

            try {

                FileInputStream fileIn = new FileInputStream("data");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                this.model = (DefaultListModel<Bok>) in.readObject();
                in.close();
                fileIn.close();

            } catch (FileNotFoundException m) {
                m.printStackTrace();
            } catch (IOException m) {
                m.printStackTrace();
            } catch (ClassNotFoundException m) {
                m.printStackTrace();
            }

            myList.setModel( model );
        }

        //  Se innhold
        if(e.getSource().equals(myButton5)) {

            String text = "";

            if(index > -1) {

                text += "Forfatter: " + model.getElementAt( index ).getForfatter();
                text += "\nTittel: " + model.getElementAt( index ).getTittel();
                text += "\nAntall sider: " + model.getElementAt( index ).getAntallSider();

                myTextArea.setText( text );

            }
        }
    }
}
