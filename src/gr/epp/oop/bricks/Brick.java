package gr.epp.oop.bricks;

import javax.swing.JButton;

public interface Brick {
    public void onClick(JButton[][] theMap, int i, int j);
    public void initButton(JButton[][] theMap, int i, int j);
    public void changeNeighbors(JButton[][] theMap, int i, int j);
}
