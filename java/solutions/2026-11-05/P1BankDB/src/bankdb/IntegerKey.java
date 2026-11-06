package bankdb;

public class IntegerKey implements Key {

	private int key;

	public IntegerKey(int i) {
		key = i;
	}

	/** 원본은 무조건 (IntegerKey)k 캐스트 → StringKey가 섞이면 ClassCastException.
	 *  instanceof 로 먼저 확인 (null instanceof X 는 항상 false 라서 null 도 안전) */
	public boolean equals(Key k) {
		if (k instanceof IntegerKey)
			return key == ((IntegerKey) k).getIntegerKey();
		return false;
	}

	public int getIntegerKey() {
		return key;
	}
}
