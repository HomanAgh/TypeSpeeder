package se.ju23.typespeeder.logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.entity.User;
import se.ju23.typespeeder.entity.UserRepository;
import se.ju23.typespeeder.service.LoginService;
import se.ju23.typespeeder.service.NewsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class MenuLogicImpl implements MenuLogic {
    private final Scanner scanner;
    private final GameLogic gameLogic;
    private final Challenge challenge;
    private final UserRepository userRepository;
    private final LoginService loginService;
    private final NewsService newsService;
    private boolean isEnglish = true;

    public Map<String, Runnable> options = new HashMap<>();


    @Autowired
    public MenuLogicImpl(GameLogic gameLogic, Challenge challenge, UserRepository userRepository, LoginService loginService, NewsService newsService) {
        this.scanner = new Scanner(System.in);
        this.gameLogic = gameLogic;
        this.challenge = challenge;
        this.userRepository = userRepository;
        this.loginService = loginService;
        this.newsService = newsService;
        initialiseOptions();
    }

    public void runApplication() {
        System.out.println("Welcome to TypeSpeeder");
        displayMainMenu();
    }

    private void initialiseOptions() {
        options.put("1", gameLogic::startGame);
        options.put("2", this::displayUserExperienceAndLevel);
        options.put("3", this::displayTopList);
        options.put("4", challenge::startChallenge);
        options.put("5", newsService::displayNews);
        options.put("6", () -> {
            System.out.println("Exiting...");
        });
    }

    private void displayMainMenu() { //la till Scanner scanner
        boolean isLoggedIn = false;
        while (!isLoggedIn) {
            isLoggedIn = isLoggedIn();
        }
        chooseLanguage();
        if (isEnglish) {
            englishMenu();
        } else {
            swedishMenu();
        }
    }

    private void englishMenu() {
        boolean running = true;
        while (running) {
            displayEnglishMenu();
            String input = scanner.nextLine();
                Runnable option = options.get(input);
                if (option != null) {
                    option.run();
                    if(input.equals("6")){
                        running = false;
                    }
                } else {
                    System.out.println("Invalid option, please try again.");
                }
            }
    }


    public void displayEnglishMenu(){
        System.out.println("Welcome to the TypeSpeeder Application");
        System.out.println("1. Game mode");
        System.out.println("2. View experience, level, and playername");
        System.out.println("3. View top ranking");
        System.out.println("4. Challenge mode");
        System.out.println("5. News and updates");
        System.out.println("6. Exit");
        System.out.print("Select an option: ");
    }

    private void swedishMenu() {
        boolean running = true;
        while (running) {
           displaySwedishMenu();
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    gameLogic.startGame();
                    break;
                case "2":
                    displayUserExperienceAndLevel();
                    break;
                case "3":
                    displayTopList();
                    break;
                case "4":
                    challenge.startChallenge();
                    break;
                case "5":
                    newsService.displayNews();
                    break;
                case "6":
                    System.out.println("Avslutar...");
                    running = false;
                    break;
                default:
                    System.out.println("Ogiltigt alternativ, försök igen.");
                    break;
            }
        }
    }

    private void displaySwedishMenu() {
        System.out.println("Välkommen till TypeSpeeder-applikationen");
        System.out.println("1. Spel-läge");
        System.out.println("2. Visa erfarenhet, nivå och spelarnamn");
        System.out.println("3. Visa topplista");
        System.out.println("4. Utmaningsläge");
        System.out.println("5. Nyheter och uppdateringar");
        System.out.println("6. Avsluta");
        System.out.print("Välj ett alternativ: ");
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

    public void chooseLanguage() {
        System.out.println("1. English\n2. Svenska");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        isEnglish = (choice == 1);
        System.out.println(isEnglish ? "Language set to English." : "Språk inställt på svenska.");
    }

    private void displayUserExperienceAndLevel() {
        User currentUser = loginService.getCurrentUser();
        if (currentUser != null) {
            System.out.println("Experience: " + currentUser.getExperience() + ", Level: " + currentUser.getLevel() + ", Playername: " + currentUser.getPlayername());
        } else {
            System.out.println("No user logged in.");
        }
    }

    private void displayTopList() {
        List<User> userList = userRepository.findAllByOrderByExperienceDesc();
        System.out.println("Top List:");
        System.out.println("-----------------------------");
        System.out.println("Place\tUser\tXP\t\tLevel");

        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            System.out.println((i + 1) + "\t\t" + user.getUsername() + "\t" + user.getExperience() + "\t\t" + user.getLevel());
        }
    }

}
