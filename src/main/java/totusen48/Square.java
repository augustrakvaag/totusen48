package totusen48;

public class Square{
    
    private int value;
    private boolean used = false;
    private Grid grid;

    public Square(Grid grid){
        this.grid = grid;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public void add(){
        this.value *= 2;
        ((this.getGrid()).getScore()).addPoints(this.value);
    }

    public void remove(){
        this.value = 0;
    }

    public int getValue(){
        return this.value;
    }

    public void setValue(int value){
        this.value = value;
    }

    public boolean getUsed(){
        return this.used;
    }

    public void setUsed(boolean value){
        this.used = value;
    }
}