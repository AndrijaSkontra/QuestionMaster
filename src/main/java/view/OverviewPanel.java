package view;

import controller.StartTestActionListener;
import model.AUXFileReading;
import model.AllQuestionData;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;


// init and layout components in setData
public class OverviewPanel extends JPanel {

    private JTable overviewTable;
    private String[] columnNames;
    private String[][] data;
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

        overviewTable = new JTable(data, columnNames);

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

        data = new String[AllQuestionData.questionAnswers.size()][2]; // change size
        String percetnageOfKnownAnswers = calculatePercentageOfKnownAnswers();

        percentageLabel.setText("Percentage of known answers: " + percetnageOfKnownAnswers + "%");

        AllQuestionData.appendToTestsData(percetnageOfKnownAnswers);

        for (int i = 0; i < AllQuestionData.questionAnswers.size(); i++) {
            data[i][0] = AllQuestionData.allQuestions.get(i);
            data[i][1] = AllQuestionData.questionAnswers.get(i);
        }

        // reset data
        AllQuestionData.allQuestions.clear();
        AllQuestionData.questionAnswers.clear();

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        overviewTable.setModel(model);
    }

    private String calculatePercentageOfKnownAnswers() {
        float percentage;
        int numOfQuestions = AllQuestionData.allQuestions.size();
        int numOfKnownAnswers = 0;
        for (String answer : AllQuestionData.questionAnswers) {
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
