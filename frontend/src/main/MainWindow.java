import java.awt.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Sat Apr 14 13:58:10 MDT 2012
 */



/**
 * @author Rod Hilton
 */
public class MainWindow extends JPanel {
    public MainWindow() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Rod Hilton
        cipherTextLabel = new JLabel();
        cipherScrollPane = new JScrollPane();
        cipherTextArea = new JTextArea();
        buttonPanel = new JPanel();
        crackButton = new JButton();
        keyLabel = new JLabel();
        keyTextField = new JTextField();
        plainScrollPane = new JScrollPane();
        plainTextArea = new JTextArea();

        //======== this ========
        setMinimumSize(new Dimension(114, 97));

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {400, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {32, 120, 26, 120, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //---- cipherTextLabel ----
        cipherTextLabel.setText("Cipher Text:");
        add(cipherTextLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //======== cipherScrollPane ========
        {
            cipherScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            //---- cipherTextArea ----
            cipherTextArea.setFont(new Font("Courier", cipherTextArea.getFont().getStyle(), cipherTextArea.getFont().getSize()));
            cipherTextArea.setText("hello world this is a test of some stuff and some junk and i like to make words wrap");
            cipherTextArea.setLineWrap(true);
            cipherScrollPane.setViewportView(cipherTextArea);
        }
        add(cipherScrollPane, new GridBagConstraints(0, 1, 1, 1, 10.0, 2.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //======== buttonPanel ========
        {
            buttonPanel.setLayout(new GridBagLayout());
            ((GridBagLayout)buttonPanel.getLayout()).columnWidths = new int[] {0, 0, 279, 0};
            ((GridBagLayout)buttonPanel.getLayout()).rowHeights = new int[] {0, 0};
            ((GridBagLayout)buttonPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
            ((GridBagLayout)buttonPanel.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

            //---- crackButton ----
            crackButton.setText("Crack!");
            buttonPanel.add(crackButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));

            //---- keyLabel ----
            keyLabel.setText("Key:");
            buttonPanel.add(keyLabel, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));

            //---- keyTextField ----
            keyTextField.setEditable(false);
            buttonPanel.add(keyTextField, new GridBagConstraints(2, 0, 1, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        add(buttonPanel, new GridBagConstraints(0, 2, 1, 1, 2.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 5, 0), 0, 0));

        //======== plainScrollPane ========
        {
            plainScrollPane.setViewportBorder(null);

            //---- plainTextArea ----
            plainTextArea.setEditable(false);
            plainTextArea.setFont(UIManager.getFont("TextArea.font"));
            plainScrollPane.setViewportView(plainTextArea);
        }
        add(plainScrollPane, new GridBagConstraints(0, 3, 1, 1, 0.0, 2.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Rod Hilton
    private JLabel cipherTextLabel;
    private JScrollPane cipherScrollPane;
    private JTextArea cipherTextArea;
    private JPanel buttonPanel;
    private JButton crackButton;
    private JLabel keyLabel;
    private JTextField keyTextField;
    private JScrollPane plainScrollPane;
    private JTextArea plainTextArea;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
