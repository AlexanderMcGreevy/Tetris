import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class TetrisLay {
    public JPanel panel1;          // must be public or have a getter
    private JPanel board;
    private JPanel game;
    private JPanel previewPiece;
    private JPanel menu;
    private JButton startBtn;
    private JLabel Score;
    private JLabel Level;
    private TetrisView view;



    private TetrisView view;
    private Piece currentPiece;
    private Random random = new Random();
    private static final int BOARD_WIDTH = 10;
    private static final int BOARD_HEIGHT = 20;
    private int[][] tetBoard = new int[BOARD_HEIGHT][BOARD_WIDTH];

    public void setView(TetrisView view) {
        this.view = view;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public void newPiece() {
        currentPiece = new Piece(random.nextInt(7)); // Random 7 pieces
        view.repaint();
    }

    public void moveLeft() {
        if (canMove(currentPiece, -1, 0)) {
            currentPiece.moveLeft();
            view.repaint();
        }
    }

    public void moveRight() {
        if (canMove(currentPiece, 1, 0)) {
            currentPiece.moveRight();
            view.repaint();
        }
    }

    public void moveDown() {
        if (canMove(currentPiece, 0, 1)) {
            currentPiece.moveDown();
            view.repaint();
        } else {
            placePiece();
            newPiece();
        }
    }

    public void rotate() {
        currentPiece.rotate();
        if (!canMove(currentPiece, 0, 0)) {
            currentPiece.unrotate();
        }
        view.repaint();
    }

    public void drop() {
        while (canMove(currentPiece, 0, 1)) {
            currentPiece.moveDown();
        }
        placePiece();
        newPiece();
        view.repaint();
    }

    private boolean canMove(Piece piece, int dx, int dy) {
        int[][] shape = piece.getPiece();
        int newX = piece.getX() + dx;
        int newY = piece.getY() + dy;
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] != 0) {
                    int boardX = newX + col;
                    int boardY = newY + row;

                    // Check if the new position is out of bounds
                    if (boardX < 0 || boardX >= BOARD_WIDTH || boardY < 0 || boardY >= BOARD_HEIGHT) {
                        return false;
                    }

                    // Check if the new position collides with existing pieces
                    if (tetBoard[boardY][boardX] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void placePiece() {
        int[][] shape = currentPiece.getPiece();
        int x = currentPiece.getX();
        int y = currentPiece.getY();

        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] != 0) {
                    tetBoard[y + row][x + col] = shape[row][col];
                }
            }
        }
    }
}