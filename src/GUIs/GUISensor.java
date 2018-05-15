package GUIs;

import DAOs.*;
import Entidades.*;
import java.awt.Dimension;
import java.util.List;
import java.awt.Point;
import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import myUtil.JanelaPesquisar;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;

public class GUISensor extends JDialog {

    ImageIcon iconeCreate = new ImageIcon(getClass().getResource("/icones/create.png"));
    ImageIcon iconeRetrieve = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
    ImageIcon iconeUpdate = new ImageIcon(getClass().getResource("/icones/update.png"));
    ImageIcon iconeDelete = new ImageIcon(getClass().getResource("/icones/delete.png"));
    ImageIcon iconeSave = new ImageIcon(getClass().getResource("/icones/save.png"));
    ImageIcon iconeCancel = new ImageIcon(getClass().getResource("/icones/cancel.png"));
    ImageIcon iconeListar = new ImageIcon(getClass().getResource("/icones/list.png"));
    JButton btnCreate = new JButton(iconeCreate);
    JButton btnRetrieve = new JButton(iconeRetrieve);
    JButton btnUpdate = new JButton(iconeUpdate);
    JButton btnDelete = new JButton(iconeDelete);
    JButton btnSave = new JButton(iconeSave);
    JButton btnCancel = new JButton(iconeCancel);
    JButton btnList = new JButton(iconeListar);

    JLabel labelIdSensor = new JLabel("IdSensor");
    JTextField textFieldIdSensor = new JTextField(20);
    JLabel labelNomeSensor = new JLabel("NomeSensor");
    JTextField textFieldNomeSensor = new JTextField(20);
    JLabel labelTipoSensorIdTipoSensor = new JLabel("TipoSensorIdTipoSensor");
    JTextField textFieldTipoSensorIdTipoSensor = new JTextField(20);
    JLabel labelLocalIdLocal = new JLabel("LocalIdLocal");
    JTextField textFieldLocalIdLocal = new JTextField(20);

//Daos para FK
    DAOTipoSensor daoTipoSensor = new DAOTipoSensor();
    DAOLocal daoLocal = new DAOLocal();

//Entidades para FK
    TipoSensor tipoSensor = new TipoSensor();
    Local local = new Local();

    JPanel pnAvisos = new JPanel();
    JLabel labelAviso = new JLabel("");

    String acao = "";//variavel para facilitar insert e update
    DAOSensor daoSensor = new DAOSensor();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
    Sensor sensor;

    private void atvBotoes(boolean c, boolean r, boolean u, boolean d) {
        btnCreate.setEnabled(c);
        btnRetrieve.setEnabled(r);
        btnUpdate.setEnabled(u);
        btnDelete.setEnabled(d);
        btnList.setEnabled(r);
    }

    public void mostrarBotoes(boolean visivel) {
        btnCreate.setVisible(visivel);
        btnRetrieve.setVisible(visivel);
        btnUpdate.setVisible(visivel);
        btnDelete.setVisible(visivel);
        btnList.setVisible(visivel);
        btnSave.setVisible(!visivel);
        btnCancel.setVisible(!visivel);
    }

    private void habilitarAtributos(boolean idSensor, boolean nomeSensor, boolean tipoSensorIdTipoSensor, boolean localIdLocal) {
        if (idSensor) {
            textFieldIdSensor.requestFocus();
            textFieldIdSensor.selectAll();
        }
        textFieldIdSensor.setEnabled(idSensor);
        textFieldIdSensor.setEditable(idSensor);
        textFieldNomeSensor.setEditable(nomeSensor);
        textFieldTipoSensorIdTipoSensor.setEditable(tipoSensorIdTipoSensor);
        textFieldLocalIdLocal.setEditable(localIdLocal);

    }

    public void zerarAtributos() {
        textFieldNomeSensor.setText("");
        textFieldTipoSensorIdTipoSensor.setText("");
        textFieldLocalIdLocal.setText("");
    }
    Color corPadrao = labelIdSensor.getBackground();

