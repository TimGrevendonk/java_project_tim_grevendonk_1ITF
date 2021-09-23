//Grevendonk Tim r0831309
package fact.it.projectthemepark.model;

import org.apache.catalina.valves.rewrite.InternalRewriteMap;

public class Person {
    private String firstName;
    private String surName;
// constructors
    public Person() {
    }

    public Person(String firstName, String surName) {
        this.firstName = firstName;
        this.surName = surName;
    }
// methods
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String toString() {
        return surName.toUpperCase() + " " + firstName;
    }
}
