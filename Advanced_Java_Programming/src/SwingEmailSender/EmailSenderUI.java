package SwingEmailSender;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.regex.Pattern;

public class EmailSenderUI extends JFrame {

    private JTextField senderField, receiverField, subjectField;
    private JTextArea messageArea;
    private JButton sendButton, clearButton;
    private JLabel statusLabel;
    private EmailService emailService;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);

    public EmailSenderUI() {
        emailService = new EmailService();
        initializeUI();
    }

    private void initializeUI() {
        // Frame setup
        setTitle("Classic Email Sender");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(230, 230, 230));
        setContentPane(mainPanel);

        // Header
        JLabel titleLabel = new JLabel("Email Sender Using Swing", JLabel.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setForeground(new Color(50, 50, 50));
        titleLabel.setBorder(new EmptyBorder(10, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(245, 245, 245));
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                new EmptyBorder(10, 10, 10, 10)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Sender
        addFormLabel(formPanel, "From:", gbc, 0);
        senderField = new JTextField(20);
        styleTextField(senderField);
        gbc.gridx = 1;
        formPanel.add(senderField, gbc);

        // Receiver
        addFormLabel(formPanel, "To:", gbc, 1);
        receiverField = new JTextField(20);
        styleTextField(receiverField);
        gbc.gridx = 1;
        formPanel.add(receiverField, gbc);

        // Subject
        addFormLabel(formPanel, "Subject:", gbc, 2);
        subjectField = new JTextField(20);
        styleTextField(subjectField);
        gbc.gridx = 1;
        formPanel.add(subjectField, gbc);

        // Message
        addFormLabel(formPanel, "Message:", gbc, 3);
        messageArea = new JTextArea(8, 20);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
        messageArea.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setBorder(BorderFactory.createBevelBorder(1));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        formPanel.add(scrollPane, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 245));
        sendButton = new JButton("Send");
        clearButton = new JButton("Clear");
        styleClassicButton(sendButton);
        styleClassicButton(clearButton);
        buttonPanel.add(sendButton);
        buttonPanel.add(clearButton);

        // Status label
        statusLabel = new JLabel("Ready to send email");
        statusLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        statusLabel.setForeground(new Color(50, 50, 50));

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(245, 245, 245));
        bottomPanel.add(statusLabel, BorderLayout.WEST);
        bottomPanel.add(buttonPanel, BorderLayout.EAST);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Action listeners
        sendButton.addActionListener(e -> {
            if (validateInputs()) sendEmail();
        });
        clearButton.addActionListener(e -> clearForm());
    }

    private void addFormLabel(JPanel panel, String text, GridBagConstraints gbc, int row) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Tahoma", Font.BOLD, 14));
        label.setForeground(new Color(50, 50, 50));
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.0;
        gbc.gridheight = 1;
        panel.add(label, gbc);
    }

    private void styleTextField(JTextField field) {
        field.setFont(new Font("Tahoma", Font.PLAIN, 14));
        field.setBackground(Color.WHITE);
        field.setForeground(Color.BLACK);
    }

    private void styleClassicButton(JButton button) {
        button.setFont(new Font("Tahoma", Font.BOLD, 14));
        button.setBackground(new Color(220, 220, 220));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(true);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
    }

    private boolean validateInputs() {
        if (!validateEmail(senderField.getText())) {
            JOptionPane.showMessageDialog(this, "Enter valid sender email", "Error", JOptionPane.ERROR_MESSAGE);
            senderField.requestFocus();
            return false;
        }
        if (!validateEmail(receiverField.getText())) {
            JOptionPane.showMessageDialog(this, "Enter valid receiver email", "Error", JOptionPane.ERROR_MESSAGE);
            receiverField.requestFocus();
            return false;
        }
        if (subjectField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter subject", "Error", JOptionPane.ERROR_MESSAGE);
            subjectField.requestFocus();
            return false;
        }
        if (messageArea.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter message", "Error", JOptionPane.ERROR_MESSAGE);
            messageArea.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validateEmail(String email) {
        return email != null && !email.trim().isEmpty() && emailPattern.matcher(email).matches();
    }

    private void sendEmail() {
        sendButton.setEnabled(false);
        statusLabel.setText("Sending email...");

        SwingWorker<Boolean, Void> worker = new SwingWorker<>() {
            @Override
            protected Boolean doInBackground() {
                Email email = new Email(senderField.getText(), receiverField.getText(), subjectField.getText(), messageArea.getText());
                return emailService.sendEmail(email);
            }

            @Override
            protected void done() {
                try {
                    boolean success = get();
                    if (success) {
                        statusLabel.setText("Email sent successfully!");
                        JOptionPane.showMessageDialog(EmailSenderUI.this, "Email Sent!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        clearForm();
                    } else {
                        statusLabel.setText("Failed to send email");
                        JOptionPane.showMessageDialog(EmailSenderUI.this, "Failed to send email", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    statusLabel.setText("Error: " + e.getMessage());
                } finally {
                    sendButton.setEnabled(true);
                }
            }
        };
        worker.execute();
    }

    private void clearForm() {
        senderField.setText("");
        receiverField.setText("");
        subjectField.setText("");
        messageArea.setText("");
        statusLabel.setText("Ready to send email");
        senderField.requestFocus();
    }
}
