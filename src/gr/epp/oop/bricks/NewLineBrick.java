package gr.epp.oop.bricks;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class NewLineBrick extends JButton implements Brick{

    private int cols,rows;
    
    public NewLineBrick(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }
    
    @Override
    public void initButton(JButton[][] theMap, int i, int j) {
        
        try {
            Image img = ImageIO.read(getClass().getResource("/gr/epp/oop/resources/NewLineBrick.bmp"));
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
        
        for(int r=0; r<cols; r++){
            try{//den mou bazei brick sto prwto line
            if( theMap[0][r].getName().equals("0") ){
                theMap[0][r] = new SimpleBrick("B",rows,cols);
                theMap[0][r].setBackground(RandomColor.getRandomColor(4));
                flg = true;
            }
            }catch(Exception e){System.out.println(theMap[0][r].getName());}
        }
        
        //an einai false shmainei oti to plegma einai plhres ara den kanei tipota
        if(flg){
            theMap[i][j].setBackground(Color.WHITE);
            theMap[i][j].setName("0");
        }
    }
}