    public GUISensor(Point posicao, Dimension dimensao) {
        setTitle("CRUD - Sensor");
        setSize(dimensao);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);
        habilitarAtributos(true, false, false, false);
        btnCreate.setToolTipText("Inserir novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        JToolBar Toolbar1 = new JToolBar();
        Toolbar1.add(labelIdSensor);
        Toolbar1.add(textFieldIdSensor);
        Toolbar1.add(btnRetrieve);
        Toolbar1.add(btnCreate);
        Toolbar1.add(btnUpdate);
        Toolbar1.add(btnDelete);
        Toolbar1.add(btnSave);
        Toolbar1.add(btnCancel);
        Toolbar1.add(btnList);
        btnSave.setVisible(false);
        btnCancel.setVisible(false);  //atributos
        JPanel centro = new JPanel();
        centro.setLayout(new GridLayout(4, 2));
        centro.add(labelNomeSensor);
        centro.add(textFieldNomeSensor);
        centro.add(labelTipoSensorIdTipoSensor);
        centro.add(textFieldTipoSensorIdTipoSensor);
        centro.add(labelLocalIdLocal);
        centro.add(textFieldLocalIdLocal);
        pnAvisos.add(labelAviso);
        pnAvisos.setBackground(Color.yellow);
        cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(pnAvisos, BorderLayout.SOUTH);
        textFieldIdSensor.requestFocus();
        textFieldIdSensor.selectAll();
        textFieldIdSensor.setBackground(Color.GREEN);
        labelAviso.setText("Digite um IdSensor e clic [Pesquisar]");

//--------------- listeners ----------------- 
        textFieldIdSensor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRetrieve.doClick();
            }
        });

//-----------------------------  btnRetrieve ------------------------------------------
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                sensor = new Sensor();
                textFieldIdSensor.setText(textFieldIdSensor.getText().trim());//caso tenham sido digitados espaços

                if (textFieldIdSensor.getText().equals("")) {
                    List<String> listaAuxiliar = daoSensor.listInOrderNomeStrings("nome");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnRetrieve.getLocationOnScreen();
                        lc.x = lc.x + btnRetrieve.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldIdSensor.setText(aux[0]);
                            btnRetrieve.doClick();
                        } else {
                            textFieldIdSensor.requestFocus();
                            textFieldIdSensor.selectAll();
                        }
                    }

                    textFieldIdSensor.requestFocus();
                    textFieldIdSensor.selectAll();
                } else {
                    try {
                        sensor.setIdSensor(Integer.valueOf(textFieldIdSensor.getText()));
                        sensor = daoSensor.obter(sensor.getIdSensor());
                        if (sensor != null) { //se encontrou na lista
                            textFieldNomeSensor.setText(String.valueOf(sensor.getNomeSensor()));
                            textFieldTipoSensorIdTipoSensor.setText(String.valueOf(sensor.getTipoSensorIdTipoSensor().getIdTipoSensor() + "-" + sensor.getTipoSensorIdTipoSensor().getNomeTipoSensor()));
                            textFieldLocalIdLocal.setText(String.valueOf(sensor.getLocalIdLocal().getIdLocal() + "-" + sensor.getLocalIdLocal().getNomeLocal()));
                            atvBotoes(false, true, true, true);
                            habilitarAtributos(true, false, false, false);
                            labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                            acao = "encontrou";
                        } else {
                            atvBotoes(true, true, false, false);
                            zerarAtributos();
                            labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]");
                        }
                        textFieldIdSensor.setBackground(Color.green);
                    } catch (Exception x) {
                        textFieldIdSensor.setOpaque(true);
                        textFieldIdSensor.selectAll();
                        textFieldIdSensor.requestFocus();
                        textFieldIdSensor.setBackground(Color.red);
                        labelAviso.setText("Tipo errado - " + x.getMessage());
                    }
                }
            }
        });

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                habilitarAtributos(false, true, true, true);
                textFieldNomeSensor.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        });

