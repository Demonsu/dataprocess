package user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TrieTree {  
    final int MAX_SIZE=75;  
    public class TrieTreeNode {       
        int nCount;//璁板綍璇ュ瓧绗﹀嚭鐜版鏁�  
        int id;
        char ch; //璁板綍璇ュ瓧绗�  
        TrieTreeNode[] child;  
          
        public TrieTreeNode() {  
            nCount=1;  
            child=new TrieTreeNode[MAX_SIZE];  
        }  
    }
    //瀛楀吀鏍戠殑鎻掑叆鍜屾瀯寤�  
    public void createTrie(TrieTreeNode node,String str){  
        if (str==null||str.length()==0) {  
            return;  
        }  
        str=str.trim();
        char[] letters=str.toCharArray();  
        for (int i = 0; i < letters.length; i++) {  
            int pos = letters[i] - '0';    
            //System.out.println(pos);
            if (node.child[pos] == null) {    
                node.child[pos] = new TrieTreeNode();     
            }else {  
                node.child[pos].nCount++;  
            }  
            node.ch=letters[i];              
            node = node.child[pos];              
        }  
    }  
    public void addId(TrieTreeNode node,String str, int id){  
        if (str==null||str.length()==0) {  
            return;  
        }  
        str=str.trim();
        char[] letters=str.toCharArray();  
        for (int i = 0; i < letters.length; i++) {  
            int pos = letters[i] - '0';  
            if (pos<0)
            	return;
            //System.out.println(pos);
            if (node.child[pos] == null) {    
                node.child[pos] = new TrieTreeNode();
                node.child[pos].id=id;
                node.id=id;
            }else {  
                node.child[pos].nCount++;  
                node.child[pos].id=id;
            }  
            node.ch=letters[i];    
            node.id=id;
            node = node.child[pos];              
        }  
        //System.out.println("add: "+str+"->"+id);
    }
    //瀛楀吀鏍戠殑鏌ユ壘  
    public int findCount(TrieTreeNode node,String str){  
        if (str==null||str.length()==0) {  
            return -1;  
        }  
        str=str.trim();
        char[] letters=str.toCharArray();  
        for (int i = 0; i < letters.length; i++) {  
            int pos = letters[i] - '0';    
            if (node.child[pos] == null) {    
                return 0;     
            }else {  
                node=node.child[pos];  
            }             
        }  
        return node.nCount;  
    } 
    public int findId(TrieTreeNode node,String str){  
    	//System.out.println("find: "+str);
        if (str==null||str.length()==0) {  
            return -1;  
        }  
        str=str.trim();
        char[] letters=str.toCharArray();  
        for (int i = 0; i < letters.length; i++) {  
            int pos = letters[i] - '0';    
            if (pos<0)
            {
            	System.out.println(letters[i]);
            	return -1;
            }
            if (node.child[pos] == null) {    
                return 0;     
            }else {  
                node=node.child[pos];  
            }             
        }  
        return node.id;  
    }  
    
	@SuppressWarnings("resource")
	public static void main(String[] args) {
        TrieTree tree = new TrieTree();  
        TrieTreeNode root=tree.new TrieTreeNode();  
        
	    
	    PrintWriter writer=null;
		try {
			writer = new PrintWriter("user.csv", "UTF-8");
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
			String userid="",profile="";
			int cur=1000;
			int lineindex=0;
            // 一次读入一行，直到读入null为文件结束
            while ((line = reader.readLine()) != null) {
				if ((lineindex++)%10000==0)
				{
					float x=lineindex;
					System.out.println(x/70000000+"%");
				}
				if(line.contains("review/userId:"))
				{
					userid=line.split(":", 2)[1];
				}else if (line.contains("review/profileName:"))
				{
					profile=line.split(":", 2)[1];
					if (tree.findCount(root, userid)<=0)
					{
						tree.createTrie(root, userid);
						writer.write((cur++)+","+userid+","+profile+"\n");
					}
				}
            }
		} catch (IOException e) {
			System.out.println(e);
		}


        writer.close();
	}
          
}  

