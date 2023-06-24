package view;

import controller.StartTestActionListener;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class QuestionPanel extends JPanel {

    private JLabel questionLabel;
    private JRadioButton knowAnswerButton;
    private JRadioButton dontKnowAnswerButton;
    private ButtonGroup buttonGroup;
    private JButton nextQuestionButton;
    private StartTestActionListener startTestActionListener;

    public QuestionPanel() {

        initComponents();
        layoutComponents();
    }

    public void setMainActionListener(StartTestActionListener startTestActionListener) {
        this.startTestActionListener = startTestActionListener;
    }

    private void initComponents() {
        questionLabel = new JLabel();
        questionLabel.setFont(MainFrame.ITALIC);

        knowAnswerButton = new JRadioButton("I know the answer");
        knowAnswerButton.setActionCommand("KNOW");
        knowAnswerButton.setFont(MainFrame.BOLD);

        dontKnowAnswerButton = new JRadioButton("I don't know the answer");
        dontKnowAnswerButton.setActionCommand("DONT KNOW");
        dontKnowAnswerButton.setFont(MainFrame.BOLD);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(knowAnswerButton);
        buttonGroup.add(dontKnowAnswerButton);

        nextQuestionButton = new JButton("Next question");
        nextQuestionButton.setFont(MainFrame.BOLD);

    }

    private void layoutComponents() {
        setLayout(new MigLayout("fillx, gapy 7%"));
        add(questionLabel, "wrap");
        add(knowAnswerButton, "wrap");
        add(dontKnowAnswerButton, "wrap");
        add(new JSeparator(), "wrap, height 100%");
        add(nextQuestionButton, "align center, wrap");


    }

    public void activatePanel() {
        nextQuestionButton.addActionListener(startTestActionListener);
    }

    public JButton getNextQuestionButton() {
        return nextQuestionButton;
    }

    public JLabel getQuestionLabel() {
        return questionLabel;
    }

    public JRadioButton getKnowAnswerButton() {
        return knowAnswerButton;
    }

    public JRadioButton getDontKnowAnswerButton() {
        return dontKnowAnswerButton;
    }

    public ButtonGroup getButtonGroup() {
        return buttonGroup;
    }
}
