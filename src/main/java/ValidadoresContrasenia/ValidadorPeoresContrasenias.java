package ValidadoresContrasenia;

import java.io.*;

public class ValidadorPeoresContrasenias extends Validador {

    private String ruta = "./archivos/topPeoresContrasenias.txt";
    private File unaRuta = new File(ruta);

    private boolean noEstaEntrePeoresContrasenias(String contrasenia) {

        try {
            FileReader fr = new FileReader(unaRuta);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.equals(contrasenia)) {
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean validarContrasenia(String contrasenia){
        return noEstaEntrePeoresContrasenias(contrasenia);
    }
}