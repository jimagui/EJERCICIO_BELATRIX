package pe.com.ejercicio.core.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import pe.com.ejercicio.core.jobLogger.JobLogger;
import pe.com.ejercicio.core.util.Conexion;


public class JobTest {
	
	public Map<String, Object> dbParams;
	
	 @Before
	  public void configuracion() throws Exception {
		 dbParams = new HashMap<String,Object>();
		 dbParams.put("dbms","mysql");
		 dbParams.put("serverName","10.240.147.30"); 
         dbParams.put("portNumber","3306");
         dbParams.put("database","dbalmacen");
         dbParams.put("userName","almacen");
         dbParams.put("password","123456");
         dbParams.put("logFileFolder","C:\\Users\\JIM-PC\\Desktop\\ruta\\");
         
	  }
	 
	@Test
	public void probarConexion(){
		try {
			Conexion con= Conexion.getInstancia("mysql","10.240.147.30", "3306","dbalmacen", "almacen", "123456");			
		    con.conectar();
	        int resultadoReal = con.ejecutarUpdateQuery("insert into Log_ Values('ERROR 06/12/2019 ','1') ");
	        con.desconectar();
			int resultadoEsperado =1;
			assertEquals(resultadoEsperado, resultadoReal, 0.01);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
	public void probarJobLogger(){
		try {
			JobLogger jobLogger = new JobLogger(true,true,true,true,true,true, dbParams);
			jobLogger.LogMessage("HOLA MUNDO",true, true, true);			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void probarJobLogger02(){
		try {
			JobLogger jobLogger = new JobLogger(false,false,false,false,false,false, dbParams);
			jobLogger.LogMessage("HOLA MUNDO",true, true, true);			
		} catch (Exception e) {
			fail("EXCEPCION ESPERADA : "+e.getMessage());
		}		
	}
	
	@Test
	public void probarJobLogger03(){
		try {
			JobLogger jobLogger = new JobLogger(true,false,false,false,false,false, dbParams);
			jobLogger.LogMessage("HOLA MUNDO",true, true, true);			
		} catch (Exception e) {
			fail("EXCEPCION ESPERADA : "+e.getMessage());
		}		
	}


}
