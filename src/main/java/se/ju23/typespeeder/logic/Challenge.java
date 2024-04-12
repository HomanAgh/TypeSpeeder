package se.ju23.typespeeder.logic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.service.LoginService;

import java.util.Scanner;

@Component
public class Challenge {

    private static final Scanner SCANNER = new Scanner(System.in);
    private final LoginService loginService;

    @Autowired
    public Challenge(LoginService loginService) {
        this.loginService = loginService;
    }
    public void startChallenge() { // int så den returnerar poäng
        System.out.println("Type: 'I come from the land down under' as fast as you can!");
        String challengeText = "I come from the land down under";
        long startTime = System.currentTimeMillis();

        String userInput = SCANNER.nextLine();

        long endTime = System.currentTimeMillis();
        double elapsedTimeInSeconds = (endTime - startTime) / 1000.0;

        if(userInput.equalsIgnoreCase(challengeText) && elapsedTimeInSeconds <= 10) {
            System.out.println("Congratulations! You've completed the challenge in " + elapsedTimeInSeconds + " seconds and earned 30 points!");
            loginService.updateUserExperience(10);
        } else {
            loginService.updateUserExperience(-10);
            System.out.println("Challenge failed. You took " + elapsedTimeInSeconds + " seconds. Try again!");
        }
    }
    public String lettersToType() {
        return "I come from the land down under";
    }
}
