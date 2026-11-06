package controller;

import model.Parcel;

/** CardDeck 역할: 상자를 들어온 순서대로 하나씩 내준다 */
public class ParcelQueue {
	private Parcel[] parcels;
	private int next = 0;

	public ParcelQueue(Parcel[] p) {
		parcels = p;
	}

	public boolean hasMore() {
		return next < parcels.length;
	}

	public Parcel nextParcel() {
		return parcels[next++];
	}

	public int remaining() {
		return parcels.length - next;
	}
}
