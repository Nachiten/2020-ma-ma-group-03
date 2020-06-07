package Validador;

import java.io.*;

public class ValidarEnPeoresContra extends Validacion {

    String ruta;
    public ValidarEnPeoresContra(String ruta) {this.ruta = ruta;}


    public  boolean validarContraEnLista(String ruta, String password) {
        try {
            File unaRuta = new File(ruta);
            FileReader fr = new FileReader(unaRuta);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains(password)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean validarContra(String password) /*throws FileNotFoundException, IOException*/ {
        return validarContraEnLista(ruta,password);
    }


}
