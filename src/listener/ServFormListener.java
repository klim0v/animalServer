package listener;

import controllers.GeneralController;
import form.ServForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;

import static com.company.Valid.valid;


public class ServFormListener implements ActionListener {
    private ServForm servForm;
    static boolean started = false;

    public ServFormListener(ServForm servForm) {
        this.servForm = servForm;
        this.servForm.getConnectButton().addActionListener(this);
        this.servForm.getExitButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(servForm.getConnectButton())) {

            Calendar c=Calendar.getInstance();
            int year=c.get(Calendar.YEAR);
            int month=c.get(Calendar.MONTH)+1;
            int day=c.get(Calendar.DAY_OF_MONTH);
            int hour=c.get(Calendar.HOUR_OF_DAY);
            int minute=c.get(Calendar.MINUTE);
            int sec=c.get(Calendar.SECOND);
            String date = year + "/" + month + "/" + day + " " + hour + ":" + minute + ":" + sec + "\t";

            if (servForm.getConnectButton().getText().equals("Старт!")) {

                String port = servForm.getPortTextArea().getText();
                if (valid(port)) {
                    try {
                        GeneralController.startServ(port);
                    } catch (IOException e1) {
                        servForm.getDetalsTextArea().setText("Порт занят!");
                        return;
                    }
                } else {
                    servForm.getDetalsTextArea().setText("Не корректно введен порт");
                    return;
                }

                servForm.getConnectButton().setText("Стоп!");
                date += "Запущен на ";
                servForm.getIpTextArea().setEditable(false);
                servForm.getPortTextArea().setEditable(false);
                //servForm.getExitButton().setEnabled(false);
                started = true;
            }
            else {
                try {
                    GeneralController.stopServ();
                } catch (IOException e1) {
                    servForm.getDetalsTextArea().setText("Сервер занят и не может быть остановлен");
                    return;
                }
                servForm.getConnectButton().setText("Старт!");
                date += "Остановлен на ";
                servForm.getIpTextArea().setEditable(true);
                servForm.getPortTextArea().setEditable(true);
                started = false;
            }
            date += servForm.getIpTextArea().getText() + ":" + servForm.getPortTextArea().getText();
            servForm.getDetalsTextArea().setText(servForm.getDetalsTextArea().getText() + date + '\n');
        }

        if (e.getSource().equals(servForm.getExitButton())) {
            if (!started) {
                GeneralController.saveAndExit();
                servForm.dispose();
            } else {
                JOptionPane.showMessageDialog(servForm, "Сначала остановите сервер!");
            }
        }



    }
}
