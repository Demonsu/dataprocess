package user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Recom {


	// TODO Auto-generated method stub
	public static void main(String[] args) {
		try{
	    PrintWriter writer=null;
			try {
				writer = new PrintWriter("recom.csv", "UTF-8");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		File file = new File("recommend");
	    BufferedReader reader = null;
	    reader = new BufferedReader(new FileReader(file));
	    String line = null;
	    int lineindex=0;
	    try {
			while ((line = reader.readLine()) != null) {
				if ((lineindex++)%10000==0)
				{
					float x=lineindex;
					System.out.println(x*100/1100000+"%");
				}
				line=line.replace(']', ' ');
				String[] attrs=line.split("\\[");
				String user=attrs[0].trim();
				String[] songs=attrs[1].split(",");
				for (String song : songs)
				{
					writer.print(user+","+song.split(":")[0].trim()+","+song.split(":")[1].trim()+"\n");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.close();
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


}
