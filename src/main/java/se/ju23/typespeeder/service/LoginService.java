package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.entity.User;
import se.ju23.typespeeder.entity.UserRepository;

import java.util.Optional;

/*@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    public boolean login(String username, String password) {
        return userRepository.existsByUsernameAndPassword(username, password);
    }
}*/
@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    // Variable to hold the currently logged-in user
    private User currentUser;

    public boolean login(String username, String password) {
        Optional<User> foundUser = userRepository.findByUsernameAndPassword(username, password);
        if (foundUser.isPresent()) {
            currentUser = foundUser.get(); // Set the current user on successful login
            return true;
        } else {
            return false;
        }
    }
    public void updateUserExperience(int points) {
        if(points>0) {
            if (shouldAddLevel(points)) {
                this.currentUser.setLevel(this.currentUser.getLevel() + 1);
            }
            storePoints(points);
        }
        if(points<0){
            if (shouldDecreesLevel(points)){
                this.currentUser.setLevel(this.currentUser.getLevel() - 1);
            }
            storePoints(points);
        }
    }

private void storePoints(int points){
    this.currentUser.addPoints(points);
    this.userRepository.save(this.currentUser);
}
        private boolean shouldAddLevel(int points) {
            long newExperience = this.currentUser.getExperience() + points;

            long nextMultipleOfTen = (this.currentUser.getExperience() / 10 + 1) * 10;

            return newExperience >= nextMultipleOfTen;
        }

    private boolean shouldDecreesLevel(int points) {
        long newExperience = this.currentUser.getExperience() + points;
        long lastMultipleOfTen = (this.currentUser.getExperience() / 10) * 10;

        return newExperience <= lastMultipleOfTen && this.currentUser.getExperience() >= lastMultipleOfTen;
    }

    public User getCurrentUser() {
        // Method to get the currently logged-in user
        return currentUser;
    }
}