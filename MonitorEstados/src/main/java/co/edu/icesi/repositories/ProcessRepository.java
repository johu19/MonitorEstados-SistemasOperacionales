package co.edu.icesi.repositories;

import java.util.ArrayList;
import java.util.Hashtable;

import org.springframework.stereotype.Repository;

import co.edu.icesi.model.Process;

@Repository
public class ProcessRepository {
	
	private Hashtable<String, Process> processes;
	
	public ProcessRepository() {
		processes=new Hashtable<String, Process>();
	}
	
	
	public void addProcess(Process p) {
		processes.put(p.getId(), p);
	}
	
	
	public Process getProcess(String id) {
		return processes.get(id);
	}
	
	
	public Iterable<Process> getAllProcess(){
		return processes.values();
	}
	
	

	
}
