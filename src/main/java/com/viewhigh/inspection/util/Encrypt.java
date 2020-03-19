package com.viewhigh.inspection.util;
import java.security.MessageDigest;
public class Encrypt{
	public static String Md5(String plainText ) { 
		try { 
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			md.update(plainText.getBytes()); 
			byte b[] = md.digest(); 
			int i; 
		
			StringBuffer buf = new StringBuffer(""); 
			for (int offset = 0; offset < b.length; offset++) { 
				i = b[offset]; 
				if(i<0) {i+= 256;}
				if(i<16) 
                {buf.append("0"); }
				buf.append(Integer.toHexString(i)); 
			} 
			
			return buf.toString().substring(9,24);
		} catch (Exception e) { 
			e.printStackTrace(); 
			return plainText;
		} 
	} 
}