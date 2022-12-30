
/**
 * Represents an object of type Actor. An Actor has a name and a gender.
 *
 * @author (Stella K.) Kelly Cao, Alexa Halim, Marleigh Ausbrooks 
 * @version (28 Nov 2021, 4:35pm)
 */
public class Actor
{
    private String name;
    private String gender;

    /**
     * Constructor for objects of class Actor
     */
    public Actor(String name, String gender){
        this.name = name; 
        this.gender = gender; 
    }

    /**
     * Returns the name of this actor
     * 
     * @return The name of this actor
     */
    public String getName(){
        return this.name; 
    }

    /**
     * Returns the gender of this actor
     * 
     * @return The gender of this actor
     */
    public String getGender(){
        return this.gender;
    }

    /**
     * Sets the name of this actor
     * 
     * @param name  name of this actor
     */
    public void setName(String name ){
        this.name = name;
    }

    /**
     * Sets the gender of this actor
     * 
     * @param gender    gender of this actor
     */
    public void setGender(String gender){
        this.gender = gender; 
    }

    /**
     * This method is defined here because Actor (mutable) is used as a key in a Hashtable.
     * It makes sure that same Actors have always the same hash code.
     * So, the hash code of any object taht is used as key in a hash table,
     * has to be produced on an *immutable* quantity,
     * like a String (such a string is the name of the actor in our case)
     * 
     * @return an integer, which is the has code for the name of the actor
     */
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Tests this actor against the input one and determines whether they are equal.
     * Two actors are considered equal if they have the same name and gender.
     * 
     * @return true if both objects are of type Actor, 
     * and have the same name and gender, false in any other case.
     */
    public boolean equals(Object other) {
        if (other instanceof Actor) {
            return this.name.equals(((Actor) other).name) && 
            this.gender.equals(((Actor) other).gender); // Need explicit (Actor) cast to use .name
        } else {
            return false;
        }
    }
    
    /**
     * Retrns a string representation of this Actor. 
     * For easier testing, in the current version it 
     * returns only the name of the actor. Normally, 
     * it should also include their gender as well.
     * 
     * @return a string representation of this actor containing their 
     *          name and gender. 
     */
    public String toString(){
        String s = "Name: " + getName() + " Gender: " + getGender();
        return s; 
    }

    public static void main (String args[]){
        System.out.println("*---------------------*");
        System.out.println("Testing Actor Class");
        System.out.println("*---------------------*");
        Actor a1 = new Actor("Chris Evans", "Male"); 
        Actor a2 = new Actor("Emma D'arcy","Non-binary"); 
        System.out.println("Actor 1\n" + a1.toString() + "\nActor 2\n" + a2.toString()); 
        a1.setName("Alexa"); 
        System.out.println("Actor 1 New Name: " + a1.getName()); 
        a1.setGender("Female"); 
        System.out.println("Actor 1 New Gender: " + a1.getGender()); 
        System.out.println("Actor 1 equals Actor 2 (expected false): " + a1.equals(a2));
        Actor a3 = new Actor("Emma D'arcy","Non-binary");
        System.out.println("Actor 3\n" + a3.toString());
        System.out.println("Actor 2 equals Actor 3 (expected true): " + a2.equals(a3));
    }

}
