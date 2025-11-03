package chapter10;

import java.awt.Color;
import java.awt.Graphics2D;

public class Pentagon extends Polygon {

    public Pentagon(int sidelength, int x, int y, Color color) {
        super(sidelength, x, y, color);
    }

    @Override
    protected String getName() {
        return "Pentagon";        
    }

    @Override
    protected void draw(Graphics2D g) {
        g.setColor(color);

        // need to caclulate truetop x using some math
        double smallw = Math.cos(Math.toRadians(36));
        double kindah = Math.sin(Math.toRadians(36));
        double truetopx = x + smallw;
        double leftsidey = y + kindah;
        // everything is drawable from those two numbers ... see whiteboard 11/3/2025
        g.drawLine((int)Math.round(truetopx), y, x, (int)Math.round(leftsidey));


    }

}
