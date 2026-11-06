package bankdb;

public class Database {

	private Record[] base;
	public static final int NOT_FOUND = -1;

	public Database(int initial_size) {
		if (initial_size > 0)
			base = new Record[initial_size];
		else
			base = new Record[1];
	}

	public int capacity() {
		return base.length;
	}

	/** 같은 키의 인덱스, 없으면 NOT_FOUND. 빈 칸(null)은 건너뛴다. */
	public int findLocation(Key k) {
		for (int i = 0; i < base.length; i++)
			if (base[i] != null && base[i].getKey().equals(k))
				return i;
		return NOT_FOUND;
	}

	/** 첫 빈 칸 인덱스, 없으면 NOT_FOUND */
	public int findEmpty() {
		for (int i = 0; i < base.length; i++)
			if (base[i] == null)
				return i;
		return NOT_FOUND;
	}

	public Record find(Key k) {
		int index = findLocation(k);
		if (index == NOT_FOUND)
			return null;
		return base[index];
	}

	public boolean delete(Key k) {
		int index = findLocation(k);
		if (index == NOT_FOUND)
			return false;
		base[index] = null;
		return true;
	}

	public boolean insert(Record r) {
		if (findLocation(r.getKey()) != NOT_FOUND)   // 같은 키가 이미 있음
			return false;
		int index = findEmpty();
		if (index != NOT_FOUND)
			base[index] = r;
		else {                                        // 꽉 참 → 2배로 늘려 복사
			Record[] temp = new Record[base.length * 2];
			for (int i = 0; i < base.length; i++)
				temp[i] = base[i];
			temp[base.length] = r;
			base = temp;
		}
		return true;
	}
}
