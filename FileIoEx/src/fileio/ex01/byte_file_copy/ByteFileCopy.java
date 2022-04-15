package fileio.ex01.byte_file_copy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/*
 파일 입출력 / 네트워크 통신 3단계
 1. open (스트림 생성) 
 2. read / write (읽기/저장하기)
 3. close (스트림 닫기)
*/

public class ByteFileCopy {
	public static void main(String[] args) throws IOException {
		// 1단계 stream open 
		InputStream in = new FileInputStream("putty.exe");
		OutputStream out = new FileOutputStream("aatty.exe");
		int copyByte = 0; // 얼마나 읽었는가 ?
		int byteData = 0; // 1byte 저장할 저장소
		
		/*
		함수가 읽을 데이터가 없을 때 -1 반환함 
		byte는 0 ~ 255까지밖에 표현할 수 없음
		>> -1 저장하려면 int 사용
		*/ 
		
		// 시간 측정을 위해 계산해보기 
		long sTime = System.currentTimeMillis();
		System.out.println("copy file start!");
	
		// 2단계 stream read / write
		while (true) { // 파일 복사하기
			byteData = in.read();
			
			if(byteData == -1)
				break;
			
			out.write(byteData);
			copyByte++;
		}
		
		System.out.println("copy file end!");
		
		long eTime = System.currentTimeMillis();
		
		// 3단계 stream close
		in.close();
		out.close();
		
		System.out.println("copy Bytes: " + copyByte);
		System.out.println("copy Times: " + (eTime - sTime));
		
	}
}
