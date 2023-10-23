package jop_shop.model;

public class CutProcess extends Process {
	private String cuttingType;
	private String machineType;

	public CutProcess(int processId, String processData, int departmentId) {
		super(processId, processData, departmentId);
	}

	public CutProcess(int processId, String processData, int departmentId, String cuttingType, String machineType) {
		super(processId, processData, departmentId);
		this.cuttingType = cuttingType;
		this.machineType = machineType;
	}

	public String getCuttingType() {
		return cuttingType;
	}

	public void setCuttingType(String cuttingType) {
		this.cuttingType = cuttingType;
	}

	public String getMachineType() {
		return machineType;
	}

	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}
}
