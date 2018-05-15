package GUIs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import myUtil.CentroDoMonitorMaior;

public class GUIMenuPrincipal extends JFrame {

    private Container cp;
    private Point p;
    private JPanel pnNorte = new JPanel();
    private JPanel pnCentro = new JPanel();
    private JLabel lbTitulo = new JLabel("Sistema Coletor");
    private Font fonte = new Font("Monotype Corsiva", Font.BOLD, 30);
    private JLabel labelComImagemDeTamanhoDiferente = new JLabel();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuCadastros = new JMenu("Cadastros");
//------------------------ Itens do Menu ----------------------------
    private JMenuItem crudTipoSensor = new JMenuItem("TipoSensor");
    private JMenuItem crudDadosSensor = new JMenuItem("DadosSensor");
    private JMenuItem crudSensor = new JMenuItem("Sensor");
    private JMenuItem crudLocal = new JMenuItem("Local");

    public GUIMenuPrincipal(Dimension dimensao) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(dimensao);
        setTitle("Sistema Coletor");

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        pnNorte.add(lbTitulo);
        lbTitulo.setFont(fonte);
        pnNorte.setBackground(Color.LIGHT_GRAY);
        pnCentro.add(labelComImagemDeTamanhoDiferente);
        pnCentro.setBackground(Color.BLACK);

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);

        setJMenuBar(menuBar);
        menuBar.add(menuCadastros);
        menuCadastros.add(crudTipoSensor);
        menuCadastros.add(crudDadosSensor);
        menuCadastros.add(crudSensor);
        menuCadastros.add(crudLocal);
        crudTipoSensor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUITipoSensor guiTipoSensor = new GUITipoSensor(p, dimensao);
            }
        });

        crudDadosSensor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // GUIDadosSensor guiDadosSensor = new GUIDadosSensor(p, dimensao);
            }
        });

        crudSensor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUISensor guiSensor = new GUISensor(p, dimensao);
            }
        });

        crudLocal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUILocal guiLocal = new GUILocal(p, dimensao);
            }
        });

        p = new CentroDoMonitorMaior().getCentroMonitorMaior(this);
        setLocation(p);
        setVisible(true);
    } //fecha o contrutor

    public static void main(String[] args) {
        new GUIMenuPrincipal(new Dimension(800, 600));
    }
}
