import java.awt.*;

public class GrafikkElement {

    int x, y, bredde, hoyde, retning, fase;
    boolean synlig = true, isPacman = false;
    Color farge;

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setBredde(int bredde) { this.bredde = bredde; }
    public void setHoyde(int hoyde) { this.hoyde = hoyde; }
    public void setRetning(int retning) { this.retning = retning; }
    public void setFarge(Color farge) { this.farge = farge; }
    public void setSynlig(boolean synlig) { this.synlig = synlig; }

    public void drawMe(Graphics g) {

        if(synlig) {

            g.setColor( this.farge );
            g.fillOval( this.x, this.y, this.bredde, this.hoyde );

        }

        if(isPacman) {

            g.setColor(Color.black);

            int arc, shift = 0;

            if( this.fase == 0 )
                arc = 90;
            else {
                arc = 45;
                shift = 22;  // have to shift the angle slightly so it displays correctly for 45 degrees.
            }

            g.fillArc( this.x - 1, this.y - 1, this.bredde + 2, this.hoyde + 2, this.retning + shift, arc);
        }
    }
}
