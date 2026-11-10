package controller;

import model.*;

/** 홀짝 구슬 게임 진행 (원본: marblegame/GameController.java — 입력·출력 대신 값을 받고 메시지를 리턴) */
public class GameController {

	private Player player;
	private Computer computer;

	public GameController(Player p, Computer c) {
		player = p;
		computer = c;
	}

	private boolean isEven(int n) {
		return n % 2 == 0;
	}

	/** [6번] 당신의 공격 */
	public String playerTurn(int n, boolean playerEven, int computerGuard) {
		if (playerEven == isEven(computerGuard)) {      // 내가 외친 홀짝 == 실제 홀짝
			player.addBeads(n);
			computer.removeBeads(n);
			return "당신이 이겼습니다. 구슬 획득! : " + n;
		} else {
			player.removeBeads(n);
			computer.addBeads(n);
			return "당신이 졌습니다. 구슬 제출! : " + n;
		}
	}

	/** [7번] 컴퓨터의 공격 — 두 수의 홀짝이 같으면 컴퓨터가 맞힌 것 */
	public String computerTurn(int playerGuard, int computerAttack) {
		if (isEven(playerGuard) == isEven(computerAttack)) {
			player.removeBeads(computerAttack);
			computer.addBeads(computerAttack);
			return "당신이 졌습니다. 구슬 제출! : " + computerAttack;
		} else {
			player.addBeads(computerAttack);
			computer.removeBeads(computerAttack);
			return "당신이 이겼습니다. 구슬 획득! : " + computerAttack;
		}
	}

	/** 둘 중 하나라도 졌으면 true (완성) */
	public boolean isOver() {
		return player.lose() || computer.lose();
	}
}
