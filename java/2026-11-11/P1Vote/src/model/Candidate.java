package model;

/** 후보 한 명 (이름 + 득표 수) */
public class Candidate {

	private String name;
	private int votes;

	public Candidate(String name) {
		this.name = name;
		votes = 0;
	}

	public String getName() {
		return name;
	}

	public int getVotes() {
		return votes;
	}

	/** [1번] 한 표 더하기 */
	public void addVote() {
		// TODO
	}
}
