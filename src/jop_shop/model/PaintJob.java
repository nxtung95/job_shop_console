package jop_shop.model;

import java.sql.Date;

public class PaintJob extends Job {
	private String color;
	private int volume;

	public PaintJob(int jobNumber, Date completedDate, Date commencedDate, int assemblyId, int processId, int laborTime) {
		super(jobNumber, completedDate, commencedDate, assemblyId, processId, laborTime);
	}

	public PaintJob(int jobNumber, Date completedDate, Date commencedDate, int assemblyId, int processId, int laborTime, String color, int volume) {
		super(jobNumber, completedDate, commencedDate, assemblyId, processId, laborTime);
		this.color = color;
		this.volume = volume;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}
}
