import javax.swing.SwingUtilities;
import gui.MainGUI;

/**
 * main
 */
public class main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainGUI mainGUI = new MainGUI();
                mainGUI.setVisible(true);
            }
        });
    }
}