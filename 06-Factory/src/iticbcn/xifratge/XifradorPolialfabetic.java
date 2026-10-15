package iticbcn.xifratge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador{
    public char[] alfabet = {'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 
    'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 
    'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 
    'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};
    private static final long clauSecreta = 72383;
    private char[] alfabetpermutat;
    private Random generadorPermutacions;
    private long validaClau(String clau) throws ClauNoSuportada {
        try {
            if (clau == null) {
                throw new ClauNoSuportada("La clau per xifrat Polialfabètic no pot ser null");
            }
            return Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
    }
    public void initRandom(long clauSecreta) {
       generadorPermutacions = new Random(clauSecreta);
    }
    public char[] permutarAlfabet(char[] alfabet) {
        ArrayList<Character> llista = new ArrayList<>();
        for (int i = 0; i < alfabet.length; i++) {
            llista.add(alfabet[i]);
        }
        Collections.shuffle(llista, generadorPermutacions);
        char[] permutat = new char[alfabet.length];
        for (int i = 0; i < llista.size(); i++) {
            permutat[i] = llista.get(i);
        }
        return permutat;
    }


    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        long llavor = validaClau(clau);
        initRandom(llavor);
        
        StringBuilder msgXifrat = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            alfabetpermutat = permutarAlfabet(alfabet);
            boolean esMinuscula = Character.isLowerCase(msg.charAt(i));
            char lletraMajuscula = Character.toUpperCase(msg.charAt(i));
            boolean trobada = false;
            for (int j = 0; j < alfabet.length; j++) {
                if (lletraMajuscula == alfabet[j]) {
                    char lletraXifrada = alfabetpermutat[j];
                    if (esMinuscula) {
                        lletraXifrada = Character.toLowerCase(lletraXifrada);
                    }
                    msgXifrat.append(lletraXifrada);
                    trobada = true;
                    break;
                }
            }
            if (!trobada) {
                msgXifrat.append(msg.charAt(i));
            }
        }
        return new TextXifrat(msgXifrat.toString().getBytes());
    }
    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        long llavor = validaClau(clau);
        if (xifrat == null) return null;
        initRandom(llavor);
        
        String msgXifrat = new String(xifrat.getBytes());
        StringBuilder msgDesxifat = new StringBuilder();
        for (int i = 0; i < msgXifrat.length(); i++) {
            alfabetpermutat = permutarAlfabet(alfabet);
            boolean esMinuscula = Character.isLowerCase(msgXifrat.charAt(i));
            char lletraMajuscula = Character.toUpperCase(msgXifrat.charAt(i));
            boolean trobada = false;
            for (int j = 0; j < alfabetpermutat.length; j++) {
                if (lletraMajuscula == alfabetpermutat[j]) {
                    char lletraDesxifrada = alfabet[j];
                    if (esMinuscula) {
                        lletraDesxifrada = Character.toLowerCase(lletraDesxifrada);
                    }
                    msgDesxifat.append(lletraDesxifrada);
                    trobada = true;
                    break;
                }
            }
            if (!trobada) {
                msgDesxifat.append(msgXifrat.charAt(i));
            }
        }
        return msgDesxifat.toString();
    }
}
