import com.formdev.flatlaf.FlatIntelliJLaf;
import view.MainFrame;

import javax.swing.*;

public class RunQuestionMaster {

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
