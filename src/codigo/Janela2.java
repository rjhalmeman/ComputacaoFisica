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

public class Janela2 extends Observable {

    //atributos
    Point p;

    public Janela2(Observer observador, Point p) {
        //Adiciona o objeto observador a lista de observadores  
        addObserver(observador);
        this.p = p;
    }

    public void inicializaInterface() {

        JFrame f = new JFrame();
        f.setTitle("janela 2 - Observable");
        f.setSize(300, 300);
        f.setLocation(p);
        Container cp = new JPanel();
        cp = f.getContentPane();
        cp.setLayout(new GridLayout(3, 1));

        cp.add(new JLabel("Digite um valor e clic o botão"));
        final JTextField tf = new JTextField(5);
        tf.setText("0");
        cp.add(tf);
        JButton btn = new JButton("Enviar valor");
        cp.add(btn);


        //ativa update, para não ter que clicar 2 vezes o botao enviar na primeira vez
        notifyObservers(new Integer(0));
        setChanged();
        
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int i = Integer.parseInt(tf.getText()) + 1;
                tf.setText(String.valueOf(i));
                notifyObservers(i);
                setChanged();
            }
        });


    }
}
