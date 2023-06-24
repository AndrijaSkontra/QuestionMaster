package view;

import controller.StartTestActionListener;
import model.QuestionsAndAnswersData;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


// init and layout components in setData
public class OverviewPanel extends JPanel {

    private JTable overviewTable;
    private String[] columnNames;
    private String[][] questionsAndAnswersData;
    private JScrollPane tableScrollPane;
    private JButton backButton;
    private StartTestActionListener startTestActionListener;
    private JLabel overviewLabel;
    private JLabel percentageLabel;

    public OverviewPanel() {
        columnNames = new String[]{"Question", "Answer known"};

        initComponents();
        layoutComponents();
    }

    public void setMainActionListener(StartTestActionListener startTestActionListener) {
        this.startTestActionListener = startTestActionListener;
    }

    private void initComponents() {

        overviewLabel = new JLabel("Overview");
        overviewLabel.setFont(MainFrame.BOLD);
        percentageLabel = new JLabel();
        backButton = new JButton("Back");

        overviewTable = new JTable(questionsAndAnswersData, columnNames);

        TableColumnModel columnModel = overviewTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(300);
        columnModel.getColumn(1).setPreferredWidth(100);

        tableScrollPane = new JScrollPane(overviewTable);
    }


    private void layoutComponents() {

        setLayout(new MigLayout("center"));
        add(overviewLabel, "align center, wrap");
        add(percentageLabel, "align center, wrap");
        add(tableScrollPane, "width 80%, height 60%, align center, gapbottom 22%");

        add(backButton, "align center, dock south, wmax 70");
    }

    public void activatePanel() {
        backButton.addActionListener(startTestActionListener);
    }

    public void setData() {

        questionsAndAnswersData = new String[QuestionsAndAnswersData.getAllAnswersSize()][2];
        String percentageOfKnownAnswers = calculatePercentageOfKnownAnswersAsString();

        percentageLabel.setText("Percentage of known answers: " + percentageOfKnownAnswers + "%");

        QuestionsAndAnswersData.appendToTestsData(percentageOfKnownAnswers);

        for (int i = 0; i < QuestionsAndAnswersData.getAllAnswersSize(); i++) {
            questionsAndAnswersData[i][0] = QuestionsAndAnswersData.getAllQuestionElement(i);
            questionsAndAnswersData[i][1] = QuestionsAndAnswersData.getAllQuestionElement(i);
        }

        // reset data
        QuestionsAndAnswersData.clearAllAnswers();
        QuestionsAndAnswersData.clearAllQuestions();

        DefaultTableModel model = new DefaultTableModel(questionsAndAnswersData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        overviewTable.setModel(model);
    }

    private String calculatePercentageOfKnownAnswersAsString() {
        float percentage;
        int numOfQuestions = QuestionsAndAnswersData.getAllQuestionsSize();
        int numOfKnownAnswers = 0;
        for (String answer : QuestionsAndAnswersData.getAllAnswers()) {
            if (answer.equals("YES")) {
                numOfKnownAnswers++;
            }
        }
        percentage = (float) numOfKnownAnswers / numOfQuestions * 100;
        String percentageString = String.format("%.2f", percentage);
        return percentageString;
    }

    public JButton getBackButton() {
        return backButton;
    }
}
