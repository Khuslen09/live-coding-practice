package tester;

import java.util.function.BooleanSupplier;
import model.*;
import controller.*;

/** Day 10 Java P1 — 투표 집계(객체 배열) 채점기 (20항목) */
public class VoteTester {

	static int pass = 0, total = 0;

	static void check(String name, BooleanSupplier test) {
		total++;
		String result;
		try {
			if (test.getAsBoolean()) { pass++; result = "[OK]   "; }
			else result = "[FAIL] ";
		} catch (Throwable e) {
			result = "[FAIL] (" + e.getClass().getSimpleName() + ") ";
		}
		System.out.println(result + name);
	}

	static Ballot four() {
		return new Ballot(new String[] { "Kim", "Lee", "Park", "Choi" });
	}

	public static void main(String[] args) {
		// --- 1. Candidate.addVote
		check("1-1 addVote: 0 → 1", () -> {
			Candidate c = new Candidate("Kim");
			c.addVote();
			return c.getVotes() == 1;
		});
		check("1-2 addVote 3번 → 3", () -> {
			Candidate c = new Candidate("Lee");
			c.addVote(); c.addVote(); c.addVote();
			return c.getVotes() == 3;
		});

		// --- 2. Ballot 생성자 (객체 배열)
		check("2-1 생성자: 후보 4명 → size() == 4", () -> four().size() == 4);
		check("2-2 생성자: 각 칸이 null 이 아니고 이름이 순서대로", () -> {
			Ballot b = four();
			return b.get(0).getName().equals("Kim") && b.get(3).getName().equals("Choi");
		});
		check("2-3 생성자: 처음엔 모두 0표 / 후보 2명짜리도 됨", () -> {
			Ballot b = new Ballot(new String[] { "A", "B" });
			return b.size() == 2 && b.get(0).getVotes() == 0 && b.get(1).getVotes() == 0;
		});

		// --- 3. vote
		check("3-1 vote(1): true, Lee 1표", () -> {
			Ballot b = four();
			return b.vote(1) && b.get(1).getVotes() == 1;
		});
		check("3-2 vote(0), vote(0), vote(3): Kim 2표, Choi 1표", () -> {
			Ballot b = four();
			b.vote(0); b.vote(0); b.vote(3);
			return b.get(0).getVotes() == 2 && b.get(3).getVotes() == 1;
		});
		check("3-3 vote(4): 범위 밖 → false, 아무도 표 안 늘어남", () -> {
			Ballot b = four();
			boolean r = b.vote(4);
			int sum = 0;
			for (int i = 0; i < 4; i++) sum += b.get(i).getVotes();
			return !r && sum == 0;
		});
		check("3-4 vote(-1): false (예외 없이)", () -> !four().vote(-1));

		// --- 4. total
		check("4-1 total: 처음엔 0", () -> four().total() == 0);
		check("4-2 total: 5번 투표 → 5 (잘못된 표는 빼고)", () -> {
			Ballot b = four();
			b.vote(0); b.vote(1); b.vote(1); b.vote(9); b.vote(2); b.vote(3);
			return b.total() == 5;
		});

		// --- 5. winner
		check("5-1 winner: 0표면 -1", () -> four().winner() == -1);
		check("5-2 winner: Park 3표로 최다 → 2", () -> {
			Ballot b = four();
			b.vote(2); b.vote(0); b.vote(2); b.vote(2); b.vote(1);
			return b.winner() == 2;
		});
		check("5-3 winner: Lee·Choi 2표 동점 → 번호 작은 1", () -> {
			Ballot b = four();
			b.vote(3); b.vote(1); b.vote(3); b.vote(1); b.vote(0);
			return b.winner() == 1;
		});

		// --- 6. report
		check("6-1 report(0): \"후보 0번(Kim)이 0표를 득표하였습니다.\"", () ->
				four().report(0).equals("후보 0번(Kim)이 0표를 득표하였습니다."));
		check("6-2 report(2): \"후보 2번(Park)이 3표를 득표하였습니다.\"", () -> {
			Ballot b = four();
			b.vote(2); b.vote(2); b.vote(2);
			return b.report(2).equals("후보 2번(Park)이 3표를 득표하였습니다.");
		});

		// --- 7. VoteController.run
		check("7-1 run({0,1,1,2}): 4표 반영 → 4", () -> {
			Ballot b = four();
			int n = new VoteController(b).run(new int[] { 0, 1, 1, 2 });
			return n == 4 && b.get(1).getVotes() == 2 && b.total() == 4;
		});
		check("7-2 run({3,3,7,0,0}): 7 에서 멈춤 → 2, Kim 0표", () -> {
			Ballot b = four();
			int n = new VoteController(b).run(new int[] { 3, 3, 7, 0, 0 });
			return n == 2 && b.get(3).getVotes() == 2 && b.get(0).getVotes() == 0;
		});
		check("7-3 run({}): 빈 배열 → 0 / run({-1, 2}) → 0", () -> {
			Ballot b = four();
			VoteController vc = new VoteController(b);
			return vc.run(new int[] {}) == 0 && vc.run(new int[] { -1, 2 }) == 0 && b.total() == 0;
		});
		check("7-4 run 후 results()[1]: \"후보 1번(Lee)이 2표를 득표하였습니다.\"", () -> {
			Ballot b = four();
			VoteController vc = new VoteController(b);
			vc.run(new int[] { 1, 0, 1, 5 });
			String[] r = vc.results();
			return r.length == 4 && r[1].equals("후보 1번(Lee)이 2표를 득표하였습니다.");
		});

		System.out.println("Score: " + pass + " / " + total);
	}
}
