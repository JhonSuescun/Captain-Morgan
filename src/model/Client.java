package model;


/**
 * Esta clase contiene los atributos y metodos de un cliente que quiere realizar una carga en un barco.
 * @author Jhon Suescun
 * @version 1.0
 */
public class Client
{

  //constants
  private final static String NORMAL = "normal";
  private final static String SILVER = "silver";
  private final static String GOLD = "gold";
  private final static String PLATINUM = "platinum";

  private final static double TO_SILVER = 0.015;
  private final static double TO_GOLD = 0.03;
  private final static double TO_PLATINUM = 0.05;


  //attributes
  private String clientName;
  private int commercialID;
  private String expeditionDate;
  private String type;
  private double totalTransported;
  private double totalPaid;


  
  
	/**
	* metodo constructor normal de la clase.<br>
	* @param clientName nombre del cliente.
	* @param commercialID codigo mercantil del cliente.
	* @param expeditionDate fecha de expedicion del codigo mercantil.
	* <b>pre: </b> commercialID debe ser un numero positivo.<br>
	* <b>post: </b> se inicializan las variables que se usaran en la clase<br>
	*/
  //builder
  public Client(String clientName, int commercialID, String expeditionDate)
  {
    this.clientName = clientName;
    this.commercialID = commercialID;
    this.expeditionDate = expeditionDate;
    type = NORMAL;
    totalTransported = 0;
    totalPaid = 0;

  }



  //public methods (getters and setters )
  
  	/**
	* metodo que regresa el nombre del cliente.<br>
	* <b>pre: </b> <br>
	* <b>post: </b> se obtiene el nombre del cliente <br>
	*/
  public String getClientName()
  {
    return clientName;
  }



	/**
	* metodo para establecer un nuevo nombre para el cliente segun un parametro.<br>
	* @param clientName nombre del cliente.
	* <b>pre: </b> <br>
	* <b>post: </b> Se establece un nuevo nombre para el cliente<br>
	*/
  public void setClientName(String clientName)
  {
    this.clientName = clientName;
  } 
  
  
	/**
	* metodo que regresa el codigo mercantil de un cliente.<br>
	* <b>pre: </b> <br>
	* <b>post: </b> se obtiene el codigo mercantil del cliente. <br>
	* @return retorna un entero que contiene el codigo comercial de cualquier cliente.
	*/
  public int getCommercialID()
  {
    return commercialID;
  } 
  
  
	/**
	* metodo que establece un nuevo codigo comercial a partir de un commercialID.<br>
	* @param commercialID codigo mercantil del cliente.
	* <b>pre: </b> commercialID debe ser un numero positivo.<br>
	* <b>post: </b> se establece un nuevo commercialID. <br>
	*/
  public void setCommercialID(int commercialID)
  {
    this.commercialID = commercialID;
  }


	/**
	* metodo regresa la fecha de expedicion del codigo mercantil de un cliente.<br>
	* <b>pre: </b> <br>
	* <b>post: </b> se obtiene la fecha de expedicion de un codigo mercantil. <br>
	* @return retorna un String con la fecha de expedicion de un codigo mercantil de algun cliente.
	*/
  public String getExpeditionDate()
  {
    return expeditionDate;
  }


	/**
	* metodo que establece una nueva fecha de expedicion a partir de otra que entramos como parametro.<br>
	* @param expeditionDate fecha de expedicion del codigo mercantil.
	* <b>pre: </b> debe ser una fecha valida.<br>
	* <b>post: </b> se establece una nueva fecha de expedicion para un codigo mercantil.<br>
	*/
  public void setExpeditionDate(String expeditionDate)
  {
    this.expeditionDate = expeditionDate;
  }



	/**
	* metodo que regresa el tipo o categoria de un cliente.<br>
	* <b>pre: </b> <br>
	* <b>post: </b> se obtiene la categoria del cliente.<br>
	* @return retorna un String que contiene la categoria de un cliente.
	*/
  public String getType()
  {
    return type;
  }


	/**
	* metodo que regresa el total transportado por un cliente.<br>
	* <b>pre: </b> <br>
	* <b>post: </b> se obtiene el peso total transportado por un cliente en todos sus viajes.<br>
	* @return retorna un double con el peso total transportado por un cliente cualquiera.
	*/
  public double getTotalTransported()
  {
    return totalTransported;
  }
  
  
  
	/**
	* metodo que establece un nuevo peso total transportado a partir de la suma del antiguo peso con el nuevo.<br>
	* @param totalTransported contiene el peso transportado en su ultimo viaje
	* <b>pre: </b> totalTransported debe ser un numero positivo.<br>
	* <b>post: </b> se estable un nuevo peso total transportado<br>
	*/
  public void setTotalTransported(double totalTrasnported)
  {
    this.totalTransported += totalTransported;
  }
  
  
  
	/**
	* metodo que regresa el total pagado por un cliente en todos sus viajes.<br>
	* <b>pre: </b> <br>
	* <b>post: </b> se obtiene el total pagado por un cliente en sus viajes<br>
	* @return retorna un double que contiene el toal pagado por culaquier cliente.
	*/
  public double getTotalPaid()
  {
    return totalPaid;
  }



	/**
	* metodo que establece un nuevo pago total de un cliente, esto a partir de la suma del antiguo peso total mas uno nuevo digitado.<br>
	* @param totalPaid total pagado por el cliente en el ultimo viaje
	* <b>pre: </b> totalPaid debe ser positivo.<br>
	* <b>post: </b> se establece un pago total de algun cliente<br>
	*/
  public void setTotalPaid(double totalPaid)
  {
    this.totalPaid += totalPaid;
  }




  //others methods
  
  
  	/**
	* metodo que sube de categoria a un cliente tomado en cuenta ciertos factores especificos.<br>
	* <b>pre: </b> <br>
	* <b>post: </b> se le da una nueva categoria al cliente en caso de cumplir con alguna de las condiciones. <br>
	*/
  public void upgradeCategory()
  {
    int cont = 0;

    if(getTotalTransported() >= 35000 && getTotalTransported() < 55000 && getType() == NORMAL)
      {
       type = SILVER;
       cont++; 
      }

    if((getTotalTransported() >= 55000 || (getTotalPaid() >= 2000000 && getTotalPaid() < 5000000)) && getType() == SILVER)
      {
        type = GOLD;
        cont++;
      }
  
    if(getTotalPaid() >= 5000000 && getType() == GOLD)
      {
        type = PLATINUM;
        cont++;
      }

    if (cont != 0)
    {
      System.out.printf("%n\t!!!%s %s %s %s¡¡¡%n",
      "Felicitaciones", getClientName(), "has ascendido a", getType());

    }
  
  }


  	/**
	* metodo que regresa un numero(descuento) segun la categoria del cliente.<br>
	* <b>pre: </b> <br>
	* <b>post: </b> se obtiene numero que representa el descuento. <br>
	* @return retorna un double que sirve como descuento para el total pagado por una carga.
	*/
  // discount payments
  public double discounts()
  {
    double discount = 0.0;

    switch (type)
    {
      case SILVER:
        discount = TO_SILVER;
        break;

      case GOLD:
        discount = TO_GOLD;
        break;
      
      case PLATINUM:
        discount = TO_PLATINUM;
        break;
    }

    return discount;

  }


}