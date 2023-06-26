package view;

import controller.SettingsActionListener;
import controller.StartTestActionListener;
import controller.StatisticsActionListener;
import model.AUXFileReading;
import model.QuestionsAndAnswersData;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The main frame of the application.
 * Contains all the panels.
 * @author Andrija Škontra
 */
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

    /**
     * Initialize the components of the frame.
     */
    private void initComponents() {
        startPanel = new StartPanel();
        questionPanel = new QuestionPanel();
        overviewPanel = new OverviewPanel();
        settingsPanel = new SettingsPanel();
        statisticsPanel = new StatisticsPanel();
    }
    /**
     * Layout the components of the frame.
     * Uses MigLayout.
     */
    private void layoutComponents() {

        setLayout(new MigLayout("", "[]", "[]"));
        // startPanel.setVisible(false); hidemode 3,
        add(startPanel, "width 100%, height 100%");
    }

    /**
     * Activates the frame in a way that the
     * panels can communicate with each other.
     */
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

    /**
     * After the user has chosen the mode, the
     * questioning starts. This method is called from
     * the StartTestActionListener.
     */
    public void startQuestioning() {
        startPanel.setVisible(false);
        add(startPanel, "hidemode 3");
        questionPanel.setVisible(true);
        add(questionPanel, "width 100%, height 100%");
        nextQuestion();

    }

    /**
     * This method is called every time a question is answered.
     * When there are no more questions left, the overview panel
     * is shown.
     */
    public void nextQuestion() {

        if (AUXFileReading.getQuestionListSize() > 0) {
            int randomIndex = ThreadLocalRandom.current().nextInt(0, AUXFileReading.getQuestionListSize());
            String question = AUXFileReading.getQuestionListElement(randomIndex);
            QuestionsAndAnswersData.addToAllQuestions(question);
            AUXFileReading.removeQuestionListElement(randomIndex);
            questionPanel.getQuestionLabel().setText("<html><p>" + question + "</p></html>");

        } else {

            questionPanel.setVisible(false);
            add(questionPanel, "hidemode 3");
            overviewPanel.setData();
            overviewPanel.setVisible(true);
            add(overviewPanel, "width 100%, height 100%");

        }
    }

    /**
     * Opens the start mode dialog where we can see
     * different modes to choose from.
     */
    public void openStartModeDialog() {

        startModeDialog = new StartModeDialog();

        startTestActionListener.setStartModeDialog(startModeDialog);
        startModeDialog.setMainActionListener(startTestActionListener);
        startModeDialog.activateDialog();
    }

    /**
     * Opens the settings panel where we can see
     * different settings to choose from.
     * Light mode and Dark mode currently.
     */
    public void openSettingsPanel() {
        startPanel.setVisible(false);
        add(startPanel, "hidemode 3");
        settingsPanel.setVisible(true);
        add(settingsPanel, "width 100%, height 100%");
    }

    /**
     * Opens the statistics panel where we can see
     * all the attempts, when were they made and also
     * the percentage of correct answers.
     */
    public void openStatisticsPanel() {
        startPanel.setVisible(false);
        add(startPanel, "hidemode 3");
        statisticsPanel.setVisible(true);
        add(statisticsPanel, "width 100%, height 100%");
    }

    /**
     * Returns the user to the start panel from
     * the overview panel after back button is pressed.
     */
    public void backToStartPanelFromOverview() {
        overviewPanel.setVisible(false);
        add(overviewPanel, "hidemode 3");
        startPanel.setVisible(true);
        add(startPanel, "width 100%, height 100%");
    }

    /**
     * Returns the user to the start panel from
     * the settings panel after back button is pressed.
     */
    public void backToStartPanelFromSettings() {
        settingsPanel.setVisible(false);
        add(settingsPanel, "hidemode 3");
        startPanel.setVisible(true);
        add(startPanel, "width 100%, height 100%");
    }

    /**
     * Returns the user to the start panel from
     * the statistics panel after back button is pressed.
     */
    public void backToStartPanelFromStatistics() {
        statisticsPanel.setVisible(false);
        add(statisticsPanel, "hidemode 3");
        startPanel.setVisible(true);
        add(startPanel, "width 100%, height 100%");
    }

    /**
     * Updates the UI of the frame and all the panels.
     * This is needed when we change Look and Feel(theme).
     */
    public void updateUI() {
        SwingUtilities.updateComponentTreeUI(this);
        SwingUtilities.updateComponentTreeUI(questionPanel);
        SwingUtilities.updateComponentTreeUI(overviewPanel);
        SwingUtilities.updateComponentTreeUI(settingsPanel);
        SwingUtilities.updateComponentTreeUI(statisticsPanel);
    }
}
