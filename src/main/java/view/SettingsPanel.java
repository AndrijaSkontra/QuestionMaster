package view;

import controller.SettingsActionListener;
import controller.StartTestActionListener;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

/**
 * The panel that is displayed when the user clicks the "Settings" button.
 */
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

    /**
     * Initializes the components of the panel.
     */
    private void initComponents() {
        darkModeButton = new JButton("Dark Mode");
        lightModeButton = new JButton("Light Mode");
        backButton = new JButton("Back");
        changeThemeLabel = new JLabel("CHANGE THEME:");
        changeThemeLabel.setFont(MainFrame.BOLD);
    }

    /**
     * Lays out the components of the panel.
     * Uses MigLayout as layout menager.
     */
    private void layoutComponents() {

        setLayout(new MigLayout("center, gap 3%"));
        add(changeThemeLabel, "align center, wrap");
        add(darkModeButton, "align center, wrap");
        add(lightModeButton, "align center, wrap");
        add(new JSeparator(), "height 100%, wrap"); // forcing the back button to bottom
        add(backButton, "align center");
    }

    /**
     * Activates the panel.
     * Adds action listeners to the buttons.
     */
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
