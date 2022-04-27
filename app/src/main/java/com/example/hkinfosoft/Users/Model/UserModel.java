package com.example.hkinfosoft.Users.Model;

import com.google.gson.JsonObject;

public class UserModel {
    String gender,email,phone,cell;
    JsonObject name;
    JsonObject location;
    JsonObject login;
    JsonObject dob;
    JsonObject registered;
    JsonObject id;
    JsonObject picture;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public JsonObject getName() {
        return name;
    }

    public void setName(JsonObject name) {
        this.name = name;
    }

    public JsonObject getLocation() {
        return location;
    }

    public void setLocation(JsonObject location) {
        this.location = location;
    }

    public JsonObject getLogin() {
        return login;
    }

    public void setLogin(JsonObject login) {
        this.login = login;
    }

    public JsonObject getDob() {
        return dob;
    }

    public void setDob(JsonObject dob) {
        this.dob = dob;
    }

    public JsonObject getRegistered() {
        return registered;
    }

    public void setRegistered(JsonObject registered) {
        this.registered = registered;
    }

    public JsonObject getId() {
        return id;
    }

    public void setId(JsonObject id) {
        this.id = id;
    }

    public JsonObject getPicture() {
        return picture;
    }

    public void setPicture(JsonObject picture) {
        this.picture = picture;
    }

    public JsonObject getInfo() {
        return info;
    }

    public void setInfo(JsonObject info) {
        this.info = info;
    }

    JsonObject info;
}
