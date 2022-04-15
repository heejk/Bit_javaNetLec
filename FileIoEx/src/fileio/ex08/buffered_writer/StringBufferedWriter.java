package fileio.ex08.buffered_writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class StringBufferedWriter {

	public static void main(String[] args) throws IOException {
		Writer out = new FileWriter("String.txt");
		BufferedWriter bOut = new BufferedWriter(out);
		Scanner s = new Scanner(System.in);
		
		while (true) {
			System.out.print("input >> ");
			String str = s.next();
			
			if (str.equals("exit"))
				break;
			
			bOut.write(str);
			bOut.newLine();
		}
		
		bOut.close();
		s.close();
		
		System.out.println("End.");
	}

}
