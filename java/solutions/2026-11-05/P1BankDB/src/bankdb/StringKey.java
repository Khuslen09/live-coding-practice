package bankdb;

public class StringKey implements Key {   // 원본은 implements Key 가 빠져 있었음

	private String key;

	public StringKey(String s) {
		key = s;
	}

	public boolean equals(Key k) {
		if (k instanceof StringKey)
			return key.equals(((StringKey) k).getStringKey());   // == 아님! 내용 비교
		return false;
	}

	public String getStringKey() {
		return key;
	}
}
