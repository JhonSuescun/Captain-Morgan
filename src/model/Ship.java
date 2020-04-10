package model;

import java.util.ArrayList;


/**
 * Esta clase contiene los atributos y metodos de un barco que se encarga del transporte maritimo.
 * @author Jhon Suescun
 * @version 1.0
 */
public class Ship
{
  // constants
  private final static int MAX_KG = 28000;
  private final static int MIN_KG = 12000;
  private final static int MIN_LOADS = 2;


  //attributes
  private String shipName;
  private String captainName;
  private ArrayList<Load> loads;
  private ArrayList<Client> clients;


  
  
	/**
	*metodo constructor normal de la clase.<br>
	* @param shipName nombre que se le asigna al barco
	* @param catainName nombre que se le asigno al capitan
	* <b>pre: </b> todas la variables encontradas en los parametros<br>
	* <b>post: </b> todos los atributos de la clase son inicializados  <br>
	*/
  //builder
  public Ship(String shipName, String captainName)
  {
    this.shipName = shipName;
    this.captainName = captainName;
    loads = new ArrayList<Load>();
    clients = new ArrayList<Client>();
  }


	/**
	*metodo que regresa el nombre del barco.<br>
	* <b>pre: </b> <br>
	* <b>post: </b> da el nombre que el barco tenga en ese momento<br>
	* @return regresa un String con el nombre del barco actual.
	*/
  //public methods (getters and setters)
  public String getShipName()
  {
    return shipName;
  }

  
	/**
	* metodo para establecer el nombre del barco.<br>
	* @param shipName nombre que se le asigna al barco
	* <b>pre: </b> se nesecita un String con el nombre del barco<br>
	* <b>post: </b> el nombre del barco es cambiado por el escrtiro en el parametro  <br>
	*/
  public void setShipName(String shipName)
  {
    this.shipName = shipName;
  }


	/**
	*devuelve el nombre del capitan.<br>
	* <b>pre: </b> <br>
	* <b>post: </b> se devuelve el nombre del capitan. <br>
	* @return regresa un String con el actual nombre del capitan.
	*/
  public String getCaptainName()
  {
    return captainName;
  }



	/**
	* metodo que establece el nombre del capitan.<br>
	* @param catainName nombre que se le asigno al capitan
	* <b>pre: </b> Un String con el nombre del capitan.<br>
	* <b>post: </b> El nombre del capitan ha sido cambiado por el digitado en el parametro.<br>
	*/
  public void setCaptainName(String captainName)
  {
    this.captainName = captainName;
  }



	/**
	*metodo que añade un cliente a una ArrayList<br>
	* @param clientName nombre del cliente
	* @param commercialID numero del registro mercantil
	* @param expeditionDate fecha de expedicion del registro mercantil
	* <b>pre: </b> todas la variables encontradas en los parametros<br>
	* <b>post: </b> se agrega un nuevo cliente a una ArrayList con los clientes.<br>
	*/
  // others methods

  public void addClient(String clientName, int commercialID, String expeditionDate)
  {
    Client newClient = new Client(clientName, commercialID, expeditionDate);
    clients.add(newClient);
  }


	/**
	* metodo para añadir una carga a un ArrayList.<br>
	* @param Load contiene toda la informacion de una carga
	* <b>pre: </b> una Carga, con todos sus atributos.<br>
	* <b>post: </b> añade una carga a un ArrayList.<br>
	*/
  public void addLoad(Load clientLoad)
  {
    loads.add(clientLoad);
  }
  
  
  	/**
	*metodo que remueve una carga de un ArrayList.<br>
	* <b>pre: </b> <br>
	* <b>post: </b> remueve una carga de un ArrayList.<br>
	*/
  public void removeLoad()
  {
    loads.remove(loads.size() - 1);
  }


	/**
	*metodo para buscar un cliente segun su Id.<br>
	/@param commercialID contiene el Id del cliente
	* <b>pre: </b> el comercial Id deber ser postivo<br>
	* <b>post: </b> se obtiene un cliente.<br>
	* @return se retorna el cliente que tenga la misma Id
	*/
  public Client searchClient(int commercialID)
  {
    Client result = null;

    for(int i=0 ; i<clients.size() ; i++)
    {
      if(commercialID == clients.get(i).getCommercialID())
        result = clients.get(i);
    }
    return result;

  }



