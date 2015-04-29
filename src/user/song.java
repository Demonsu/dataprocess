package user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import user.TrieTree.TrieTreeNode;

public class song {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        TrieTree tree = new TrieTree();  
        TrieTreeNode root=tree.new TrieTreeNode();  
        PrintWriter writer=null;
    		try {
    			writer = new PrintWriter("song.csv", "UTF-8");
    		} catch (FileNotFoundException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		} catch (UnsupportedEncodingException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    	    
    		try {
    			File file = new File("xf.txt");
    	        BufferedReader reader = null;
                reader = new BufferedReader(new FileReader(file));
                String line = null;
    			String productId="",title="";
    			int cur=3000;
    			int lineindex=0;
                // 一次读入一行，直到读入null为文件结束
                while ((line = reader.readLine()) != null) {
    				if ((lineindex++)%10000==0)
    				{
    					float x=lineindex;
    					System.out.println(x*100/70000000+"%");
    				}
    				if(line.contains("product/productId:"))
    				{
    					productId=line.split(":", 2)[1];
    				}else if (line.contains("product/title:"))
    				{
    					title=line.split(":", 2)[1];
    					if (tree.findCount(root, productId)<=0)
    					{
    						tree.createTrie(root, productId);
    						writer.write((cur++)+","+productId+","+title+"\n");
    					}
    				}
                }
    		} catch (IOException e) {
    			System.out.println(e);
    		}


            writer.close();
    	}
	}

