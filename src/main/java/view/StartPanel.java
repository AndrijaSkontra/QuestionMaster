package view;

import controller.StartTestActionListener;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class StartPanel extends JPanel {

    private JButton startButton;
    private JButton statisticsButton;
    private JButton settingsButton;
    private StartTestActionListener startTestActionListener;

    public StartPanel() {

        initComponents();
        layoutComponents();

    }

    public void setMainActionListener(StartTestActionListener startTestActionListener) {
        this.startTestActionListener = startTestActionListener;
    }

    private void initComponents() {
        startButton = new JButton(" START NEW TEST");
        startButton.setFont(MainFrame.BOLD);

        settingsButton = new JButton("SETTINGS");
        settingsButton.setFont(MainFrame.BOLD);

        statisticsButton = new JButton("STATISTICS");
        statisticsButton.setFont(MainFrame.BOLD);
    }

    private void layoutComponents() {

        setLayout(new MigLayout("wrap, center, gap 20% 20%, insets 10%", "[]", "[][][]"));
        add(startButton);
        add(statisticsButton, "grow");
        add(settingsButton, "grow");

    }

    public void activatePanel() {

        startButton.addActionListener(startTestActionListener);
        statisticsButton.addActionListener(startTestActionListener);
        settingsButton.addActionListener(startTestActionListener);

    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getStatisticsButton() {
        return statisticsButton;
    }

    public JButton getSettingsButton() {
        return settingsButton;
    }
}
