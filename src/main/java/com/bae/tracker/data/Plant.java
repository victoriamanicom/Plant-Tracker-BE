package com.bae.tracker.data;

public class Plant {

	private int id;
	private String name;
	private int potSize;
	private String leafColour;
	private boolean isSucculent;
	private String imgUrl;

	public Plant() {
		super();
	}

	public Plant(String name, int potSize, String leafColour, boolean isSucculent, String imgUrl) {
		super();
		this.name = name;
		this.potSize = potSize;
		this.leafColour = leafColour;
		this.isSucculent = isSucculent;
		this.imgUrl = imgUrl;
	}

	public Plant(int id, String name, int potSize, String leafColour, boolean isSucculent, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.potSize = potSize;
		this.leafColour = leafColour;
		this.isSucculent = isSucculent;
		this.imgUrl = imgUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPotSize() {
		return potSize;
	}

	public void setPotSize(int potSize) {
		this.potSize = potSize;
	}

	public String getLeafColour() {
		return leafColour;
	}

	public void setLeafColour(String leafColour) {
		this.leafColour = leafColour;
	}

	public boolean isSucculent() {
		return isSucculent;
	}

	public void setSucculent(boolean isSucculent) {
		this.isSucculent = isSucculent;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Override
	public String toString() {
		return "Plant [name=" + name + ", potSize=" + potSize + ", leafColour=" + leafColour + ", isSucculent="
				+ isSucculent + "]";
	}

}
