public class RotX {
    public static char[] minuscules = { 'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è', 
    'f', 'g', 'h', 'i', 'í', 'ì', 'ï', 'j', 'k', 'l', 
    'm', 'n', 'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's', 
    't', 'u', 'ú', 'ù', 'ü', 'v', 'w', 'x', 'y', 'z'};

    public static char[] majuscules = {'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 
    'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 
    'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 
    'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};

    public static char desplaçar(char c, int desplaçament) {
        for (int i = 0; i < minuscules.length; i++) {
            if (minuscules[i] == c) {
                int nouIndex = (i + desplaçament) % minuscules.length;
                if (nouIndex < 0) {
                    nouIndex+= minuscules.length;
                }
                return minuscules[nouIndex];
            }
        }

        for (int i = 0; i < majuscules.length; i++) {
            if (majuscules[i] == c) {
                int nouIndex = (i + desplaçament) % majuscules.length;
                if (nouIndex < 0) {
                    nouIndex+= majuscules.length;
                }
                return majuscules[nouIndex];
            }
        }
        return c;
    }
    public static String xifraRotX(String cadena, int desplaçament) {
        char[] caracters = new char[cadena.length()];
        for (int i = 0; i < cadena.length(); i++) {
            caracters[i] = desplaçar(cadena.charAt(i), desplaçament);
        }
        return new String(caracters);
    }
    public static String desxifraRotX(String cadena, int desplaçament) {
        char[] caracters = new char[cadena.length()];
        for (int i = 0; i < cadena.length(); i++) {
            caracters[i] = desplaçar(cadena.charAt(i), -desplaçament);
        }
        return new String(caracters);
    }

    public static void forçaBrutaRotX(String cadenaXifrada) {
        System.out.println("\nMissatge xifrat: " + cadenaXifrada);
        System.out.println("---------");
        for (int i = 1; i < minuscules.length; i++) {
            System.out.println("(" + i + ") " + desxifraRotX(cadenaXifrada, i));
        }
    }
    public static void main(String[] args) {
        String[] proves = {
            "ABC",
            "XYZ",
            "Hola, Mr. calçot",
            "Perdó, per tu què és?"
        };
        int[] desplaçaments = {0, 2, 4, 6};

        System.out.println("Xifrat");
        System.out.println("---------");
        for (int i = 0; i < proves.length; i++) {
            String p = proves[i];
            int desp = desplaçaments[i];
            System.out.println("(" + desp + ")-" + p + " => " + xifraRotX(p, desp));
        }
        
        System.out.println("\nDesxifrat");
        System.out.println("---------");
        for (int i = 0; i < proves.length; i++) {
            String p = proves[i];
            int desp = desplaçaments[i];
            String xifrat = xifraRotX(p, desp);
            System.out.println("(" + desp + ")" + xifrat + " => " + desxifraRotX(xifrat, desp));
        }
        forçaBrutaRotX(xifraRotX("Perdó, per tu què és?", 6));

    }
 
}
