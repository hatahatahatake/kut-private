public class Board {
    // --- フィールド
    private int[][] board = new int[4][4];
    // 2次元配列で作るとき
    // private int[] board; // ただの配列で作るとき

    // --- コンストラクタ ---
    // boardを作る
    public Board() {
	for ( int y = 0; y < 4; y ++ ) {
	    for ( int x = 0; x < 4; x ++ ) {
		this.board[x][y] = 0;
	    }
	}
    }

    // --- メソッド ---
    // あるマスのタイルの値を返す
    public int getCell(int x, int y) {
       	return this.board[x][y];
    }

    // あるマスのタイルの値を設定する
    public void setCell(int x, int y, int num) {
	this.board[x][y] = num;
    }

    // (課題1)盤面を表示する関数
    public void print() {
	for ( int y = 0; y < 4; y ++ ) {
	    for ( int x = 0; x < 4; x ++ ) {
		System.out.printf("%5d", this.board[x][y]); // numを4桁で表示
	    }
	    System.out.println();
	}
    }
    /*
    // ヒント（テストコード）
    public static void main(String[] args) {
	Board b = new Board();
	for ( int y = 0; y < 4; y++ ) {
	    for ( int x = 0; x < 4; x++ ) {
		int num = y * 4 + x; // 仮におく値
		b.setCell(x, y, num);
		System.out.println("(x:" + x + ",y:" + y + ") = " 
				   + b.getCell(x, y));
	    }
	}
	System.out.println();
	b.print();
	} */
}
