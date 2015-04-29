package user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class AlterCSV {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
        PrintWriter writer=null;
    		try {
    			writer = new PrintWriter("user_fit.csv", "UTF-8");
    		} catch (FileNotFoundException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		} catch (UnsupportedEncodingException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
		File file = new File("D:/user.csv");
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
				String[] attrs=line.split(",");
				writer.print(attrs[0]+","+attrs[0]+","+attrs[2]+","+attrs[1]+"\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.close();
	
	}
	catch(Exception e)
	{
		
	}

}
}
