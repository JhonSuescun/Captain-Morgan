package ui;

import model.*;
import java.util.*;

public class Main
{
  //constants
  private final static int NUM_CLIENTS = 5;
  private final static int LOAD = 1;
  private final static int UNLOAD = 2;
  private final static int INFORMATION = 3;
  private final static int SET_SAIL = 4;


  //method main
  public static void main(String[] args)
  {
    //variables
    Scanner sc;
    String shipName;
    String captainName;
    
    String clientName;
    int commercialID;
    String expeditionDate;

    //objects
    Ship myShip;
    
    
    //initialize
    sc = new Scanner(System.in);


    
    //program start
    System.out.printf("%n%n\t\t\t\t%s%n%n \t%s%n \t%s%n \t%s%n \t%s%n \t%s%n \t%s%n %n\t%s%n",
    "BIENVENIDO A APPLICATION LOAD MANAGEMENT",
    "La siguiente aplicacion busca  que  puedas  administrar de la", 
    "manera mas optima posible los transportes hecho por tu barco,",
    "asi como obtener  informacion  de tus clientes que te  pueden", 
    "interesar, como: el  total de  kilogramos  trasnporatadas con",
    "tu barco o el valor total pagado por el cliente a lo largo de",
    "todos sus transportes.",
    "Antes de comenzar;");

    System.out.print("\n\t--> por favor introduce el nombre de tu barco:\n\t    - ");
    shipName = sc.nextLine();
    
    System.out.print("\n\t--> por favor introduce el nombre del capitan\n\t    - ");
    captainName = sc.nextLine();
    
    myShip = new Ship(shipName, captainName);  
    
    System.out.printf("\n\n\n\t%s %s,%n \t%s%n \t%s%n \t%s%n \t%s%n",
    "Ahoy", myShip.getCaptainName(),
    "nos complace que hayas decidido  usar esta aplicacion para",
    "encargarte de las necesidades administrativas de tu barco,",
    "para comenzar a navegar en este  nuevo  mundo  necesito la",
    "informacion de tus cinco clientes.");


    //add attributes to objects 
    for(int i=0 ; i<NUM_CLIENTS ; i++)
    {
      System.out.print("\n\n\n\tCLIENTE #"+(i+1)+"\n\t-> introduce el nombre del cliente:\n\t   ");
      clientName = sc.nextLine();

      System.out.print("\n\t-> introduce su codigo mercantil: \n\t   ");
      commercialID = sc.nextInt();
      sc.nextLine();

      System.out.print("\n\t-> introduce la fecha de expedicion del registro mercantil (dd/mm/aa):\n\t   ");
      expeditionDate = sc.nextLine();

      myShip.addClient(clientName, commercialID, expeditionDate);
    }


    //menu
    menu(myShip);


  }


