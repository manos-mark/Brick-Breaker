package gr.epp.oop.bricks.util;

import java.util.ArrayList;

public class BricksController {
    private ArrayList<BricksView> viewList;
    private BricksModel model;
    
    public BricksController(int removedBricks) {
        viewList = new ArrayList<>();
        model = new BricksModel();
        setRemovedBricks(removedBricks);
    }
    
    private void setRemovedBricks(int removedBricks){
        model.setRemovedBricks(removedBricks);
    }
    
    private int getRemovedBricks(){
        return model.getRemovedBricks();
    }

    public void addView(BricksView view) {
        viewList.add(view);
    }

    public void removeView(BricksView view) {
        viewList.remove(view);
    }
    
    private void fireBricksChanged(){
        for (int i = 0; i < viewList.size(); i++) {
            viewList.get(i).BricksChanged( getRemovedBricks() );
        }
    }
    
    public void changeRemovedBricks(int removedBricks){
        setRemovedBricks(removedBricks);
        fireBricksChanged();
    }  
}
