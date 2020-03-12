package com.br.stl.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.br.stl.model.entity.Cliente;
import com.br.stl.model.entity.ClienteImagem;
import com.google.gson.Gson;

public class ClientePostTest {

	
	public static void main(String[] args) {

		inserirCliente();
		
	}
	
	private static void inserirCliente() {
//		Cliente cli = Cliente.createInstance("Luis Maini Fortes", "009337073-39", "41996448025");
//		ClienteImagem cliImagem;
//		
//		File file = new File("/temp/sap.png");
//		byte[] picInBytes = new byte[(int) file.length()];
//
//		FileInputStream fileInputStream;
//		try {
//			fileInputStream = new FileInputStream(file);
//			fileInputStream.read(picInBytes);
//			fileInputStream.close();
//			
//			cliImagem = ClienteImagem.createInstance(cli, Base64.getEncoder().encodeToString(picInBytes) );
//			
//			
//			URL url = new URL("http://localhost:8181/api/saveimg");
//			URLConnection con = url.openConnection();
//			HttpURLConnection http = (HttpURLConnection)con;
//			http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//			http.setRequestMethod("POST"); // PUT is another valid option
//			http.setDoOutput(true);
//			
//			Gson gson = new Gson();
//			byte[] out = gson.toJson(cliImagem).getBytes(StandardCharsets.UTF_8);
//			http.setFixedLengthStreamingMode(out.length);
//			http.connect();
//			
//			OutputStream os = http.getOutputStream();
//			os.write(out);
//			os.close();
//			
//			//System.out.print("Status: " + http);
//		
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
