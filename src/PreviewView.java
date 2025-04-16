import javax.swing.*;
import java.awt.*;
//this class creates teh preview view of the next piece that will appear
public class PreviewView extends JPanel {
    private TetrisLay lay;
    private final int BLOCK_SIZE = 20; // smaller than main board

    public void setLay(TetrisLay lay) {
        this.lay = lay;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPreview(g);
    }
//draws the preview of the next piece
    private void drawPreview(Graphics g) {
        if (lay == null || lay.getNextPiece() == null) return;

        int[][] shape = lay.getNextPiece().getPiece();
        Color pieceColor = lay.getNextPiece().getColor();
        int shapeHeight = shape.length;
        int shapeWidth = shape[0].length;

        // Calculate centering offsets
        int xOffset = (getWidth() - shapeWidth * BLOCK_SIZE) / 2;
        int yOffset = (getHeight() - shapeHeight * BLOCK_SIZE) / 2;

        g.setColor(pieceColor);

        for (int i = 0; i < shapeHeight; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] != 0) {
                    int x = xOffset + j * BLOCK_SIZE;
                    int y = yOffset + i * BLOCK_SIZE;
                    g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                    g.setColor(pieceColor);
                }
            }
        }
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(4 * BLOCK_SIZE, 4 * BLOCK_SIZE); // max piece size
    }
}
