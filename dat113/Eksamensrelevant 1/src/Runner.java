import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Runner extends JFrame implements ActionListener {

    static Runner myFrame;

    static JButton registerInformation;
    static JLabel typeSeteR;
    static JLabel klasseR;
    static JLabel alderR;
    static JLabel prisR;

    public static void main(String[] args) {
        CreateAndShowGUI();
    }


    public Runner() {
    }


    static void CreateAndShowGUI() {

        myFrame = new Runner();

        myFrame.setTitle("1 - Flybillett");
        myFrame.setSize(400, 200);
        myFrame.setResizable(false);
        myFrame.setVisible(true);
        myFrame.setLayout( null );
        myFrame.setLocationRelativeTo( null );

        JPanel myPanel = new JPanel();
        myPanel.setLayout( new GridLayout(0,2) );

        JLabel typeSeteL = new JLabel("Type sete:");
        typeSeteR = new JLabel("-");
        JLabel klasseL = new JLabel("Business class / Coach:");
        klasseR = new JLabel("-");
        JLabel alderL = new JLabel("Alder:");
        alderR = new JLabel("-");
        JLabel prisL = new JLabel("Pris:");
        prisR = new JLabel("-");

        myPanel.add(typeSeteL);
        myPanel.add(typeSeteR);
        myPanel.add(klasseL);
        myPanel.add(klasseR);
        myPanel.add(alderL);
        myPanel.add(alderR);
        myPanel.add(prisL);
        myPanel.add(prisR);

        registerInformation = new JButton("Registrere informasjon");
        registerInformation.setPreferredSize(new Dimension(70, 25));

        myPanel.add( registerInformation );  // Add the button
        registerInformation.addActionListener( myFrame );  // Add an ActionListener to the button

        myPanel.setBounds(25, 25, 375, 125);

        myFrame.add( myPanel );
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    private String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals( registerInformation )) {

            String sete = "", alderString = "", klasse = "";
            int alderInt = 0, pris = 0;
            boolean cancelRegistration = false;

            try {

                while( !sete.equals("midtgang") && !sete.equals("vindu"))
                    sete = JOptionPane.showInputDialog("Hva slags sete ønsker du (midtgang eller vindu)?");

            } catch(NullPointerException n) {
                cancelRegistration = true;
            }

            if(!cancelRegistration) {

                // Oppdater informasjon om sete og spør deretter om alder
                typeSeteR.setText(capitalize(sete));

                while (true) {
                    try {
                        alderString = JOptionPane.showInputDialog(null, "Hva er din alder?");
                        alderInt = Integer.parseInt(alderString);
                        break;
                    } catch (NumberFormatException n) {
                        JOptionPane.showMessageDialog(null, "Ugyldig alder.");
                    }
               }

                alderR.setText(alderString);

            }

            try {

                while( !klasse.equals("business") && !klasse.equals("coach"))
                    klasse = JOptionPane.showInputDialog("Hvilken klasse ønsker du (business eller coach)?");
            } catch(NullPointerException n) {
                cancelRegistration = true;
            }

            //  Oppdater slik at informasjon om pris vises. Reglene for pris er som følger.
            //      * Business koster alltid 15000.
            //      * For Coach varier prisen basert på alder og sete.
            //      * Midtgang koster 1500 for alle under 15 år, og 2000 for alle fra 15 år og oppgover.
            //      * Vindu koster 1000 for alle under 15 år, og 1500 for alle fra 15 år og oppover.


            if(!cancelRegistration) {

                klasseR.setText( capitalize(klasse) );  // Set the label text for class

                switch( klasse ) {

                    case "business" :
                        pris = 15000;
                        break;

                    case "coach" :
                        if( sete.equals("midtgang") ) {  // Midtgang

                            if(alderInt < 15) pris = 1500;
                            else pris = 2000;

                        }
                        else {  // Vindu

                            if(alderInt < 15) pris = 1000;
                            else pris = 1500;

                        }
                        break;
                }

                prisR.setText("" + pris);  // Update price label
            }
        }
    }
}
