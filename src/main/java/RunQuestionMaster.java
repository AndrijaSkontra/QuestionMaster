import com.formdev.flatlaf.FlatIntelliJLaf;
import view.MainFrame;

import javax.swing.*;

/**
 * Main class to run the QuestionMaster application.
 * @author Andrija Škontra
 * @version 1.0.0
 */
public class RunQuestionMaster {

    /**
     * Main method to run the QuestionMaster application.
     */
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
        });

    }
}
