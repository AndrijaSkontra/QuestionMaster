package view;

import controller.StartTestActionListener;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class StartModeDialog extends JDialog{

    private JButton ClassicModeButton;
    private JButton NumberModeButton;
    private JLabel chooseModeLabel;
    private StartTestActionListener startTestActionListener;
    private JTextField inputNumberField;

    public StartModeDialog() {
        super();
        setSize(300, 300);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
    }

    public void setMainActionListener(StartTestActionListener startTestActionListener) {
        this.startTestActionListener = startTestActionListener;
    }

    private void initComponents() {
        ClassicModeButton = new JButton("Classic Mode");
        ClassicModeButton.setFont(MainFrame.BOLD);

        NumberModeButton = new JButton("Number Mode");
        NumberModeButton.setFont(MainFrame.BOLD);

        chooseModeLabel = new JLabel("Choose mode: ");
        chooseModeLabel.setFont(MainFrame.ITALIC);

        inputNumberField = new JTextField();
    }

    private void layoutComponents() {
        setLayout(new MigLayout("fillx, center, gap 5%"));
        add(chooseModeLabel, "spanx, align center, gapbottom 10%, wrap");
        add(ClassicModeButton, "spanx, grow, gapbottom 20%, wmax 170, align center, wrap");
        add(NumberModeButton, "spanx, grow, gapbottom 2%, wmax 170, align center, wrap");
        add(new JLabel("Input number: "), "align right");
        add(inputNumberField, "grow, wmax 50");
    }

    public void activateDialog() {
        ClassicModeButton.addActionListener(startTestActionListener);
        NumberModeButton.addActionListener(startTestActionListener);
    }

    public JButton getClassicModeButton() {
        return ClassicModeButton;
    }

    public JButton getNumberModeButton() {
        return NumberModeButton;
    }

    public JTextField getInputNumberField() {
        return inputNumberField;
    }
}
