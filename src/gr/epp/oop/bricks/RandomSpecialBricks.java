package gr.epp.oop.bricks;

import java.awt.Color;
import java.lang.reflect.Field;

//BombBrick: Πατώντας το διαγράφονται όλα τα κατά μια θέση γειτονικά σε αυτό τουβλάκια.
// ColorBombBrick: Όταν πατιέται διαγράφονται μόνο τα ομοιόχρωμα και κατά μια θέση γειτνιάζοντα με αυτό
//τουβλάκια.
// JokerBrick: Δεν δύναται να πατηθεί απευθείας. Συσχετίζεται αυτόματως κάθε φορά με το χρώμα που
//βολεύει ώστε να διαγραφούν τα μέγιστα δυνατά τουβλάκια αναλόγως του χρώματος του Brick που
//πατήθηκε.
// NewLineBrick: Όταν πατηθεί εισάγει στην κορυφογραμμή του πλέγματος μια νέα γραμμή κανονικών Bricks
//τυχαίου (random) χρώματος. Σε περίπτωση που το πλέγμα είναι πλήρες δεν δύναται να πατηθεί.
// SuffleBrick: Σε περίπτωση που πατηθεί μπορεί το χρώμα με το οποίο είναι την εκάστοτε φορά συσχετισμένο
//να αλλάξει τυχαία ώστε να εξυπηρετήσει τις κατά περιπτώσει ανάγκες του παίχτη. Η εναλλαγή δύναται να
//γίνει αποκλειστικά και μόνο μέχρι μια φορά.


public class RandomSpecialBricks {
    
    private static final String[] bricks = { "BB", "CB", "JB", "NL", "SB"};
    private static int i;
    private static String[] arr;
    
    public static String[] getRandomBrick(int rows, int cols){
        
        i = (int)(Math.random() * 5);
        
        arr = new String[3];
        
        arr[0] = Integer.toString( (int)(Math.random() * rows) );
                
        arr[1] = Integer.toString( (int)(Math.random() * cols) );
        
        arr[2] = bricks[i];
                
        return arr;
    }
}
