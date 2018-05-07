package codigo;

// @author Radames
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ObservadorMonitor extends JFrame implements Observer {

    private Point p;
    final JTextField tf = new JTextField(5);
    int contador = 0;

    public ObservadorMonitor(Point p) {
        this.p = p;
        inicializaInterface();
    }

    public void inicializaInterface() {

        setTitle("Observer");
        setSize(300, 300);
        setLocation(p);
        Container cp = new JPanel();
        cp = getContentPane();
        cp.setLayout(new GridLayout(3, 1));

        JLabel labelAviso = new JLabel("---");
        cp.add(labelAviso);

        cp.add(tf);
        JButton btn = new JButton("ZERAR");
        cp.add(btn);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tf.setText("0");
                contador = 0;
            }
        });


    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Integer) {
            //foi setado um valor na janela observada....             
//            tf.setText(String.valueOf(((Integer) arg).intValue())); //valor informado na thread
            contador++;
            tf.setText(String.valueOf(contador));
        } else if (arg instanceof Boolean) {
            if (((Boolean) arg).booleanValue()) {
            }
        }
    }
   
}
