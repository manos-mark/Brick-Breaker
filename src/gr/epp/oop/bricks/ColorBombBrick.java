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
public class ColorBombBrick extends JButton implements Brick{
        
    @Override
    public void initButton(JButton[][] theMap, int i, int j) {
        
        try {
            Image img = ImageIO.read(getClass().getResource("/gr/epp/oop/resources/ColorBombBrick.bmp"));
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
        boolean flg = false;        
        //change up button
        try{
            if( theMap[i][j].getBackground() ==  theMap[i-1][j].getBackground() ){
                theMap[i-1][j].setBackground(Color.WHITE);
                theMap[i-1][j].setName("0");
                flg = true;
            }
        }catch(Exception e){}
   
        //change down button
        try{
            if( theMap[i][j].getBackground() ==  theMap[i+1][j].getBackground() ){
                theMap[i+1][j].setBackground(Color.WHITE);
                theMap[i+1][j].setName("0");
                flg = true;
            }
        }catch(Exception e){}
    
        //change left button
        try{    
            if( theMap[i][j].getBackground() ==  theMap[i][j-1].getBackground() ){
                theMap[i][j-1].setBackground(Color.WHITE);
                theMap[i][j-1].setName("0");
                flg = true;
            }
        }catch(Exception e){}
        
        //change right button
        try{    
            if( theMap[i][j].getBackground() ==  theMap[i][j+1].getBackground() ){
                theMap[i][j+1].setBackground(Color.WHITE);
                theMap[i][j+1].setName("0");
                flg = true;
            }
        }catch(Exception e){} 
        
        //change current
        try{    
            if(flg){
                theMap[i][j].setBackground(Color.WHITE);
                theMap[i][j].setName("0");
            }
        }catch(Exception e){} 
        
    }   
    
}

    
