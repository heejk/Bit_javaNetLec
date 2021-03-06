package net.ex02.echo_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 클라이언트는 서버보다 훨씬 단순
 
 1) 서버의 주소(ip, port)를 가지고 소켓 생성 >> 생성자에서 connect 진행 
 2) 객체 생성 > 연결됨 > 통신 가능 
 3) 서버가 Echo 서버이므로 클라이언트가 보낸 데이터를 그대로 돌려줌
 4) close() 호출 > 연결된 스트림 해제 
 */

public class EchoClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// 서버에 접속한 소켓 생성 >> 127.0.0.1: loop address 외부망으로 나가지 말고 자신의 Host내에서 통신
		Socket clientSocket = new Socket("127.0.0.1", 9000);
		
		// 소켓 생성자에서 연결 스트림 생성 > 통신 가능 >> 서버에 전송할 문자열 입력받기 위해 입력 객체 생성
		InputStreamReader ink = new InputStreamReader(System.in);
		BufferedReader keyboard = new BufferedReader(ink);
		
		// 소켓 객체로부터 송수신 스트림 얻기
		OutputStream out = clientSocket.getOutputStream();
		OutputStreamWriter outW = new OutputStreamWriter(out);
		PrintWriter pw = new PrintWriter(outW);
		
		InputStream in = clientSocket.getInputStream();
		InputStreamReader inR = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(inR);
		
		// 사용자가 입력한 데이터를 서버로 전송 > 서버가 echo한 데이터 수신 > 콘솔
		while (true) {
			// 사용자 입력 
			System.out.print("input >> ");
			String line = keyboard.readLine();
			
			if  (line.equals("quit")) {
				System.out.println("Client Ended !");
				break;
			}
			
			// 서버로 전송
			System.out.println("Server Sended: " + line);
			pw.println(line);
			pw.flush();
			
			// 서버의 echo 데이터 수신 
			String echo = br.readLine();
			if (echo == null) {
				System.out.println("Server Ended !");
				break;
			}
			
			System.out.println("Received Server: " + echo);
		}
		
		// 스트림 연결 종료
		pw.close();
		br.close();
		System.out.println("client - server Ended !");
		
	}

}
