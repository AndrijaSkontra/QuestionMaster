package controller;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import view.MainFrame;
import view.SettingsPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <h3>ActionListener for the SettingsPanel.</h3>
 * <p>
 * This class is responsible for handling the user's actions in the SettingsPanel.
 * </p>
 */
public class SettingsActionListener implements ActionListener {

    private MainFrame mainFrame;
    private SettingsPanel settingsPanel;

    public SettingsActionListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void setSettingsPanel(SettingsPanel settingsPanel) {
        this.settingsPanel = settingsPanel;
    }

    /**
     * Handles the user's actions in the SettingsPanel.
     * @param e the event to be processed
     */
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
