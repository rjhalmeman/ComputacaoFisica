/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.awt.Point;

/**
 *
 * @author Radames
 */
public class Main {
 Point p = new Point(100,100);
    public Main() {       
        
        ObservadorMonitor obsMon = new ObservadorMonitor(p);
        p.x = p.x+300;
        p.y = p.y+50;
        Janela1 j1 = new Janela1(obsMon,p);
        j1.inicializaInterface();
        p.x = p.x+300;
        p.y = p.y+50;
        Janela2 j2 = new Janela2(obsMon,p);
        j2.inicializaInterface();
    }

    public static void main(String[] args) {
        Main m = new Main();    
        
    }
      
}
