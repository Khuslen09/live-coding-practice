package model;

/** CardPlayerBehavior 와 같은 역할: "더 받을래?" + "하나 받기" */
public interface LoadBehavior {
	public boolean wantsMore();
	public boolean load(Parcel p);
}
