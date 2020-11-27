package testsPersistencia;

import org.junit.Test;
import persistenciaMySQL.Persistencia;

public class TestPersistenciaRestaurar {

	@Test
	public void restoreDB() {
		Persistencia.restaurar();
	}

}
