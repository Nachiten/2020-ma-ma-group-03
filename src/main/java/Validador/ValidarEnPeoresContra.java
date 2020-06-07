package Validador;

import java.io.*;

public class ValidarEnPeoresContra extends Validacion {


    private String ruta = "./archivos/topDiezMilPeoresPassword.txt";
    private File unaRuta = new File(ruta);

    private boolean validarContraEnLista( String password) {
        try {
            FileReader fr = new FileReader(unaRuta);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean validarContra(String password){
        return validarContraEnLista(password);
    }
}
