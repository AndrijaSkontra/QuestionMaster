package view;

import controller.StatisticsActionListener;
import model.AUXFileReading;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class StatisticsPanel extends JPanel {

    private JTextArea textArea;
    private JScrollPane textAreaWithScrollPane;
    private JLabel statisticsLabel;
    private JButton backButton;
    private StatisticsActionListener statisticsActionListener;

    public StatisticsPanel() {

        initComponents();
        layoutComponents();
        activatePanel();
    }

    public void setStatisticsActionListener(StatisticsActionListener statisticsActionListener) {
        this.statisticsActionListener = statisticsActionListener;
    }

    private void initComponents() {

        statisticsLabel = new JLabel("Statistics:");
        statisticsLabel.setFont(MainFrame.BOLD);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setText(AUXFileReading.getStatistics());

        textAreaWithScrollPane = new JScrollPane(textArea);

        backButton = new JButton("Back");

    }

    private void layoutComponents() {
        setLayout(new MigLayout("center, gap 3%"));
        add(statisticsLabel, "align center, wrap");
        add(textAreaWithScrollPane, "align center, width 70%, height 70%, wrap");
        add(backButton, "align center");
    }

    public void activatePanel() {
        backButton.addActionListener(statisticsActionListener);
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}
