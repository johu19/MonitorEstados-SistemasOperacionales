package co.edu.icesi.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.model.Process;
import co.edu.icesi.repositories.ProcessRepository;

@Service
public class ProcessService {
	
	@Autowired
	private ProcessRepository  repos;
	
	
	public Iterable<Process> findLinuxProcess(){
		return new ArrayList<Process>();
	}
	
	
	public void stopLinuxProcess(String id) {
		
	}
	
	
	public Iterable<Process> findWindowsProcess(){
		//TODO
		return null;
	}
	
	
	public void stopWindowsProcess(String id) {
		//TODO
	}

}
