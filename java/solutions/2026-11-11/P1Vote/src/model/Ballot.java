package model;

/** 투표함 = 후보 객체 배열
 *  원본: Hyunha/2025COM2018 COM2018/src/chapters/ch07/VoteCount.java (int[] votes)
 *  — int 배열 대신 Candidate 객체 배열로 바꾼 버전 */
public class Ballot {

	private Candidate[] candidates;

	/** [2번] names 길이만큼 Candidate 배열을 만들고, 각 칸에 new Candidate(names[i]) 를 넣는다 */
	public Ballot(String[] names) {
		candidates = new Candidate[names.length];       // new ① 빈 칸(null) 만들기
		for (int i = 0; i < names.length; i++)
			candidates[i] = new Candidate(names[i]);    // new ② 칸마다 객체 넣기
	}

	/** 후보 수 (완성) */
	public int size() {
		return candidates.length;
	}

	/** i 번 후보, 범위 밖이면 null (완성) */
	public Candidate get(int i) {
		if (i >= 0 && i < candidates.length)
			return candidates[i];
		return null;
	}

	/** [3번] v 번 후보에게 한 표. 0 <= v < 후보 수 이면 반영하고 true, 아니면 아무것도 안 하고 false */
	public boolean vote(int v) {
		if (v >= 0 && v < candidates.length) {         // 범위 검사 두 개 다
			candidates[v].addVote();                   // 표 세기는 Candidate 에게 맡김
			return true;
		}
		return false;
	}

	/** [4번] 전체 표 수 */
	public int total() {
		int sum = 0;
		for (Candidate c : candidates)
			sum += c.getVotes();
		return sum;
	}

	/** [5번] 최다 득표 후보의 번호. 동점이면 번호가 작은 후보. 아직 0표면 -1 */
	public int winner() {
		if (total() == 0)
			return -1;
		int best = 0;
		for (int i = 1; i < candidates.length; i++)
			if (candidates[i].getVotes() > candidates[best].getVotes())   // > 라서 동점이면 앞 번호 유지
				best = i;
		return best;
	}

	/** [6번] 정확히 "후보 0번(Kim)이 3표를 득표하였습니다." 형식 */
	public String report(int i) {
		return "후보 " + i + "번(" + candidates[i].getName() + ")이 " + candidates[i].getVotes() + "표를 득표하였습니다.";
	}
}
