package SwingEmailSender;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new EmailSenderUI().setVisible(true);
        });
    }
}