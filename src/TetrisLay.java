import java.util.Random;

public class TetrisLay {
    private TetrisView view;
    private Piece currentPiece;
    private Random random = new Random();

    public void setView(TetrisView view) {
        this.view = view;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public void newPiece() {
        currentPiece = new Piece(random.nextInt(7)); // Assuming 7 different pieces
        view.repaint();
    }

    public static void main(String[] args) {
        TetrisView view = new TetrisView();
        TetrisLay lay = new TetrisLay();
        view.setLay(lay);
        lay.setView(view);
        lay.newPiece(); // Initialize the first piece
        view.setVisible(true);
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