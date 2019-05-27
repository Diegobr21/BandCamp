package sistema;

/**
 * Contiene las credenciales tanto de la base de datos como del servidor.
 */
public class Credenciales {
	// comentado temporalmente hasta que el servidor real esté listo y se mantenga la misma información
	
	public static final String DB_USER = "sa";
	public static final String DB_PASSWORD = "123";
	public static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=Servidor;sendStringParametersAsUnicode=false";
	
//	public static final String DB_USER = "DanielSal";
//	public static final String DB_PASSWORD = "Admin1205";
//	public static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=Servidor;sendStringParametersAsUnicode=false";
	 
	public static final String SERVER_IP = "localhost";
	public static final int SERVER_PORT = 9000;
}
