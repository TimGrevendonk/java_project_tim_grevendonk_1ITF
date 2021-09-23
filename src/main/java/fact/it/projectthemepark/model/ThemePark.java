//grevendonk Tim r0831309
package fact.it.projectthemepark.model;

import java.util.ArrayList;

public class ThemePark {
    private String name;
    private int numberVisitors;
    private ArrayList<Attraction> attractions = new ArrayList<>();
// constructors
    public ThemePark(String name) {
        this.name = name;
    }
// methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberVisitors() {
        return numberVisitors;
    }

    public ArrayList<Attraction> getAttractions() {
        return attractions;
    }
    public int getNumberOfAttractions() {
        return attractions.size();
    }
    public void addAttraction(Attraction attraction) {
        attractions.add(attraction);
    }

    public Attraction searchAttractionByName(String name) {
        // of Attraction(Class) for every attraction(local variable) of(:) attractions(array)
        for (Attraction attraction : attractions) {
        // get the name of that attraction, if it equals the variable name return that, ignores capitals
            if (attraction.getName().equalsIgnoreCase(name)) {
                return attraction;
            }
        }
        return null;
    }
    public void registerVisitor(Visitor visitor) {
        this.numberVisitors += 1;
        visitor.setThemeParkCode(numberVisitors);

    }
}
