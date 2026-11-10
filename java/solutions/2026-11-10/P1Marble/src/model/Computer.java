package model;

/** 컴퓨터 참가자 */
public class Computer extends Gamer {

	public Computer() {
		super(10);
	}

	public String name() {
		return "컴퓨터";
	}

	/** [4번] 공격할 때 집는 구슬 수: 1 ~ min(5, 내 구슬) 중 무작위 */
	public int pickForAttack() {
		int n = 5;
		if (getBeads() < 5)      // beads 는 부모의 private → getter 로만
			n = getBeads();
		return (int) (Math.random() * n) + 1;          // 1 ~ n
	}

	/** [5번] 방어할 때 집는 구슬 수: 0 ~ min(5, 내 구슬) 중 무작위 */
	public int pickForGuard() {
		int n = 5;
		if (getBeads() < 5)
			n = getBeads();
		return (int) (Math.random() * (n + 1));        // 0 ~ n
	}
}
