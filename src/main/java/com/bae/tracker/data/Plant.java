package com.bae.tracker.data;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Plant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true)
	private String name;

	@Column
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

	@Override
	public int hashCode() {
		return Objects.hash(id, imgUrl, isSucculent, leafColour, name, potSize);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plant other = (Plant) obj;
		return id == other.id && Objects.equals(imgUrl, other.imgUrl) && isSucculent == other.isSucculent
				&& Objects.equals(leafColour, other.leafColour) && Objects.equals(name, other.name)
				&& potSize == other.potSize;
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

	public boolean getIsSucculent() {
		return isSucculent;
	}

	public void setIsSucculent(boolean isSucculent) {
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
