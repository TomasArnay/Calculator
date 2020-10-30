import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
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

public class Calculator implements ActionListener {
    public void start() {
        setFrame();
    }

    public void setFrame() {
        CenterFrame cf = new CenterFrame();
        Dimension resolution = cf.center();
        int width = resolution.width;
        int height = resolution.height;

        frame.add(panel);
        frame.add(panel2);
        frame.setBounds(width / 2 - 150, height / 2 - 150, 320, 450);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setPanel();
    }

    public void setPanel() {
        Color colorFrame = new Color(hex("f4fdfb"));
        Color colorFrame2 = new Color(hex("ffffff"));

        panel.setBackground(colorFrame);
        panel2.setBackground(colorFrame2);

        panel.add(labOperation);
        panel.add(labResult);
        panel.add(darkMode);

        panel.setBounds(0, 0, 320, 100);
        panel2.setBounds(0, 100, 320, 340);

        setLabels();
        setDarkMode();
        createButtons();
        assignValue();
    }

    public void setLabels() {
        // Size of labels
        labOperation.setBounds(0, 20, 290, 20);
        labResult.setBounds(0, 60, 290, 30);

        // Text of labels
        labOperation.setFont(new Font("Arial", Font.PLAIN, 20));
        labOperation.setHorizontalAlignment(SwingConstants.RIGHT);

        labResult.setFont(new Font("Arial", Font.PLAIN, 40));
        labResult.setHorizontalAlignment(SwingConstants.RIGHT);

        // Font color of label
        labOperation.setForeground(c);
        labResult.setForeground(c);
    }

    public void setDarkMode(){
        darkMode.setBounds(0, 0, 50, 25);
        darkMode.setFont(new Font("Arial", Font.PLAIN, 30));
        darkMode.setBorder(compound);
        darkMode.setForeground(c);
    }

    public void createButtons() {
        String name = "Button";
        String special = "Special";
        
        for (int i = 0; i < 10; i++) {
            String number = String.valueOf(i);
            
            aNumbers[i] = new JButton();
            aNumbers[i].setName(name + number);
            aNumbers[i].setFont(new Font("Arial", Font.PLAIN, 30));
            aNumbers[i].setHorizontalTextPosition(SwingConstants.CENTER);
            aNumbers[i].setBorder(compound);
            aNumbers[i].setForeground(c);
            aNumbers[i].setIcon(btn1);
            aNumbers[i].setRolloverIcon(btn1_pressed);
            aNumbers[i].setText(number);
            assignSizeNumber(aNumbers[i], i);
            aNumbers[i].addActionListener(this);
            
            panel2.add(aNumbers[i]);
        }
        
        for (int i = 0; i < 6; i++) {
            String number = String.valueOf(i);
            
            aSigns[i] = new JButton();
            aSigns[i].setName(special + number);
            aSigns[i].setFont(new Font("Arial", Font.PLAIN, 30));
            aSigns[i].setHorizontalTextPosition(SwingConstants.CENTER);
            aSigns[i].setBorder(compound);
            aSigns[i].setForeground(c);
            aSigns[i].setIcon(btn_special);
            aSigns[i].setRolloverIcon(btn1_pressed);
            assignSizeSign(aSigns[i], i);
            aSigns[i].addActionListener(this);
            
            panel2.add(aSigns[i]);
        }
    }
    
    //Define position of the numbers
    public void assignSizeNumber(JButton button, int i) {
        if(i == 0){
            button.setBounds(x + 80, 355, width, height);
        }
        else if(i >= 1 && i <= 3){
            button.setBounds(x, 270, width, height);
            x = x + 80;
            x = cleanX(i, x);
        }
        else if(i >= 4 && i <= 6){
            button.setBounds(x, 185, width, height);
            x = x + 80;
            x = cleanX(i, x);
        }
        else if(i >= 7 && i <= 9){
            button.setBounds(x, 100, width, height);
            x = x + 80;
            x = cleanX(i, x);
        }
    }
    
    public int cleanX(int i, int x){
        if(i == 3 || i == 6 || i == 9){
            return 7;
        }
        return x;
    }
    
