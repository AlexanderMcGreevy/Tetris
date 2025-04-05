import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TetrisGameFrame extends JFrame {
    public TetrisGameFrame() {
        setTitle("Tetris Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TetrisLay lay = new TetrisLay();
        lay.initGameView();     // adds TetrisView inside the game panel
        lay.startGame();

        setContentPane(lay.panel1);  // show the full form layout
        pack();  // Sizes window based on preferred sizes (like 300x600 for game board)

        // Set a minimum size so users can’t shrink it too small
        setMinimumSize(new Dimension(400, 650)); // Adjust as needed for side panel/labels

        setLocationRelativeTo(null);  // Center on screen

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
