package tester;

import java.util.function.BooleanSupplier;
import model.*;
import controller.*;

/** Day 5 ① 블랙잭 채점기 — 항목별 [OK]/[FAIL], 마지막 줄 Score */
public class BlackjackTester {

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

	/** 정해진 순서대로 카드를 내주는 덱 (CardDeck 상속 + newCard 재정의) */
	static class StackedDeck extends CardDeck {
		private int[] ranks;
		private int next = 0;
		StackedDeck(int... r) { ranks = r; }
		public Card newCard() {
			return new Card(Card.SPADES, ranks[next++]);
		}
		int used() { return next; }
	}

	/** 키보드 대신 미리 정한 횟수만큼만 카드를 원하는 사람 */
	static class ScriptedHuman extends HumanPlayer {
		private int wants;
		ScriptedHuman(int max, int w) { super(max, "test"); wants = w; }
		public boolean wantsACard() {
			if (wants > 0) { wants--; return true; }
			return false;
		}
	}

	static ComputerPlayer handOf(int... ranks) {
		ComputerPlayer p = new ComputerPlayer(11);
		for (int r : ranks) p.receiveCard(new Card(Card.HEARTS, r));
		return p;
	}

	public static void main(String[] args) {
		// --- CardPlayer.receiveCard / showCards
		check("receiveCard: 빈 손에 받으면 true", () -> new ComputerPlayer(2).receiveCard(new Card(Card.CLUBS, 5)));
		check("receiveCard: 한도(2장) 넘으면 false", () -> {
			ComputerPlayer p = new ComputerPlayer(2);
			p.receiveCard(new Card(Card.CLUBS, 5));
			p.receiveCard(new Card(Card.CLUBS, 6));
			return !p.receiveCard(new Card(Card.CLUBS, 7)) && p.showCards().length == 2;
		});
		check("showCards: 받은 장수만큼, 받은 순서대로", () -> {
			ComputerPlayer p = handOf(3, 9);
			Card[] c = p.showCards();
			return c.length == 2 && c[0].getRank() == 3 && c[1].getRank() == 9;
		});
		// --- totalScore
		check("totalScore: 숫자 카드 2+3+4 = 9", () -> handOf(2, 3, 4).totalScore() == 9);
		check("totalScore: J Q K 는 10씩 → 30", () -> handOf(11, 12, 13).totalScore() == 30);
		check("totalScore: A + K = 21 (A는 11)", () -> handOf(1, 13).totalScore() == 21);
		check("totalScore: A + 9 + 5 = 15 (A를 1로)", () -> handOf(1, 9, 5).totalScore() == 15);
		check("totalScore: A + A + 9 = 21", () -> handOf(1, 1, 9).totalScore() == 21);
		check("totalScore: 빈 손 = 0", () -> new ComputerPlayer(5).totalScore() == 0);
		// --- ComputerPlayer.wantsACard
		check("Computer.wantsACard: 16 이면 true", () -> handOf(10, 6).wantsACard());
		check("Computer.wantsACard: 17 이면 false", () -> !handOf(10, 7).wantsACard());
		// --- Dealer
		check("dealOneTo: 덱 맨 위 카드 1장 전달", () -> {
			StackedDeck d = new StackedDeck(7, 8);
			ComputerPlayer p = new ComputerPlayer(5);
			new Dealer(d).dealOneTo(p);
			return p.showCards().length == 1 && p.showCards()[0].getRank() == 7;
		});
		check("dealTo: 컴퓨터는 17 이상이 될 때까지 받기", () -> {
			StackedDeck d = new StackedDeck(5, 6, 4, 3, 10);
			ComputerPlayer p = new ComputerPlayer(11);
			new Dealer(d).dealTo(p);
			return p.totalScore() == 18 && d.used() == 4;
		});
		check("dealTo: 처음부터 원하지 않으면 한 장도 안 줌", () -> {
			StackedDeck d = new StackedDeck(5);
			ComputerPlayer p = handOf(10, 9);
			new Dealer(d).dealTo(p);
			return d.used() == 0 && p.showCards().length == 2;
		});
		check("dealTo: 손이 꽉 차면 멈춤 (무한루프 X)", () -> {
			StackedDeck d = new StackedDeck(2, 2, 2, 2, 2, 2);
			ComputerPlayer p = new ComputerPlayer(3);
			new Dealer(d).dealTo(p);
			return p.showCards().length == 3 && d.used() <= 4;
		});
		// --- BlackjackController.playRound  (카드 순서: 사람, 컴, 사람, 컴, 그다음 dealTo)
		check("playRound: 처음 두 장 21 → BLACKJACK, 칩 +2", () -> {
			ScriptedHuman h = new ScriptedHuman(11, 0);
			String r = new BlackjackController(new Dealer(new StackedDeck(1, 10, 13, 9)), h, new ComputerPlayer(11)).playRound();
			return r.equals("BLACKJACK") && h.countChips() == 2;
		});
		check("playRound: 사람이 21 초과 → LOSE, 칩 -1", () -> {
			ScriptedHuman h = new ScriptedHuman(11, 1);
			String r = new BlackjackController(new Dealer(new StackedDeck(10, 10, 6, 7, 9)), h, new ComputerPlayer(11)).playRound();
			return r.equals("LOSE") && h.countChips() == -1;
		});
		check("playRound: 컴퓨터가 21 초과 → WIN", () -> {
			ScriptedHuman h = new ScriptedHuman(11, 0);
			String r = new BlackjackController(new Dealer(new StackedDeck(10, 10, 8, 6, 9)), h, new ComputerPlayer(11)).playRound();
			return r.equals("WIN") && h.countChips() == 1;
		});
		check("playRound: 점수 같음 → DRAW, 칩 그대로", () -> {
			ScriptedHuman h = new ScriptedHuman(11, 0);
			String r = new BlackjackController(new Dealer(new StackedDeck(10, 10, 8, 8)), h, new ComputerPlayer(11)).playRound();
			return r.equals("DRAW") && h.countChips() == 0;
		});
		check("playRound: 사람 19 vs 컴 20 → LOSE", () -> {
			ScriptedHuman h = new ScriptedHuman(11, 1);
			String r = new BlackjackController(new Dealer(new StackedDeck(5, 10, 4, 10, 10)), h, new ComputerPlayer(11)).playRound();
			return r.equals("LOSE") && h.countChips() == -1;
		});

		System.out.println("Score: " + pass + " / " + total);
	}
}
