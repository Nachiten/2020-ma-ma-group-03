package persistenciaMySQL;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Persistencia {
	public static void backup() {
		   try {
		      Process p = Runtime
		            .getRuntime()
		            .exec("mysqldump -u root -p0147852. persistenciatp");
					//  .exec("mysqldump -u root -p1234 persistenciatp");

		      InputStream is = p.getInputStream();
		      FileOutputStream fos = new FileOutputStream("gesoc2.sql");
		      byte[] buffer = new byte[1000];

		      int leido = is.read(buffer);
		      while (leido > 0) {
		         fos.write(buffer, 0, leido);
		         leido = is.read(buffer);
		      }

		      fos.close();

		   } catch (Exception e) {
		      e.printStackTrace();
		   }
		}
	public static void restaurar() {
		   try {
		      Process p = Runtime
		            .getRuntime()
					//  .exec("mysql -u root -p1234 persistenciatp");
		            .exec("mysql -u root -p0147852. persistenciatp");

		      OutputStream os = p.getOutputStream();
		      FileInputStream fis = new FileInputStream("gesoc.sql");//No olvidar quitar el número 2 para restaurar sólo la api de ML
		      byte[] buffer = new byte[1000];

		      int leido = fis.read(buffer);
		      while (leido > 0) {
		         os.write(buffer, 0, leido);
		         leido = fis.read(buffer);
		      }

		      os.flush();
		      os.close();
		      fis.close();

		   } catch (Exception e) {
		      e.printStackTrace();
		   }
		}
}
