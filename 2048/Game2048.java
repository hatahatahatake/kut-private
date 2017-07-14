import java.util.Random;
import java.util.Scanner;
 
public class Game2048 {
    // --- フィールド
    Board board;
    final int BOARD_SIZE = 4;
    int sw = 0;      // ランダムな数字を入れるかどうかの変数
    int Score = 0;   // 得点を求める変数
 
    // --- コンストラクタ
    // Boardを初期化する
    public Game2048() {
	this.board = new Board();
    }
 
    // --- メソッド
    // 毎ターン呼ばれ, キーボードの入力を受け付ける
    public void turn(Random random) {
	Scanner scan = new Scanner(System.in);
	char dir = scan.next().charAt(0); // 1文字目を dir に格納する
	if ( dir == 'n' ) {
	    startGame(random);  // 開始状態に戻す
	} else {
	    move(dir);
	    if ( this.sw == 1 ) {
		this.putRandom(random);
		this.sw = 0;    // swを0にすることで0のときに数字が出てこないようにする
	    } else {
		System.out.println("Can't move!!");
		turn(random);
	    }
	}
    }
 
    // (課題5)盤面をゲーム開始状態にする
    public void startGame(Random random) {
	for ( int y = 0; y < 4; y ++ ) {
	    for ( int x = 0; x < 4; x ++ ) {
		board.setCell(x, y, 0);
	    }
	}
	this.putRandom(random);  
	this.putRandom(random);  // ランダムな数字を2つ呼び出す
    }

    
    // (課題2, 4, 7) wasdのどれかの方向にタイルを動かす
    private void move(char dir) {
	// --- wを入力したときのスライド動作 ---
	if ( dir == 'w' ) {
	    for ( int x = 0; x < BOARD_SIZE; x++ ) {
		for ( int y = 1; y < BOARD_SIZE; y++ ) {
		    if ( board.getCell(x, y) != 0) {
			for ( int y2 = y-1; y2 >= 0; y2-- ) {
			    if ( board.getCell(x, y2) == 0 ) {
				board.setCell(x, y2, board.getCell(x, y2+1));
				board.setCell(x, y2+1, 0);
				this.sw = 1;
                 
			    }
			}
		    }
		}
	    }
	}
 
	// --- aを入力したときのスライド動作 ---
	if ( dir == 'a' ) {
	    for ( int y = 0; y < BOARD_SIZE; y++ ) {
		for ( int x = 1; x < BOARD_SIZE; x++ ) {
		    if ( board.getCell(x, y) != 0) {
			for ( int x2 = x-1; x2 >= 0; x2-- ) {
			    if ( board.getCell(x2, y) == 0 ) {
				board.setCell(x2, y, board.getCell(x2+1, y));
				board.setCell(x2+1, y, 0);
				this.sw = 1;
			    }
			}
		    }
		}
	    }
	}
 
	// --- sを入力したときのスライド動作 ---
	if ( dir == 's' ) {
	    for ( int x = BOARD_SIZE-1; x >= 0; x-- ) {
		for ( int y = BOARD_SIZE-1; y >= 0; y-- ) {
		    if ( board.getCell(x, y) != 0) {
			for ( int y2 = y+1; y2 < BOARD_SIZE; y2++ ) {
			    if ( board.getCell(x, y2) == 0 ) {
				board.setCell(x, y2, board.getCell(x, y2-1));
				board.setCell(x, y2-1, 0);
				sw = 1;
			    }
			}
		    }
		}
	    }
	}
 
	// --- dを入力したときのスライド動作 ---
	if ( dir == 'd' ) {
	    for ( int x = BOARD_SIZE-1; x >= 0; x-- ) {
		for ( int y = BOARD_SIZE-1; y >= 0; y-- ) {
		    if ( board.getCell(x, y) != 0) {
			for ( int x2 = x+1; x2 < BOARD_SIZE; x2++ ) {
			    if ( board.getCell(x2, y) == 0 ) {
				board.setCell(x2, y, board.getCell(x2-1, y));
				board.setCell(x2-1, y, 0);
				this.sw = 1;
			    }
			}
		    }
		}
	    }
	}
	this.plus(dir);
    }
 
