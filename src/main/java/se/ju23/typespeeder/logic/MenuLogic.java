package se.ju23.typespeeder.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.Controller.NewsController;
import se.ju23.typespeeder.service.LoginService;

import java.util.Scanner;

@Component
public class MenuLogic {

    private final Scanner scanner = new Scanner(System.in);
    private final GameLogic gameLogic = new GameLogic();

    @Autowired
    private LoginService loginService;
    private NewsController newsController;
    private boolean isEnglish = true; // Default language setting

    public void runApplication() {
        System.out.println("Welcome to TypeSpeeder");
        displayMainMenu();
    }
    public void displayMainMenu() {
        boolean isLoggedIn = false; // ändra till false när du inte testar spelet
        while (!isLoggedIn) {
            isLoggedIn = isLoggedIn();
        }
        chooseLanguage();
        if(isEnglish) {
            englishMenu();
        } else swedishMenu();
    }
    private void englishMenu() {
        boolean running = true;
        while (running) {
            System.out.println("Welcome to the TypeSpeeder Application");

            System.out.println("1. Game mode.");
            System.out.println("2. View personal ranking.");
            System.out.println("3. View top ranking");
            System.out.println("4. Challenge mode.");
            System.out.println("5. News and updates.");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    gameLogic.startGame();
                case "2":
                    break;
                case "3":
                    //top rank
                    break;
                case "4":
                    gameLogic.startChallengeMode();
                    break;
                case "5":
                    newsController.displayNews();
                    break;
                case "6":
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        }
    }
    private void swedishMenu() {
        boolean running = true;
        while (running) {
            System.out.println("Välkommen till TypeSpeeder-applikationen"); //översätta till svenska, homanegool.

            System.out.println("1. Spel-läge");
            System.out.println("2. Visa peronlig ranking");
            System.out.println("3. Visa topplista.");
            System.out.println("4. Utamningsläge.");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    gameLogic.startGame();
                    break;
                case "2":
                    //personlig rank
                    break;
                case "3":
                    //topplista
                    break;
                case "4.":
                    gameLogic.startChallengeMode();
                    break;
                case "6":
                    System.out.println("Avslutar...");
                    running = false;
                    break;
                default:
                    System.out.println("Ogiltigt alternativ, försök igen.");
            }
        }
    }

    private boolean isLoggedIn() {
        System.out.println("Please enter your username:");
        String username = scanner.nextLine();
        System.out.println("Please enter your password:");
        String password = scanner.nextLine();
        boolean isLoggedIn = loginService.login(username, password);
        if (isLoggedIn) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed. Please try again.");
        }
        return isLoggedIn;
    }

    private void goToGameMenu() {
        // Placeholder for game menu logic
        System.out.println("Welcome to the game menu!");
    }

    private void chooseLanguage() {
        System.out.println("1. English\n2. Svenska");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        if (choice == 1) {
            isEnglish = true;
            System.out.println("Language set to English.");
        } else if (choice == 2) {
            isEnglish = false;
            System.out.println("Språk inställt på svenska.");
        } else {
            System.out.println("Invalid option. Please try again.");
        }
    }

}