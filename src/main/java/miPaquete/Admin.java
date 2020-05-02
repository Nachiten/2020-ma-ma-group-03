package miPaquete;

class Admin extends Usuario {

    // Contructor
    Admin(String unUsuario, String unaContra){
        user = unUsuario;
        password = unaContra;

        // La fecha de la ultima contraseña es la actual
        contrasAnteriores = new String[5];


    }


}
