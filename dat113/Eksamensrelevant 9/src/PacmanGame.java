import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static java.awt.event.KeyEvent.*;

public class PacmanGame extends JPanel implements KeyListener{

    Pacman p;

    static JFrame myFrame;
    static PacmanGame myPacmanGame;

    Eple[][] mineEpler = new Eple[10][10];


    void CreateAndShowPanel() {

        // Using a panel to fix problems with flickering, since it is automatically double-buffered.

        p = new Pacman();
        p.setX(2);
        p.setY(2);

        for(int i = 0; i < 10; i++) {  // rows

            for(int j = 0; j < 10; j++) {  // columns

                mineEpler[i][j] = new Eple();
                mineEpler[i][j].setX( 20 + j * 55 );
                mineEpler[i][j].setY( 20 + i * 55 );

            }
        }

        this.setBackground(Color.magenta);
        this.setPreferredSize(new Dimension(550, 550));  // makes myFrame resize itself to the panel
        myFrame.add(this, BorderLayout.CENTER);
        myFrame.pack();
        myFrame.setLocationRelativeTo( null );  // centers the window
        myFrame.addKeyListener( this );

        repaint();
    }


    public static void main(String[] args) {

        myFrame = new JFrame();
        myFrame.setTitle("9 - Pacman");
        myFrame.setResizable(false);
        myFrame.setVisible(true);
        myFrame.setLayout( new BorderLayout() );
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myPacmanGame = new PacmanGame();
        myPacmanGame.CreateAndShowPanel();
    }


    public void sjekkKollisjon(Eple e){

        Rectangle r1 = new Rectangle(p.x, p.y, p.hoyde, p.bredde);
        Rectangle r2 = new Rectangle(e.x, e.y, e.hoyde, e.bredde);

        if(r1.intersects(r2)){
            System.out.println("Krasj");
            e.setSynlig(false);
        }
    }


    public void paint(Graphics g) {

        super.paint(g);

        //g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
          //      RenderingHints.VALUE_ANTIALIAS_OFF);

        Graphics2D g2d = (Graphics2D)g;

        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON );

/*
        g.setColor(Color.black);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        for(int i = 0; i < 10; i++) {  // rows
            for(int j = 0; j < 10; j++) {  // columns

                mineEpler[i][j].drawMe(g);
                sjekkKollisjon( mineEpler[i][j] );
            }
        }

        p.drawMe(g); // p er et objekt av Pacman
*/

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        for(int i = 0; i < 10; i++) {  // rows
            for(int j = 0; j < 10; j++) {  // columns

                mineEpler[i][j].drawMe(g2d);
                sjekkKollisjon( mineEpler[i][j] );
            }
        }

        p.drawMe(g2d); // p er et objekt av Pacman


    }


    @Override public void keyTyped(KeyEvent keyEvent) {}
    @Override public void keyReleased(KeyEvent keyEvent) {}


    @Override
    public void keyPressed(KeyEvent keyEvent) {

        if(keyEvent.getKeyCode() == VK_LEFT ) {
            p.flyttVenstre();
            repaint();
        }

        if(keyEvent.getKeyCode() == VK_RIGHT ) {
            p.flyttHoyre();
            repaint();
        }

        if(keyEvent.getKeyCode() == VK_UP ) {
            p.flyttOpp();
            repaint();
        }

        if(keyEvent.getKeyCode() == VK_DOWN ) {
            p.flyttNed();
            repaint();
        }
    }
}
