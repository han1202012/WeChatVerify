package shuliang.han.vertify;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class VerifyUtils {
	
	private final static String token = "hanshuliang";
	
	public static boolean checkSignature(String signature, String timestamp, String nonce) {
		
		//��token timestamp nonce �����ֵ�˳������
		String[] params = new String[]{token, timestamp, nonce};
		Arrays.sort(params);
		
		//������������������֮��ƴ�ӳ��ַ���
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i < params.length; i ++){
			buffer.append(params[i]);
		}
		
		//str���ڴ洢���ܺ���ַ���
		String str = null;
		
		try {
			//��ȡsha1���ܶ���
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			//����Ϻ���ַ���ʹ��sha1����, ���ܺ���һ��byte����
			byte[] byteDigest = digest.digest(buffer.toString().getBytes());
			//��ȡ���ܺ���ַ���, ��byte����ת��Ϊ�ַ���
			str = byte2str(byteDigest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		/*
		 * �����ܺ���ַ��� �� signature �������бȽ�, 
		 * ������ܺ���ַ���Ϊnullֱ�ӷ���false
		 * ������ܺ���ַ�����Ϊnull, ֱ�ӷ���
		 */
		boolean isVerified = (str != null) ? str.equals(signature.toUpperCase()) : false;
		
		return isVerified;
	}
	
	/*
	 * ��byte����תΪ�ַ���
	 */
	public static String byte2str(byte[] byteArray) {
		String str = "";
		for(int i = 0; i < byteArray.length; i ++){
			str += byte2HexStr(byteArray[i]);
		}
		return str;
	}
	
	/*
	 * ��byteתΪ�ַ���
	 */
	public static String byte2HexStr(byte b) {
		char[] digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
		
		char[] temp = new char[2];
		temp[0] = digit[(b >>> 4) & 0x0F];
		temp[1] = digit[b & 0x0F];
		
		String s = new String(temp);
		
		return s;
	}
	
	

}
