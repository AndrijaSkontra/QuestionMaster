package controller;

import model.AUXFileReading;
import model.AllQuestionData;
import view.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartTestActionListener implements ActionListener {

    private StartPanel startPanel;
    private MainFrame mainFrame;
    private StartModeDialog startModeDialog;
    private QuestionPanel questionPanel;
    private OverviewPanel overviewPanel;
    private StatisticsPanel statisticsPanel;

    public StartTestActionListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void setStartPanel(StartPanel startPanel) {
        this.startPanel = startPanel;
    }

    public void setStartModeDialog(StartModeDialog startModeDialog) {
        this.startModeDialog = startModeDialog;
    }

    public void setQuestionPanel(QuestionPanel questionPanel) {
        this.questionPanel = questionPanel;
    }

    public void setOverviewPanel(OverviewPanel overviewPanel) {
        this.overviewPanel = overviewPanel;
    }

    public void setStatisticsPanel(StatisticsPanel statisticsPanel) {
        this.statisticsPanel = statisticsPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startPanel.getStartButton()) {
            mainFrame.openStartModeDialog();

        } else if (e.getSource() == startPanel.getStatisticsButton()) {
            mainFrame.openStatisticsPanel();

        } else if (e.getSource() == startPanel.getSettingsButton()) {
            mainFrame.openSettingsPanel();

        } else if (e.getSource() == startModeDialog.getClassicModeButton()) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
            int value = fileChooser.showOpenDialog(mainFrame);
            if (value == JFileChooser.APPROVE_OPTION) {

                // Here we get the list of questions from the file
                AUXFileReading.file = fileChooser.getSelectedFile();
                try {
                    AUXFileReading.splitFileToList();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            startModeDialog.dispose();
            mainFrame.startQuestioning();

        } else if (e.getSource() == startModeDialog.getNumberModeButton()) {

            String stringNumber = startModeDialog.getInputNumberField().getText();
            try {
                int number = Integer.parseInt(stringNumber);
                AUXFileReading.numberModeQuestionList(number);

                // bug fix
                AUXFileReading.file = null;

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainFrame, "Please input a number!");
                return;
            }

            startModeDialog.dispose();
            mainFrame.startQuestioning();

        } else if (e.getSource() == questionPanel.getNextQuestionButton()) {
            // Check to see are there any buttons selected
            if (questionPanel.getButtonGroup().getSelection() != null) {

                // send answer to AllQuestionData
                if (questionPanel.getButtonGroup().getSelection().getActionCommand().equals("KNOW")) {
                    AllQuestionData.questionAnswers.add("YES");
                } else if (questionPanel.getButtonGroup().getSelection().getActionCommand().equals("DONT KNOW")) {
                    AllQuestionData.questionAnswers.add("NO");
                }
                questionPanel.getButtonGroup().clearSelection();
                mainFrame.nextQuestion();
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Please select an answer");
            }
        } else if (e.getSource() == overviewPanel.getBackButton()) {
            mainFrame.backToStartPanel();
            statisticsPanel.getTextArea().setText(AUXFileReading.getStatistics());
        }
    }
}
