public class Rot13{
    public static char[] minuscules = { 'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è', 
    'f', 'g', 'h', 'i', 'í', 'ì', 'ï', 'j', 'k', 'l', 
    'm', 'n', 'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's', 
    't', 'u', 'ú', 'ù', 'ü', 'v', 'w', 'x', 'y', 'z'};

    public static char[] majuscules = {'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 
    'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 
    'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 
    'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};

    public static char desplaçar(char c, int despaçament) {
        for (int i = 0; i < minuscules.length; i++) {
            if (minuscules[i] == c) {
                int nouIndex = (i + despaçament) % minuscules.length;
                if (nouIndex < 0) {
                    nouIndex+= minuscules.length;
                }
                return minuscules[nouIndex];
            }
        }

        for (int i = 0; i < majuscules.length; i++) {
            if (majuscules[i] == c) {
                int nouIndex = (i + despaçament) % majuscules.length;
                if (nouIndex < 0) {
                    nouIndex+= majuscules.length;
                }
                return majuscules[nouIndex];
            }
        }
        return c;
    } 

    public static String xifraRot13(String cadena) {
        char[] caracters = new char[cadena.length()];
        for (int i = 0; i < cadena.length(); i++) {
            caracters[i] = desplaçar(cadena.charAt(i), 13);
        }
        return new String(caracters);
    }

     public static String desxifraRot13(String cadena) {
        char[] caracters = new char[cadena.length()];
        for (int i = 0; i < cadena.length(); i++) {
            caracters[i] = desplaçar(cadena.charAt(i), -13);
        }
        return new String(caracters);
    }

    public static void main(String[] args) {
        String[] proves = {
            "ABC",
            "XYZ",
            "Hola, Mr. calçot",
            "Perdó, per tu què és?"
        };
        System.out.println("Xifrat");
        System.out.println("---------");
        for (String p : proves) {
            System.out.println(p + " => " + xifraRot13(p));
        }
        System.out.println();
        
        System.out.println("Desxifrat");
        System.out.println("---------");
        for (String p : proves) {
            String xifrat = xifraRot13(p);
            System.out.println(p + " => " + desxifraRot13(p));
        }

    }
}