package totusen48;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;;

public class Controller {

    Grid grid;
    Score score;
    Filewriter fw;
    boolean gameOver = false;
 
    @FXML
    private Pane border;

    @FXML
    private Pane textBorder;

    @FXML
    private Text scoreText;

    @FXML
    private Text highscoreText;

    @FXML
    private Rectangle newGame;

    @FXML
    private Text gameOverText;

    @FXML
    void initialize(){
        this.fw = new Filewriter();
        this.grid = new Grid();
        this.grid.startField();
        this.score = this.grid.getScore(); //Grid and Controller uses same Score object
        updateTiles();
        highscoreText.setText(fw.getHighscore());
        gameOverText.setVisible(false);
        
        newGame.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                newGame();
            }
            
        });
    }

    public void updateTiles(){

        Square[][] grid = this.grid.getField();
        ObservableList<Node> squares =  border.getChildren();
        ObservableList<Node> textFields =  textBorder.getChildren();

        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                squares.get(4*i + j).getStyleClass().clear(); //resets style of all squares
                squares.get(4*i + j).getStyleClass().add("square" + grid[i][j].getValue()); //attaches new style with value of square

                Text textField = (Text) textFields.get(4*i + j);
                textField.getStyleClass().clear(); //resets text of all squares
                textField.getStyleClass().add("text" + grid[i][j].getValue()); //sets color and size of text
                if(grid[i][j].getValue() != 0){
                    textField.setText(Integer.toString(grid[i][j].getValue())); //Sets text to value of square
                }
                else {
                    textField.setText(""); //Empty squares have no text
                }
            }
        }
        scoreText.setText(Integer.toString(this.score.getPoints()));
    }

    public void checkLoss(){
        if(this.grid.checkLoss(this.grid.getField())){
            fw.addHighscore(this.score.getPoints());
            gameOverText.setVisible(true);
            this.gameOver = true;
        }
    }

    public void moveUp(){
        if(!gameOver && this.grid.checkLegalUp(this.grid.getField())){
            this.grid.moveUp(this.grid.getField());
            updateTiles();
            checkLoss();
        }
    }

    public void moveDown(){
        if(!gameOver && this.grid.checkLegalDown(this.grid.getField())){
        this.grid.moveDown(this.grid.getField());
        updateTiles();
        checkLoss();
        }
    }

    public void moveLeft(){
        if(!gameOver && this.grid.checkLegalLeft(this.grid.getField())){
        this.grid.moveLeft(this.grid.getField());
        updateTiles();
        checkLoss();
        }
    }

    public void moveRight(){
        if(!gameOver && this.grid.checkLegalRight(this.grid.getField())){
        this.grid.moveRight(this.grid.getField());
        updateTiles();
        checkLoss();
        }
    }

    public void newGame(){
        this.gameOver = false;
        this.grid = new Grid();
        this.score = new Score();
        this.grid.startField();
        this.score = this.grid.getScore();
        updateTiles();
        highscoreText.setText(fw.getHighscore());
        gameOverText.setVisible(false);
    }
}