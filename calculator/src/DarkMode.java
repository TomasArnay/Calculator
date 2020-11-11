import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Color;

public class DarkMode {

    //Construct
    public DarkMode(JPanel panel, JPanel panel2, JButton darkButton, JLabel labOperation, JLabel labResult, JButton[] aNumbers, JButton[] aSigns){
        this.panel = panel;
        this.panel2 = panel2;
        this.darkButton = darkButton;
        this.labOperation = labOperation;
        this.labResult = labResult;
        this.aNumbers = aNumbers;
        this.aSigns = aSigns;
    }

    //started
    public void start(){
        setDarkButton();
        setPanels();
        setLabels();
        setArrays();
    }

    //custom dark button
    public void setDarkButton() {
        ImageIcon theme_button_dark = new ImageIcon("calculator/src/Images/theme_button.png");
        Color color_dark_button = new Color(hex("ffffff"));

        darkButton.setIcon(theme_button_dark);
        darkButton.setForeground(color_dark_button);
        darkButton.setHorizontalTextPosition(SwingConstants.CENTER);
    }

    //Set panel
    public void setPanels(){
        Color panelDark = new Color(hex("212b41"));
        Color panelDark2 = new Color(hex("2e3951"));

        panel.setBackground(panelDark);
        panel2.setBackground(panelDark2);
    }

    //Set label
    public void setLabels(){
        labOperation.setForeground(labDark);
        labResult.setForeground(labDark);
    }
    
    //numbers and signs buttons
    public void setArrays(){
        ImageIcon btn_number_dark = new ImageIcon("calculator/src/Images/background_number_dark.png");
        Color numberColorDark = new Color(hex("D8D8DA"));
        ImageIcon btn_number_dark_pressed = new ImageIcon("calculator/src/Images/background_dark_number_pressed.png");
        ImageIcon btn_special_dark = new ImageIcon("calculator/src/Images/background_special_dark.png");
        ImageIcon btn_special_dark_pressed = new ImageIcon("calculator/src/Images/background_special_dark_pressed.png");

        for(int i = 0; i < aNumbers.length; i++){
            aNumbers[i].setIcon(btn_number_dark);
            aNumbers[i].setForeground(numberColorDark);
            aNumbers[i].setRolloverIcon(btn_number_dark_pressed);
        }
        
        for(int i = 0; i < aSigns.length; i++){
            aSigns[i].setIcon(btn_special_dark);
            aSigns[i].setForeground(labDark);
            aSigns[i].setRolloverIcon(btn_special_dark_pressed);
        }
    }
    
    //Convert color
    private int hex(String color_hex) {
        return Integer.parseInt(color_hex, 16);
    }
    
    //Variables
    private JPanel panel;
    private JPanel panel2;
    private JButton darkButton;
    private JLabel labOperation;
    private JLabel labResult;
    private JButton[] aNumbers;
    private JButton[] aSigns;
    private Color labDark = new Color(hex("97DBD0"));
}
