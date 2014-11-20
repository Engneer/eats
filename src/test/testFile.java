package test;
import  java.io.*;
public class testFile {
	public static void main(String[] arg){
		File file=new File("text.txt");
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
