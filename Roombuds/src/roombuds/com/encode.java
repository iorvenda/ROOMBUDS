package roombuds.com;

import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

public class encode {

	public static void main(String[] args) {
	  String a = getHash("password".getBytes());
		
		System.out.println(a);
	} 
		public static String getHash(byte[]inputBytes)
		{
			String hashValue = null;
			String algorithm="SHA-256";
			try {
				MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
				messageDigest.update(inputBytes);
				byte[] digestedBytes = messageDigest.digest();
				hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();
			}
			catch(Exception e)
			{
				
			}
			return hashValue;
		}
	 

	}


