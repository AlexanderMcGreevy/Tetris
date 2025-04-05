import javax.swing.JFrame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TetrisGameFrame extends JFrame {
    public TetrisGameFrame() {
        setTitle("Tetris Game");
        setSize(200, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        TetrisLay lay = new TetrisLay();
        lay.initGameView();     // adds TetrisView inside the game panel
        lay.newPiece();         // start with a random piece
        setContentPane(lay.panel1);  // show the full form layout


        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        lay.moveLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        lay.moveRight();
                        break;
                    case KeyEvent.VK_DOWN:
                        lay.moveDown();
                        break;
                    case KeyEvent.VK_UP:
                        lay.rotate();
                        break;
                    case KeyEvent.VK_SPACE:
                        lay.drop();
                        break;
                }
            }
        });
        setFocusable(true);
    }
}