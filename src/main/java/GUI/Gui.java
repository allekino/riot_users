package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Gui {


    private static JFrame mainFrame;
    private static JPanel innerPanel;
    private JPanel panel;

    public static void main(String[] args) {
        paint();
    }

    public static void paint() {
        if (mainFrame == null) {
            mainFrame = new JFrame("Gui");
            setup();
            innerPanel = new JPanel();
            mainFrame.add(innerPanel);
            mainFrame.validate();
            mainFrame.repaint();
        }
    }

    public static void addButton(String name) {
        JButton button = new JButton(name);
        button.setSize(button.getPreferredSize());

        button.addActionListener(e -> {
            String innerPath = "D:\\work\\git\\riot\\bats\\";
            String path = String.format("cmd /c start %s%s.bat", innerPath, name);
            Runtime rn = Runtime.getRuntime();
            try {
                Process ex = rn.exec(path);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        innerPanel.add(button);
        innerPanel.validate();
        innerPanel.repaint();
    }

    public static void clearFrame() {
        if (innerPanel == null) {
            return;
        }
        innerPanel.removeAll();
        innerPanel.validate();
        innerPanel.repaint();
    }

    private static void setup() {
        mainFrame.setLayout(new GridLayout(0, 1));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 300);
        mainFrame.setVisible(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
    }
}
