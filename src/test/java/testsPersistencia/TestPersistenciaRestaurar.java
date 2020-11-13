package testsPersistencia;

import org.junit.Test;
import persistenciaMySQL.Persistencia;

public class TestPersistenciaRestaurar {

	@Test
	public void test() {
		Persistencia.restaurar();
	}

}
