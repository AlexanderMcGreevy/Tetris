import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class TetrisView extends JPanel {
    private TetrisLay lay;

    public void setLay(TetrisLay lay) {
        this.lay = lay;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        drawPiece(g);
    }

    private void drawBoard(Graphics g) {
        g.setColor(Color.BLACK);
        for (int x = 0; x < getWidth(); x += 30) {
            for (int y = 0; y < getHeight(); y += 30) {
                g.drawRect(x, y, 30, 30);
            }
        }
    }

    private void drawPiece(Graphics g) {
        Piece piece = lay.getCurrentPiece();
        if (piece != null) {
            int[][] shape = piece.getPiece();
            int x = piece.getX();
            int y = piece.getY();
            g.setColor(Color.RED);
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
}


