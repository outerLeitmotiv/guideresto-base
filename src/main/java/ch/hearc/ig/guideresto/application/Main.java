package ch.hearc.ig.guideresto.application;
import ch.hearc.ig.guideresto.presentation.CLI;
import ch.hearc.ig.guideresto.services.*;


import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    var scanner = new Scanner(System.in);
    var cityService = new CityServiceImpl();
    var evaluationService = new EvaluationServiceImpl();
    var restaurantTypeService = new RestaurantTypeServiceImpl();
    var restaurantService = new RestaurantServiceImpl();
    var evaluationCriteriaService = new EvaluationCriteriaServiceImpl();

    var printStream = System.out;
    var cli = new CLI(scanner, printStream,  cityService,  evaluationService,
            restaurantTypeService,  restaurantService, evaluationCriteriaService);
    cli.start();
  }
}







