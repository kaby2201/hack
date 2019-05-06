import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InternettProxy extends JFrame implements ActionListener {

    static Proxy p;
    static InternettProxy iP;
    static JFrame myFrame;
    JPanel myPanel;
    JButton myButton;
    JTextField myTextField;
    Image img;


    void CreateAndShowGUI() {

        myPanel = new JPanel();
        myPanel.setLayout( new FlowLayout(FlowLayout.LEFT) );
        myPanel.setBackground( Color.magenta );
        myPanel.setPreferredSize(new Dimension(1024,768));

        myFrame.add( myPanel );
        JLabel myLabel = new JLabel("Skriv inn adresse");
        myPanel.add(myLabel);

        myTextField = new JTextField("https://upload.wikimedia.org/wikipedia/commons/thumb/f/f0/Hjelm_av_jern_fra_vikingtid_fra_Gjermundbu.jpg/242px-Hjelm_av_jern_fra_vikingtid_fra_Gjermundbu.jpg", 20);
        myButton = new JButton("Last ned");
        myPanel.add( myTextField );
        myPanel.add( myButton );
        myButton.addActionListener( this );
        myFrame.validate();

        p = new Proxy();
    }

    public static void main(String[] args) {

        //  Initialize, and create and show the GUI.

        myFrame = new JFrame();
        myFrame.setTitle("8 - Internett-Proxy");

        myFrame.setSize(1024, 768);
        myFrame.setVisible(true);

        myFrame.setLocationRelativeTo( null );
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        iP = new InternettProxy();
        iP.CreateAndShowGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals( myButton )) {

            //  When the button gets pressed, try to download from the URL, or if it has been
            //  downloaded previously, retrieve it from the HashMap.

            img = p.getImage( myTextField.getText() );

            if(img != null) {

                //  If we get an image, display it in the panel.

                ImageIcon i = new ImageIcon( img );
                JLabel myImageLabel = new JLabel( i );
                myPanel.add( myImageLabel );
                myFrame.pack();

            }
        }
    }
}
