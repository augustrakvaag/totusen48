package totusen48;

import java.util.*;

public class Grid {

    private Square[][] field = new Square[4][4];
    Score score;
    
    public void startField(){
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                this.field[i][j] = new Square(this);
            }
        }
        addNewSquare(this.field);
        addNewSquare(this.field);
        score = new Score();
    }

    public Square[][] moveLeft(Square[][] field){
        for(int i=0; i<4; i++){
            int leftest = 0;
            for(int j=1; j<4; j++){
                Square leftestSquare = field[i][leftest];
                if(field[i][j].getValue() == 0){
                    continue;
                }
                if(j == leftest){
                    continue;
                }
                if(field[i][leftest].getValue() == 0){
                    field[i][leftest].setValue(field[i][j].getValue());
                    field[i][j].remove();
                }
                else if((leftestSquare.getValue() == field[i][j].getValue()) && leftestSquare.getUsed() == false){
                    field[i][leftest].add();
                    field[i][j].remove();
                    field[i][leftest].setUsed(true);
                }
                else{
                    if(leftest+1 == j){
                        leftest++;
                        continue;
                    }
                    field[i][leftest+1].setValue(field[i][j].getValue());
                    field[i][j].remove();
                    leftest++;
                }
            }
        }
        prepareNewMove(field);
        return field;
    }

    public Square[][] moveRight(Square[][] field){
        for(int i=0; i<4; i++){
            int rightest = 3;
            for(int j=2; j>=0; j--){
                Square rightestSquare = field[i][rightest];
                if(field[i][j].getValue() == 0){
                    continue;
                }
                if(j == rightest){
                    continue;
                }
                if(field[i][rightest].getValue() == 0){
                    field[i][rightest].setValue(field[i][j].getValue());
                    field[i][j].remove();
                }
                else if((rightestSquare.getValue() == field[i][j].getValue()) && rightestSquare.getUsed() == false){
                    field[i][rightest].add();
                    field[i][j].remove();
                    field[i][rightest].setUsed(true);
                }
                else{
                    if(rightest-1 == j){
                        rightest--;
                        continue;
                    }
                    field[i][rightest-1].setValue(field[i][j].getValue());
                    field[i][j].remove();
                    rightest--;

                }
            }
        }
        prepareNewMove(field);
        return field;
    }

    public Square[][] moveUp(Square[][] field){
        Square[][] tempMap = transpose(field);
        tempMap = moveLeft(tempMap);
        tempMap = transpose(tempMap);
        this.field = tempMap;
        return tempMap;
    }

    public Square[][] moveDown(Square[][] field){
        Square[][] tempMap = transpose(field);
        tempMap = moveRight(tempMap);
        tempMap = transpose(tempMap);
        this.field = tempMap;
        return tempMap;
    }

    public void addNewSquare(Square[][] field){
        List <Square> zeros = getAllEmpty(field);
        int randomNumber = RandomNumber(zeros.size());
        int randomValueNumber = RandomNumber(10);
        boolean checker = (randomValueNumber==1);
        int value = checker ? 4 : 2;
        (zeros.get(randomNumber)).setValue(value);
    }

    public List<Square> getAllEmpty(Square[][] field){
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

    public void prepareNewMove(Square[][] field){
        addNewSquare(field);
        for(int i=0; i<4; i++){
            for(Square spot : field[i]){
                spot.setUsed(false);
            }
        }
    }

    public boolean checkLoss(Square[][] field){
        List<Square> zeros = getAllEmpty(field);
        if(zeros.isEmpty()){
            return true;
        }
        return false;
    }

    public Square[][] transpose(Square[][] field){
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

    public void setField(int[][] field){
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                this.field[i][j].setValue(field[i][j]);
            }
        }
    }

    public int RandomNumber(int max){
        Random r = new Random();
        return r.nextInt(max);
    }

    public String toString(){
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

    public Score getScore(){
        return score;
    }

    public void setScore(Score score){
        this.score = score;
    }
}