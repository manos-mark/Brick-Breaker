package gr.epp.oop.bricks;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author admin
 */
public class BombBrick extends JButton implements Brick{
        
    @Override
    public void initButton(JButton[][] theMap, int i, int j) {
        theMap[i][j].setBackground(Color.red);
        try {
            Image img = ImageIO.read(getClass().getResource("/gr/epp/oop/resources/BombBrick.bmp"));
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
    public void changeNeighbors(JButton[][] theMap, int i, int j){
        //change current
        try{    
            theMap[i][j].setBackground(Color.WHITE);
            theMap[i][j].setName("0");
        }catch(Exception e){} 
        
        //change up button
        try{
            theMap[i-1][j].setBackground(Color.WHITE);
            theMap[i-1][j].setName("0");
        }catch(Exception e){}
   
        //change down button
        try{
            theMap[i+1][j].setBackground(Color.WHITE);
            theMap[i+1][j].setName("0");
        }catch(Exception e){}
    
        //change left button
        try{    
            theMap[i][j-1].setBackground(Color.WHITE);
            theMap[i][j-1].setName("0");
        }catch(Exception e){}
        
        //change right button
        try{    
            theMap[i][j+1].setBackground(Color.WHITE);
            theMap[i][j+1].setName("0");
        }catch(Exception e){} 
        
        //change right up
        try{    
            theMap[i-1][j+1].setBackground(Color.WHITE);
            theMap[i-1][j+1].setName("0");
        }catch(Exception e){} 
        
        //change left up
        try{    
            theMap[i-1][j-1].setBackground(Color.WHITE);
            theMap[i-1][j-1].setName("0");
        }catch(Exception e){} 
        
        //change left down
        try{    
            theMap[i+1][j-1].setBackground(Color.WHITE);
            theMap[i+1][j-1].setName("0");
        }catch(Exception e){} 
        
        //change right down
        try{    
            theMap[i+1][j+1].setBackground(Color.WHITE);
            theMap[i+1][j+1].setName("0");
        }catch(Exception e){} 
        
    }   
    
}
