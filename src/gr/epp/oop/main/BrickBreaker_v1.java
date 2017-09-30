package gr.epp.oop.main;
import gr.epp.oop.bricks.util.BricksController;
import gr.epp.oop.ui.*;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
/**
 *
 * @author manos markodimitrakis 4007
 */
public class BrickBreaker_v1 {

    public static final int WIDTH = 800, HEIGHT = 720;
    
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            
                BricksController controller = new BricksController(0);
        
                JFrame theFrame = new JFrame("Brick Breaker");

                TopPanel mainPanel = new TopPanel(controller);

                theFrame.add(mainPanel);
                theFrame.setResizable(false);
                theFrame.setSize(WIDTH,HEIGHT);
                theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                theFrame.setLocationRelativeTo(null);
                theFrame.setVisible(true);
                
            }
        });
        
    }
    
}
