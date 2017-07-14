import java.util.Random;

// CUIの課題では, このクラスを実行する
public class PlayGame2048 {
    // --- メソッド
    public static void main(String[] args) {
	Random random = new Random(); // ランダムな値を作るためのクラス
	                              // 例: random.nextInt(5);
	                              //     で0--4のランダムな整数が得られる
	Game2048 game = new Game2048();
	game.startGame(random); // (課題5)ゲームの初期盤面を作る
	while( game.movable() ) {
	    game.show(); // (課題1)毎ターン盤面を表示する
	    game.turn(random); // (課題2以降)毎ターンの処理
	}
	game.show();
	System.out.println("Game Over!!"); 
    }
}
