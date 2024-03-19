package totusen48;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class totusen48Test {

    @Test
    @DisplayName("Check field start")
    public void testStart(){
        Grid grid = new Grid();
        grid.startField();
        assertFalse(grid.getAllEmpty(grid.getField()).isEmpty()); //checks that a new field isn't empty
        assertEquals(grid.getScore().getPoints(), 0); //Checks that points start at 0
    }

    @Test
    @DisplayName("Test move left")
    public void testMoveLeft(){
        Grid grid = new Grid();
        grid.startField();
        grid.setField(new int[][]{{0,0,0,2},{0,0,0,0},{0,0,0,0},{0,0,0,0}});
        grid.moveLeft(grid.getField());
        assertEquals(grid.toString().charAt(0), 50); //50 ascii value for 2
    }
    
    @Test
    @DisplayName("Test move right")
    public void testMoveRight(){
        Grid grid = new Grid();
        grid.startField();
        grid.setField(new int[][]{{2,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}});
        grid.moveRight(grid.getField());
        assertEquals(grid.toString().charAt(6), 50); //50 ascii value for 2
    }

    @Test
    @DisplayName("Test move up")
    public void testMoveUp(){
        Grid grid = new Grid();
        grid.startField();
        grid.setField(new int[][]{{0,0,0,0},{0,0,0,0},{0,0,0,0},{2,0,0,0}});
        grid.moveUp(grid.getField());
        assertEquals(grid.toString().charAt(0), 50); //50 ascii value for 2
    }

    @Test
    @DisplayName("Test move down")
    public void testMoveDown(){
        Grid grid = new Grid();
        grid.startField();
        grid.setField(new int[][]{{0,0,0,2},{0,0,0,0},{0,0,0,0},{0,0,0,0}});
        grid.moveDown(grid.getField());
        System.out.println(grid.toString());
        assertEquals(grid.toString().charAt(33), 50); //50 ascii value for 2
    }

    @Test
    @DisplayName("Test merging")
    public void testMerge(){
        Grid grid = new Grid();
        grid.startField();
        grid.setField(new int[][]{{2,2,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}});
        grid.moveLeft(grid.getField());
        assertEquals(grid.toString().charAt(0), 52); //52 ascii value for 4
    }

    @Test
    @DisplayName("Test writing to file")
    public void writeFile(){
        Filewriter fw = new Filewriter();
        fw.setFilename("C:/Users/auras/OneDrive/Documents/Koding/Objektorientert/tdt4100-students-24/minegenkode/src/test/java/totusen48/FilewriteTest.txt");
        fw.addHighscore(50);
        assertEquals("50", fw.getHighscore());
    }
}


