package org.example;

import org.example.GettingToKnowTheLanguage.GettingToKnowTheLanguage;
import org.example.MasteringTheAPI.MasteringTheAPI;

public class App {
  public static void main(String[] args) {
    System.out.println("=====================");
    System.out.println("Getting To Know The Language");
    new GettingToKnowTheLanguage().execute();
    System.out.println("=====================\n\n\n");

    System.out.println("=====================");
    System.out.println("Mastering The API");
    new MasteringTheAPI().execute();
    System.out.println("=====================\n\n\n");
  }
}
