package gr.epp.oop.ui;

import gr.epp.oop.bricks.util.BricksController;
import gr.epp.oop.bricks.util.BricksView;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TopPanel extends JPanel implements BricksView{
    
    private JLabel levelLabel, nextLevelScoreLabel, currentScoreLabel, totalScoreLabel, leftUp, rightUp, leftDown, rightDown;
    private GamePanel gamePanel;
    private JPanel topContainer, botContainer;
    public int level = 1;//max is 8
    private int nextLevelScore, totalScore, currentScore, totalRemovedBricks;
    private BricksController controller;
    public TopPanel(BricksController controller){
        this.controller = controller;
        initComp();
    }
    
    private void initComp(){
        
        nextLevelScore = 80 + (level * 20); //kanonika 80 + alla einai ypervolika eukolo
        setLayout(new BorderLayout());
        
        gamePanel = new GamePanel(controller,level);
        gamePanel.setCols((14 + (level-1) / 2));
        gamePanel.setRows((12 + level / 2));
        
        add(gamePanel, BorderLayout.CENTER);
        
        topContainer = new JPanel();
        topContainer.setLayout(new GridLayout());
        
        botContainer = new JPanel();
        botContainer.setLayout(new GridLayout());
        
        levelLabel = new JLabel("Level: ");
        topContainer.add(levelLabel);
        leftUp = new JLabel();
        leftUp.setText(""+level);
        topContainer.add(leftUp);
        
        currentScoreLabel = new JLabel("Current Score: ");
        topContainer.add(currentScoreLabel);
        rightUp = new JLabel();
        rightUp.setText("0");
        topContainer.add(rightUp);
        
        nextLevelScoreLabel = new JLabel("Score to Next Level: ");
        botContainer.add(nextLevelScoreLabel);
        leftDown = new JLabel();
        leftDown.setText(""+nextLevelScore);
        botContainer.add(leftDown);
        
        totalScoreLabel = new JLabel("Total Score: ");
        botContainer.add(totalScoreLabel);
        rightDown = new JLabel();
        rightDown.setText("0");
        botContainer.add(rightDown);
        
        add(topContainer, BorderLayout.NORTH);
        add(botContainer, BorderLayout.SOUTH);
        
        controller.addView(this);
    }

    @Override
    public void BricksChanged(int removedBricks) {
        totalRemovedBricks += removedBricks;
        
        if(totalRemovedBricks <= 4){
            currentScore += removedBricks;
            totalScore += removedBricks;
        }else if(totalRemovedBricks >= 5 && totalRemovedBricks <= 12){
            currentScore += (int) (1.5 * removedBricks);
            totalScore += (int) (1.5 * removedBricks);
        }else{
            currentScore += 2 * removedBricks;
            totalScore += 2 * removedBricks;
        }
        
        rightUp.setText(""+currentScore);
        rightDown.setText(""+totalScore);
        
        if(currentScore >= nextLevelScore){
            goNextLevel();
        }
    }
    
    private void goNextLevel(){
        if(level < 8){
            
            level++;
            currentScore = totalRemovedBricks = 0;
            nextLevelScore = 80 + (level * 20);
            
            rightUp.setText(""+currentScore);
            leftUp.setText(""+level);
            leftDown.setText(""+nextLevelScore);
            rightDown.setText(""+totalScore);
            
            remove(gamePanel);
            gamePanel = new GamePanel(controller,level);
            add(gamePanel, BorderLayout.CENTER);
            
        }
    }
}
