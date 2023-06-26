package view;

import controller.StatisticsActionListener;
import model.AUXFileReading;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

/**
 * Class that represents the statistics panel.
 * It contains a text area with the statistics of the player.
 * It also contains a back button to go back to the main menu.
 */
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

    /**
     * Method that initializes the components of the panel.
     */
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

    /**
     * Method that sets the layout of the panel.
     * Uses MigLayout as layout manager.
     */
    private void layoutComponents() {
        setLayout(new MigLayout("center, gap 3%"));
        add(statisticsLabel, "align center, wrap");
        add(textAreaWithScrollPane, "align center, width 70%, height 70%, wrap");
        add(backButton, "align center");
    }

    /**
     * Method that activates the panel.
     * Currently only the back button needs activation.
     */
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
