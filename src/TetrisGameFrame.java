import javax.swing.JFrame;

public class TetrisGameFrame extends JFrame {
    public TetrisGameFrame() {
        setTitle("Tetris Game");
        setSize(200, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        TetrisView view = new TetrisView();
        TetrisLay lay = new TetrisLay();
        view.setLay(lay);
        lay.setView(view);
        lay.newPiece(); // Initialize the first piece

        add(view);
        view.setVisible(true);
    }
}