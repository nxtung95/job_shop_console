package jop_shop.model;

public class FitProcess extends Process {
	private String fitType;

	public FitProcess(int processId, String processData, int departmentId) {
		super(processId, processData, departmentId);
	}

	public FitProcess(int processId, String processData, int departmentId, String fitType) {
		super(processId, processData, departmentId);
		this.fitType = fitType;
	}

	public String getFitType() {
		return fitType;
	}

	public void setFitType(String fitType) {
		this.fitType = fitType;
	}
}
