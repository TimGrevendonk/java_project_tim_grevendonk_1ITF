//Grevendonk Tim r0831309
package fact.it.projectthemepark.model;

public class Attraction {
    private String name;
    private int duration;
    private String photo;
    private Staff responsible;
// constructors
    public Attraction() {
    }

    public Attraction(String name) {
        this.name = name;
    }

    public Attraction(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }
// methods
    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public String getPhoto() {
        return photo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Staff getResponsible() {
        return responsible;
    }

    public void setResponsible(Staff responsible) {
        this.responsible = responsible;
    }
}

