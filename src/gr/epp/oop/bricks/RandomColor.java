package gr.epp.oop.bricks;

import java.awt.Color;
import java.lang.reflect.Field;

public class RandomColor {
    
    private static final String[] colors = { "blue", "pink", "cyan", "yellow", "green", "magenta", "orange", "black", "red"};
    private static int i;
    private static Color color;
    
    public static Color getRandomColor(int max){
        
        i = (int)(Math.random() * max);
        
        try {
            Field field = Class.forName("java.awt.Color").getField(colors[i]);
            color = (Color)field.get(null);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
            color = null; // Not defined
        }
        
        return color;
    }
    
}
