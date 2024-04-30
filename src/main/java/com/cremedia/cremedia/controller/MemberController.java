package com.cremedia.cremedia.controller;

public class MemberController {

public String getMember() {
        return "Member";
    }

    public String getAll() {
        return "Members";
    }

    public String add() {
        return "Member added";
    }

    public String update() {
        return "Member updated";
    }

    public String delete() {
        return "Member deleted";
    }

    public String getById() {
        return "Member by id";
    }

    public String getByName() {
        return "Member by name";
    }

    public String getBySurname() {
        return "Member by surname";
    }

    public String getByNameAndSurname() {
        return "Member by name and surname";
    }
}
