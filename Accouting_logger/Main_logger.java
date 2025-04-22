package Accounting_logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class Main_logger {

    private static final String BACKGROUND_IMAGE_PATH = "Your favourite image Here"; // relative path
    private static final String CSV_FILENAME = "accounting_log.csv";

    private JFrame frame;
    private JTextField itemField;
    private JTextField amountField;
    private JTextArea memoArea;
    private String lastType = "";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main_logger().createAndShowGUI());
    }

    public void createAndShowGUI() {
        frame = new JFrame("Accounting App - Main_logger");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(730, 400);
        frame.setLocationRelativeTo(null);

        JPanel backgroundPanel = new JPanel() {
            Image bg = new ImageIcon(new File(BACKGROUND_IMAGE_PATH).getAbsolutePath()).getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));
        backgroundPanel.setOpaque(false);

        // Input Fields
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel.setOpaque(false);
        itemField = new JTextField();
        amountField = new JTextField();

        JLabel itemLabel = new JLabel("Item:");
        itemLabel.setForeground(Color.BLUE);
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setForeground(Color.BLUE);

        inputPanel.add(itemLabel);
        inputPanel.add(itemField);
        inputPanel.add(amountLabel);
        inputPanel.add(amountField);

        // Income / Expense Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);
        JButton incomeButton = new JButton("Income");
        incomeButton.setForeground(Color.RED);
        JButton expenseButton = new JButton("Expense");
        expenseButton.setForeground(Color.BLUE);
        buttonPanel.add(incomeButton);
        buttonPanel.add(expenseButton);

        // Save / Open Section
        JPanel filePanel = new JPanel(new FlowLayout());
        filePanel.setOpaque(false);
        JLabel optionLabel = new JLabel("(Choose)");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Open");
        filePanel.add(optionLabel);
        filePanel.add(saveButton);
        filePanel.add(loadButton);

        // Memo Area
        JLabel memoLabel = new JLabel("Comment:");
        memoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        memoArea = new JTextArea(4, 30);
        memoArea.setLineWrap(true);
        memoArea.setWrapStyleWord(true);
        memoArea.setEditable(true);
        JScrollPane memoScroll = new JScrollPane(memoArea);

        // Button Actions
        incomeButton.addActionListener(e -> lastType = "Income");
        expenseButton.addActionListener(e -> lastType = "Expense");

        saveButton.addActionListener(e -> saveToCSV());
        loadButton.addActionListener(e -> loadFromCSV());

        backgroundPanel.add(Box.createVerticalStrut(10));
        backgroundPanel.add(inputPanel);
        backgroundPanel.add(buttonPanel);
        backgroundPanel.add(filePanel);
        backgroundPanel.add(memoLabel);
        backgroundPanel.add(memoScroll);

        frame.setContentPane(backgroundPanel);
        frame.setVisible(true);
    }

    private void saveToCSV() {
        String item = itemField.getText().trim();
        String amount = amountField.getText().trim();
        String memo = memoArea.getText().trim();

        if (item.isEmpty() || amount.isEmpty() || lastType.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter item, amount, and choose income or expense.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            File file = new File(CSV_FILENAME);
            boolean fileExists = file.exists();

            FileOutputStream fos = new FileOutputStream(file, true);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            BufferedWriter writer = new BufferedWriter(osw);

            if (!fileExists) {
                fos.write(0xEF);
                fos.write(0xBB);
                fos.write(0xBF);
            }

            writer.write(String.format("%s,%s,%s,%s%n", item, amount, lastType, memo));
            writer.close();

            JOptionPane.showMessageDialog(frame, "Saved to CSV file!", "Success", JOptionPane.INFORMATION_MESSAGE);

            itemField.setText("");
            amountField.setText("");
            memoArea.setText("");
            lastType = "";

        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Save Error: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadFromCSV() {
        try (Scanner scanner = new Scanner(new File(CSV_FILENAME))) {
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append("\n");
            }
            JOptionPane.showMessageDialog(frame, sb.length() > 0 ? sb.toString() : "No data available.", "Load Result", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Load Error: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
