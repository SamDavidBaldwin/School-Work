/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Fall 2021
 * Instructor: Prof. Brian King
 *
 * Name: Samuel Baldwin
 * Section: MWF 8:30am
 * Date: 10/22/21
 * Time: 7:22 AM
 *
 * Project: csci205_labs
 * Package: lab08
 * Class: Board
 *
 * Description:
 *
 * ****************************************
 */
package lab09;

public class Board {
    /**
     * The board initializes as an empty two dimensional array
     */
    private String[][] board;

    /**
     * The state of the board
     */
    private GameState boardState;

    /**
     * Constructor of the board object
     * @param x The X dimension of the array
     * @param y The Y dimension of the array
     */
    public Board(int x, int y){
        this.board = new String[x][y];
        this.boardState = GameState.NEW_GAME;
        for(int i = 0; i < this.board.length; i++){
            board[i] = new String[]{"Null", "Null", "Null", "Null"};
            }
        }

    @Override
    public String toString() {
        String s = "";
        for (String[] strings : this.board) {
            for (int j = 0; j < strings.length; j++) {
                s += "[" + strings[j] + "]";
            }
            s += "\n";
        }
        return s;
    }

    public void setBoardPosition(int Position, String[] strings) {
        this.board[Position] = strings;
    }
}
