package controller;

import model.AUXFileReading;
import model.QuestionsAndAnswersData;
import view.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * <h3>ActionListener for the StartTestPanel.</h3>
 * <p>
 * This class is responsible for handling the user's actions in the StartTestPanel.
 * </p>
 */
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

    /**
     * Invoked when an action occurs in the StartPanel.
     * Based on what button is pressed, the program will procced the corresponding way.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        // 0 - start, 1 - statistics, 2 - settings
        int buttonPressed = actionPerformedStartPanel(source);
        if (buttonPressed == 0) {
            actionPerformedStartModeDialog(source);
        }
        if (buttonPressed == 1) {
            actionPerformedQuestionPanel(source);
        }
        if (buttonPressed == 2) {
            actionPerformedOverviewPanel(source);
        }

    }

    /**
     * Checks what button in startPanel is pressed.
     * @param source the event to be processed
     * @return 0 if startButton is pressed, 1 if statisticsButton is pressed,
     * 2 if settingsButton is pressed, -1 if none of the buttons are pressed
     */
    private int actionPerformedStartPanel(Object source) {

        boolean startButtonPressed = (source == startPanel.getStartButton());
        if (startButtonPressed) {
            mainFrame.openStartModeDialog();
            return 0;
        }
        boolean statisticsButtonPressed = (source == startPanel.getStatisticsButton());
        if (statisticsButtonPressed) {
            mainFrame.openStatisticsPanel();
            return 1;
        }
        boolean settingsButtonPressed = (source == startPanel.getSettingsButton());
        if (settingsButtonPressed) {
            mainFrame.openSettingsPanel();
            return 2;
        }
        return -1;
    }

    /**
     * Checks input from start mode dialog.
     * @param source the event to be processed
     */
    private void actionPerformedStartModeDialog(Object source) {

        boolean classicModeButtonPressed = (source == startModeDialog.getClassicModeButton());
        if (classicModeButtonPressed) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
            int value = fileChooser.showOpenDialog(mainFrame);
            if (value == JFileChooser.APPROVE_OPTION) {

                // Here we get the list of questions from the file
                AUXFileReading.setFile(fileChooser.getSelectedFile());
                try {
                    AUXFileReading.splitFileToList();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            startModeDialog.dispose();
            mainFrame.startQuestioning();
            return;
        }
        boolean numberModeButtonPressed = (source == startModeDialog.getNumberModeButton());
        if (numberModeButtonPressed) {

            String stringNumber = startModeDialog.getInputNumberField().getText();
            try {
                int number = Integer.parseInt(stringNumber);
                AUXFileReading.numberModeQuestionList(number);
                AUXFileReading.setFile(null);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainFrame, "Please input a number!");
                return;
            }

            startModeDialog.dispose();
            mainFrame.startQuestioning();
            return;
        }
    }

    /**
     * Checks what button in questionPanel is pressed.
     * @param source the event to be processed
     */
    private void actionPerformedQuestionPanel(Object source) {

        boolean nextQuestionButtonPressed = (source == questionPanel.getNextQuestionButton());
        if (nextQuestionButtonPressed) {
            // Check to see are there any buttons selected
            if (questionPanel.getButtonGroup().getSelection() != null) {
                // send answer to AllQuestionData
                if (questionPanel.getButtonGroup().getSelection().getActionCommand().equals("KNOW")) {
                    QuestionsAndAnswersData.addToAllAnswers("YES");
                } else if (questionPanel.getButtonGroup().getSelection().getActionCommand().equals("DONT KNOW")) {
                    QuestionsAndAnswersData.addToAllAnswers("NO");
                }
                questionPanel.getButtonGroup().clearSelection();
                mainFrame.nextQuestion();
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Please select an answer");
            }
        }
    }

    /**
     * Checks what button in overviewPanel is pressed.
     * @param source the event to be processed.
     */
    private void actionPerformedOverviewPanel(Object source) {
        boolean backButtonPressed = (source == overviewPanel.getBackButton());
        if (backButtonPressed) {
            mainFrame.backToStartPanelFromOverview();
            statisticsPanel.getTextArea().setText(AUXFileReading.getStatistics());
        }
    }
}
