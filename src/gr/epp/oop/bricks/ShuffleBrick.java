package gr.epp.oop.bricks;

import gr.epp.oop.ui.GamePanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ShuffleBrick extends JButton implements Brick{
    
    @Override
    public void initButton(JButton[][] theMap, int i, int j) {
        
        try {
            Image img = ImageIO.read(getClass().getResource("/gr/epp/oop/resources/ShuffleBrick.bmp"));
            theMap[i][j].setIcon(new ImageIcon(img));
          } catch (IOException ex) {
            System.out.println(ex);
          }
    }

    @Override
    public void onClick(JButton[][] theMap, int i, int j) {
        changeNeighbors(theMap, i, j);
    }

    @Override
    public void changeNeighbors(JButton[][] theMap, int i, int j) {
        theMap[i][j].setIcon(null);
        theMap[i][j] = new SimpleBrick("B");
        theMap[i][j].setBackground(RandomColor.getRandomColor(4));      
    }
    

}
