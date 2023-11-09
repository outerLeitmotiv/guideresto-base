package ch.hearc.ig.guideresto.application;
import ch.hearc.ig.guideresto.presentation.CLI;
import ch.hearc.ig.guideresto.services.CityServiceImpl;
import ch.hearc.ig.guideresto.services.EvaluationServiceImpl;
import ch.hearc.ig.guideresto.services.RestaurantServiceImpl;
import ch.hearc.ig.guideresto.services.RestaurantTypeServiceImpl;

import java.io.PrintStream;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    var scanner = new Scanner(System.in);
    var cityService = new CityServiceImpl();
    var evaluationService = new EvaluationServiceImpl();
    var restaurantTypeService = new RestaurantTypeServiceImpl();
    var restaurantService = new RestaurantServiceImpl();

    var printStream = System.out;
    var cli = new CLI(scanner, printStream,  cityService,  evaluationService,  restaurantTypeService,  restaurantService);
    cli.start();
  }
}







