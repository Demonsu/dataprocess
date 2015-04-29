package user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import user.TrieTree.TrieTreeNode;

public class CountSong {


	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
        TrieTree tree = new TrieTree();  
        TrieTreeNode root=tree.new TrieTreeNode();  
    	    
		File file = new File("part_review.csv");
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(file));
		int count=0;
		int lineindex=0;
        // 一次读入一行，直到读入null为文件结束
		String line="";
		String[] tokens;
        while ((line = reader.readLine()) != null) {
        	if (lineindex++ % 1000000==0)
        		System.out.println("processed:"+lineindex/1000000+"million lines");
        	tokens=line.split(",",3);
			if (tree.findCount(root, tokens[1].trim())<=0)
			{
	        	count++;
				tree.createTrie(root, tokens[1].trim());
			}

        }
        System.out.println(count);
	
	}
}
