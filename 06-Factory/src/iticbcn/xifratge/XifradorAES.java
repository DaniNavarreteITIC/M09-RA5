package iticbcn.xifratge;
import java.security.SecureRandom;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class XifradorAES {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "ClaveSecreta123";
    public void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
                "Olá André como estás tu cuñado",
                "Agora illa Otto"};

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifratas = null;
            String desxifrat = "";
            try {
                bXifratas = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifratas, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: "
                        + e.getLocalizedMessage());
            }

            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifratas));
            System.out.println("DEC: " + desxifrat);
        }
    }
        public byte[] xifraAES(String msg, String clau) throws Exception {
            // obtenir bytes string
            byte[] bytesString = msg.getBytes("UTF-8");
            //generar ivParameterSpec
            SecureRandom generador = new SecureRandom();
            generador.nextBytes(iv);
            IvParameterSpec ivSpec = new  IvParameterSpec(iv);
            // Genera hash
            SecretKeySpec secretKey = generaHash(clau);
            //Encrypt
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(FORMAT_AES);
            cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, secretKey, ivSpec);
            byte[] missatgeXifrat = cipher.doFinal(bytesString);
            // Combinar IV i part xifrada
            byte[] iv_i_missatgeXifrat = new byte[MIDA_IV + missatgeXifrat.length];
            System.arraycopy(iv, 0, iv_i_missatgeXifrat, 0, MIDA_IV);
            System.arraycopy(missatgeXifrat, 0, iv_i_missatgeXifrat, MIDA_IV, missatgeXifrat.length);
            return iv_i_missatgeXifrat;
        }
        public String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
            // Extreure l'IV.
            // Creem un array buit de 16 bytes per guardar l'IV.
            byte[] ivExtret = new byte[MIDA_IV];
            // Copiem els primers 16 bytes del paquet total cap al nostre ivExtret.
            System.arraycopy(bIvIMsgXifrat, 0, ivExtret, 0, MIDA_IV);
            // Empaquetem l'IV en el format que demana Java.
            IvParameterSpec ivSpec = new IvParameterSpec(ivExtret);
        
            // Extreure la part xifrada.
            // Calculem la mida del text xifrat restant-li els 16 bytes de l'IV al total.
            int midaMissatge = bIvIMsgXifrat.length - MIDA_IV;
            // Creem un array buit amb aquesta mida exacta.
            byte[] missatgeXifrat = new byte[midaMissatge];
            // Copiem la resta dels bytes (saltant-nos els primers 16) cap a aquest nou array.
            System.arraycopy(bIvIMsgXifrat, MIDA_IV, missatgeXifrat, 0, midaMissatge);
        
            // Fer hash de la clau.
            // Utilitzem el mètode auxiliar per transformar la contrasenya en una clau vàlida.
            SecretKeySpec secretKey = generaHash(clau);
        
            // Desxifrar.
            // Creem el motor de xifrat indicant el format.
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(FORMAT_AES);
            // Iniciem el motor en mode "DESXIFRAR" i li passem la clau i l'IV.
            cipher.init(javax.crypto.Cipher.DECRYPT_MODE, secretKey, ivSpec);

            // Fem la traducció: passem de text il·legible als bytes originals del missatge.
            byte[] textDesxifratEnBytes = cipher.doFinal(missatgeXifrat);
        
            // Retornar el text desxifrat.
            // Convertim els bytes originals a un String (text normal) utilitzant UTF-8.
            return new String(textDesxifratEnBytes, "UTF-8");
        }
        public SecretKeySpec generaHash(String password) throws Exception {
            byte[] passwordBytes = password.getBytes("UTF-8");
            java.security.MessageDigest sha = java.security.MessageDigest.getInstance(ALGORISME_HASH);
            byte[] key = sha.digest(passwordBytes);
            return new SecretKeySpec(key, ALGORISME_XIFRAT);
        }
    
}

