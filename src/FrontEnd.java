
//this class initiates the gameframe along with the form to display the game
public class FrontEnd {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TetrisGameFrame frame = new TetrisGameFrame();
                frame.setVisible(true);
            }
        });
    }
}