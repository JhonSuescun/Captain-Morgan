package model;


/**
 * Esta clase contiene los atributos y metodos de las cargas que se haran en un barco.
 * @author Jhon Suescun
 * @version 1.0
 */
public class Load
{

  //constants
  private final static String DANGEROUS = "dangerous";
  private final static String PERISHABLE = "perishable";
  private final static String NO_PERISHABLE = "noPerishable";

  private final static int TO_DANGEROUS = 390000;
  private final static int TO_PERISHABLE = 250000;
  private final static int TO_NO_PERISHABLE = 80000;


  //attributes
  private int numberOfBoxes;
  private double weight;
  private String type;
  Client owner;


  
  /**
	* metodo constructor normal de la clase.<br>
	* @param numberOfBoxes numero de cajas a transportar.
	* @param weight peso por caja en gramos.
	* @param owner cliente a quien pertenece la carga.
	* <b>pre: </b> tanto numero de cajas como peso debe ser mayor o igual a 0.<br>
	* <b>post: </b> se inicializan las variables que se usaran en la clase<br>
	*/
  //builder
  public Load(int numberOfBoxes, double weight, Client owner)
  {
    this.numberOfBoxes = numberOfBoxes;
    this.weight = weight;
    type = "";
    this.owner = owner;
  }


  //public methods (getters and setters)
  
	/**
	* metodo que obtiene el numero de cajas en la carga.<br>
	* <b>pre: </b> <br>
	* <b>post: </b> se obtiene el numero de cajas. <br>
	* @return se retorna el numero de cajas en una carga.
	*/
  public int getNumberOfBoxes()
  {
    return numberOfBoxes;
  }



	/**
	* metodo que establece un nuevo numero de cajas segun lo que usemos como parametro.<br>
	* @param numberOfBoxes numero de cajas a transportar.
	* <b>pre: </b> el numero de cajas deber ser mayor o igual a 0.<br>
	* <b>post: </b> se cambia el numero de cajas, segun lo que hayamos definido como parametro.<br>
	*/
  public void setNumberOfBoxes(int numberOfBoxes)
  {
    this.numberOfBoxes = numberOfBoxes;
  }



	/**
	* metodo para obtener el peso por caja de una carga.<br>
	* <b>pre: </b> <br>
	* <b>post: </b> da el peso por caja. <br>
	* @return retorna un double con el peso por cada caja.
	*/
  public double getWeight()
  {
    return weight;
  }


	/**
	* metodo que establece un nuevo peso por caja segun lo que pasemos por parametro.<br>
	* @param weight peso por caja en gramos.
	* <b>pre: </b> weight debe ser mayo que 0.<br>
	* <b>post: </b> establece un nuevo peso por caja. <br>
	*/
  public void setWeight(int weight)
  {
    this.weight = weight;
  }
  
  
	/**
	* metodo para obtener el type de carga.<br>
	* <b>pre: </b> <br>
	* <b>post: </b> se obtiene el tipo de carga. <br>
	* @return se retorna un String que indica el tipo de carga.
	*/
  public String getType()
  {
    return type;
  }


/**
	* metodo que establece el tipo de carga a partie de un entero.<br>
	* @param numType un numero entero.
	* <b>pre: </b> el numero debe estar entre 1 y 3.<br>
	* <b>post: </b> se cambia el tipo de clase segun el entero que se enceuntr como parametro.<br>
	*/
  public void setType(int numType)
  {
    switch (numType)
    {
      case 1:
        type = DANGEROUS;
        break;

      case 2:
        type = PERISHABLE;
        break;

      case 3:
        type = NO_PERISHABLE;
        break;
    }

  }


  //others methods
  
  
	/**
	* metodo qeu da el peso total en kilogramos de una carga.<br>
	* <b>pre: </b> <br>
	* <b>post: </b> da el peso total de una carga en kilogramos. <br>
	* @return retorna un double con el peso total de la carga.
	*/
  // returns the total weight of the load made by the customer
  public double convertToKg()
  {
    //declaration
    double totalWeightKg;

    //initialize
    totalWeightKg = 0.0;

    //transform to kg
    totalWeightKg = ( (double) getNumberOfBoxes()*getWeight())/1000;

    return totalWeightKg;
    
  }



	/**
	* metodo que da el precio de alguna carga.<br>
	* <b>pre: </b> <br>
	* <b>post: </b> se obtiene el precio total de alguna carga. <br>
	* @return retorna un double con el precio total de una carga.
	*/
  // return the total price of the load
  public double pricePerLoad()
  {
    double total = 0;

    switch (getType())
    {
      case DANGEROUS:
        total = (convertToKg() * TO_DANGEROUS)*(1 - owner.discounts());
        break;
      
      case PERISHABLE:
        total = (convertToKg() * TO_PERISHABLE)*(1 - owner.discounts());
        break;

      case NO_PERISHABLE:
        total = (convertToKg() * TO_NO_PERISHABLE)*(1 - owner.discounts());
        break;
    }
    
    return total;
  } 




}