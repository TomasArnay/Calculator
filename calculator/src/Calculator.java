import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Calculator {
    public void start(){
        setFrame();
    }

    public void setFrame(){ 
        CenterFrame cf = new CenterFrame();
        Dimension resolution = cf.center();
        int width = resolution.width;
        int height = resolution.height;

        frame.add(panel);
        frame.add(panel2);
        frame.setBounds(width/2 - 150, height/2 - 150, 320, 450);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setPanel();
    }

    public void setPanel(){
        Color colorFrame = new Color(hex("f4fdfb"));
        Color colorFrame2 = new Color(hex("ffffff"));

        panel.setBackground(colorFrame);
        panel2.setBackground(colorFrame2);

        panel.add(labOperation);
        panel.add(labResult);

        panel.setBounds(0, 0, 320, 100);
        panel2.setBounds(0, 100, 320, 340);

        setLabels();
        createButtons();
        assignSize();
        assignValue();
    }

    public void setLabels(){
        //Size of labels
        labOperation.setBounds(0, 20, 290, 20);
        labResult.setBounds(0, 60, 290, 30);
        
        //Text of labels
        labOperation.setFont(new Font("Arial", Font.PLAIN, 20));
        labOperation.setHorizontalAlignment(SwingConstants.RIGHT);

        labResult.setFont(new Font("Arial", Font.PLAIN, 40));
        labResult.setHorizontalAlignment(SwingConstants.RIGHT);

        //Font color of label
        labOperation.setForeground(c);
        labResult.setForeground(c);
    }

    public void createButtons(){
        String name = "Button";
        Border line = new LineBorder(Color.WHITE);
		Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        
        for(int i = 0; i < 10; i++){
            String number = String.valueOf(i);

            aButtons[i] = new JButton();
            aButtons[i].setName(name + number);
            aButtons[i].setFont(new Font("Arial", Font.PLAIN, 30));
            aButtons[i].setHorizontalTextPosition(SwingConstants.CENTER);
            aButtons[i].setBorder(compound);
            aButtons[i].setForeground(c);

            panel2.add(aButtons[i]);
        }
    }

    public void assignSize(){
        aButtons[0].setBounds(87, 355, 50, 50);
        aButtons[1].setBounds(7, 270, 50, 50);
        aButtons[2].setBounds(87, 270, 50, 50);
        aButtons[3].setBounds(167, 270, 50, 50);
        aButtons[4].setBounds(7, 185, 50, 50);
        aButtons[5].setBounds(87, 185, 50, 50);
        aButtons[6].setBounds(167, 185, 50, 50);
        aButtons[7].setBounds(7, 100, 50, 50);
        aButtons[8].setBounds(87, 100, 50, 50);
        aButtons[9].setBounds(167, 100, 50, 50);
    }

    public void assignValue(){
        aButtons[0].setText("0");
        aButtons[1].setText("1");
        aButtons[2].setText("2");
        aButtons[3].setText("3");
        aButtons[4].setText("4");
        aButtons[5].setText("5");
        aButtons[6].setText("6");
        aButtons[7].setText("7");
        aButtons[8].setText("8");
        aButtons[9].setText("9");
    }


    private int hex(String color_hex){
        return Integer.parseInt(color_hex, 16);
    }

    private JFrame frame = new JFrame("Calculator");
    private JPanel panel = new JPanel(null);
    private JPanel panel2 = new JPanel(null);
    private JLabel labOperation = new JLabel("Txt1");
    private JLabel labResult = new JLabel("Txt2");
    JButton aButtons[] = new JButton[10];
    Color c = new Color(hex("0C1332"));
}
