package se.ju23.typespeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import se.ju23.typespeeder.logic.GameLogic;
import se.ju23.typespeeder.logic.MenuLogic;
import se.ju23.typespeeder.service.LoginService;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        MenuLogic menuLogic = context.getBean(MenuLogic.class);
        menuLogic.displayMainMenu();

        GameLogic gameLogic = new GameLogic();
        gameLogic.startGame();

    }
}
