package testsPersistencia;

import static org.junit.Assert.*;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

public class ContextTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	/*
	 * --- ORDEN PARA CORRER LOS TESTS DE PERSISTENCIA (de los datos viejos) ---
	 * 1 - PersistenciaTipoTest
	 * 2 - PersistenciaEntidadJuridica
	 * 3 - PersistenciaCategoriasCriteriosTest
	 * 4 - PersistenciaOperacionesTest
	 * 5 - PersistenciaApiMLTest
	 */

	@Test
	public void contextUp() {
		assertNotNull(entityManager());
	}
	@Test
	public void contextUpWithTransaction() {
		withTransaction(() -> {});
	}
}