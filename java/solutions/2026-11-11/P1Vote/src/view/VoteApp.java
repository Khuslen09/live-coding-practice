package view;

import java.util.Scanner;
import model.*;
import controller.*;

/** 콘솔 실행용 (완성) — 채점은 tester.VoteTester */
public class VoteApp {

	public static void main(String[] args) {
		Ballot ballot = new Ballot(new String[] { "Kim", "Lee", "Park", "Choi" });
		VoteController controller = new VoteController(ballot);
		Scanner in = new Scanner(System.in);
		System.out.println("원하는 후보에 투표하세요: 0, 1, 2, 3 (그 밖의 수를 넣으면 끝)");
		boolean processing = true;
		while (processing && in.hasNextInt())
			processing = ballot.vote(in.nextInt());
		in.close();
		for (String line : controller.results())
			System.out.println(line);
		int w = ballot.winner();
		if (w == -1)
			System.out.println("투표가 없습니다.");
		else
			System.out.println("당선: " + ballot.get(w).getName());
	}
}
