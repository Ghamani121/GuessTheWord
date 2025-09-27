package com.guesstheword.backend.models;
import java.time.LocalDateTime;

public class User {
    private int user_id;
    private String username;
    private String hash_password;
    private boolean is_admin;
    private LocalDateTime created_at;

    //consturctors
    public User(){}

    public User(String username,String hash_password,boolean is_admin){
        this.username=username;
        this.hash_password=hash_password;
        this.is_admin=is_admin;
        this.created_at=LocalDateTime.now();
    }

    public User(int user_id,String username,String hash_password,boolean is_admin,LocalDateTime created_at){
        this.user_id=user_id;
        this.username=username;
        this.hash_password=hash_password;
        this.is_admin=is_admin;
        this.created_at=created_at;
    }

    public int getUserId() {return user_id;}
    public void setUserId(int user_id) {this.user_id=user_id;}

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getHashPassword() { return hash_password; }
    public void setHashPassword(String hash_password) { this.hash_password = hash_password; }

    public boolean isAdmin() { return is_admin; }
    public void setAdmin(boolean admin) { is_admin = admin; }

    public LocalDateTime getCreatedAt() { return created_at; }
    public void setCreatedAt(LocalDateTime created_at) { this.created_at = created_at; }

    
}

