package iticbcn.xifratge;
import java.security.SecureRandom;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class XifradorAES implements Xifrador{
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private byte[] iv = new byte[MIDA_IV];

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            if (clau == null) {
                throw new ClauNoSuportada("La clau per AES no pot ser null");
            }
            byte[] bytesString = msg.getBytes("UTF-8");
            
            SecureRandom generador = new SecureRandom();
            generador.nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            
            SecretKeySpec secretKey = generaHash(clau);
            
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(FORMAT_AES);
            cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, secretKey, ivSpec);
            byte[] missatgeXifrat = cipher.doFinal(bytesString);
            
            byte[] iv_i_missatgeXifrat = new byte[MIDA_IV + missatgeXifrat.length];
            System.arraycopy(iv, 0, iv_i_missatgeXifrat, 0, MIDA_IV);
            System.arraycopy(missatgeXifrat, 0, iv_i_missatgeXifrat, MIDA_IV, missatgeXifrat.length);
            
            return new TextXifrat(iv_i_missatgeXifrat);
        } catch (Exception e) {
            throw new ClauNoSuportada("Error en xifrar AES: " + e.getLocalizedMessage());
        }
    }
    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            if (clau == null || xifrat == null) {
                throw new ClauNoSuportada("La clau o el text xifrat no poden ser nulls");
            }
            byte[] bIvIMsgXifrat = xifrat.getBytes();
            
            byte[] ivExtret = new byte[MIDA_IV];
            System.arraycopy(bIvIMsgXifrat, 0, ivExtret, 0, MIDA_IV);
            IvParameterSpec ivSpec = new IvParameterSpec(ivExtret);
        
            int midaMissatge = bIvIMsgXifrat.length - MIDA_IV;
            byte[] missatgeXifrat = new byte[midaMissatge];
            System.arraycopy(bIvIMsgXifrat, MIDA_IV, missatgeXifrat, 0, midaMissatge);
        
            SecretKeySpec secretKey = generaHash(clau);
        
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(FORMAT_AES);
            cipher.init(javax.crypto.Cipher.DECRYPT_MODE, secretKey, ivSpec);

            byte[] textDesxifratEnBytes = cipher.doFinal(missatgeXifrat);
        
            return new String(textDesxifratEnBytes, "UTF-8");
        } catch (Exception e) {
            throw new ClauNoSuportada("Error en desxifrar AES: " + e.getLocalizedMessage());
        }
    }
    public SecretKeySpec generaHash(String password) throws Exception {
        byte[] passwordBytes = password.getBytes("UTF-8");
        java.security.MessageDigest sha = java.security.MessageDigest.getInstance(ALGORISME_HASH);
        byte[] key = sha.digest(passwordBytes);
        return new SecretKeySpec(key, ALGORISME_XIFRAT);
    }
    
}

