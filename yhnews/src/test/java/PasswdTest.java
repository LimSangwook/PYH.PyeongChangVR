import com.common.util.StringEncrypter;

public class PasswdTest {

	public static void main(String[] args) {
		try {
		
			// 비밀번호 암호화
			StringEncrypter se = new StringEncrypter("DKF8D83JFGLS=", "YWMUSEUM@SYSTEM");
			
			// 암호화
			String encrypt = se.encrypt("1111");
			System.out.println("@@@@@ 암호화 : "+encrypt);
			// 복호화
			System.out.println("@@@@@ 복호화 : "+se.decrypt(encrypt));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}