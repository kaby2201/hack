import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/*

7 - Bildespill

Relevante temaer

* Klasser
* GUI
* Programkontroll (løkker)

 */

public class BildeSpill extends JPanel implements MouseListener, ActionListener {

    static JFrame myFrame;
    static BildeSpill myBildeSpill;
    Image img;
    Graphics2D g2d;

    int currentX = -10, currentY = -10;

    ArrayList<Point> invisible;
    int[][] visible = new int[20][20];

    Timer myTimer;

    void CreateAndShowGUI() {

        ImageIcon myImageIcon = new ImageIcon("src/Bilder/0" + ((int)(Math.random() * 5) + 1) + ".png");
        img = myImageIcon.getImage();

        // Make an ArrayList of invisible points in the range [0..19] in both directions.

        invisible = new ArrayList<Point>();

        for(int i = 0; i < 20; i++) {  // Rows
            for (int j = 0; j < 20; j++) {  //  Columns
                invisible.add(new Point( j, i ));
            }
        }

        this.setBackground(Color.magenta);
        this.setPreferredSize(new Dimension(640, 480));  // makes myFrame resize itself to the panel
        myFrame.add(this, BorderLayout.CENTER);
        myFrame.pack();
        myFrame.setLocationRelativeTo( null );  // centers the window
        this.addMouseListener( this );

        myTimer = new Timer( 1000, this );
        myTimer.start();

        repaint();
    }


    public static void main(String[] args) {

        myFrame = new JFrame();
        myFrame.setTitle("7 - Bildespill");
        myFrame.setResizable(false);
        myFrame.setVisible(true);
        myFrame.setLayout( new BorderLayout() );
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myBildeSpill = new BildeSpill();
        myBildeSpill.CreateAndShowGUI();

    }


    void updateVisibility() {

        for(int i = 0; i < 20; i++) {

            for(int j = 0; j < 20; j++) {

                Rectangle myRectangle = new Rectangle( 0 + 32*j, 0 + 24 * i, 32 , 24);
                Point p = new Point(currentX, currentY);

                if(myRectangle.contains( p )) {

                    //  Find the exact location of this point in the ArrayList of points, and remove it
                    //  if it exists ( > -1 )

                    int ll = invisible.indexOf( new Point(j,i) );
                    System.out.println("int ll: " + ll);

                    if( ll > -1) {

                        invisible.remove( ll );
                        visible[i][j] = 1;

                    }
                }
            }
        }
    }


    public void paint(Graphics g){

        super.paint(g);

        g2d = (Graphics2D)g;
        int k = 6;

        //  Draw the image, and put a grid of rectangles on top to hide visibility

        g2d.drawImage(img,0,0,null);

        for(int i = 0; i < 20; i++) {  // Rows

            for(int j = 0; j < 20; j++) {  //  Columns

                if(visible[i][j] == 0) {

                    Color c = new Color( (i+j)*k, (i+j)*k , (i+j)*k );
                    g2d.setPaint( c );
                    g2d.fillRect(0 + 32 * j,0 + 24 * i,32,24);

                }
            }
        }

        g2d.setPaint(Color.red);
        g2d.fillRect( currentX - 1, currentY - 1, 3, 3);

    }

    @Override
    public void mousePressed(MouseEvent e) {

        currentX = e.getX();
        currentY = e.getY();

        updateVisibility();  //  Translate the actual x, y coordinates into the 20x20 grid and update visibility.
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        //  If the ArrayList of points still have points, remove one of them at random once per second.

        if( invisible.size() > 0) {

            int i = (int)(Math.random() * invisible.size() );

            int x = invisible.get(i).x;
            int y = invisible.get(i).y;

            visible[y][x] = 1;
            invisible.remove( i );

            repaint();

        }
    }

    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}

}
