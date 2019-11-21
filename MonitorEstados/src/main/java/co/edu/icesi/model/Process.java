package co.edu.icesi.model;

public class Process {

	
	private String id;
	private String processName;
	private double cpu;
	private int virtualMemorySize;
	
	public Process() {
		
	}

	public Process(String id, String processName, double cpu, int virtualMemorySize) {
		super();
		this.id = id;
		this.processName = processName;
		this.cpu = cpu;
		this.virtualMemorySize = virtualMemorySize;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the processName
	 */
	public String getProcessName() {
		return processName;
	}

	/**
	 * @param processName the processName to set
	 */
	public void setProcessName(String processName) {
		this.processName = processName;
	}

	/**
	 * @return the cpu
	 */
	public double getCpu() {
		return cpu;
	}

	/**
	 * @param cpu the cpu to set
	 */
	public void setCpu(double cpu) {
		this.cpu = cpu;
	}

	/**
	 * @return the virtualMemorySize
	 */
	public int getVirtualMemorySize() {
		return virtualMemorySize;
	}

	/**
	 * @param virtualMemorySize the virtualMemorySize to set
	 */
	public void setVirtualMemorySize(int virtualMemorySize) {
		this.virtualMemorySize = virtualMemorySize;
	}
	
	
	
}
