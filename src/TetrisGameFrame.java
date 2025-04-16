import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//this class creates the main game frame and initializes the game
public class TetrisGameFrame extends JFrame {
    public TetrisGameFrame() {
        setTitle("Tetris Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TetrisLay lay = new TetrisLay();
        lay.initGameView();     // prepares game + board panel

        setContentPane(lay.panel1);  // initially show the menu panel
        pack();

        setMinimumSize(new Dimension(400, 650));
        setLocationRelativeTo(null);

        // Keep key listeners active (will apply to the whole window)
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> lay.moveLeft();
                    case KeyEvent.VK_RIGHT -> lay.moveRight();
                    case KeyEvent.VK_DOWN -> lay.moveDown();
                    case KeyEvent.VK_UP -> lay.rotate();
                    case KeyEvent.VK_SPACE -> lay.drop();
                }
            }
        });

        setFocusable(true);

        // creates start game view
        SwingUtilities.invokeLater(lay::showStartDialog);
    }
}
