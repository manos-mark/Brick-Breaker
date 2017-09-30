package gr.epp.oop.bricks;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JButton;

public class SimpleBrick extends JButton implements Brick{

    private Color currentColor, clr;
    private List<Integer> list;
    private int rows,cols;
    
    public SimpleBrick(String s, int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.setName(s);
    }
    
    public SimpleBrick(String s){
        this.setName(s);
    }
    
    @Override
    public void initButton(JButton[][] theMap, int i, int j) {}

    
    @Override
    public void onClick(JButton[][] theMap, int i, int j) {
        changeNeighbors(theMap, i, j);
    }
    
    private boolean findUp(Color color, JButton[][] theMap, int i, int j) {
        try{
            clr = theMap[i-1][j].getBackground();
        }catch(Exception e){}
        
        return (clr == color);
    }
    
    private boolean findDown(Color color, JButton[][] theMap, int i, int j) {
        try{
            clr = theMap[i+1][j].getBackground();
        }catch(Exception e){}
        
        return (clr == color);
    }
    
    private boolean findLeft(Color color, JButton[][] theMap, int i, int j) {
        try{
            clr = theMap[i][j-1].getBackground();
        }catch(Exception e){}
        
        return (clr == color);
    }

    private boolean findRight(Color color, JButton[][] theMap, int i, int j) {
        try{
            clr = theMap[i][j+1].getBackground();
        }catch(Exception e){}
        
        return (clr == color);
    }

    
    private void changeUp(JButton[][] theMap, int i, int j){
        //change up button
        try{
            theMap[i-1][j].setBackground(Color.WHITE);
            theMap[i-1][j].setName("0");
        }catch(Exception e){System.out.println("change up "+e);}
    }
        
    private void changeDown(JButton[][] theMap, int i, int j){
        //change down button
        try{
            theMap[i+1][j].setBackground(Color.WHITE);
            theMap[i+1][j].setName("0");
        }catch(Exception e){System.out.println("change down "+e);}
    }
            
    private void changeLeft(JButton[][] theMap, int i, int j){
        //change left button
        try{    
            theMap[i][j-1].setBackground(Color.WHITE);
            theMap[i][j-1].setName("0");
        }catch(Exception e){System.out.println("change left "+e);}
    }
            
    private void changeRight(JButton[][] theMap, int i, int j){
        //change right button
        try{    
            theMap[i][j+1].setBackground(Color.WHITE);
            theMap[i][j+1].setName("0");
        }catch(Exception e){System.err.println("change right "+e);} 
    }   

    private void findAllNeighbors(JButton[][] theMap, int i, int j) {
        
        //if the same, change up color
        if( i>=1 && findUp(currentColor, theMap, i, j) ){
            changeUp(theMap, i, j); 
                list.add(i-1);
                list.add(j);
        }
        
        //if the same, change down color
        if( i<rows-1 && findDown(currentColor, theMap, i, j) ){
            changeDown(theMap, i, j); 
            list.add(i+1);
            list.add(j);
        }
        
        //if the same, change left color
        if( j>=1 && findLeft(currentColor, theMap, i, j) ){
            changeLeft(theMap, i, j); 
            list.add(i);
            list.add(j-1);
        }
        
        //if the same, change right color
        if( j<cols-1 && findRight(currentColor, theMap, i, j) ){
            changeRight(theMap, i, j); 
            list.add(i);
            list.add(j+1);
        }
         
    }

    @Override
    public void changeNeighbors(JButton[][] theMap, int i, int j) {
        //initialize list
        list = new ArrayList<>();
        //find current brick's color
        try{
            currentColor = theMap[i][j].getBackground();
        }catch(Exception e){System.out.println("Cant find current color "+e);}
        
        findAllNeighbors(theMap, i, j);//find up, down, left, right neighbors with the same color
        //recursion to find all neighbors with the same color
        for(int x=0; x<list.size(); x+=2){
            findAllNeighbors(theMap, list.get(x), list.get(x+1));
        }
    }
    
}
