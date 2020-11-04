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
import javax.swing.border.EmptyBorder;

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
        Color colorFrame = new Color(hex("D1F7F5"));
        Color colorFrame2 = new Color(hex("ffffff"));

        panel.setBackground(colorFrame);
        panel2.setBackground(colorFrame2);

        panel.add(labOperation);
        panel.add(labResult);
        panel.add(darkModeButton);

        panel.setBounds(0, 0, 320, 100);
        panel2.setBounds(0, 100, 320, 350);

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
        darkModeButton.setBounds(0, 0, 80, 20);
        darkModeButton.setFont(new Font("Arial", Font.PLAIN, 15));
        darkModeButton.setBorder(margin);
        darkModeButton.setForeground(c);
        darkModeButton.setText("Theme");
        darkModeButton.addActionListener(this);
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
            aNumbers[i].setBorder(margin);
            aNumbers[i].setIcon(btn1);
            aNumbers[i].setRolloverIcon(btn1_pressed);
            aNumbers[i].setText(number);
            aNumbers[i].setForeground(numberColor);
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
            aSigns[i].setBorder(margin);
            aSigns[i].setIcon(btn_special);
            aSigns[i].setRolloverIcon(btn1_pressed);
            aSigns[i].setForeground(numberColor);
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
        if(e.getActionCommand().equals("Theme")){
            count++;
            darkTheme();
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
        } else{
            total = String.valueOf(result);
        }
        
        labResult.setText(total);
    }
    
    public void darkTheme(){
        if(count % 2 != 0){
            ImageIcon theme_button_dark = new ImageIcon("src/Images/theme_button.png");
            Color color_dark_button = new Color(hex("ffffff"));
            darkModeButton.setIcon(theme_button_dark);
            darkModeButton.setForeground(color_dark_button);
            darkModeButton.setHorizontalTextPosition(SwingConstants.CENTER);

            Color panelDark = new Color(hex("212b41"));
            Color panelDark2 = new Color(hex("2e3951"));

            Color labDark = new Color(hex("97DBD0"));

            panel.setBackground(panelDark);
            panel2.setBackground(panelDark2);

            labOperation.setForeground(labDark);
            labResult.setForeground(labDark);

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
        }else{
            Calculator c = new Calculator();
            c.start();
        }
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
    private Color c = new Color(hex("0C1332"));
    private ImageIcon btn1 = new ImageIcon("src/Images/btn_numbers.png");
    private ImageIcon btn1_pressed = new ImageIcon("src/Images/btn1_pressed.png");
    private ImageIcon btn_special = new ImageIcon("src/Images/btn1.png");
    private ImageIcon btn_number_dark = new ImageIcon("src/Images/background_number_dark.png");
    private ImageIcon btn_number_dark_pressed = new ImageIcon("src/Images/background_dark_number_pressed.png");
    private ImageIcon btn_special_dark = new ImageIcon("src/Images/background_special_dark.png");
    private ImageIcon btn_special_dark_pressed = new ImageIcon("src/Images/background_special_dark_pressed.png");
    private int x = 7;
    private int y = 100;
    int width = 50;
    int height = 50;
    private JButton darkModeButton = new JButton();
    private Border margin = new EmptyBorder(5, 15, 5, 15);
    private Color numberColor = new Color(hex("4F4E4E"));
    private Color numberColorDark = new Color(hex("D8D8DA"));
    private int count; 
}
