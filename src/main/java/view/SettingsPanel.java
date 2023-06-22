package view;

import controller.SettingsActionListener;
import controller.StartTestActionListener;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class SettingsPanel extends JPanel{

    private JButton darkModeButton;
    private JButton lightModeButton;
    private JButton backButton;
    private SettingsActionListener settingsActionListener;
    private JLabel changeThemeLabel;

    public SettingsPanel() {
        initComponents();
        layoutComponents();
        activatePanel();
    }

    public void setSettingsActionListener(SettingsActionListener settingsActionListener) {
        this.settingsActionListener = settingsActionListener;
    }

    private void initComponents() {
        darkModeButton = new JButton("Dark Mode");
        lightModeButton = new JButton("Light Mode");
        backButton = new JButton("Back");
        changeThemeLabel = new JLabel("CHANGE THEME:");
        changeThemeLabel.setFont(MainFrame.BOLD);
    }

    private void layoutComponents() {

        setLayout(new MigLayout("center, gap 3%"));
        add(changeThemeLabel, "align center, wrap");
        add(darkModeButton, "align center, wrap");
        add(lightModeButton, "align center, wrap");
        add(new JSeparator(), "height 100%, wrap"); // forcing the back button to bottom
        add(backButton, "align center");
    }

    public void activatePanel() {
        darkModeButton.addActionListener(settingsActionListener);
        lightModeButton.addActionListener(settingsActionListener);
        backButton.addActionListener(settingsActionListener);
    }

    public JButton getDarkModeButton() {
        return darkModeButton;
    }

    public JButton getLightModeButton() {
        return lightModeButton;
    }

    public JButton getBackButton() {
        return backButton;
    }
}
