package com.cremedia.cremedia.models;


import java.time.LocalDate;

public class User2 {

    Integer id;
    String username;
    String name;
    String surname;
    String email;
    String number;
    String password;
    String avatar;
    String bio;
    String tag;
    LocalDate dob;
    String gender;
    String country;
    Boolean visibility;
    Integer followers;
    Integer followees;
    Boolean active;
    Boolean pro;
    Boolean verified;
    LocalDate last_login;

    public User2(Integer id, String username, String name, String surname, String email, String number, String password, String avatar, String bio, String tag, LocalDate dob, String gender, String country, Boolean visibility, Integer followers, Integer followees, Boolean active, Boolean pro, Boolean verified, LocalDate last_login){
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.number = number;
        this.password = password;
        this.avatar = avatar;
        this.bio = bio;
        this.tag = tag;
        this.dob = dob;
        this.gender = gender;
        this.country = country;
        this.visibility = visibility;
        this.followers = followers;
        this.followees = followees;
        this.active = active;
        this.pro = pro;
        this.verified = verified;
        this.last_login = last_login;

    }

    public User2() {

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername(){
        return username;
    }
    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username){
        this.username = username;
    }
    public void setNumber(String  number) {
        this.number = number;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getAvatar() {
        return avatar;

    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getTag() {
        return tag;
    }


    public void setTag(String tag) {
        this.tag = tag;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender){
        this.gender = gender;

    }

    public String getCountry(){
        return country;
    }

    public Boolean getVisibility(){
        return visibility;
    }

    public void setVisibility(Boolean visibility){
        this.visibility = visibility;
    }
    public Integer getFollowers(){
        return followers;
    }

    public Integer getFollowees(){
        return followees;
    }

    public Boolean getActive(){
        return active;
    }

    public Boolean getPro(){
        return pro;
    }

    public Boolean getVerified(){
        return verified;
    }

    public LocalDate getLast_login(){
        return last_login;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public void setFollowers(Integer followers){
        this.followers = followers;
    }

    public void setFollowees(Integer followees){
        this.followees = followees;
    }

    public void setActive(Boolean active){
        this.active = active;
    }

    public void setPro(Boolean pro){
        this.pro = pro;
    }

    public void setVerified(Boolean verified){
        this.verified = verified;
    }

    public void setLast_login(LocalDate last_login){
        this.last_login = last_login;
    }


}
