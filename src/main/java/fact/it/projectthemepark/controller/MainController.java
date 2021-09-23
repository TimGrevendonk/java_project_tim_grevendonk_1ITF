package fact.it.projectthemepark.controller;

import fact.it.projectthemepark.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class MainController {
    /*You will need these ArrayLists later on.*/
    private ArrayList<Staff> staffMembers;
    private ArrayList<Visitor> visitors;
    private ArrayList<ThemePark> themeParks;

    @PostConstruct
    private void fillInformation() {
        this.staffMembers = fillStaffMembers();
        this.visitors = fillVisitors();
        this.themeParks = fillThemeParks();
    }

//    go to staffdetails exam edition
    @RequestMapping("/0_exam")
    public String staffdetails(HttpServletRequest request, Model model) {

// get values from page
        String firstName = request.getParameter("firstName");
        String surName = request.getParameter("surName");
// make the "employedSince" String into a localDate
        String employedSinceStr = request.getParameter("employedSince");
        LocalDate employedSince = LocalDate.parse(employedSinceStr);
// check if the student box was checked, if not it would be NULL
        Boolean student = (request.getParameter("student") != null);
// check if the thempark was selected, or divert to error page
        if (request.getParameter("themePark") != null && Integer.parseInt(request.getParameter("themePark")) < 0) {
            model.addAttribute("errormessage", "You did not choose a theme park!");
            return "error";
        }
        ThemePark themePark = themeParks.get(Integer.parseInt(request.getParameter("themePark")));
// make a new staff member with the constructor of Staff + set extra parameters
        Staff staff = new Staff(firstName, surName);
        staff.setStartDate(employedSince);
        staff.setStudent(student);
        staff.setEmployedAt(themePark);
// add the staff member to the staffMembers list
        staffMembers.add(staff);
// also add the current staff member to model to have the welcome on the next page
        model.addAttribute("staff", staff);

        return "/0_exam";

    }

    @RequestMapping("/1_newVisitor")
    public String newVisitor(Model model) {

        model.addAttribute("themeParks", themeParks);

        return "1_newVisitor";
    }

    @RequestMapping("/2_welcome")
    public String newVisitor(HttpServletRequest request, Model model) {
// get values from page
        String firstName = request.getParameter("firstName");
        String surName = request.getParameter("surName");
        int yearOfBirth = Integer.parseInt(request.getParameter("birthYear"));
// make the new visitor with the constructor of Visitor + add the birth year
        Visitor visitor = new Visitor(firstName, surName);
        visitor.setYearOfBirth(yearOfBirth);
// get the themeParks array and find the value at index (index got from radio buttons)
        ThemePark themePark = themeParks.get(Integer.parseInt(request.getParameter("themePark")));
// from that themePark, invoke the method:
        themePark.registerVisitor(visitor);
// add the visitor bound to its parameters to the visitors list
        visitors.add(visitor);
// also add the current visitor to model to have the welcome on the next page
        model.addAttribute("visitor", visitor);

        return "2_welcome";
    }

    @RequestMapping("/3_newStaff")
    public String newStaff(Model model) {

//      add themeparks for dropdown list
        model.addAttribute("themeParkList", themeParks);

        return "3_newStaff";
    }

    @RequestMapping("/4_staffDetails")
    public String newStaff(HttpServletRequest request, Model model) {
// get values from page
        String firstName = request.getParameter("firstName");
        String surName = request.getParameter("surName");
// make the "employedSince" String into a localDate
        String employedSinceStr = request.getParameter("employedSince");
        LocalDate employedSince = LocalDate.parse(employedSinceStr);
// check if the student box was checked, if not it would be NULL
        Boolean student = (request.getParameter("student") != null);
// make a new staff member with the constructor of Staff + set extra parameters
        Staff staff = new Staff(firstName, surName);
        staff.setStartDate(employedSince);
        staff.setStudent(student);
// add the staff member to the staffMembers list
        staffMembers.add(staff);
// also add the current staff member to model to have the welcome on the next page
        model.addAttribute("staff", staff);

        return "4_staffDetails";
    }

    @RequestMapping("/5_staffMembers")
    public String staffMembers(Model model) {
// add list to model. for text, buttons or list items on next page
        model.addAttribute("memberList", staffMembers);

        return "5_staffMembers";
    }

    @RequestMapping("6_allVisitors")
    public String Visitors(Model model) {
// add list to model. for text, buttons or list items on next page
        model.addAttribute("visitorList", visitors);

        return "6_allVisitors";
    }

    @RequestMapping("/7_newPark")
    public String newPark() {
        return "7_newPark";
    }

    @RequestMapping("/8_allThemeParks")
    public String allThemeParks(HttpServletRequest request, Model model) {
// get values from page
        String themePark = request.getParameter("themePark");
// if themePark is NULL, do not add it
// ("all theme parks" button links directly to this page so no themePark value is created)
        if (themePark != null) {
            ThemePark newPark = new ThemePark(themePark);
            themeParks.add(newPark);
        }
// add list to model. for text, buttons or list items on next page
        model.addAttribute("themeParkList", themeParks);

        return "8_allThemeParks";
    }

    @RequestMapping("/9_newAttraction")
    public String newAttraction(Model model) {
// add lists to model. for text, buttons or list items on next page
        model.addAttribute("themeParkList", themeParks);
        model.addAttribute("staffMemberList", staffMembers);
        return "9_newAttraction";
    }

    @RequestMapping("/10_allAttractions")
    public String allAttraction(HttpServletRequest request, Model model) {
// go to error page if a value wasn't selected but skip/do not compare null values
        if (request.getParameter("park") != null && Integer.parseInt(request.getParameter("park")) < 0) {
            model.addAttribute("errormessage", "No theme park has been selected!");
            return "error";
        }
        if (request.getParameter("staff") != null && Integer.parseInt(request.getParameter("staff")) < 0) {
            model.addAttribute("errormessage", "No staff member has been selected!");
            return "error";
        }
// get the themeParks array and find the value at index (index got from radio buttons/dropdown/link indexes form 8_ )
        ThemePark themePark = themeParks.get(Integer.parseInt(request.getParameter("park")));
// checks if no values are entered in staff, if no value skip the adding of the attraction
        if (request.getParameter("staff") != null) {
// get values from page (name, duration, photo)
            String attractionName = request.getParameter("name");
            int duration = Integer.parseInt(request.getParameter("duration"));
            String photo = request.getParameter("photo");
// get the staffMembers array and find the value at index (index got from radio buttons/dropdown)
            Staff responsible = staffMembers.get(Integer.parseInt(request.getParameter("staff")));
// creat new attraction with Attraction constructor + set parameters.
            Attraction attraction = new Attraction(attractionName, duration);
            attraction.setPhoto("/img/" + photo);
            attraction.setResponsible(responsible);
// add the attraction to the themePark got from the themeParks array
            themePark.addAttraction(attraction);
// add the themPark for the title name
        }
        model.addAttribute("themePark", themePark);
// add the attractions array of the current themePark for text, buttons and list items on next page.
        ArrayList<Attraction> attractions = themePark.getAttractions();
        model.addAttribute("attractions", attractions);

        return "10_allAttractions";
    }

    @RequestMapping("/11_attractionName")
    public String attractionName(HttpServletRequest request, Model model) {
// check if a value is entered as an attraction
        if (request.getParameter("attraction").length() < 1) {
            model.addAttribute("errormessage", "No attraction has been entered!");
            return "error";
        }
// get the attraction entered in the form
        String attractionName = request.getParameter("attraction");
// get the themParks and check every attraction per themePark
        ArrayList<ThemePark> themePark = themeParks;
        for (ThemePark park : themePark) {
// set the attraction and return it if it matches
            if (park.searchAttractionByName(attractionName) != null) {
                model.addAttribute("attraction", park.searchAttractionByName(attractionName));
                return "11_attractionName";
            }
        }
// if nothing is returned, return no attraction with that name message
        model.addAttribute("errormessage", "No attraction with that name found.");
        return "error";
    }

    /*You will need these methods later on.*/
    private ArrayList<Staff> fillStaffMembers() {
        ArrayList<Staff> staffMembers = new ArrayList<>();

        Staff staff1 = new Staff("Johan", "Bertels");
        staff1.setStartDate(LocalDate.of(2002, 5, 1));
        Staff staff2 = new Staff("An", "Van Herck");
        staff2.setStartDate(LocalDate.of(2019, 3, 15));
        staff2.setStudent(true);
        Staff staff3 = new Staff("Bruno", "Coenen");
        staff3.setStartDate(LocalDate.of(1995, 1, 1));
        Staff staff4 = new Staff("Wout", "Dayaert");
        staff4.setStartDate(LocalDate.of(2002, 12, 15));
        Staff staff5 = new Staff("Louis", "Petit");
        staff5.setStartDate(LocalDate.of(2020, 8, 1));
        staff5.setStudent(true);
        Staff staff6 = new Staff("Jean", "Pinot");
        staff6.setStartDate(LocalDate.of(1999, 4, 1));
        Staff staff7 = new Staff("Ahmad", "Bezeri");
        staff7.setStartDate(LocalDate.of(2009, 5, 1));
        Staff staff8 = new Staff("Hans", "Volzky");
        staff8.setStartDate(LocalDate.of(2015, 6, 10));
        staff8.setStudent(true);
        Staff staff9 = new Staff("Joachim", "Henau");
        staff9.setStartDate(LocalDate.of(2007, 9, 18));
        staffMembers.add(staff1);
        staffMembers.add(staff2);
        staffMembers.add(staff3);
        staffMembers.add(staff4);
        staffMembers.add(staff5);
        staffMembers.add(staff6);
        staffMembers.add(staff7);
        staffMembers.add(staff8);
        staffMembers.add(staff9);
        return staffMembers;
    }

    private ArrayList<Visitor> fillVisitors() {
        ArrayList<Visitor> visitors = new ArrayList<>();
        Visitor visitor1 = new Visitor("Dominik", "Mioens");
        visitor1.setYearOfBirth(2001);
        Visitor visitor2 = new Visitor("Zion", "Noops");
        visitor2.setYearOfBirth(1996);
        Visitor visitor3 = new Visitor("Maria", "Bonetta");
        visitor3.setYearOfBirth(1998);
        Visitor visitor4 = new Visitor("Tim", "Grevendonk");
        visitor4.setYearOfBirth(1997);
        visitors.add(visitor1);
        visitors.add(visitor2);
        visitors.add(visitor3);
        visitors.add(visitor4);
        visitors.get(0).addToWishList("De grote golf");
        visitors.get(0).addToWishList("Sky Scream");
        visitors.get(1).addToWishList("Piratenboot");
        visitors.get(1).addToWishList("Sky Scream");
        visitors.get(1).addToWishList("Halvar the ride");
        visitors.get(1).addToWishList("DreamCatcher");
        visitors.get(2).addToWishList("DinoSplash");
        visitors.get(3).addToWishList("Piratenboot");
        visitors.get(3).addToWishList("Halvar the ride");
        return visitors;
    }

    private ArrayList<ThemePark> fillThemeParks() {
        ArrayList<ThemePark> themeparks = new ArrayList<>();
        ThemePark themepark1 = new ThemePark("Plopsaland");
        ThemePark themepark2 = new ThemePark("Plopsa Coo");
        ThemePark themepark3 = new ThemePark("Holiday Park");
        Attraction attraction1 = new Attraction("Anubis the Ride", 60);
        Attraction attraction2 = new Attraction("De grote golf", 180);
        Attraction attraction3 = new Attraction("Piratenboot", 150);
        Attraction attraction4 = new Attraction("SuperSplash", 258);
        Attraction attraction5 = new Attraction("Dansende fonteinen");
        Attraction attraction6 = new Attraction("Halvar the ride", 130);
        Attraction attraction7 = new Attraction("DinoSplash", 240);
        Attraction attraction8 = new Attraction("Bounty Tower", 180);
        Attraction attraction9 = new Attraction("Sky Scream", 50);
        attraction1.setPhoto("/img/anubis the ride.jpg");
        attraction2.setPhoto("/img/de grote golf.jpg");
        attraction3.setPhoto("/img/piratenboot.jpg");
        attraction4.setPhoto("/img/supersplash.jpg");
        attraction5.setPhoto("/img/dansende fonteinen.jpg");
        attraction6.setPhoto("/img/halvar the ride.jpg");
        attraction7.setPhoto("/img/dinosplash.jpg");
        attraction8.setPhoto("/img/bountytower.jpg");
        attraction9.setPhoto("/img/sky scream.jpg");
        attraction1.setResponsible(staffMembers.get(0));
        attraction2.setResponsible(staffMembers.get(1));
        attraction3.setResponsible(staffMembers.get(2));
        attraction4.setResponsible(staffMembers.get(3));
        attraction5.setResponsible(staffMembers.get(4));
        attraction6.setResponsible(staffMembers.get(5));
        attraction7.setResponsible(staffMembers.get(6));
        attraction8.setResponsible(staffMembers.get(7));
        attraction9.setResponsible(staffMembers.get(8));
        themepark1.addAttraction(attraction1);
        themepark1.addAttraction(attraction2);
        themepark1.addAttraction(attraction3);
        themepark1.addAttraction(attraction4);
        themepark2.addAttraction(attraction5);
        themepark2.addAttraction(attraction6);
        themepark3.addAttraction(attraction7);
        themepark3.addAttraction(attraction8);
        themepark3.addAttraction(attraction9);
        themeparks.add(themepark1);
        themeparks.add(themepark2);
        themeparks.add(themepark3);
        return themeparks;
    }

}

