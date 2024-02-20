package se.ju23.typespeeder.entity;


import jakarta.persistence.*;

@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Change the field name to 'id' and keep the primary key

    private int result;

    @ManyToOne
    @JoinColumn(name = "user_id") // Adjust the column name as per your User entity
    private User user; // Add a reference to the User entity

    public Result() {
    }

    public Result(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", result=" + result +
                ", user=" + user +
                '}';
    }
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;
    private int result;
    private User user;

    public Result(int userid, int result) {
        this.userid = userid;
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUserid() {
        return userid;
    }

    @Override
    public String toString() {
        return "Result{" +
                "userid=" + userid +
                ", result=" + result +
                ", user=" + user +
                '}';
    }*/
}
