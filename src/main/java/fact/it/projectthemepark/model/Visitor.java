//Grevendonk Tim r0831309
package fact.it.projectthemepark.model;

import org.thymeleaf.spring5.processor.SpringUErrorsTagProcessor;

import java.util.ArrayList;

public class Visitor extends Person{
    private int yearOfBirth;
    private int themeParkCode;
    private ArrayList<String> wishList = new ArrayList<>();

    public Visitor(String firstName, String surName) {
        super(firstName, surName);
        themeParkCode = -1;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public int getThemeParkCode() {
        return themeParkCode;
    }

    public void setThemeParkCode(int themeParkCode) {
        this.themeParkCode = themeParkCode;
    }

    public ArrayList<String> getWishList() {
        return wishList;
    }

    public boolean addToWishList(String attractionName){
        if (getNumberOfWishes() < 5) {
            wishList.add(attractionName);
            return true;
        }
        return false;

    }
    public int getNumberOfWishes() {
       return wishList.size();
    }
    public String toString() {
        return "Visitor " + super.toString() + " with theme park code " + themeParkCode;
    }
}
