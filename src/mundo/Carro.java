package mundo;

/**
 * Es la clase que representa a un Carro <br>
         * <b>inv: </b> <br>
     * nombre != null <br>
     * cilindraje >0<br>
     * precio >0 <br>
     * aceleracion >0 <br>     
     * caballosFuerza >0 <br>     
     * imagen != null <br>   
     */
public class Carro {
	  // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    //public static final String ComboBoxalgo = "ComboBoxalgo";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El modelo del carro
     */
    private String nombre;
    
    /**
     * El cilindraje del carro
     */
    private int cilindraje;
    
    /**
     * El precio del carro
     */
    private double precio;
    
    /**
     * La aceleracion del carro
     */
    private double aceleracion;
    
    /**
     * Los caballos de fuerza del carro
     */
    private int caballosFuerza;
    
    /**
     * La ruta de la imagen del aspirante
     */
    private String imagen;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo carro con los parámetros indicados
     * @param nombreA El nombre del carro - nombreA != null
     * @param cilindrajeA cilindraje
     * @param precioA precio
     * @param aceleracionA aceleracion
     * @param caballosFuerzaA caballos de fuerza
     * @param imagenA La ruta a la imagen del carro - imagenA != null
     */
    public Carro( String nombreA, int cilindrajeA, double precioA, double aceleracionA ,  int caballosFuerzaA ,String imagenA )
    {
        nombre = nombreA;
        cilindraje = cilindrajeA;
        precio = precioA;
        aceleracion= aceleracionA;
        caballosFuerza = caballosFuerzaA;
        imagen = imagenA;

        verificarInvariante( );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre del carro
     * @return nombre
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna cilindraje
     * @return cilindraje
     */
    public int darcilindraje( )
    {
        return cilindraje;
    }

    /**
     * Retorna precio
     * @return precio
     */
    public double darprecio( )
    {
        return precio;
    }

    /**
     * Retorna aceleracion
     * @return aceleracion
     */
    public double daraceleracion( )
    {
        return aceleracion;
    }

    /**
     * Retorna caballosFuerza
     * @return caballosFuerza
     */
    public int darcaballosFuerza( )
    {
        return caballosFuerza;
    }

    /**
     * Retorna la ruta de la imagen del Carro
     * @return imagen
     */
    public String darImagen( )
    {
        return imagen;
    }

    /**
     * Retorna una cadena con el nombre del carro
     * @return nombre
     */
    public String toString( )
    {
        return nombre;
    }

    

    public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void setAceleracion(double aceleracion) {
		this.aceleracion = aceleracion;
	}

	public void setCaballosFuerza(int caballosFuerza) {
		this.caballosFuerza = caballosFuerza;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
    
    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

	/**
     * Verifica el invariante de la clase <br>
     * <b>inv: </b> <br>
     * nombre != null <br>
     * cilindraje >0<br>
     * precio >0 <br>
     * aceleracion >0 <br>     
     * caballosFuerza >0 <br>     
     * imagen != null <br>   
     */
    private void verificarInvariante( )
    {
        assert ( nombre != null ) : "nombre no puede ser null";
        assert ( cilindraje>0) : "cilindraje no puede ser 0";
        assert (   precio>0) : "precio no puede ser 0";
        assert ( aceleracion>0) : "aceleracion no puede ser 0";
        assert ( caballosFuerza>0) : "caballosFuerza no puede ser 0";
        assert ( imagen != null ) : "imagen no puede ser null";

    }
}