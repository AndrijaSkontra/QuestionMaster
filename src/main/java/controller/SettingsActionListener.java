package controller;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import view.MainFrame;
import view.SettingsPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsActionListener implements ActionListener {

    MainFrame mainFrame;
    private SettingsPanel settingsPanel;

    public SettingsActionListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void setSettingsPanel(SettingsPanel settingsPanel) {
        this.settingsPanel = settingsPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        boolean darkModeButtoPressed = (source == settingsPanel.getDarkModeButton());
        if (darkModeButtoPressed) {
            try {
                UIManager.setLookAndFeel(new FlatDarculaLaf());
            } catch (UnsupportedLookAndFeelException ex) {
                throw new RuntimeException(ex);
            }
            mainFrame.updateUI();
            return;
        }
        boolean lightModeButtonPressed = (source == settingsPanel.getLightModeButton());
        if (lightModeButtonPressed) {
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
            } catch (UnsupportedLookAndFeelException ex) {
                throw new RuntimeException(ex);
            }
            mainFrame.updateUI();
            return;
        }
        boolean backButtonPressed = (source == settingsPanel.getBackButton());
        if (backButtonPressed) {
            mainFrame.backToStartPanelFromSettings();
            return;
        }
    }
}
