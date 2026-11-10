package controller;

import model.*;

/** 투표 진행 */
public class VoteController {

	private Ballot ballot;

	public VoteController(Ballot b) {
		ballot = b;
	}

	/** [7번] 원본 VoteCount 의 while 반복과 같은 규칙:
	 *  inputs 를 앞에서부터 하나씩 투표에 반영하다가, 범위 밖 번호가 나오면 그 자리에서 멈춘다 (뒤는 무시).
	 *  실제로 반영된 표 수를 리턴 */
	public int run(int[] inputs) {
		// TODO
		return 0;
	}

	/** 후보별 결과 줄 (완성) */
	public String[] results() {
		String[] lines = new String[ballot.size()];
		for (int i = 0; i < lines.length; i++)
			lines[i] = ballot.report(i);
		return lines;
	}
}
