package gr.epp.oop.bricks;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JokerBrick extends JButton implements Brick{
    
    @Override
    public void initButton(JButton[][] theMap, int i, int j) {
        
        theMap[i][j].setBackground(Color.BLACK);
        try {
            Image img = ImageIO.read(getClass().getResource("/gr/epp/oop/resources/JokerBrick.bmp"));
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
        boolean flg = false;        
        Color color = Color.BLACK;//red is temp never used
        
        try{
            //if up same as down
            if( theMap[i-1][j].getBackground() ==  theMap[i+1][j].getBackground() && !theMap[i+1][j].getName().equals("0") ){
                flg = true;
                color = theMap[i+1][j].getBackground();
            }
        }catch(Exception e){}

        try{
            //if up same as left
            if( theMap[i-1][j].getBackground() ==  theMap[i][j-1].getBackground() && !theMap[i][j-1].getName().equals("0") ){
                flg = true;
                color = theMap[i][j-1].getBackground();
            }
        }catch(Exception e){}

        try{
            //if up same as right
            if( theMap[i-1][j].getBackground() ==  theMap[i][j+1].getBackground() && !theMap[i][j+1].getName().equals("0") ){
                flg = true;
                color = theMap[i][j+1].getBackground();
            }
        }catch(Exception e){}

        try{
            //if right same as down
            if( theMap[i][j+1].getBackground() ==  theMap[i+1][j].getBackground() && !theMap[i+1][j].getName().equals("0") ){
                flg = true;
                color = theMap[i+1][j].getBackground();
            }
        }catch(Exception e){}

        try{
            //if right same as left
            if( theMap[i][j+1].getBackground() ==  theMap[i][j-1].getBackground() && !theMap[i][j-1].getName().equals("0") ){
                flg = true;
                color = theMap[i][j-1].getBackground();
            }
        }catch(Exception e){}
            
        try{
            //if down same as left
            if( theMap[i+1][j].getBackground() ==  theMap[i][j-1].getBackground() && !theMap[i][j-1].getName().equals("0") ){
                flg = true;
                color = theMap[i][j-1].getBackground();
            }
        }catch(Exception e){}
        
        
        //change current
        if(flg){
            theMap[i][j].setIcon(null);
            theMap[i][j] = new SimpleBrick("B");
            theMap[i][j].setBackground(color);
        }else{//set again joker brick cuz is already removed
            theMap[i][j] = new JokerBrick();
            theMap[i][j].setBackground(Color.BLACK);
            initButton(theMap, i, j);
            theMap[i][j].setName("JB");
        }
        
    }
}
