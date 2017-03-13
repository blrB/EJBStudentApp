package by.bsuir.aipos.client;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ExitAdapter extends WindowAdapter {

    @Override
    public void windowClosing(WindowEvent e) {
        int confirm = JOptionPane.showOptionDialog(
                null, "Are you sure to close?",
                "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (confirm == 0) {
            MainWindow.logger.info("Exit client");
            System.exit(0);
        }
    }
}
