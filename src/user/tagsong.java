package user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import user.TrieTree.TrieTreeNode;

public class tagsong {


	@SuppressWarnings("resource")
	public static void main(String[] args) {

        	PrintWriter writer=null;
    		try {
    			writer = new PrintWriter("songwithtag.csv", "UTF-8");
    		} catch (FileNotFoundException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		} catch (UnsupportedEncodingException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    	    Map<String,String> songTag=new HashMap<String, String>();
    	    
    		try {
    			File file = new File("KeywordsOfSongs.out");
    	        BufferedReader reader = null;
                reader = new BufferedReader(new FileReader(file));
                String line = null;
    			String songID="";
    			String tag="";
    			int lineindex=0;
    			String[] tokens=null;
                // 一次读入一行，直到读入null为文件结束
                while ((line = reader.readLine()) != null) {
    				if ((lineindex++)%10000==0)
    				{
    					float x=lineindex/10000;
    					System.out.println(x/55+"%");
    				}
    				tokens=line.split("\t");
    				songTag.put(tokens[0].trim(), tokens[1].trim());	
                }
                file =new File("song_fit.csv");
                reader = new BufferedReader(new FileReader(file));
                lineindex=0;
                while ((line = reader.readLine()) != null) {
    				if ((lineindex++)%10000==0)
    				{
    					float x=lineindex/10000;
    					System.out.println(x/55+"%");
    				}
    				tokens=line.split(",");
    				writer.write(line+",\""+songTag.get(tokens[2])+"\"\n");
                }
    		} catch (IOException e) {
    			System.out.println(e);
    		}


            writer.close();
    	}
	
}
