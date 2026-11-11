package model;

/** 분식집 키오스크 주문판 = MenuItem 객체 배열 (P1 Ballot 와 같은 역할) */
public class OrderBoard {

	private MenuItem[] items;

	/** [1번] names·prices 길이만큼 MenuItem 배열을 만들고 i 번 칸에 new MenuItem(names[i], prices[i]) */
	public OrderBoard(String[] names, int[] prices) {
		items = new MenuItem[names.length];
		for (int i = 0; i < names.length; i++)
			items[i] = new MenuItem(names[i], prices[i]);
	}

	/** 메뉴 수 (완성) */
	public int size() {
		return items.length;
	}

	/** i 번 메뉴, 범위 밖이면 null (완성) */
	public MenuItem get(int i) {
		if (i >= 0 && i < items.length)
			return items[i];
		return null;
	}

	/** [2번] idx 번 메뉴를 qty 개 주문. idx 가 범위 안이고 qty >= 1 이면 반영하고 true, 아니면 false */
	public boolean order(int idx, int qty) {
		if (idx >= 0 && idx < items.length && qty >= 1) {
			items[idx].addOrder(qty);
			return true;
		}
		return false;
	}

	/** [3번] 총 매출 = 모든 메뉴의 (가격 × 팔린 개수) 합 */
	public int totalSales() {
		int sum = 0;
		for (MenuItem m : items)
			sum += m.getPrice() * m.getCount();
		return sum;
	}

	/** [4번] 가장 많이 팔린 메뉴 번호. 동점이면 번호가 작은 메뉴. 하나도 안 팔렸으면 -1 */
	public int bestSeller() {
		int best = 0;
		for (int i = 1; i < items.length; i++)
			if (items[i].getCount() > items[best].getCount())
				best = i;
		if (items[best].getCount() == 0)   // 1등이 0개면 아무것도 안 팔린 것
			return -1;
		return best;
	}

	/** [5번] 정확히 "김밥 x3 = 9000원" 형식 (x 앞 공백 1칸, = 앞뒤 공백, 원은 붙여서) */
	public String line(int i) {
		MenuItem m = items[i];
		return m.getName() + " x" + m.getCount() + " = " + (m.getPrice() * m.getCount()) + "원";
	}
}
