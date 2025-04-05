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
    private PreviewView preview;
    private Random random = new Random();
    private Piece nextPiece = new Piece(random.nextInt(7));
    private Piece currentPiece;
    private static final int BOARD_WIDTH = 10;
    private static final int BOARD_HEIGHT = 20;
    private int[][] tetBoard = new int[BOARD_HEIGHT][BOARD_WIDTH];
    private Timer gameTimer;


    public int[][] getBoard() {
        return tetBoard;
    }

    public void setView(TetrisView view) {
        this.view = view;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public void newPiece() {
        currentPiece = nextPiece;
        currentPiece.setX(3); // Reset position (optional safety)
        currentPiece.setY(0);

        nextPiece = new Piece(random.nextInt(7));

        // 👇 If new piece can't be placed, game over
        if (!canMove(currentPiece, 0, 0)) {
            stopGame();
            JOptionPane.showMessageDialog(null, "Game Over!");
            return;
        }

        view.repaint();
        preview.repaint();
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
            boolean kicked= false;
            for (int dx=-1; dx<=1; dx++) {
                currentPiece.setX(currentPiece.getX() +dx);
                if (canMove(currentPiece, 0, 0)) {
                    kicked = true;
                    break;
                }
                currentPiece.setX(currentPiece.getX() -dx);
            }
            if (!kicked) {
                currentPiece.rotateBack();
            }

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

    public void startGame() {
        resetBoard(); // clear everything
        if (gameTimer != null) {
            gameTimer.stop();
        }

        gameTimer = new Timer(500, e -> moveDown());
        gameTimer.start();

        newPiece(); // Start the game with the first piece
    }

    public void resetBoard() {
        for (int y = 0; y < BOARD_HEIGHT; y++) {
            for (int x = 0; x < BOARD_WIDTH; x++) {
                tetBoard[y][x] = 0;  // 0 = empty
            }
        }
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
        int type = currentPiece.getType() + 1;

        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] != 0) {
                    tetBoard[y + row][x + col] = type;
                }
            }
        }
    }
    public void setScore(int score) {
        Score.setText("Score: " + score);
    }

    public void setLevel(int level) {
        Level.setText("Level: " + level);
    }


    public TetrisView getView() {
        return view;
    }

    public Piece getNextPiece() {
        return nextPiece;
    }

    public void stopGame() {
        if (gameTimer != null) {
            gameTimer.stop();
        }
    }



    public void initGameView() {
        view = new TetrisView();
        preview = new PreviewView();

        view.setLay(this);
        preview.setLay(this);

        game.setLayout(new BorderLayout());
        game.add(view, BorderLayout.CENTER);

        previewPiece.setLayout(new BorderLayout());
        previewPiece.add(preview, BorderLayout.CENTER);

        game.revalidate();
        game.repaint();
        previewPiece.revalidate();
        previewPiece.repaint();
    }


}