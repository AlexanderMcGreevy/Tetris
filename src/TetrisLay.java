import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
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
    private JLabel highScore;
    private JLabel highScoreVal;
    private TetrisView view;
    private PreviewView preview;
    private Random random = new Random();
    private Piece nextPiece = new Piece(random.nextInt(7));
    private Piece currentPiece;
    private static final int BOARD_WIDTH = 10;
    private static final int BOARD_HEIGHT = 20;
    private int[][] tetBoard = new int[BOARD_HEIGHT][BOARD_WIDTH];
    private Timer gameTimer;
    private int score = 0;
    private int level = 0;
    private int linesClearedTotal = 0;
    private int highScoreValue = 0;
    private Map<String, Integer> leaderboard = new HashMap<>();
    private final String leaderboardFile = "leaderboard.ser";




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

    private void clearFullRows() {
        int rowsCleared = 0;
        for (int y = BOARD_HEIGHT - 1; y >= 0; y--) {
            boolean fullRow = true;
            for (int x = 0; x < BOARD_WIDTH; x++) {
                if (tetBoard[y][x] == 0) {
                    fullRow = false;
                    break;
                }
            }

            if (fullRow) {
                rowsCleared++;
                // Move all rows above this one down
                for (int row = y; row > 0; row--) {
                    System.arraycopy(tetBoard[row - 1], 0, tetBoard[row], 0, BOARD_WIDTH);
                }

                // Clear the top row
                for (int col = 0; col < BOARD_WIDTH; col++) {
                    tetBoard[0][col] = 0;
                }

                y++; // Check same row again since it was shifted down
            }
        }
        if (rowsCleared>0) {
            linesClearedTotal += rowsCleared;

            int oldLevel = level;
            level = linesClearedTotal / 2;

            // Update game speed if level increased
            if (level > oldLevel && gameTimer != null) {
                gameTimer.setDelay(Math.max(100, 500 - (level * 50)));
            }

            int points=switch (rowsCleared) {
                case 1 -> 40 * (level + 1);
                case 2 -> 100 * (level + 1);
                case 3 -> 300 * (level + 1);
                case 4 -> 1200 * (level + 1);
                default -> 0;
            };
            score+=points;
            setScore(score);
            setLevel(level);
        }
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
        score = 0;
        setScore(score);




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
        clearFullRows();
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

        // Update high score if needed
        if (score > highScoreValue) {
            highScoreValue = score;
        }

        String name = JOptionPane.showInputDialog(null, "Game Over!\nYour Score: " + score + "\nHigh Score: " + highScoreValue + "\n\nEnter your name:");
        if (name != null && !name.isEmpty()) {
            TetrisSocketClient.sendScore(name, score);
        }

        // Ask if they want to start a new game
        int option = JOptionPane.showConfirmDialog(null,
                "Play again?",
                "New Game",
                JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            startGame(); // restart the game
        } else {
            System.exit(0); // close app
        }
        leaderboard.put(name, score);
        saveLeaderboard();

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
        loadLeaderboard();

    }

    public void showStartDialog() {
        int option = JOptionPane.showConfirmDialog(null,
                "Welcome to Tetris!\n\nWould you like to start the game?",
                "Start Game",
                JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            startGame();
        } else {
            System.exit(0); // Quit if they say no
        }
    }

    @SuppressWarnings("unchecked")
    public void loadLeaderboard() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(leaderboardFile))) {
            leaderboard = (Map<String, Integer>) in.readObject();
            System.out.println("Leaderboard loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("No leaderboard file found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveLeaderboard() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(leaderboardFile))) {
            out.writeObject(leaderboard);
            System.out.println("Leaderboard saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}