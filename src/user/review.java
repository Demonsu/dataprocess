package user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import user.TrieTree.TrieTreeNode;

public class review {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		// TODO Auto-generated method stub
        TrieTree songTree = new TrieTree();  
        TrieTreeNode songTreeRoot=songTree.new TrieTreeNode();  
        
        TrieTree userTree = new TrieTree();  
        TrieTreeNode userTreeRoot=userTree.new TrieTreeNode();  
        
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
    	    
    		try {
    			File file = new File("song.csv");
    	        BufferedReader reader = null;
                reader = new BufferedReader(new FileReader(file));
                String line = null;
                while ((line = reader.readLine()) != null) {
                	String[] attrs=line.split(",");
                	songTree.addId(songTreeRoot, attrs[1], Integer.parseInt(attrs[0]));
                }
                
                file = new File("user.csv");
                reader = new BufferedReader(new FileReader(file));
                while ((line = reader.readLine()) != null) {
                	String[] attrs=line.split(",");
                	userTree.addId(userTreeRoot, attrs[1], Integer.parseInt(attrs[0]));
                }                
                
                file = new File("xf.txt");
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
    				}else if (line.contains("review/score:"))
    				{
    					rating=line.split(":", 2)[1];
    					//System.out.println(user+" "+song+" "+rating);
    					writer.print(userTree.findId(userTreeRoot, user)+","+songTree.findId(songTreeRoot, song)+","+rating+"\n");
    				}
                }
                
    		} catch (IOException e) {
    			System.out.println(e);
    		}
    		

            writer.close();
    	

	}

}
