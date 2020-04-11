package miPaquete;

import java.util.ArrayList;
import java.util.List;

public class UserList{

    // Lista de usuarios del sistema [Deberian ser guardados en disco]
    public static List<Users> listaUsuarios = new ArrayList<>();

    public static void crearNuevoUsuario(String usuario, String contrasenia, String tipoUser){
        listaUsuarios.add( new Users(usuario, contrasenia, tipoUser) );
    }

    public static void cheackearCreacionUsuario(String unNombre){
        for ( Users unUser : listaUsuarios ) {
            if ( unUser.user.equals(unNombre) ) {
                System.out.println("El usuario de nombre: " + unUser.user + " fue creado satisfactoriamente. Tipo: " + unUser.tipo  );
                return;
            }
        }
        // xd | Nunca deberia entrar aca a menos q algo muy raro y flashero pase
        System.out.println("El usuario NO fue creado correctamente D:");
    }

    // Logica repetida entre este metodo y el anterior... no se como fixear y me da paja. By: Nachiten
    public static boolean checkearUsuarioExiste(String unUsuario){

        //Lista de string que guarda todos los usuarios existentes iterando por la lista de usuarios
        List<String> listaNombresUsers = new ArrayList<>();

        for (Users unUser : listaUsuarios){
            // Añadir todos los nombres de usuario a esta lista
            listaNombresUsers.add(unUser.user);
        }

        // Checkear que unUsuario exista en esta lista
        return listaNombresUsers.contains(unUsuario);
    }

    public static boolean checkearContrasenia(String laPassword, String usuario){

        Users elUsuario = new Users("0","0","0");

        for (Users unUser : listaUsuarios){
            // Encuentro el usuario de nombre "usuario"
            if ( unUser.user.equals(usuario) ) {
                elUsuario = unUser;
                break;
            }
        }

        // Este checkeo es innecesario ya que el usuario viene incializado ... LogIN linea 20
        if (elUsuario.tipo.equals("0")){
            return false;
        }

        // Retornar si la contra es correcta o no
        return elUsuario.password.equals(laPassword);
    }


    // Para Testing
    public static void retornarLista(){

        int i = 0;

        for (Users unUser : listaUsuarios){
            System.out.println("Usuario N: " + i + " "+ unUser.user);
            System.out.println("Contra N: " + i + " " + unUser.password);
            System.out.println("Tipo N: " + i + " " + unUser.tipo);

            i++;
        }
    }


}