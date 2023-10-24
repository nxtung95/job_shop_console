package jop_shop.model;

public class Process {
	protected int processId;
	protected String processData;
	protected int departmentId;

	public Process(String processData, int departmentId) {
		this.processData = processData;
		this.departmentId = departmentId;
	}

	public Process() {

	}

	public int getProcessId() {
		return processId;
	}

	public void setProcessId(int processId) {
		this.processId = processId;
	}

	public String getProcessData() {
		return processData;
	}

	public void setProcessData(String processData) {
		this.processData = processData;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
}
