package totusen48;

import java.util.*;

public class Grid {

    private Square[][] field = new Square[4][4];
    Score score;
    
    public void startField(){
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                this.field[i][j] = new Square(this); //Creates Square objects in field
            }
        }
        addNewSquare(this.field); //adds two random squares to start the game
        addNewSquare(this.field);
        score = new Score();
    }

    public Square[][] moveLeft(Square[][] field){
        for(int i=0; i<4; i++){
            int leftest = 0;
            for(int j=1; j<4; j++){
                Square leftestSquare = field[i][leftest];
                if(field[i][j].getValue() == 0){ //Empty squares should not be moved
                    continue; 
                }
                if(j == leftest){ //Square is leftmost. Can not be moved
                    continue;
                }
                if(field[i][leftest].getValue() == 0){ //Leftmost square is empty
                    field[i][leftest].setValue(field[i][j].getValue()); //Creates a copy of current square and places it in leftmost
                    field[i][j].remove(); //removes current square
                }
                else if((leftestSquare.getValue() == field[i][j].getValue()) && leftestSquare.getUsed() == false){ //square and leftmost should merge
                    field[i][leftest].add(); //doubles leftmost square
                    field[i][j].remove(); //removes current square
                    field[i][leftest].setUsed(true); //sets used to true so it can not merge again this round
                }
                else{ //leftmost and current square are different values
                    if(leftest+1 == j){ //adjacent. Should not move
                        leftest++; //current square becomes leftmost
                        continue; //moves on to next square
                    }
                    field[i][leftest+1].setValue(field[i][j].getValue()); //square next to leftest gets value of current square
                    field[i][j].remove(); //removes current square
                    leftest++;
                }
            }
        }
        prepareNewMove(field); //adds a new square and sets all squares to not used
        return field;
    }

    public Square[][] moveRight(Square[][] field){
        for(int i=0; i<4; i++){
            int rightest = 3;
            for(int j=2; j>=0; j--){
                Square rightestSquare = field[i][rightest];
                if(field[i][j].getValue() == 0){ //empty squares should not be moved
                    continue;
                }
                if(j == rightest){ //square is rightmost. Can not be moved
                    continue;
                }
                if(field[i][rightest].getValue() == 0){ //rightmost square is empty
                    field[i][rightest].setValue(field[i][j].getValue()); //Creates a copy of current square and places it in rightmost
                    field[i][j].remove(); //removes current square
                }
                else if((rightestSquare.getValue() == field[i][j].getValue()) && rightestSquare.getUsed() == false){ //square and rightmost should merge
                    field[i][rightest].add(); //doubles rightmost square
                    field[i][j].remove(); //removes current square
                    field[i][rightest].setUsed(true); //sets used to true so it can not merge again this round
                }
                else{ //rightmost and current square are different values
                    if(rightest-1 == j){ //adjacent. Should not move
                        rightest--; //current square becomes rightmost
                        continue; //moves on to next square
                    }
                    field[i][rightest-1].setValue(field[i][j].getValue()); //square next to leftest gets value of current square
                    field[i][j].remove(); //removes current square
                    rightest--;

                }
            }
        }
        prepareNewMove(field); //adds a new square and sets all squares to not used
        return field;
    }

    public Square[][] moveUp(Square[][] field){ //transposes the matrix, moves it left, transposes back
        Square[][] tempMap = field.clone();
        tempMap = transpose(tempMap);
        tempMap = moveLeft(tempMap);
        tempMap = transpose(tempMap);
        field = tempMap;
        return tempMap;
    }

    public Square[][] moveDown(Square[][] field){ //transposes the matrix, moves it right, transposes back
        Square[][] tempMap = field.clone();
        tempMap = transpose(tempMap);
        tempMap = moveRight(tempMap);
        tempMap = transpose(tempMap);
        field = tempMap;
        return tempMap;
    }

    public void addNewSquare(Square[][] field){ //Adds a new square to the field
        List <Square> zeros = getAllEmpty(field); //gets all empty squares
        if(zeros.isEmpty()){
            return;
        }
        int randomNumber = RandomNumber(zeros.size()); //chooses random empty square
        int randomValueNumber = RandomNumber(10);
        boolean checker = (randomValueNumber==1); //10% chance to get 4 instead of 2
        int value = checker ? 4 : 2; 
        (zeros.get(randomNumber)).setValue(value); //sets the random square to chosen value
    }

    public List<Square> getAllEmpty(Square[][] field){ //Gets all empty squares in a field
        List<Square> zeros = new ArrayList<Square>();
        for(int i=0; i<4; i++){
            for(Square spot : field[i]){
                if(spot.getValue() == 0){
                    zeros.add(spot);
                }
            }
        }
        return zeros;
    }

    public void prepareNewMove(Square[][] field){ //adds a square to the field and sets all squares to not used
        addNewSquare(field);
        for(int i=0; i<4; i++){
            for(Square spot : field[i]){
                spot.setUsed(false);
            }
        }
    }

    public boolean checkLegalUp(Square[][]field){
        Square[][] tempMap = deepClone(field);
        if(!this.toString(tempMap).equals(this.toString(moveUp(tempMap)))){ //checks if grid after moving will be the same as before => not a legal move
            return true;
        }
        return false;
    }

    public boolean checkLegalDown(Square[][]field){
        Square[][] tempMap = deepClone(field);
        if(!this.toString(tempMap).equals(this.toString(moveDown(tempMap)))){ //checks if grid after moving will be the same as before => not a legal move
            return true;
        }
        return false;
    }

    public boolean checkLegalLeft(Square[][]field){
        Square[][] tempMap = deepClone(field);
        if(!this.toString(tempMap).equals(this.toString(moveLeft(tempMap)))){ //checks if grid after moving will be the same as before => not a legal move
            return true;
        }
        return false;
    }

    public boolean checkLegalRight(Square[][]field){
        Square[][] tempMap = deepClone(field);
        if(!this.toString(tempMap).equals(this.toString(moveUp(tempMap)))){ //checks if grid after moving will be the same as before => not a legal move
            return true;
        }
        return false;
    }

    public boolean checkLoss(Square[][] field){ //loses when there are no moves to do
        boolean loss = true;
        if(checkLegalDown(field)){ //checks if grid after moving will be the same as before => not a legal move
            loss = false;
        }
        if(checkLegalUp(field)){
            loss = false;
        }
        if(checkLegalLeft(field)){
            loss = false;
        }
        if(checkLegalRight(field)){
            loss = false;
        }
        return loss;

    }

    public Square[][] transpose(Square[][] field){ //Transposes a matrix
        Square[][] newGrid = new Square[4][4];
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                newGrid[i][j] = field[j][i];
            }
        }
        return newGrid;
    }

    public Square[][] getField(){
        return this.field;
    }

    public void setField(Square[][] field){ 
        this.field = field;
    }

    public void setField(int[][] field){ //sets the field from an array of numbers. Used in tests
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                this.field[i][j].setValue(field[i][j]);
            }
        }
    }

    public int RandomNumber(int max){ //generates random number
        Random r = new Random();
        return r.nextInt(max);
    }

    public String toString(Square[][] field){ //prints the field. used in tests or playing the game in CLI
        String[][] valueArray = new String[4][4];
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                valueArray[i][j] = Integer.toString(field[i][j].getValue());
            }
        }
        String value = "";
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                value += valueArray[i][j];
                value += " ";
            }
            value += "\n";
        }
        return value;
    }

    public Square[][] deepClone(Square[][] original) {
        Square[][] copy = new Square[4][];
        for (int i=0; i<4; i++) {
            copy[i] = new Square[4];
            for (int j=0; j<4; j++) {
                Square newSquare = new Square();
                newSquare.setValue(original[i][j].getValue());
                copy[i][j] = newSquare;
            }
        }
        return copy;
    }

    public Score getScore(){
        return score;
    }

    public void setScore(Score score){
        this.score = score;
    }
}