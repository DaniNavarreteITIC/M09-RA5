package iticbcn.xifratge;

public class XifradorRotX implements Xifrador{
    public char[] minuscules = { 'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è', 
    'f', 'g', 'h', 'i', 'í', 'ì', 'ï', 'j', 'k', 'l', 
    'm', 'n', 'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's', 
    't', 'u', 'ú', 'ù', 'ü', 'v', 'w', 'x', 'y', 'z'};

     char[] majuscules = {'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 
    'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 
    'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 
    'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};

    private int validaClau(String clau) throws ClauNoSuportada {
        try {
            if (clau == null) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
            int k = Integer.parseInt(clau);
            if (k < 0 || k > 40) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
            return k;
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }

    public char desplaçar(char c, int desplaçament) {
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

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int k = validaClau(clau);
        if (msg == null) return null;
        char[] caracters = new char[msg.length()];
        for (int i = 0; i < msg.length(); i++) {
            caracters[i] = desplaçar(msg.charAt(i), k);
        }
        return new TextXifrat(new String(caracters).getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int k = validaClau(clau);
        if (xifrat == null) return null;
        String cadena = new String(xifrat.getBytes());
        char[] caracters = new char[cadena.length()];
        for (int i = 0; i < cadena.length(); i++) {
            caracters[i] = desplaçar(cadena.charAt(i), -k);
        }
        return new String(caracters);
    }
}