    // 同じ数字が上下左右にある時に2倍して併合していく操作
    public void plus(char dir) {
	/* wを入力したとき → 上の値を2倍してスライド */
	if ( dir == 'w' ) {
	    for ( int x = 0; x < BOARD_SIZE; x++ ) {
		for ( int y = 0; y < BOARD_SIZE; y++ ) {
		    if ( board.getCell(x, y) != 0 ) {
			for ( int y2 = y+1; y2 < BOARD_SIZE; y2++ ) {
			    if ( board.getCell(x, y2) != 0 ) {
				if ( board.getCell(x, y2-1) == board.getCell(x, y2) ) {
				    board.setCell(x, y2-1, 2 * board.getCell(x, y2));
				    board.setCell(x, y2, 0);
				    this.sw = 1;
				    Score += board.getCell(x, y2-1);
				}
			    }
			}
		    }
		}
	    }
	}
     
	/* aを入力したとき → 左の値を2倍してスライド */
	if ( dir == 'a' ) {
	    for ( int y = 0; y < BOARD_SIZE; y++ ) {
		for ( int x = 0; x < BOARD_SIZE; x++ ) {
		    if ( board.getCell(x, y) != 0 ) {  
			for ( int x2 = x+1; x2 < BOARD_SIZE; x2++ ) {
			    if ( board.getCell(x2, y) != 0 ) {
				if ( board.getCell(x2-1, y) == board.getCell(x2, y) ) {
				    board.setCell(x2-1, y, 2 * board.getCell(x2, y));
				    board.setCell(x2, y, 0);
				    this.sw = 1;
				    Score += board.getCell(x2-1, y);
				}
			    }
			}
		    }
		}
	    }
	}
 
	/* sを入力したとき → 下の値を2倍してスライド */
	if ( dir == 's' ) {  
	    for ( int x = BOARD_SIZE-1; x >= 0; x-- ) {
		for ( int y = BOARD_SIZE-1; y >= 0; y-- ) {
		    if ( board.getCell(x, y) != 0 ) {
			for ( int y2 = y-1; y2 >= 0; y2-- ) {
			    if ( board.getCell(x, y2) != 0 ) {
				if ( board.getCell(x, y2+1) == board.getCell(x, y2) ) {
				    board.setCell(x, y2+1, 2 * board.getCell(x, y2));
				    board.setCell(x, y2, 0);
				    this.sw = 1;
				    Score += board.getCell(x, y2+1);
				}
			    }
			}
		    }
		}
	    }
	}
     
	/* dを入力したとき → 右の値を2倍してスライド */
	if ( dir == 'd' ) { 
	    for ( int x = BOARD_SIZE-1; x > 0; x-- ) {
		for ( int y = BOARD_SIZE-1; y >= 0; y-- ) {
		    if ( board.getCell(x, y) != 0 ) {
			for ( int x2 =x-1; x2 >= 0; x2-- ) {
			    if ( board.getCell(x2, y) != 0 ) {
				if ( board.getCell(x2+1, y) == board.getCell(x2, y) ) {
				    board.setCell(x2+1, y, 2 * board.getCell(x2, y));
				    board.setCell(x2, y, 0);
				    this.sw = 1;
				    Score += board.getCell(x2+1, y);
				}
			    }
			}
		    }               
		}
	    }
	}
    }  // 問題点：二つ上の数字が足せれてしまうときがある!!未解決...
 
     
    // (課題3) ランダムな空きマスに2(90%)または4(10%)のタイルを置く
    private void putRandom(Random random) {
	int x = random.nextInt(4); // 横に表示するランダムな数字
	int y = random.nextInt(4); // 上下に表示するランダムな数字
 
	while( board.getCell(x, y) != 0) {
	    x = random.nextInt(4); 
	    y = random.nextInt(4);
	}
	if ( random.nextInt(10) > 8 ) {  
	    board.setCell(x, y, 4);      // 10%の確率で4が出現
	} else board.setCell(x, y, 2);   // 90%の確率で2が出現
    }
 
    // (課題6) タイルを動かすことが出来るか調べる
    public boolean movable() {
	// 上下に連続して同じ数字があるかチェックする！
	for ( int y = 0; y < BOARD_SIZE; y++ ) {  // 横を調べる
	    for ( int x = 0; x < BOARD_SIZE; x++ ) { // 縦を調べる
		if ( board.getCell(x, y) == 0 ) {
		    // 横にも縦にもない場合
		    // 空セルがある
		    // スライドできる！
		    return true;
		}
      
		if ( x != BOARD_SIZE -1 && board.getCell(x, y)
		     == board.getCell(x+1, y) ) {
		    // 右に同じ数字が続く場所がある
		    // スライドできる！
		    return true;
		}
		if ( y != BOARD_SIZE -1 && board.getCell(x, y)
		     == board.getCell(x, y+1) ) {
		    // 下に同じ数字が続く場所がある
		    // スライドできる！
		    return true;
		}
	    }
	}
	// どの条件にも当てはまらないとき(動けない)
	return false;
    }
 
    // (課題1)毎ターン呼び出して盤面を表示する
    public void show() {
	System.out.println(" ----------------------- ");
	board.print(); // printメソッドの呼び出し
	System.out.println(" ----------------------- ");
	System.out.println("Score: " + Score);  // 得点を表示する 
	System.out.println();
    }
 
 
    /* //　テストプログラム 
       public static void main(String[] args) {
       Random random = new Random();
       Game2048 game = new Game2048();
 
       game.board.setCell(0, 0, 0);
       game.board.setCell(1, 0, 0);
       game.board.setCell(2, 0, 0);
       game.board.setCell(3, 0, 0);
       game.board.setCell(0, 1, 2);
       game.board.setCell(1, 1, 2);
       game.board.setCell(2, 1, 0);
       game.board.setCell(3, 1, 2);
       game.board.setCell(0, 2, 2);
       game.board.setCell(1, 2, 0);
       game.board.setCell(2, 2, 0);
       game.board.setCell(3, 2, 2);
       game.board.setCell(0, 3, 2);
       game.board.setCell(1, 3, 2);
       game.board.setCell(2, 3, 2);
       game.board.setCell(3, 3, 2);
 
       while ( true ) {  // スライドできるとき
       game.show();  // showメソッドで毎ターン表示する
       System.out.println(game.movable());
       game.turn(random);
       // turnメソッドにrandomを引数として代入
             
       }
       }  */
             
}