    //Define position of signs
    public void assignSizeSign(JButton button, int i){
        x = 247;
        if(i >= 0 && i <= 3){
            button.setBounds(x, y, width, height);
            if(i >= 0 && i <= 2){
                y = y + 85;
            }
        }
        else if(i == 4){
            button.setBounds(x - 80, y, width, height);
        }
        else if(i == 5){
            button.setBounds(x - 240, y, width, height);
        }
    }
    
    public void assignValue() {
        aSigns[0].setText("/");
        aSigns[1].setText("*");
        aSigns[2].setText("-");
        aSigns[3].setText("=");
        aSigns[4].setText("+");
        aSigns[5].setText("c");
    }
    
    //Convert color
    private int hex(String color_hex) {
        return Integer.parseInt(color_hex, 16);
    }
    
    //Handler events
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < 10; i++){
            String s = String.valueOf(i);
            if(e.getActionCommand().equals(s)){
                printNumber(s);
            }
        }
        if(e.getActionCommand().equals("+")){
            printNumber("+");
        }
        if(e.getActionCommand().equals("-")){
            printNumber("-");
        }
        if(e.getActionCommand().equals("*")){
            printNumber("*");
        }
        if(e.getActionCommand().equals("/")){
            printNumber("/");
        }
        if(e.getActionCommand().equals("=")){
            analizeText(labOperation.getText());
        }
        if(e.getActionCommand().equals("c")){
            delete();
        }
    }
    
    //Print number in the label
    public void printNumber(String s){
        labOperation.setText(labOperation.getText() + s);
    }
    
    //Delete label
    public void delete(){
        labOperation.setText("");
        labResult.setText("");
    }
    
    //Find sign
    public void analizeText(String s){
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*' || s.charAt(i) == '/'){
                char c = s.charAt(i);
                getSign(c, s);
            }
        }
    }
    
    //Get sign of operation
    public void getSign(char c, String s){
        double num1 = 0;
        double num2 = 0;
        String array[] = null;
        
        for(int j = 0; j < operators.length; j++){
            if(c == operators[j].charAt(0)){
                String a = String.valueOf(operators[j].charAt(0));
                array = s.split(a);
                num1 = Double.parseDouble(array[0].trim());
                num2 = Double.parseDouble(array[1].trim());
                result(num1, num2, a);
            }else if(c == operators[j].charAt(1)){
                array = s.split(operators[j]);
                num1 = Double.parseDouble(array[0].trim());
                num2 = Double.parseDouble(array[1].trim());
                result(num1, num2, operators[j]);
            }
        }
    }
    
    //perform the operation
    public void result(double num1, double num2, String o){
        double result = 0;
        String total = null;
        
        switch(o){
            case "\\+": 
            result = num1 + num2;
            break;
            case "-":
            result = num1 - num2;
            break;
            case "\\*":
            result = num1 * num2;
            break;
            case "/":
            result = num1 / num2;
            break;
        }
        if(result % 1 == 0){
            total = String.valueOf((int) result);
        }else{
            total = String.valueOf(result);
        }
        
        labResult.setText(total);
    }
    
    //Variables
    private JFrame frame = new JFrame("Calculator");
    private JPanel panel = new JPanel(null);
    private JPanel panel2 = new JPanel(null);
    private JLabel labOperation = new JLabel();
    private JLabel labResult = new JLabel();
    private JButton aNumbers[] = new JButton[10];
    private JButton aSigns[] = new JButton[6];
    private String[] operators = {"\\+", "- ", "\\*", "/ ", "= ", "c "};
    Color c = new Color(hex("0C1332"));
    private ImageIcon btn1 = new ImageIcon("src/Images/btn1.png");
    private ImageIcon btn1_pressed = new ImageIcon("src/Images/btn1_pressed.png");
    private ImageIcon btn_special = new ImageIcon("src/Images/ButtonsSpecials.png");
    private int x = 7;
    private int y = 100;
    int width = 50;
    int height = 50;
    private JButton darkMode = new JButton();
    private Border line = new LineBorder(Color.WHITE);
    private Border margin = new EmptyBorder(5, 15, 5, 15);
    private Border compound = new CompoundBorder(line, margin);
}
