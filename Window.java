import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Window extends JFrame {
    public Window(){
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setBounds(500,200, 800, 600);
        Graphics g = this.getGraphics();
        this.addMouseListener(new MouseListener() {

            public int x0,y0;
            ArrayList uz = new ArrayList();
            ArrayList Pixy = new ArrayList();
            int[] y = new int[80];
            int a = 0;

            @Override
            public void mouseClicked(MouseEvent e) {
                int width = 10;
            g.fillOval(e.getX() - width/2,e.getY() - width/2,width,width);
            uz.add(Double.valueOf(gX(e.getX())));
                System.out.println("X = " + gX(e.getX()) + " ]]Y = " + gY(e.getY()));
            if(uz.size() == 1){
                //g.drawLine(0, e.getY(),800, e.getY());
            }
            Double[] uz1 = new Double[uz.size()];
            uz.toArray(uz1);
            Polynom L = Polynom.LP(uz1);
            int j = 0;
            for(int i = -40; i < 40; i++){
                y[j] = toPixelY(L.getValue(i));
                j++;
            }
                j = 0;
                for(int i = -40; i < 40; i++){
                    if(j!=79) {
                        g.drawLine(toPixelX(i), y[j], toPixelX(i + 1), y[j + 1]);
                    }
                    j++;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            x0 = e.getX();
            y0 = e.getY();
                System.out.println("X = " + x0 + "; Y = " + y0);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                g.drawLine(400,0,400,600);
                g.drawLine(0,300,800,300);
            }

            @Override
            public void mouseExited(MouseEvent e) {
               clearfield();
            }

            public double gX(int a){
                a -= 400;
                double x = a/10;
                return x;
            }

            public double gY(int a){
                    a -= 300;
                    double x = -a/10;
                return x;
            }

            public int toPixelX(double a){
            a += 40;
            int x = (int)a*10;
            return x;
            }

            public int toPixelY(double a){
                a -= 30;
                int x = (int)-a*10;
                return x;
            }

            public void clearfield(){
                g.clearRect(0,0,800,600);
                g.drawLine(400,0,400,600);
                g.drawLine(0,300,800,300);
            }
        });
    }
}
