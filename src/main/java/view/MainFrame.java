package view;

import controller.SettingsActionListener;
import controller.StartTestActionListener;
import controller.StatisticsActionListener;
import model.AUXFileReading;
import model.AllQuestionData;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class MainFrame extends JFrame {

    private StartPanel startPanel;
    private QuestionPanel questionPanel;
    private StartModeDialog startModeDialog;
    private OverviewPanel overviewPanel;
    private SettingsPanel settingsPanel;
    private StatisticsPanel statisticsPanel;

    private StartTestActionListener startTestActionListener;
    private SettingsActionListener settingsActionListener;
    private StatisticsActionListener statisticsActionListener;

    public static final Font BOLD = new Font(Font.SERIF, Font.BOLD, 20);
    public static final Font ITALIC = new Font(Font.SERIF, Font.ITALIC, 20);

    public MainFrame() {
        super("Question Master");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(
                "src\\main\\resources\\images\\question-mark.png").getImage());

        initComponents();
        layoutComponents();
        activateFrame();

    }

    private void initComponents() {
        startPanel = new StartPanel();
        questionPanel = new QuestionPanel();
        overviewPanel = new OverviewPanel();
        settingsPanel = new SettingsPanel();
        statisticsPanel = new StatisticsPanel();
    }

    private void layoutComponents() {

        setLayout(new MigLayout("", "[]", "[]"));
        // startPanel.setVisible(false); hidemode 3,
        add(startPanel, "width 100%, height 100%");
    }

    private void activateFrame() {

        startTestActionListener = new StartTestActionListener(this);
        settingsActionListener = new SettingsActionListener(this);
        statisticsActionListener = new StatisticsActionListener(this);

        startTestActionListener.setStartPanel(startPanel);
        startPanel.setMainActionListener(startTestActionListener);
        startPanel.activatePanel();

        startTestActionListener.setQuestionPanel(questionPanel);
        questionPanel.setMainActionListener(startTestActionListener);
        questionPanel.activatePanel();

        startTestActionListener.setOverviewPanel(overviewPanel);
        overviewPanel.setMainActionListener(startTestActionListener);
        overviewPanel.activatePanel();

        settingsActionListener.setSettingsPanel(settingsPanel);
        settingsPanel.setSettingsActionListener(settingsActionListener);
        settingsPanel.activatePanel();


        statisticsActionListener.setStatisticsPanel(statisticsPanel);
        startTestActionListener.setStatisticsPanel(statisticsPanel);
        statisticsPanel.setStatisticsActionListener(statisticsActionListener);
        statisticsPanel.activatePanel();
    }

    public void startQuestioning() {
        startPanel.setVisible(false);
        add(startPanel, "hidemode 3");
        questionPanel.setVisible(true);
        add(questionPanel, "width 100%, height 100%");
        nextQuestion();

    }

    public void nextQuestion() {

        if (AUXFileReading.questionList.size() > 0) {
            int randomIndex = ThreadLocalRandom.current().nextInt(0, AUXFileReading.questionList.size());
            String question = AUXFileReading.questionList.get(randomIndex);
            AllQuestionData.allQuestions.add(question);
            AUXFileReading.questionList.remove(randomIndex);
            questionPanel.getQuestionLabel().setText("<html><p>" + question + "</p></html>");

        } else {

            questionPanel.setVisible(false);
            add(questionPanel, "hidemode 3");
            overviewPanel.setData();
            overviewPanel.setVisible(true);
            add(overviewPanel, "width 100%, height 100%");

        }
    }

    public void openStartModeDialog() {

        startModeDialog = new StartModeDialog();

        startTestActionListener.setStartModeDialog(startModeDialog);
        startModeDialog.setMainActionListener(startTestActionListener);
        startModeDialog.activateDialog();
    }

    public void backToStartPanel() {
        overviewPanel.setVisible(false);
        add(overviewPanel, "hidemode 3");
        startPanel.setVisible(true);
        add(startPanel, "width 100%, height 100%");
    }

    public void openSettingsPanel() {
        startPanel.setVisible(false);
        add(startPanel, "hidemode 3");
        settingsPanel.setVisible(true);
        add(settingsPanel, "width 100%, height 100%");
    }

    public void openStatisticsPanel() {
        startPanel.setVisible(false);
        add(startPanel, "hidemode 3");
        statisticsPanel.setVisible(true);
        add(statisticsPanel, "width 100%, height 100%");
    }

    public void backToStartPanelFromSettings() {
        settingsPanel.setVisible(false);
        add(settingsPanel, "hidemode 3");
        startPanel.setVisible(true);
        add(startPanel, "width 100%, height 100%");
    }

    public void backToStartPanelFromStatistics() {
        statisticsPanel.setVisible(false);
        add(statisticsPanel, "hidemode 3");
        startPanel.setVisible(true);
        add(startPanel, "width 100%, height 100%");
    }

    public void updateUI() {
        SwingUtilities.updateComponentTreeUI(this);
        SwingUtilities.updateComponentTreeUI(questionPanel);
        SwingUtilities.updateComponentTreeUI(overviewPanel);
        SwingUtilities.updateComponentTreeUI(settingsPanel);
        SwingUtilities.updateComponentTreeUI(statisticsPanel);
    }
}
