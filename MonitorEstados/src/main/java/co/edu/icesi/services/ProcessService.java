package co.edu.icesi.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.model.Process;
import co.edu.icesi.repositories.ProcessRepository;

@Service
public class ProcessService {

	@Autowired
	private ProcessRepository repos;

	public Iterable<Process> findLinuxProcess() {

		try {
			java.lang.Process p = Runtime.getRuntime().exec("/bin/bash -c ps -A");

			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = "";
			reader.readLine();
			while ((line = reader.readLine()) != null) {

				Process process = new Process();

				String[] data = line.split(" ");

				boolean cond = false;
				int indice = 0;
				for (int i = 0; i < data.length && !cond; i++) {
					if (!data[i].equals("")) {
						cond = true;
						process.setId(data[i].trim());
						indice = i + 10;
					}
				}

				cond = false;
				String name = new String();
				while (!cond) {
					name += data[indice] + " ";
					indice++;
					if (indice >= data.length) {
						cond = true;
					}
				}
				process.setProcessName(name);

				repos.addProcess(process);

			}

			Collection<Process> processes = repos.getAllProcess();

			int[] ids = new int[processes.size()];

			int i = 0;
			for (Process process : processes) {
				ids[i] = Integer.parseInt(process.getId());
				i++;
			}

			Arrays.sort(ids);

			ArrayList<Process> sortedProcesses = new ArrayList<Process>();

			for (int h = 0; h < ids.length; h++) {
				String currentID = ids[h] + "";
				for (Process process : processes) {
					if (process.getId().equals(currentID)) {
						sortedProcesses.add(process);
					}
				}

			}

			return sortedProcesses;

		} catch (Exception e) {

		}

		return new ArrayList<Process>();
	}

	public void stopLinuxProcess(String id) {
		String command = "kill " + id + " -c";
		repos.removeProcess(id);
		try {
			java.lang.Process p = Runtime.getRuntime().exec(command);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Iterable<Process> getAllProcesses(){
		return repos.getAllProcess();
	}
	

	public Iterable<Process> findWindowsProcess() {
		
		try {
			
			repos.setProcesses(new Hashtable<String, Process>());
			repos.getProcesses().clear();
			  
			  String command = "powershell.exe  Get-Process | select Id,ProcessName | sort -Property id | Format-Table -HideTableHeaders";
			  
			  java.lang.Process pShell = Runtime.getRuntime().exec(command);
		
			  pShell.getOutputStream().close();
			  
			  String line="";
			  
			  BufferedReader reader = new BufferedReader(new InputStreamReader(pShell.getInputStream()));

			  while ((line = reader.readLine()) != null) {
			
				 line=line.trim();

				 String[] temp=line.split(" ");

				 if(temp.length==2) {
					 Process process= new Process();
					 process.setId(temp[0]);
					 process.setProcessName(temp[1]);

					 repos.addProcess(process);
				 }		 
			   
			  }
			  reader.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		Collection<Process> processes = repos.getAllProcess();

		int[] ids = new int[processes.size()];

		int i = 0;
		for (Process process : processes) {
			ids[i] = Integer.parseInt(process.getId());
			i++;
		}

		Arrays.sort(ids);

		ArrayList<Process> sortedProcesses = new ArrayList<Process>();

		for (int h = 0; h < ids.length; h++) {
			String currentID = ids[h] + "";
			for (Process process : processes) {
				if (process.getId().equals(currentID)) {
					sortedProcesses.add(process);
				}
			}

		}

		return sortedProcesses;
	}

	public void stopWindowsProcess(String id) {
		
		System.out.println("el id del proceso a borrar es : "+id);
		String command = "powershell.exe Stop-Process -Id " +id;
		
		try {
			java.lang.Process p = Runtime.getRuntime().exec(command);
			repos.removeProcess(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
