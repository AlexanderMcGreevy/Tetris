import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class TetrisLay {
    private TetrisView view;
    private Piece currentPiece;
    private Random random = new Random();
    private JPanel panel1;
    private JPanel menu;
    private JPanel board;
    private JPanel game;
    private JPanel previewPiece;
    private JButton startBtn;

    public void setView(TetrisView view) {
        this.view = view;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public void newPiece() {
        currentPiece = new Piece(random.nextInt(7)); // Random 7  pieces
        view.repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tetris Game");
        TetrisLay lay = new TetrisLay();
        lay.panel1 = new JPanel();
        lay.panel1.setLayout(new CardLayout());
        lay.menu = new JPanel();
        lay.board = new JPanel();
        lay.game = new JPanel();
        lay.previewPiece = new JPanel();
        lay.startBtn = new JButton("Start");

        lay.panel1.add(lay.menu, "Menu");
        lay.panel1.add(lay.board, "Board");
        lay.board.setLayout(new BorderLayout());
        lay.board.add(lay.game, BorderLayout.CENTER);
        lay.board.add(lay.previewPiece, BorderLayout.EAST);

        TetrisView view = new TetrisView();
        lay.setView(view);
        view.setLay(lay);
        lay.game.setLayout(new BorderLayout());
        lay.game.add(view, BorderLayout.CENTER);

        lay.newPiece(); // Initialize the first piece

        frame.setContentPane(lay.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void moveLeft() {
        view.repaint();
    }

    public void moveRight() {
        view.repaint();
    }

    public void moveDown() {
        view.repaint();
    }

    public void rotate() {
        view.repaint();
    }

    public void drop() {
        view.repaint();
    }

    public void clearLines() {
        view.repaint();
    }

    public void gameOver() {
        view.repaint();
    }

    public void pause() {
        view.repaint();
    }

    public void resume() {
        view.repaint();
    }

    public void start() {
        view.repaint();
    }

    public void stop() {
        view.repaint();
    }

    public void setLevel(int level) {
        view.repaint();
    }

    public void setScore(int score) {
        view.repaint();
    }

    public void setLines(int lines) {
        view.repaint();
    }

    public void setNextPiece(int[][] piece) {
        view.repaint();
    }

    public void setHoldPiece(int[][] piece) {
        view.repaint();
    }

    public void setPiece(int[][] piece, int x, int y) {
        view.repaint();
    }

    public void setGameOver(boolean b) {
        view.repaint();
    }



    public void setVisible(boolean b) {
        // TODO method stub
    }

    public void repaint() {
        if (view != null) {
            view.repaint();
        }
    }
}