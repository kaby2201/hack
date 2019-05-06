import java.awt.*;

public class Pacman extends GrafikkElement {

    int gridSize = 55;

    public Pacman() {

        this.setBredde(50);
        this.setHoyde(50);

        this.setRetning(315);

        this.setFarge( Color.yellow );
        this.isPacman = true;
    }

    void oppdaterFase() {

        if(this.fase == 0) fase = 1;
        else fase = 0;

    }

    void flyttVenstre() {

        this.x -= gridSize;
        if(this.x < 0) this.x = gridSize * 9 + 2;
        this.retning = 135;
        oppdaterFase();
    }

    void flyttHoyre() {

        this.x += gridSize;
        if(this.x > gridSize * 10) this.x = 2;
        this.retning = 315;
        oppdaterFase();

    }

    void flyttOpp() {

        this.y -= gridSize;
        if(this.y < 0) this.y = gridSize * 9 + 2;
        this.retning = 45;
        oppdaterFase();

    }

    void flyttNed() {

        this.y += gridSize;
        if(this.y > gridSize * 10) this.y = 2;
        this.retning = 225;
        oppdaterFase();

    }
}