//-----------------------------  SAVE ------------------------------------------
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean deuRuim = false;
                if (acao.equals("insert")) {
                    sensor = new Sensor();
                }
                try {
                    sensor.setIdSensor(Integer.valueOf((textFieldIdSensor.getText())));
                } catch (Exception erro2) {
                    deuRuim = true;
                    textFieldIdSensor.setBackground(Color.red);
                }
                sensor.setNomeSensor(String.valueOf(textFieldNomeSensor.getText()));
                sensor.setTipoSensorIdTipoSensor(daoTipoSensor.obter(Integer.valueOf(textFieldTipoSensorIdTipoSensor.getText().split("-")[0])));
                sensor.setLocalIdLocal(daoLocal.obter(Integer.valueOf(textFieldLocalIdLocal.getText().split("-")[0])));
                if (!deuRuim) {
                    if (acao.equals("insert")) {
                        daoSensor.inserir(sensor);
                        labelAviso.setText("Registro inserido.");
                    } else {
                        daoSensor.atualizar(sensor);
                        labelAviso.setText("Registro alterado.");
                    }
                    habilitarAtributos(true, false, false, false);
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                }//!deu ruim
                else {
                    labelAviso.setText("Erro nos dados - corrija");
                    labelAviso.setBackground(Color.red);
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                atvBotoes(false, true, false, false);
                habilitarAtributos(true, false, false, false);
                mostrarBotoes(true);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                acao = "list";
                GUISensorListagem guiSensorListagem = new GUISensorListagem(daoSensor.listInOrderNome(), getBounds().x, getBounds().y, dimensao);
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "update";
                mostrarBotoes(false);
                habilitarAtributos(false, true, true, true);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + sensor.getNomeSensor() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoSensor.remover(sensor);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    textFieldNomeSensor.requestFocus();
                    textFieldNomeSensor.selectAll();
                }
            }
        });// ----------------   Janela Pesquisar para FKs -----------------
        textFieldTipoSensorIdTipoSensor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                List<String> listaAuxiliar = daoTipoSensor.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5,
                            textFieldTipoSensorIdTipoSensor.getBounds().y + textFieldTipoSensorIdTipoSensor.getHeight()).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        textFieldTipoSensorIdTipoSensor.setText(selectedItem);

                        //preparar para salvar
                        tipoSensor = daoTipoSensor.obter(Integer.valueOf(aux[0]));

                    } else {
                        textFieldTipoSensorIdTipoSensor.requestFocus();
                        textFieldTipoSensorIdTipoSensor.selectAll();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Não há nenhum sensor cadastrado.");
                }
            }
        });
        textFieldLocalIdLocal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                List<String> listaAuxiliar = daoLocal.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5,
                            textFieldLocalIdLocal.getBounds().y + textFieldLocalIdLocal.getHeight()).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        textFieldLocalIdLocal.setText(selectedItem);

                        //preparar para salvar
                        local = daoLocal.obter(Integer.valueOf(aux[0]));

                    } else {
                        textFieldLocalIdLocal.requestFocus();
                        textFieldLocalIdLocal.selectAll();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Não há nenhum sensor cadastrado.");
                }
            }
        });
        textFieldNomeSensor.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldNomeSensor.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldNomeSensor.setBackground(corPadrao);
            }
        });
        textFieldTipoSensorIdTipoSensor.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldTipoSensorIdTipoSensor.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldTipoSensorIdTipoSensor.setBackground(corPadrao);
            }
        });
        textFieldLocalIdLocal.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldLocalIdLocal.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldLocalIdLocal.setBackground(corPadrao);
            }
        });
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //antes de sair do sistema, grava os dados da lista em disco
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Sai   
                dispose();
            }
        });
        setModal(true);
        setLocation(posicao);
        setVisible(true);//faz a janela ficar visível  
    }

    public static void main(String[] args) {
        new GUISensor(new Point(880, 250), new Dimension(800, 600));
    }
}
