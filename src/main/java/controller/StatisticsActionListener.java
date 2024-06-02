package controller;

import view.MainFrame;
import view.StatisticsPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <h3>ActionListener for the StatisticsPanel.</h3>
 * <p>
 * This class is responsible for handling the user's actions in the StatisticsPanel.
 * </p>
 */
public class StatisticsActionListener implements ActionListener {

    private MainFrame mainFrame;
    private StatisticsPanel statisticsPanel;

    public StatisticsActionListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void setStatisticsPanel(StatisticsPanel statisticsPanel) {
        this.statisticsPanel = statisticsPanel;
    }

    /**
     * Handles the user's actions in the StatisticsPanel.
     * @param e the event to be processed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean backButtonPressed = (e.getSource() == statisticsPanel.getBackButton());
        if (backButtonPressed) {
            mainFrame.backToStartPanelFromStatistics();
        }
    }
}
