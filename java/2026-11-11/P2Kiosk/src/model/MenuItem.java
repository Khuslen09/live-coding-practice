package model;

/** 메뉴 하나 (이름, 가격, 팔린 개수) — 완성 */
public class MenuItem {

	private String name;
	private int price;
	private int count;

	public MenuItem(String name, int price) {
		this.name = name;
		this.price = price;
		count = 0;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public int getCount() {
		return count;
	}

	public void addOrder(int qty) {
		count += qty;
	}
}