  public static void menu(Ship myShip)
  {
    // variables
    Scanner sc;
    int choice = 0;
    boolean menu = true;
    int commercialID = 0;
    int numberOfBoxes = 0;
    int weightPerBox = 0;
    int numType = 0;
    String type = "";
    String moreLoads = "";
    boolean continueLoads = true;
    Client owner;
    Load clientLoad;



    //initialize
    sc = new Scanner(System.in);
    choice = 0;
    menu = true;
    commercialID = 0;
    numberOfBoxes = 0;
    weightPerBox = 0;
    numType = 0;
    type = "";
    moreLoads = "";
    continueLoads = true;
    clientLoad = null;


    //start method
    while(menu)
    {
      System.out.printf("%n%n\t%s%s %n\t%s %n\t%s %n\t%s %n\t%s %n\t%s%n %n\t%s%n",
      "BIENVENIDO AL BARCO ", myShip.getShipName().toUpperCase(),
      "¿qué desea hacer a continuación?",
      "1. cargar el barco",
      "2. descargar el barco",
      "3. conocer el peso de la carga total",
      "4. zarpar",
      "5. salir de la aplicacion");

      do
      {
        System.out.print("\n\tDigite que desea hacer: ");
        choice = sc.nextInt();
        sc.nextLine();
      } while(choice <=0 || choice >= 6);

      System.out.println();

      switch (choice)
      {
        case LOAD:

          do
          {
            do
              {
                System.out.printf("%n\tpor favor digite su codigo mercantil: ");
                commercialID = sc.nextInt();
                sc.nextLine();
              } while(myShip.searchClient(commercialID) == null);
            
            owner = myShip.searchClient(commercialID);

            System.out.printf("%n%n\tHOLA %s%n", owner.getClientName());

            System.out.printf("\tpor favor digita el numero de cajas a transportar: ");
            numberOfBoxes = sc.nextInt();
            sc.nextLine();

            System.out.printf("%n\tpor favor digita el peso por caja (en gramos): ");
            weightPerBox = sc.nextInt();
            sc.nextLine();

            System.out.printf("%n\t%s %n\t%s %n\t%s %n\t%s%n",
            "Tipo de carga", 
            "1. Peligrosa",
            "2. Perecedera",
            "3. No perecedera");
            do
            {
              System.out.printf("%n\tPor favor digite el numero de su eleccion: ");
              numType = sc.nextInt();
              sc.nextLine();
            } while(numType <= 0 || numType >= 4);

            
            clientLoad = new Load(numberOfBoxes, weightPerBox, owner);
            myShip.addLoad(clientLoad);

            clientLoad.setType(numType);

            if(myShip.restrictionMaxWeight() == true || myShip.restrictionSaniRule() == true)
            {
              System.out.printf("%n\tERROR: has excedido el limite de peso del barco o has incumplido con las normas de sanidad");
              System.out.printf("%n\tNo se ha realizado la carga satisfactoriamente");
              myShip.removeLoad();
            }
            else
            {

              System.out.printf("%n\tla carga se ha realizado exitosamente%n");
              
              owner.setTotalTransported(clientLoad.convertToKg());
              owner.setTotalPaid(clientLoad.pricePerLoad());

              System.out.printf("%n%n\t%s %n\t%-24s: %s %n\t%-24s: %d %n\t%-24s: %.2f%s %n\t%-24s: %s %n\t%-24s: $ %.2f%n",
              "INFORMACION DE LA CARGA.",
              "PROPIETARIO ", owner.getClientName(),
              "MERCANTIL ID ", owner.getCommercialID(),
              "PESO CARGA ", clientLoad.convertToKg(), " kg",
              "TIPO DE CARGA ", clientLoad.getType(),
              "PRECIO POR LA CARGA ", clientLoad.pricePerLoad());
            }

            System.out.printf("%n%n\t¿Desea realizar otra carga? (SI/NO): ");
            moreLoads = sc.nextLine();

            if(moreLoads.equalsIgnoreCase("no"))
              continueLoads = false;

          } while(continueLoads);

          break;

        case UNLOAD:
          
          myShip.removeTotalTYP(clientLoad.convertToKg(), clientLoad.pricePerLoad());
          myShip.removeLoads();

          System.out.printf("%n\tEl barco ha sido descargado exitosamente");
          System.out.print("\n\tpresione enter para continuar...");
          sc.nextLine();
          break;

        case INFORMATION:
          System.out.printf("%n\t%s%.2f%s",
          "el barco ha sido cargado con: ", myShip.totalWeightLoads(), " kg");
          System.out.print("\n\tpresione enter para continiuar...");
          sc.nextLine();
          break;

        case SET_SAIL:
          if(myShip.restrictionMinWeight() == true || myShip.restrictionMinLoads() == true)
          {
            System.out.printf("%n\tERROR: no has alcanzado el peso minimo o la cargas minimas para zarpar");
            System.out.printf("%n\tEl barco fallo al intentar zarpar... presione enter para continuar");
            sc.nextLine();
          }
          else
          {
            System.out.printf("%n\tEl barco ha zarpado con exito");
            System.out.printf("%n\tTu carga ha sido entregada con exito%n");

            myShip.checkUpgradePerClient();

            System.out.printf("%n\tEl barco ha sido descargado... presiona enter para continuar");
            sc.nextLine();
          
          }
          break;
      }


    }
  }



  
}