package user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class MusicReview {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
        PrintWriter writer=null;
		try {
			writer = new PrintWriter("review.csv", "UTF-8");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 File file = new File("xf.txt");
		 BufferedReader reader = null;
		 String line="";
         reader = new BufferedReader(new FileReader(file));
         int lineindex=0;
		 String user="",song="",rating="";
         while ((line = reader.readLine()) != null) {
				if ((lineindex++)%10000==0)
				{
					float x=lineindex;
					System.out.println(x*100/70000000+"%");
				}
				if(line.contains("product/productId:"))
				{
					song=line.split(":", 2)[1];
				}else if (line.contains("review/userId:"))
				{
					user=line.split(":", 2)[1];
				}else if (line.contains("review/text:"))
				{
					rating=line.split(":", 2)[1];
					writer.print(user.trim()+","+song.trim()+","+rating+"\n");
					//System.out.println(user+" "+song+" "+rating);
					//writer.print(userTree.findId(userTreeRoot, user)+","+songTree.findId(songTreeRoot, song)+","+rating+"\n");
				}
         }
         
		

     writer.close();
	}

}