	/**
	*metodo remueve todas las cargas del barco.<br>
	* <b>pre: </b> <br>
	* <b>post: </b> todos las cargas en un ArrayList son borradas.<br>
	*/
  //unload the ship
  public void removeLoads()
  {
   loads.clear(); 
  }



	/**
	*metodo metodo que da el peso total de todas las cargas hechas.<br>
	* <b>pre: </b> <br>
	* <b>post: </b> se obtiene el peso total de las cargas <br>
	*@return se retorna el peso total de todas las cargas.
	*/
  //calculate the weight transported by the ship
  public double totalWeightLoads()
  {
    //declaration
    double total;

    //initialize
    total = 0;
    
    for(int i=0 ; i<loads.size() ; i++)
      total += loads.get(i).convertToKg();

    return total;

  }


  
  
  	/**
	*metodo que varifica si se esta cumpliendo con el peso maximo del barco.<br>
	* <b>pre: </b> <br>
	* <b>post: </b> se da un indicandor de si cumple o no la restriccion. <br>
	* @return retorna un boolean que indica si es verdad o no que cumple con la restriccion.
	*/
  // restriction #1 (maximum load less than 28000)
  public boolean restrictionMaxWeight()
  {
    boolean restriction = false;

    if(totalWeightLoads() > MAX_KG)
    {
      restriction = true;
    }

    return restriction;
  }



	/**
	*metodo que verifica si se esta cumpliendo con las reglas de sanidad para zarpar.<br>
	* <b>pre: </b> <br>
	* <b>post: </b> se da si la carga cumple con la restriccion <br>
	* @return se retorna un boolean indicando si es verdad o no que cumple la restriccion.
	*/
  // restriction #2 (sanitation rules)
  public boolean restrictionSaniRule()
  {
    boolean restriction = false;

    for(int i=0 ; i<loads.size() - 1 ; i++)
    {
      for(int j=(i+1) ; j<loads.size() ; j++)
      {
        if( (loads.get(i).getType().equals("perishable") && loads.get(j).getType().equals("dangerous")) || (loads.get(i).getType().equals("dangerous") && loads.get(j).getType().equals("perishable")) )
        restriction = true;
      }
    }

    return restriction;
  }



	/**
	* metodo que verifica si se cumple con el peso minimo para zarpar.<br>
	* <b>pre: </b> <br>
	* <b>post: </b> indica si se cumple o no con la restriccion <br>
	* @return retorna un boolean que da verdad o falso dependiendo de la restriccion
	*/
  // restriction #3 (minimum weight to set sail)
  public boolean restrictionMinWeight()
  {
    boolean restriction = false;

    if(totalWeightLoads() < MIN_KG)
    {
      restriction = true;
    }

    return restriction;
  }



/**
	* metodo revisa si se esta cumpliendo con las cargas minimas para zarpar.<br>
	* <b>pre: </b> <br>
	* <b>post: </b> devuelve si se puede o no zarpar <br>
	* @return regresa un boolean que indica si es falso o no que cumple con la restriccion
	*/
  // restriction #4 (minimum number of loads)
  public boolean restrictionMinLoads()
  {
    boolean restriction = false;

    if(loads.size() < MIN_LOADS)
    {
      restriction = false;
    }

    return restriction;
    
  }



/**
	* metodo que verifica una vez ha zarpado el barco si el cliente puede subir de categoria.<br>
	* <b>pre: </b> <br>
	* <b>post: </b> se sube de categoria a los clientes que cumplen con ciertas condiciones. <br>
	*/
  // upgrade every client
  public void checkUpgradePerClient()
  {
    for(int i=0 ; i<clients.size() ; i++)
    {
      clients.get(i).upgradeCategory();
    }

  }



/**
	* metodo que remueve las cargas del barco en caso de equivocarse. <br>
	* @param totalTransported double con la cantidad total transportada por el cliente en todos sus viajes.
	* @param totalPaid double con la cantidad pagada por el cliente durante todos sus viajes
	* <b>pre: </b> totalTransported >= 0 && totalPaid >= 0 <br>
	* <b>post: </b> remueve todas las cargas de un barco.<br>
	*/
  // removes what is charged and paid by the client, in case of unloading before setting sail
  public void removeTotalTYP(double totalTransported, double totalPaid)
  {
    for(int i=0 ; i<clients.size() ; i++)
    {
      clients.get(i).setTotalTransported(-totalTransported);
      clients.get(i).setTotalPaid(-totalPaid);
    }

  }




}