package Accouting_logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class Main_logger {

    private static final String BACKGROUND_IMAGE_PATH = "Accounting/0c77d862252232dc194ebfa0ed83d32fa028a1bc.png"; // 相対パス
    private static final String CSV_FILENAME = "accounting_log.csv";

    private JFrame frame;
    private JTextField itemField;
    private JTextField amountField;
    private JTextArea memoArea;
    private String lastType = ""; // 最後に押された収入 or 出費

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main_logger().createAndShowGUI());
    }

    public void createAndShowGUI() {
        frame = new JFrame("Accounting App - Main_logger");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(730, 400);
        frame.setLocationRelativeTo(null);

        // 背景付きパネル
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

        // accounting item and amount input fields
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        inputPanel.setOpaque(false);
        itemField = new JTextField();
        amountField = new JTextField();
        JLabel itemLabel = new JLabel("Accounting item:");
        itemLabel.setForeground(Color.WHITE);

        JLabel amountLabel = new JLabel("Cost: $");
        amountLabel.setForeground(Color.WHITE);

        inputPanel.add(itemLabel);
        inputPanel.add(itemField);
        inputPanel.add(amountLabel);
        inputPanel.add(amountField);


        // ボタン（収入・出費）
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);
        JButton incomeButton = new JButton("収入");
        incomeButton.setForeground(Color.RED);
        JButton expenseButton = new JButton("出費");
        expenseButton.setForeground(Color.BLUE);

        buttonPanel.add(incomeButton);
        buttonPanel.add(expenseButton);

        // （選択）＋保存・開くボタン
        JPanel filePanel = new JPanel(new FlowLayout());
        filePanel.setOpaque(false);
        JLabel optionLabel = new JLabel("（選択）");
        JButton saveButton = new JButton("保存");
        JButton loadButton = new JButton("開く");

        filePanel.add(optionLabel);
        filePanel.add(saveButton);
        filePanel.add(loadButton);

        // 備考欄
        JLabel memoLabel = new JLabel("備考欄:");
        memoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        memoArea = new JTextArea(4, 30);
        memoArea.setLineWrap(true);
        memoArea.setWrapStyleWord(true);
        memoArea.setEditable(true);
        JScrollPane memoScroll = new JScrollPane(memoArea);

        // イベント
        incomeButton.addActionListener(e -> lastType = "収入");
        expenseButton.addActionListener(e -> lastType = "出費");

        saveButton.addActionListener(e -> saveToCSV());
        loadButton.addActionListener(e -> loadFromCSV());

        // パネルに追加
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
            JOptionPane.showMessageDialog(frame, "会計項目、金額、収入/出費の選択をしてください。", "エラー", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            File file = new File(CSV_FILENAME);
            boolean fileExists = file.exists();

            FileOutputStream fos = new FileOutputStream(file, true);
            OutputStreamWriter osw = new OutputStreamWriter(fos, fileExists ? "UTF-8" : "UTF-8");
            BufferedWriter writer = new BufferedWriter(osw);

            // 初回保存時にBOM付きで書く（UTF-8でExcelでも開ける）
            if (!fileExists) {
                fos.write(0xEF);
                fos.write(0xBB);
                fos.write(0xBF);
            }

            writer.write(String.format("%s,%s,%s,%s%n", item, amount, lastType, memo));
            writer.close();

            JOptionPane.showMessageDialog(frame, "CSVファイルに保存しました！", "保存完了", JOptionPane.INFORMATION_MESSAGE);

            itemField.setText("");
            amountField.setText("");
            memoArea.setText("");
            lastType = "";

        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "保存エラー：" + e.getMessage(), "ファイルエラー", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void loadFromCSV() {
        try (Scanner scanner = new Scanner(new File(CSV_FILENAME))) {
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append("\n");
            }
            JOptionPane.showMessageDialog(frame, sb.length() > 0 ? sb.toString() : "データがありません。", "読み込み結果", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "読み込みエラー：" + e.getMessage(), "ファイルエラー", JOptionPane.ERROR_MESSAGE);
        }
    }
}
