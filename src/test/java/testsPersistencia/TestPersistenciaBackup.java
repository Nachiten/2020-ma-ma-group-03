package testsPersistencia;

import org.junit.Test;
import persistenciaMySQL.Persistencia;

public class TestPersistenciaBackup {

	@Test
	public void backupDB() {
		Persistencia.backup();
	}

}
