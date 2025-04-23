import java.awt.*;

//this class creates the pieces of the game and includes their rotational functionality
public class Piece {
    private int[][] shape;
    private int x, y;
    private int type;
    private int rotation;

    //creates the base pieces the game can choose from
    private static final int[][][] pieces = {
            {{1, 1, 1, 1}},                 // I
            {{1, 1, 1}, {0, 1, 0}},         // T
            {{1, 1}, {1, 1}},               // O
            {{1, 1, 0}, {0, 1, 1}},         // S
            {{0, 1, 1}, {1, 1, 0}},         // Z
            {{1, 1, 1}, {1, 0, 0}},         // L
            {{1, 1, 1}, {0, 0, 1}}          // J
    };

    public Piece(int type) {
        this.type = type;
        this.rotation = 0;
        this.shape = pieces[type];
        this.x = 3;
        this.y = 0;
    }

    public int[][] getPiece() {
        return shape;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getType() { return type; }
    public int getRotation() { return rotation; }

    public void moveLeft() { x--; }
    public void moveRight() { x++; }
    public void moveDown() { y++; }

    public void rotate() {
        shape = rotateMatrix(shape);
    }

    private int[][] rotateMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] result = new int[col][row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                result[j][row - 1 - i] = matrix[i][j];
            }
        }
        return result;
    }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    public void setRotation(int rotation) {
        this.rotation = rotation;
        shape = pieces[type]; // No actual rotation variant yet
    }

    public void setType(int type) {
        this.type = type;
        this.shape = pieces[type];
    }

    public void setPiece(int[][] piece) {
        this.shape = piece;
    }
    private int[][] rotateMatrixCounterClockwise(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] result = new int[col][row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                result[col - 1 - j][i] = matrix[i][j];
            }
        }
        return result;
    }

    //undoes the rotation if the piece goes through a wall
    public void rotateBack() {
        shape = rotateMatrixCounterClockwise(shape);
    }


    public void setPiece(int type, int rotation) {
        this.type = type;
        this.rotation = rotation;
        this.shape = pieces[type];
    }

    public Color getColor() {
        return switch (type) {
            case 0 -> Color.CYAN;
            case 1 -> Color.YELLOW;
            case 2 -> Color.MAGENTA;
            case 3 -> Color.ORANGE;
            case 4 -> Color.BLUE;
            case 5 -> Color.GREEN;
            case 6 -> Color.RED;
            default -> Color.GRAY;
        };
    }
}
