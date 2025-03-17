public class Piece {
    private int[][] shape;
    private int x, y;
    private int type;
    private int rotation;
    private static final int[][][] pieces = {
            {{1, 1, 1, 1}}, // I piece
            {{1, 1, 1}, {0, 1, 0}}, // T piece
            {{1, 1}, {1, 1}}, // O piece
            {{1, 1, 0}, {0, 1, 1}}, // S piece
            {{0, 1, 1}, {1, 1, 0}}, // Z piece
            {{1, 1, 1}, {1, 0, 0}}, // L piece
            {{1, 1, 1}, {0, 0, 1}} // J piece
    };

    public Piece(int type) {
        this.type = type;
        this.rotation = 0;
        this.shape = pieces[type];
        this.x = 3; // Starting x position
        this.y = 0; // Starting y position
    }

    public int[][] getPiece() {
        return shape;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }

    public int getRotation() {
        return rotation;
    }

    public void moveLeft() {
        x--;
    }

    public void moveRight() {
        x++;
    }

    public void moveDown() {
        y++;
    }

    public void rotate() {
        rotation = (rotation + 1) % pieces[type].length;
        shape = pieces[type];
    }

    public void unrotate() {
        rotation = (rotation + pieces[type].length - 1) % pieces[type].length;
        shape = pieces[type];
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
        shape = pieces[type];
    }

    public void setType(int type) {
        this.type = type;
        this.shape = pieces[type];
    }

    public void setPiece(int[][] piece) {
        this.shape = piece;
    }

    public void setPiece(int type, int rotation) {
        this.type = type;
        this.rotation = rotation;
        this.shape = pieces[type];
    }
}