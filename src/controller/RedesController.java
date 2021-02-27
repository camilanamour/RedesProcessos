package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {
	
	public RedesController(){
		super();
	}
	
	public String sistemaOperacional(){
		String os = System.getProperty("os.name");
		return os;
	}
	
	public String ip(String os){
		String mostra = "";
		String processo = "";
		
		if(os.contains("Windows")){
			processo = "ipconfig";
		}
		if(os.contains("Linux")){
			processo = "ifconfig";
		}
		try {
			Process p = Runtime.getRuntime().exec(processo); 
				
			InputStream fluxo = p.getInputStream();	
			InputStreamReader leitor = new InputStreamReader(fluxo); 	
			BufferedReader buffer = new BufferedReader(leitor);
			StringBuffer b = new StringBuffer();
							
			String linha = buffer.readLine();
			String nome = "";
			while(linha != null){
				if(os.contains("Windows")){
					if(linha.contains("Adaptador")){
						nome = linha;
					}
					if(linha.contains("IPv4")){
						String[] vetIP = linha.split(": ");
						b.append(nome);
						b.append(" ");
						b.append(vetIP[1]);
						b.append("\n");
					}
				}
				if(os.contains("Linux")){
					if(linha.contains("flags")){
						String[] vetNome = linha.split(":");
						nome = vetNome[0];
					}
					if(linha.contains("inet ")){
						linha = linha.trim();
						String[] vetIPL = linha.split(" ");
						b.append(nome);
						b.append(": ");
						b.append(vetIPL[1]);
						b.append("\n");
					}
				}
					linha = buffer.readLine();
				}

				buffer.close();
				leitor.close();
				fluxo.close();
				mostra = b.toString();	
				
			} catch (IOException e) {
				String msgErro = e.getMessage();
				System.err.println(msgErro);
			}	
		return mostra;
	}
	
	
	public String ping(String os){
		String mostra = "";
		String processo = "";
		
		if(os.contains("Windows")){
			processo = "ping -4 -n 10 www.uol.com.br";
		}
		if(os.contains("Linux")){
			processo = "ping -4 -c 10 www.uol.com.br";
		}
		try {
			Process p = Runtime.getRuntime().exec(processo); 
				
			InputStream fluxo = p.getInputStream();	
			InputStreamReader leitor = new InputStreamReader(fluxo); 	
			BufferedReader buffer = new BufferedReader(leitor);
			StringBuffer b = new StringBuffer();
							
			String linha = buffer.readLine();
			while(linha != null){
				if(os.contains("Windows")){
					if(linha.contains("dia")){
						String[] vetPing = linha.split(", ");
						String[] vetMedia = vetPing[2].split("=");
						b.append("M�dia:");
						b.append(vetMedia[1]);
					}
									}
				if(os.contains("Linux")){
					if(linha.contains("avg")){
						String[] vetPing = linha.split("=");
						String[] vetMedia = vetPing[1].split("/");
						b.append("Media: ");
						b.append(vetMedia[1]);
						b.append(" ms");
					}
					
				}
				linha = buffer.readLine();
			}
			
				buffer.close();
				leitor.close();
				fluxo.close();
				mostra = b.toString();	
				
			} catch (IOException e) {
				String msgErro = e.getMessage();
				System.err.println(msgErro);
			}	
	return mostra;
	}
}
