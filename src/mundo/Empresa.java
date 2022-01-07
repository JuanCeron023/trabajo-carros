package mundo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JOptionPane;



/**
 * Es la clase que se encarga de manejar y organizar los carros <br>
 * <b>inv: </b> <br>
 * carros != null <br>
 * En el vector de carros no hay dos o más con el mismo nombre
 */
public class Empresa
{
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	Connection conexion;

	/**
	 * Es la lista que contiene todos los carros
	 */
	private ArrayList carros;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Construye una nueva empresa sin carros
	 */
	public Empresa( )
	{
		carros = new ArrayList( );		
		try {
			ConectarBasev1();
			String sql = "CALL buscarCarros()";
			PreparedStatement comando= conexion.prepareStatement(sql);
			ResultSet rs = comando.executeQuery();
			while(rs.next()){
				agregarCarroB(rs.getString("nombre"), Integer.valueOf(rs.getString("cilindraje")), Double.valueOf(rs.getString("precio")),  Double.valueOf(rs.getString("aceleracion")), Integer.valueOf(rs.getString("caballosFuerza")), rs.getString("imagen"));
			}

			comando.close();
			conexion.close();
		} catch (SQLException e) 
		{
			System.out.println(e);
		}
	
		System.out.println(Arrays.deepToString(carros.toArray()));
		System.out.println(carros.size());
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Retorna una lista de carros. La lista retornada no es la misma que la almacenada en esta clase, pero si tiene el mismo orden.
	 * @return lista de aspirantes
	 */
	public ArrayList darCarros( )
	{
		ArrayList copia = new ArrayList( carros );
		return copia;
	}

	/**
	 * Busca un Carro según su nombre. <br>
	 * @param nombre El nombre del carro buscado - nombre!=null
	 * @return el carro. Si no se encuentra ningún carro con ese nombre se retornó null.
	 */
	public Carro buscarCarro( String nombre )
	{
		Carro carroTemporal=null;
		boolean termine = false;
		for( int i = 0; i < carros.size( ) && !termine; i++ )
		{
			Carro carroTemporal1 = ( Carro )carros.get( i );
			String nombre1 = carroTemporal1.darNombre( );
			if( nombre1.equalsIgnoreCase( nombre ) )
			{
				carroTemporal=carroTemporal1;
				termine = true;
			}
		}

		return carroTemporal;
	}

	/**
	 * Agrega un nuevo carro a la empresa
     * @param nombreA El nombre del carro - nombreA != null
     * @param cilindrajeA cilindraje
     * @param precioA precio
     * @param aceleracionA aceleracion
     * @param caballosFuerzaA caballos de fuerza
     * @param imagenA La ruta a la imagen del carro - imagenA != null
	 * @return Se retornó true si el carro fue adicionado o false de lo contrario
	 */

	public boolean agregarCarro(  String nombreA, int cilindrajeA, double precioA, double aceleracionA ,  int caballosFuerzaA ,String imagenA )
	{
		Carro carroBuscado = buscarCarro( nombreA );
		boolean agregado = false;
		if( carroBuscado == null )
		{
			Carro nuevoCarro = new Carro( nombreA, cilindrajeA, precioA, aceleracionA, caballosFuerzaA, imagenA );
			carros.add( nuevoCarro );
			agregado = true;
			
		try {
			GuardarCarro(nuevoCarro);
		} catch (SQLException e) {
			 JOptionPane.showMessageDialog( null, "error: " + e, "Error", JOptionPane.ERROR_MESSAGE );
		}

		}
		verificarInvariante( );
		return agregado;
	}
	
	
	
	
	
	
	
	
	
	/**
	 * Agrega un nuevo carro a la empresa
     * @param nombreA El nombre del carro - nombreA != null
     * @param cilindrajeA cilindraje
     * @param precioA precio
     * @param aceleracionA aceleracion
     * @param caballosFuerzaA caballos de fuerza
     * @param imagenA La ruta a la imagen del carro - imagenA != null
	 * @return Se retornó true si el carro fue adicionado o false de lo contrario
	 */

	public boolean agregarCarroB(  String nombreA, int cilindrajeA, double precioA, double aceleracionA ,  int caballosFuerzaA ,String imagenA )
	{
		Carro carroBuscado = buscarCarro( nombreA );
		boolean agregado = false;
		if( carroBuscado == null )
		{
			Carro nuevoCarro = new Carro( nombreA, cilindrajeA, precioA, aceleracionA, caballosFuerzaA, imagenA );
			carros.add( nuevoCarro );
			agregado = true;
			
		}
		verificarInvariante( );
		return agregado;
	}
	
	
	
	
	
	
	
	
	
	
			//	aspirantes.remove( cont );
			
	// -----------------------------------------------------------------
	// Invariante
	// -----------------------------------------------------------------

	/**
	 * Verifica el invariante de la clase <br>
	 * <b>inv </b><br>
	 * carros != null <br>
	 * no hay dos carros con el mismo nombre
	 */
	private void verificarInvariante( )
	{
		assert ( carros != null ) : "La lista de carros no debe ser null";
		assert ( !buscarCarrosConNombresRepetidos( ) ) : "Hay dos carros con el mismo nombre";
	}

	/**
	 * Verifica si hay dos carros con el mismo nombre
	 * @return Se retornó true si hay dos carros con el mismo nombre; se retornó false en caso contrario.
	 */
	private boolean buscarCarrosConNombresRepetidos( )
	{
		for( int i = 0; i < carros.size( ); i++ )
		{
			Carro carrosTemporal = ( Carro )carros.get( i );
			for( int j = 0; j < carros.size( ); j++ )
			{
				if( i != j )
				{
					Carro carrosTemporal1 = ( Carro )carros.get( j );
					if( carrosTemporal1.darNombre( ).equals( carrosTemporal.darNombre( ) ) )
					{
						return true;
					}
				}
			}
		}
		return false;
	}

	// -----------------------------------------------------------------
	// Trabajo estudiante v1
	// -----------------------------------------------------------------

	/**
	 * Conecta a la base de datos
	 */
	public void ConectarBasev1() throws SQLException 
	{
		//String cadenaConexion = "jdbc:mysql://localhost:3306/bolsadeempleoa";
		//String cadenaConexion = "jdbc:mysql://localhost:3306/bolsadeempleob";
		String cadenaConexion = "jdbc:mysql://localhost:3306/empresacarros";
		String usuario="root";
		String clave = "";
		conexion = DriverManager.getConnection(cadenaConexion, usuario, clave);
		//conexion.close();
	}	
	
	
	/**
	 * Guardar al Carro
	 */
	public void GuardarCarro(Carro nuevoCarro) throws SQLException
	{
		ConectarBasev1();
		String sql = "CALL anadirCarro(?, ?, ?, ?, ?, ?)";
		PreparedStatement comando= conexion.prepareStatement(sql);

			Carro carroPosicion = nuevoCarro;
			comando.setString(1, carroPosicion.darNombre());
			comando.setInt(2, (carroPosicion.darcilindraje()));
			comando.setDouble(3, (carroPosicion.darprecio()));
			comando.setDouble(4,  (carroPosicion.daraceleracion()));
			comando.setInt(5, carroPosicion.darcaballosFuerza());
			comando.setString(6, carroPosicion.darImagen());
			comando.execute();  

		comando.close();
		conexion.close();
	}

	
	/**
	 * Eliminar Carro
	 */
	public void EliminarCarro(String nombre) throws SQLException
	{
		ConectarBasev1();
		String sql = "CALL eliminarCarro(?)";
		PreparedStatement comando= conexion.prepareStatement(sql);	
		
			comando.setString(1, nombre);	
			comando.execute();  
			
		comando.close();
		conexion.close();
		Carro nuevocarrow=buscarCarro(nombre);
		carros.remove(nuevocarrow);
		verificarInvariante();
	}
	
	
	
	
	/**
	 * Modificar Carro
	 */
	public  void ModificarCarro(String nombreA, int cilindrajeA, double precioA, double aceleracionA ,  int caballosFuerzaA ,String imagenA ) throws SQLException
	{

			Carro nuevocarrow=buscarCarro(nombreA);
			nuevocarrow.setCilindraje( cilindrajeA);
			nuevocarrow.setPrecio(precioA);
			nuevocarrow.setAceleracion(aceleracionA);
			nuevocarrow.setCaballosFuerza(caballosFuerzaA);
			nuevocarrow.setImagen(imagenA);
			
			ConectarBasev1();
			String sql = "CALL modificarCarro(?,?,?,?,?,?)";
			PreparedStatement comando= conexion.prepareStatement(sql);

			Carro carroPosicion = nuevocarrow;
			comando.setString(1, carroPosicion.darNombre());
			comando.setInt(2, (carroPosicion.darcilindraje()));
			comando.setDouble(3, (carroPosicion.darprecio()));
			comando.setDouble(4,  (carroPosicion.daraceleracion()));
			comando.setInt(5, carroPosicion.darcaballosFuerza());
			comando.setString(6, carroPosicion.darImagen());
			comando.execute();  

			comando.close();
			conexion.close();
			verificarInvariante();
		
	}
	
	
	
	// -----------------------------------------------------------------
	// Trabajo estudiante v0
	// -----------------------------------------------------------------




//getAspirantes.size()-1 ---> asi se sabe el ultimo que se agrega



	// -----------------------------------------------------------------
	// Puntos de Extensión
	// -----------------------------------------------------------------

	/**
     * Guarda un informe con los aspirantes jovenes en la carpeta data
	 * @return un mensaje indicando el exito o no del metodo
	 */
	public String metodo1( )
	{
		Collection<Carro> carrosw = carros;
		try {
			Iterator<Carro> iter =carrosw.iterator(); 
			while (iter.hasNext()) {
				
			}
			return "Se hizo el informe, el cual se encuentra en la carpeta data/";
		}
		catch (Exception e) {
			e.getMessage();
			return "No se pudo hacer el informe, " + e;}
	}
	
	
	
	/**
     * crea la base de datos
	 * @return un mensaje indicando el exito o no del metodo
	 */
	public String metodo2( )
	{	
		try {

			return "Se creó la base de datos";
		} catch (Exception e) {
			e.getMessage();
			return "No se pudo crear la base de datos, " + e;}
	}



	/**
	 * crea la tabla de aspirantes
	 * @return un mensaje indicando el exito o no del metodo
	 */
	public String metodo3( )
	{
		try {
			return "Se creó la tabla de aspirante en la base de datos";
		} catch (Exception e) {
			e.getMessage();
			return "No se pudo crear la tabla en la base de datos, " + e;}
	}

	
	
	/**
	 * Guarda los aspirantes jovenes
	 * @return un mensaje indicando el exito o no del metodo
	 */
	public String metodo4( )
	{
		try {
			return "Se añadieron los aspirantes jovenes a la base de datos";
		} catch (Exception e) {
			e.getMessage();
			return "No se pudo añadir los datos a la tabla en la base de datos, " + e;}
	}

}