package gr.epp.oop.ui;
import  gr.epp.oop.bricks.RandomSpecialBricks;
import gr.epp.oop.bricks.BombBrick;
import gr.epp.oop.bricks.RandomColor;
import gr.epp.oop.bricks.ColorBombBrick;
import gr.epp.oop.bricks.NewLineBrick;
import gr.epp.oop.bricks.JokerBrick;
import gr.epp.oop.bricks.ShuffleBrick;
import gr.epp.oop.bricks.SimpleBrick;
import gr.epp.oop.bricks.util.BricksController;
import gr.epp.oop.bricks.util.BricksView;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements BricksView{
    
    //Fields
    public static int totalSpecialBricks;
    private JButton[][] theMap;
    private JButton tempButton;
    private GridBagConstraints c;
    private JPanel panel;
    private int cols, rows, tempCols, emptyCol, click, removedBricks[], level;
    private final BricksController controller;
       
    //constructor
    public GamePanel(BricksController controller, int level){
        this.level = level;
        this.controller = controller;
        initComp();
        initMap();
        controller.addView(this);
    } 
        
    //methods    
    private void initComp() {
        cols = (14 + (level-1) / 2);
        rows = (12 + level / 2); //max 16
        tempCols = cols;
        emptyCol = 0;
        this.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.weightx = c.weighty = 0;
        
        totalSpecialBricks = (cols * rows) * 5 / 100;
        theMap  = new JButton[rows][cols];
        removedBricks = new int[200];
    }
    
    public void setCols(int cols){
        this.cols = cols;
    }
    public void setRows(int rows){
        this.rows = rows;
    }
    public int getCols(){
        return cols;
    }
    public int getRows(){
        return rows;
    }
    
    private void initMap(){
        //regular bricks initializer
        initRegularBricks();
        //special bricks initializer (elatwma oti emfanizontai kai ta 5 sthn arxh)
        initSpecialBricks();
        //Add buttons ActionListeners 
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                addMyActionListenner(theMap, i, j);
                c.gridx = j;
                c.gridy = i;
                //set button standar size
                theMap[i][j].setPreferredSize(new Dimension(40, 40));
                //add brick on the map
                add(theMap[i][j], c);
            }
        }
    }
    
    private void update(){  
        deleteEmpty();
        reArrange();
        
        revalidate();
        repaint();
    }
    
    private void deleteEmpty(){//or generate new if needed
        removedBricks[click] = 0;
        //delete elements from map
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if( theMap[i][j].getName().equals("0") ){

                    c.gridx = j;
                    c.gridy = i;

                    panel = new JPanel();
                    panel.setOpaque(false);
                    panel.setPreferredSize(new Dimension(40, 40));

                    add(panel,c);
                    removeBrick(i, j);//remove from panel
                    removedBricks[click]++;
                }else{
                    //dhmiourgounte mesw special breaks nea button
                    //ta opoia prepei na exoun actionListener
                    if( theMap[i][j].getActionListeners().length == 0 ){
                        addMyActionListenner(theMap, i, j);
                        c.gridx = j;
                        c.gridy = i;
                        //set button standar size
                        theMap[i][j].setPreferredSize(new Dimension(40, 40));
                        //add brick on the map
                        add(theMap[i][j], c);
                    }
                }
            }
        }
        //in every round it counts all the empty bricks minus last round empty bricks
        if(click > 0){
            controller.changeRemovedBricks(removedBricks[click] - removedBricks[click-1]);
        }else{
            controller.changeRemovedBricks(removedBricks[click]);
        }
        click++;
        
    }
    
    private void removeBrick(int i, int j){
        //delete elements from map
        c.gridx = j;
        c.gridy = i;

        panel = new JPanel();
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(40, 40));

        add(panel,c);
        remove(theMap[i][j]);//remove from panel
    }
    
   
    private void reArrange(){
        //loop while find empty returns true
        while(downIsEmpty()){
             moveBricksDown();
        }
        
        while( ( emptyCol = colIsEmpty() ) != 0 ){
            moveBricksLeft(emptyCol);
        }
    }

    private void addMyActionListenner(JButton[][] theMap, int i, int j) {
        theMap[i][j].addActionListener((ActionEvent e) -> {
            tempButton = (JButton) e.getSource();
            
            if( tempButton.getName().equals("B") ){
                ( (SimpleBrick) tempButton ).onClick(theMap, i, j);
                update();
            }else if( tempButton.getName().equals("BB") ){
                ( (BombBrick) tempButton ).onClick(theMap, i, j);
                update();
            }else if( tempButton.getName().equals("CB") ){
                ( (ColorBombBrick) tempButton ).onClick(theMap, i, j);
                update();
            }else if( tempButton.getName().equals("JB") ){
                removeBrick(i,j);//cuz new simple brick
                ( (JokerBrick) tempButton ).onClick(theMap, i, j);
                update();
            }else if( tempButton.getName().equals("NL") ){
                ( (NewLineBrick) tempButton ).onClick(theMap, i, j);
                update();
            }else if( tempButton.getName().equals("SB") ){
                removeBrick(i,j);//cuz new simple brick
                ( (ShuffleBrick) tempButton ).onClick(theMap, i, j);
                update();
            }
            
        });
    }
    
    private void moveBricksDown(){
        for(int j=0; j<cols; j++){
            for(int i=0; i<rows-1; i++){
                int temp = i+1;
                if( !theMap[i][j].getName().equals("0") ){
                    if( theMap[temp][j].getName().equals("0") ){
                        
                        //temp copy, before delete
                        String tempName = theMap[i][j].getName();
                        Color tempColor = theMap[i][j].getBackground();
                        
                        //delete up brick
                        theMap[i][j].setBackground(Color.WHITE);
                        theMap[i][j].setName("0");
                        removeBrick(i,j);
                        
                        //make new brick down
                        theMap[temp][j] = new SimpleBrick(tempName,rows,cols);
                        theMap[temp][j].setBackground(tempColor);
                        theMap[temp][j].setPreferredSize(new Dimension(40, 40));
                        
                        //if special brick, change
                        if( theMap[temp][j].getName().equals("BB") ){
                            addSpecialBrick(temp, j, tempName, tempColor, "BB");
                        }else if( theMap[temp][j].getName().equals("CB") ){
                            addSpecialBrick(temp, j, tempName, tempColor, "CB");
                        }else if( theMap[temp][j].getName().equals("JB") ){
                            addSpecialBrick(temp, j, tempName, tempColor, "JB");
                        }else if( theMap[temp][j].getName().equals("NL") ){
                            addSpecialBrick(temp, j, tempName, tempColor, "NL");
                        }else if( theMap[temp][j].getName().equals("SB") ){
                            addSpecialBrick(temp, j, tempName, tempColor, "SB");
                        }
                        
                        //add listener
                        addMyActionListenner(theMap, temp, j);
                        c.gridx = j;
                        c.gridy = temp;
                        //add brick on the map
                        add(theMap[temp][j], c);
                    }

                }
            }
        }
    }
    
    private boolean downIsEmpty(){
        for(int j=0; j<cols; j++){
            for(int i=0; i<rows-1; i++){
                int temp = i+1;
                if( !theMap[i][j].getName().equals("0") ){
                    if( theMap[temp][j].getName().equals("0") ){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private void initSpecialBricks(){
        String[] arr;
        
        for(int i=0; i<totalSpecialBricks; i++){
            arr = RandomSpecialBricks.getRandomBrick(rows, cols);
            int posRow = Integer.parseInt(arr[0]);
            int posCol = Integer.parseInt(arr[1]);
            theMap[posRow][posCol].setName(arr[2]);//for special bricks
            theMap[posRow][posCol].setBackground(RandomColor.getRandomColor(4 + (level-1)/2));
            
            if( theMap[posRow][posCol].getName().equals("BB") ){
                addSpecialBrick(posRow, posCol, theMap[posRow][posCol].getBackground(), "BB");
            }else if(theMap[posRow][posCol].getName().equals("CB")){
                addSpecialBrick(posRow, posCol, theMap[posRow][posCol].getBackground(), "CB");
            }else if(theMap[posRow][posCol].getName().equals("JB")){
                addSpecialBrick(posRow, posCol, theMap[posRow][posCol].getBackground(), "JB");
            }else if(theMap[posRow][posCol].getName().equals("NL")){
                addSpecialBrick(posRow, posCol, theMap[posRow][posCol].getBackground(), "NL");
            }else if(theMap[posRow][posCol].getName().equals("SB")){
                addSpecialBrick(posRow, posCol, theMap[posRow][posCol].getBackground(), "SB");
            }
            
        }
    }
    
    private void initRegularBricks(){
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                theMap[i][j] = new SimpleBrick("B",rows,cols);
                theMap[i][j].setBackground(RandomColor.getRandomColor(4 + (level -1)/2));
            }
        }
    }
    
    private void moveBricksLeft(int emptyCol){
        
        int next = emptyCol+1;
        
        for(int j=emptyCol; j<cols-1; j++){
            for(int i=0; i<rows; i++){
                
                if( theMap[i][j].getName().equals("0") ){
                    try{
                    if( !theMap[i][next].getName().equals("0") ){
                        
                        //temp copy, before delete
                        String tempName = theMap[i][next].getName();
                        Color tempColor = theMap[i][next].getBackground();
                        
                        //delete right brick
                        theMap[i][next].setBackground(Color.WHITE);
                        theMap[i][next].setName("0");
                        removeBrick(i,next);
                        
                        //make new brick left
                        theMap[i][j] = new SimpleBrick(tempName,rows,cols);
                        theMap[i][j].setBackground(tempColor);
                        theMap[i][j].setPreferredSize(new Dimension(40, 40));
                        
                        //if special brick
                        if( theMap[i][j].getName().equals("BB") ){
                            addSpecialBrick(i, j, tempName, tempColor,"BB");
                        }else if( theMap[i][j].getName().equals("CB") ){
                            addSpecialBrick(i, j, tempName, tempColor,"CB");
                        }else if( theMap[i][j].getName().equals("JB") ){
                            addSpecialBrick(i, j, tempName, tempColor,"JB");
                        }else if( theMap[i][j].getName().equals("NL") ){
                            addSpecialBrick(i, j, tempName, tempColor,"NL");
                        }else if( theMap[i][j].getName().equals("SB") ){
                            addSpecialBrick(i, j, tempName, tempColor,"SB");
                        }
                        
                        //add listener
                        addMyActionListenner(theMap, i, j);
                        c.gridx = j;
                        c.gridy = i;
                        //add brick on the map
                        add(theMap[i][j], c);
                    }
                    }catch(Exception e){}

                }
            }
            next++;
        }
    }
    
    private boolean upIsEmpty(int j){
        for(int i=rows-1; i>0; i--){
            if( !theMap[i][j].getName().equals("0") ){
                return false;
            }
        }
        return true;
    }

    private int colIsEmpty() {
        for(int i=0; i<rows; i++){
            for(int j=0; j<tempCols-1; j++){//each time reduce cols
                if( i==rows-1 && theMap[i][j].getName().equals("0") && upIsEmpty(j)){//an einai h teleutaia grammh kai einai kenh kai ola ta panw kena
                    tempCols--;
                    return j;
                }
            }
        }
        return 0;
    }
   
    private void addSpecialBrick(int i, int j, Color tempColor, String b){
        if( b.equals("BB") ){
            theMap[i][j] = new BombBrick();
            theMap[i][j].setName("BB");
            theMap[i][j].setBackground(tempColor);
            ( (BombBrick) theMap[i][j] ).initButton(theMap, i, j);
        }else if( b.equals("CB") ){
            theMap[i][j] = new ColorBombBrick();
            theMap[i][j].setName("CB");
            theMap[i][j].setBackground(tempColor);
            ( (ColorBombBrick) theMap[i][j] ).initButton(theMap, i, j);
        }else if( b.equals("JB") ){
            theMap[i][j] = new JokerBrick();
            theMap[i][j].setName("JB");
            theMap[i][j].setBackground(tempColor);
            ( (JokerBrick) theMap[i][j] ).initButton(theMap, i, j);
        }else if( b.equals("NL") ){
            theMap[i][j] = new NewLineBrick(rows,cols);
            theMap[i][j].setName("NL");
            theMap[i][j].setBackground(tempColor);
            ( (NewLineBrick) theMap[i][j] ).initButton(theMap, i, j);
        }else if( b.equals("SB") ){
            theMap[i][j] = new ShuffleBrick();
            theMap[i][j].setName("SB");
            theMap[i][j].setBackground(tempColor);
            ( (ShuffleBrick) theMap[i][j] ).initButton(theMap, i, j);
        }
        
    }
    
    private void addSpecialBrick(int i, int j, String tempName, Color tempColor, String b) {
        if( b.equals("BB") ){
            theMap[i][j] = new BombBrick();
            ( (BombBrick) theMap[i][j] ).initButton(theMap, i, j);
            theMap[i][j].setName(tempName);
            theMap[i][j].setBackground(tempColor);
            theMap[i][j].setPreferredSize(new Dimension(40, 40));
        }else if( b.equals("CB") ){
            theMap[i][j] = new ColorBombBrick();
            ( (ColorBombBrick) theMap[i][j] ).initButton(theMap, i, j);
            theMap[i][j].setName(tempName);
            theMap[i][j].setBackground(tempColor);
            theMap[i][j].setPreferredSize(new Dimension(40, 40));
        }else if( b.equals("JB") ){
            theMap[i][j] = new JokerBrick();
            ( (JokerBrick) theMap[i][j] ).initButton(theMap, i, j);
            theMap[i][j].setName(tempName);
            theMap[i][j].setBackground(tempColor);
            theMap[i][j].setPreferredSize(new Dimension(40, 40));
        }else if( b.equals("NL") ){
            theMap[i][j] = new NewLineBrick(rows,cols);
            ( (NewLineBrick) theMap[i][j] ).initButton(theMap, i, j);
            theMap[i][j].setName(tempName);
            theMap[i][j].setBackground(tempColor);
            theMap[i][j].setPreferredSize(new Dimension(40, 40));
        }else if( b.equals("SB") ){
            theMap[i][j] = new ShuffleBrick();
            ( (ShuffleBrick) theMap[i][j] ).initButton(theMap, i, j);
            theMap[i][j].setName(tempName);
            theMap[i][j].setBackground(tempColor);
            theMap[i][j].setPreferredSize(new Dimension(40, 40));
        }
    }

    @Override
    public void BricksChanged(int removedBricks) {}
}