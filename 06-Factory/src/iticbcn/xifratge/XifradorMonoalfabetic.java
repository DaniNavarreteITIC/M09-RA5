package iticbcn.xifratge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XifradorMonoalfabetic  implements Xifrador{
    public char[] alfabet = {'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 
    'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 
    'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 
    'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};
    public char[] alfabetpermutat;


    public XifradorMonoalfabetic() {
        List<Character> llista = new ArrayList<>();
        for (char c : alfabet) {
            llista.add(c);
        }
        Collections.shuffle(llista);
        alfabetpermutat = new char[llista.size()];
        for (int i = 0; i < llista.size(); i++) {
            alfabetpermutat[i] = llista.get(i);
        }
    }
    

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        if (msg == null) return null;

        StringBuilder resultat = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            char lletraOriginal = msg.charAt(i);
            boolean esMinuscula = Character.isLowerCase(lletraOriginal);
            char lletraMajuscula = Character.toUpperCase(lletraOriginal);
            boolean trobada = false;
            for (int j = 0; j < alfabet.length; j++) {
                if (lletraMajuscula == alfabet[j]) {
                    char lletraXifrada = alfabetpermutat[j];
                    if (esMinuscula) {
                        lletraXifrada = Character.toLowerCase(lletraXifrada);
                    }
                    resultat.append(lletraXifrada);
                    trobada = true;
                    break;
                }
            }
            if (!trobada) {
                resultat.append(lletraOriginal);
            }
        }
        return new TextXifrat(resultat.toString().getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        if (xifrat == null) return null;

        String missatge = new String(xifrat.getBytes());
        StringBuilder resultat = new StringBuilder();
        for (int i = 0; i < missatge.length(); i++) {
            char lletraXifrada = missatge.charAt(i);
            boolean esMinuscula = Character.isLowerCase(lletraXifrada);
            char lletraMajuscula = Character.toUpperCase(lletraXifrada);
            boolean trobada = false;
            for (int j = 0; j < alfabetpermutat.length; j++) {
                if (lletraMajuscula == alfabetpermutat[j]) {
                    char lletraOriginal = alfabet[j];
                    if (esMinuscula) {
                        lletraOriginal = Character.toLowerCase(lletraOriginal);
                    }
                    resultat.append(lletraOriginal);
                    trobada = true;
                    break;
                }
            }
            if (!trobada) {
                resultat.append(lletraXifrada);
            }
        }
        return resultat.toString();
    }
    
}
