import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Monoalfabetic {
    public static char[] alfabet = {'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 
    'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 
    'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 
    'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};
    public static char[] alfabetpermutat;

    public static void main(String[] args) {
        alfabetpermutat = permutarAlfabet(alfabet);
        String[] proves = {
            "Test 01 àrbitre, coixí, Perímetre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Órrius, Bòvilla"
        };
        for (int i = 0; i < alfabet.length; i++) {
            System.out.print(alfabet[i]);
        }
        System.out.println();
        for (int i = 0; i < alfabetpermutat.length; i++) {
            System.out.print(alfabetpermutat[i]);
        }
        System.out.println();
        System.out.println("Xifratge\n");
        for (String prova : proves) {
           System.out.printf("%s -> %s%n", prova, xifraMonoAlfa(prova)); 
        }
        System.out.println("Desxifratge:\n");
        for (String prova : proves) {
            System.out.printf("%s -> %s%n", xifraMonoAlfa(prova), desxifraMonoAlfa(xifraMonoAlfa(prova)));
        }
    }

    public static char[] permutarAlfabet(char[] alfabet) {
        ArrayList<Character> llista = new ArrayList<>();
        for (int i = 0; i < alfabet.length; i++) {
            llista.add(alfabet[i]);
        }
        Collections.shuffle(llista);
        char[] permutat = new char[alfabet.length];
        for (int i = 0; i < llista.size(); i++) {
            permutat[i] = llista.get(i);
        }
        return permutat;
    }
    

    public static String xifraMonoAlfa(String missatge) {
        StringBuilder resultat = new StringBuilder();
        for (int i = 0; i < missatge.length(); i++) {
            char lletraOriginal = missatge.charAt(i);
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
       return resultat.toString();
    }

    public static String desxifraMonoAlfa(String missatge) {
        StringBuilder resultat = new StringBuilder();
        for (int i = 0; i < missatge.length(); i++) {
            char lletraXifrada = missatge.charAt(i);
            boolean esMinuscula = Character.isLowerCase(lletraXifrada);
            char lletraMajuscula = Character.toUpperCase(lletraXifrada);
            boolean trobada = false;
            for (int j = 0; j < alfabetpermutat.length; j++) {
                if (lletraMajuscula == alfabet[j]) {
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
