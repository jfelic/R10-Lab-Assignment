import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

class Vehicle {
    private int year;
    private String make;
    private String model;

    public Vehicle() {}
    public Vehicle( int year, String make, String model ) {
        this.year = year;
        this.make = make;
        this.model = model;
    }

    @Override
    public String toString() {
        //FINISH THIS METHOD
        return this.year + " " + this.make + " " + this.model + " ";
    }
}

class Car extends Vehicle {
    private String color;

    public Car() { super(); }
    public Car( int year, String make, String model, String color ) {
        //FINISH THIS METHOD
        super(year, make, model);
        this.color = color;
    }

    @Override
    public String toString() {
        //FINISH THIS METHOD
        return this.color + " " + super.toString();
    }
}

class Motorcycle extends Vehicle {
    private int cc;

    public Motorcycle() { super(); }
    public Motorcycle( int year, String make, String model, int cc ) {
        //FINISH THIS METHOD
        super(year, make, model);
        this.cc = cc;
    }
    
    @Override
    public String toString() {
        //FINISH THIS METHOD
        return super.toString() + " (" + this.cc + " cc)";
    }
}

class Garage {
    private int id;
    private String location;
    private Vehicle[] vehicles;
    
    private static int allGarages = 0;

    public Garage() { this.vehicles = new Vehicle[5]; }
    public Garage( String location ) {
        //FINISH THIS METHOD
        this();
        this.location = location;
        this.id = allGarages++;
    }

    public Vehicle[] getVehicles() {
        return this.vehicles; 
    }
    
    @Override
    public String toString() {
    //FINISH THIS METHOD
    int vehicleCount = 0;
    for (Vehicle v : vehicles) {
        if (v != null) vehicleCount++; // Count each non-null vehicle
    }
    
    if(vehicleCount == 5) {
        return location + " garage (full)";
    } else{
        //return location + " garage (filled " + vehicleCount + " of " + vehicles.length + ")";
        return location + " garage (remaining capacity " + (5 - vehicleCount) + ")";
    }
    }
}

public class R10 {
    public static void main( String[] args ) throws FileNotFoundException {
        Scanner in = new Scanner( new File( "data.txt" ) );
        
        Garage[] garage = new Garage[ 10 ];
        int currentGarage = -1;
        int currentCarBay = -1;
        while ( in.hasNextLine() ) {
            if ( !in.hasNextInt() ) {
                currentGarage++;
                String location = in.nextLine();
                if ( location.equals( "" ) )
                    location = in.nextLine();
                garage[ currentGarage ] = new Garage( location );   
                currentCarBay = -1;
            }
            else {
                int year = in.nextInt();
                String make = in.next();
                String model = in.next();
                if ( in.hasNextInt() ) {
                    currentCarBay++;
                    garage[ currentGarage ].getVehicles()[ currentCarBay ] = new Motorcycle( year, make, model, in.nextInt() );
                }
                else {
                    currentCarBay++;
                    garage[ currentGarage ].getVehicles()[ currentCarBay ] = new Car( year, make, model, in.next() );
                }
            }
        }
        in.close();
        for ( int i = 0; i <= currentGarage; i++ ) {
            System.out.println( garage[ i ] + ":" );
            Vehicle[] v = garage[ i ].getVehicles();
            for ( int j = 0; j < 5; j++ )
                if ( v[ j ] != null )
                    System.out.println( " - " + v[ j ] );
            System.out.println();
        }
    }   
}
