import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class TetrisView extends JPanel {
    private TetrisLay lay;
    private final int BLOCK_SIZE = 30;
    private final int COLS = 10;
    private final int ROWS = 20;


    public void setLay(TetrisLay lay) {
        this.lay = lay;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        drawSettledBlocks(g);
        drawPiece(g);
    }

    private void drawSettledBlocks(Graphics g) {
        int[][] board=lay.getBoard();
        for (int y=0; y<board.length; y++) {
            for (int x=0; x<board[0].length; x++) {
                int val=board[y][x];
                if (val!=0) {
                    g.setColor(getColorForType(val-1));
                    g.fillRect(x*BLOCK_SIZE, y*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }
    }


    private Color getColorForType(int type) {
        switch (type) {
            case 0: return Color.CYAN;    // I
            case 1: return Color.YELLOW;  // O
            case 2: return Color.MAGENTA; // T
            case 3: return Color.ORANGE;  // L
            case 4: return Color.BLUE;    // J
            case 5: return Color.GREEN;   // S
            case 6: return Color.RED;     // Z
            default: return Color.GRAY;
        }
    }
    private void drawBoard(Graphics g) {
        g.setColor(Color.BLACK);
        for (int x = 0; x < COLS; x++) {
            for (int y = 0; y < ROWS; y++) {
                g.drawRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
            }
        }
    }


    private void drawPiece(Graphics g) {
        Piece piece = lay.getCurrentPiece();
        if (piece != null) {
            int[][] shape = piece.getPiece();
            int x = piece.getX();
            int y = piece.getY();
            g.setColor(piece.getColor());
            for (int i = 0; i < shape.length; i++) {
                for (int j = 0; j < shape[i].length; j++) {
                    if (shape[i][j] != 0) {
                        g.fillRect((x + j) * 30, (y + i) * 30, 30, 30);
                    }
                }
            }
        }
    }
    public void repaint() {
        super.repaint();
    }


    @Override
    public java.awt.Dimension getPreferredSize() {
        return new java.awt.Dimension(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
    }

}


