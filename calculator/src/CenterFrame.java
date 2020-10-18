import java.awt.Dimension;
import java.awt.Toolkit;

public class CenterFrame {
    public Dimension center(){
        final Toolkit j = Toolkit.getDefaultToolkit();
        final Dimension screen = j.getScreenSize();
        return screen;
    }   
}
