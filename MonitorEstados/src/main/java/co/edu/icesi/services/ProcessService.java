package co.edu.icesi.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

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
		// Stop-Process -Id 7664
		try {
			//Getting the version
			  String command = "powershell.exe  $PSVersionTable.PSVersion";
			  // Executing the command
			  java.lang.Process powerShellProcess = Runtime.getRuntime().exec(command);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

	public void stopWindowsProcess(String id) {
		// TODO
	}

}
