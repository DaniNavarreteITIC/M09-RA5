import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Polialfabetic {
    public static char[] alfabet = {'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 
    'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 
    'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 
    'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};
    private static final long clauSecreta = 72383;
    private static char[] alfabetpermutat;
    private static Random generadorPermutacions;
    public static void main(String[] args) {
        String msgs[] = {"Test 01 àrbritre, coixí, Perímetre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];
    
        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }
    
        System.out.println("Desxifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }
    public static void initRandom(long clauSecreta) {
       generadorPermutacions = new Random(clauSecreta);
    }
    public static char[] permutarAlfabet(char[] alfabet) {
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


    public static String xifraPoliAlfa(String msg) {
        String msgXifrat = "";
        for (int i = 0; i < msg.length(); i++) {
            alfabetpermutat = permutarAlfabet(alfabet);
            boolean esMinuscula = Character.isLowerCase(msg.charAt(i));
            char lletraMajuscula = Character.toUpperCase(msg.charAt(i));
            boolean trobada = false;
            for (int j = 0; j <alfabet.length; j++) {
                if (lletraMajuscula == alfabet[j]) {
                    char lletraXifrada = alfabetpermutat[j];
                    if (esMinuscula) {
                        lletraXifrada = Character.toLowerCase(lletraXifrada);
                    }
                    msgXifrat += lletraXifrada;
                    trobada = true;
                    break;
                }
            }
            if (!trobada) {
                msgXifrat += msg.charAt(i);
            }
        }
        return msgXifrat;
    }
    public static String desxifraPoliAlfa(String msgXifrat) {
        String msgDesxifat = "";
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
                    msgDesxifat += lletraDesxifrada;
                    trobada = true;
                    break;
                }
            }
            if (!trobada) {
                msgDesxifat += msgXifrat.charAt(i);
            }
        }
        return msgDesxifat; 
    }

}
