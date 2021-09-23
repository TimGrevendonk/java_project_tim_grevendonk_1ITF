//Grevendonk Tim r0831309
package fact.it.projectthemepark.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Staff extends Person {
    private LocalDate startDate;
    private boolean student;
    private ThemePark employedAt;
// constructors
    public Staff(String firstName, String surName) {
        super(firstName, surName);
        startDate = LocalDate.now();
    }
//methods
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public boolean isStudent() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }

    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern ("dd/MM/yyyy");
        if (isStudent()) {
            return "Staff member " + super.toString() + " (working student) is employed since " + startDate.format(format);
        } else {
            return "Staff member " + super.toString() + " is employed since " + startDate.format(format);
        }
    }
// exam extras

    public ThemePark getEmployedAt() {
        return employedAt;
    }

    public void setEmployedAt(ThemePark employedAt) {
        this.employedAt = employedAt;
    }
}
