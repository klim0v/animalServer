package form;

/**
 * Created by c-0k on 15.11.2016.
 */

import com.company.Server;

import javax.swing.*;

public class ServForm extends JFrame{
    JLabel welcomeLabel = new JLabel("<html><u>Серверная часть <i>ЗООПАРКА</i> Климова Сергея</html>");
    JTextArea detalsTextArea = new JTextArea();
    JLabel connectLabel = new JLabel("Введите IP адресс и номер порта:");
    JFormattedTextField ipTextArea = new JFormattedTextField();
    JFormattedTextField portTextArea = new JFormattedTextField();
    JButton connectButton = new JButton("Старт!");
    JButton exitButton = new JButton("Выход");

    public JButton getExitButton() {
        return exitButton;
    }

    public void setExitButton(JButton exitButton) {
        this.exitButton = exitButton;
    }

    public ServForm(){

        setResizable(false);

        //setUndecorated(true); //отключить верхнюю панель с заголовком
        //setDefaultLookAndFeelDecorated(true);
        //getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        setBounds(500,0,500,500);
        setSize(500, 500);

        welcomeLabel.setBounds(50, 10, 400, 15);
        add(welcomeLabel);

        detalsTextArea.setBounds(30, 30, 400, 300);
        detalsTextArea.setEditable(false);
        add(detalsTextArea);

        connectLabel.setBounds(150, 350, 300, 15);
        add(connectLabel);

        ipTextArea.setValue("localhost");
        ipTextArea.setBounds(150, 370, 100, 20);
        ipTextArea.setEditable(false);
        add(ipTextArea);

        portTextArea.setValue(Server.getDefaultPort());
        portTextArea.setBounds(260, 370, 50, 20);
        add(portTextArea);

        connectButton.setBounds(150, 400, 130, 30);
        add(connectButton);

        exitButton.setBounds(150, 440, 130, 30);
        add(exitButton);

        setLayout(null);
    }

    public JLabel getWelcomeLabel() {
        return welcomeLabel;
    }

    public void setWelcomeLabel(JLabel welcomeLabel) {
        this.welcomeLabel = welcomeLabel;
    }

    public JTextArea getDetalsTextArea() {
        return detalsTextArea;
    }

    public void setDetalsTextArea(JTextArea detalsTextArea) {
        this.detalsTextArea = detalsTextArea;
    }

    public JLabel getConnectLabel() {
        return connectLabel;
    }

    public void setConnectLabel(JLabel connectLabel) {
        this.connectLabel = connectLabel;
    }

    public JFormattedTextField getIpTextArea() {
        return ipTextArea;
    }

    public void setIpTextArea(JFormattedTextField ipTextArea) {
        this.ipTextArea = ipTextArea;
    }

    public JFormattedTextField getPortTextArea() {
        return portTextArea;
    }

    public void setPortTextArea(JFormattedTextField portTextArea) {
        this.portTextArea = portTextArea;
    }

    public JButton getConnectButton() {
        return connectButton;
    }

    public void setConnectButton(JButton connectButton) {
        this.connectButton = connectButton;
    }
}
