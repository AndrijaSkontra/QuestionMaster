package controller;

import view.MainFrame;
import view.StatisticsPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatisticsActionListener implements ActionListener {

    private MainFrame mainFrame;
    private StatisticsPanel statisticsPanel;

    public StatisticsActionListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void setStatisticsPanel(StatisticsPanel statisticsPanel) {
        this.statisticsPanel = statisticsPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == statisticsPanel.getBackButton()) {
            mainFrame.backToStartPanelFromStatistics();
        }
    }
}
