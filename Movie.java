import java.util.Hashtable;
import java.util.Vector;
import java.util.LinkedList;
import java.util.Enumeration;
import java.util.Scanner;
import java.io.*;

/**
 * Represents an object of type Movie.
 * A Movie object has a title, some Actors, and results for the twelve Bechdel tests.
 *
 * @author (Stella K.) Kelly Cao, Alexa Halim, Marleigh Ausbrooks 
 * @version (28 Nov 2021, 4:35pm)
 */
public class Movie implements Comparable<Movie> 
{
    private String title;
    private Hashtable<Actor, String> cast;
    private Vector<String> testResults;

    /**
     * Constructor for objects of class Movie. No actors and no results 
     * available yet.
     */
    public Movie(String title) {
        this.title = title;
        cast = new Hashtable<Actor, String>();
        testResults = new Vector<String>(); 
    }

    /**
     * Returns the movie's title
     * 
     * @return The title of this movie
     */
    public String getTitle() {
        return this.title;
    }
    
    /**
     * Returns the movie's actors in a Hashtable
     * 
     * @return A Hashtable with all the actors who played in this movie
     */
    public Hashtable<Actor, String> getAllActors() {
        return this.cast;
    }

    /**
     * returns a Linked List with all the actor names who played in 
     * this movie.
     * 
     * @return A LinkedList with the names of all the actors who played 
     *         in this movie
     */
    public LinkedList<String> getActors() {
        LinkedList<String> actors = new LinkedList<String>();
        Enumeration<Actor> e = cast.keys();
        while (e.hasMoreElements()) {
            Actor anActor = e.nextElement();
            actors.add(anActor.getName());
        }
        return actors;
    }

    /**
     * returns a Vector with all the Bechdel test results for this movie
     * 
     * @return A Vector with the Bechdel test results for this movie: A 
     * test result can be "1" or "0" indicating that this move passed 
     * or did not pass the corresponding test.
     */
    public Vector<String> getAllTestResults() {
        return this.testResults;
    }

    /**
     * populates the testResults vector with "0" and "1"s, each 
     * representing the result of the coresponding test on this movie. 
     * This information will be read from the file 
     * "nextBechdel_allTests.csv"
     * 
     * @param results   string consisting of of 0's and 1's. Each one 
     *                  of these values denotes the result of the 
     *                  corresponding test on this movie
     */
    public void setTestResults(String results) {
        Vector<String> newTests = new Vector<String>();
        String[] result = results.split(",");
        for (int i = 0; i < result.length; i++) {
            newTests.add(result[i]);
        }
        this.testResults = newTests;
    }

    /**
     * Tests this movie object with the input one and determines whether they are equal.
     * 
     * @return true if both objects are movies and have the same title, 
     * false in any other case.
     */
    public boolean equals(Object other) {
        if (other instanceof Movie) {
            return this.title.equals(((Movie) other).title); // Need explicit (Movie) cast to use .title
        } else {
            return false;
        }
    }

    /**
     * Takes in a String, formatted as lines are in the input file 
     * ("nextBechdel_castGender.txt"), generates an Actor, and adds the 
     * object to the actors of this movie. Input String has the 
     * following formatting: "ACTOR","CHARACTER_NAME","TYPE","BILLING",
     * "GENDER" Example of input: "Trolls","Ricky Dillon","Aspen Heitz",
     * "Supporting","18","Male"
     * 
     * @param line  String representing the information of each Actor
     * @return      The Actor that was just added to this movie
     */
    public Actor addOneActor(String line) {
        int comma = line.indexOf(",");
        String nameRest = line.substring(comma+2); 
        comma = nameRest.indexOf(","); 
        String name = nameRest.substring(0, comma - 1);
        String charRest = nameRest.substring(comma+2);
        comma = charRest.indexOf(",");
        String typeRest = charRest.substring(comma +2);
        int lastComma = typeRest.lastIndexOf(",");
        String gender = typeRest.substring(lastComma +2, typeRest.length() -1);
        comma = typeRest.indexOf(",");
        String type = typeRest.substring(0, comma-1);
        Actor anActor = new Actor(name, gender);
        cast.put(anActor, type);
        return anActor;
    }

    /**
     * Reads the input file ("nextBechdel_castGender.txt"), and adds all 
     * its Actors to this movie. Each line in the movie has the 
     * following formatting: Input String has the following formatting: 
     * "MOVIE TITLE","ACTOR","CHARACTER_NAME","TYPE","BILLING","GENDER" 
     * Example of input: "Trolls","Ricky Dillon","Aspen Heitz",
     * "Supporting","18","Male"
     * 
     * @param actorsFile    The file containing information on each 
     *                      actor who acted in the movie.
     */
    public void addAllActors(String actorsFile) {
        try{
            Scanner fileScan = new Scanner (new File(actorsFile)); 
            while(fileScan.hasNext()){
                String line = fileScan.nextLine(); 
                int comma = line.indexOf(","); 
                String movieTitle = line.substring(1, comma-1); 
                if(movieTitle.equals(this.title)){
                    this.addOneActor(line);
                }
            }
            fileScan.close(); 
        } catch(IOException ex){
            System.out.println(ex); 
        }
    }

    /**
     * Returns the movie's feministScore, which is based off whether
     * they pass the Villareal, Ko, Rees-Davies tests:
     *     0 if they pass none
     *     1 if they pass only one
     *     2 if they pass two
     *     3 if they pass all three
     * 
     * @returns their feministScore
     */
    public int feministScore() {
        int returnCount = 0;
        // get each movie (which contains name and test results in passWhite
        // check test result for Rees-Davies and if "1", add to whiteNotRees
        if (this.getAllTestResults().get(12).equals("0")){
            returnCount+= 1; // RD test
        }
        if (this.getAllTestResults().get(6).equals("0")){
            returnCount +=1; // Ko test
        }
        if(this.getAllTestResults().get(4).equals("0")){
            returnCount +=1; // Villareal
        }
        return returnCount;
    }
    
    /**
     * Method compareTo() 
     * Compares the invoking and input movies by the feministscore, 
     * breaking the tie lexicographically by movie title
     * 
     * @param other     Movie to be compared to this movie 
     * @return int      1 if the invoking movie's feminist score is higher
     *                      or if the feminist scores are the same, 
     *                      invoking movie is ranked lexicographically 
     *                      higher
     *                  -1 if the invoking movie's feminist score is lower
     *                      or if the feminist scores are the same, 
     *                      invoking movie is ranked lexicographically 
     *                      lower
     */
    public int compareTo(Movie other){
        //both feminist scores are the same 
        if((this.feministScore()-other.feministScore() == 0)){
            if (this.title.compareTo(other.getTitle()) < 0)
                return 1; // a movie's title lexicographically ranked higher
            else
                return -1;
        }
        //other feminist score is ranked lower 
        if((this.feministScore()-other.feministScore() > 0)){
            return 1; 
        }
        //other feminist score is ranked higher
        else
        return -1; 
    }

    /**
     * Retrns a string representation of this movie. for easier testing, 
     * in the current version it returns only the title of the movie and 
     * perhaps the number of actors in it.
     * 
     * @return a reasonable string representation of this movie: 
     * includes the title and the number of actors who played in it.
     */
    public String toString(){
        try {
            String s = "Movie title: " + this.title + " Actors: " + cast.size()
                + " Feminist Score: " + this.feministScore();
            return s; 
        } catch (ArrayIndexOutOfBoundsException ex) {
            String s = "*Movie must set test results before receiving a feminist score*\n";
            s += "Movie title: " + this.title + " Actors: " + cast.size();
            return s;
        }
    }

    public static void main (String[] args){
        System.out.println("*---------------------*");
        System.out.println("Testing Movie Class");
        System.out.println("*---------------------*");
        System.out.println("*---------- Small Data File -----------*");
        Movie m1 = new Movie("Alpha");
        System.out.println("Movie 1 Title: " + m1.getTitle()); 
        m1.addAllActors("data/small_castGender.txt");
        System.out.println(m1); 
        m1.setTestResults("0,0,0,1,0,0,0,1,0,0,1,1,1");
        System.out.println("Movie 1 Actors: " + m1.getAllActors()); 
        System.out.println("Movie 1 Test Results: " + m1.getAllTestResults());
        System.out.println("Movie 1 Feminist Score (expected 2): " + m1.feministScore());
        
        System.out.println("*---------- Complete Data File -----------*");
        Movie m2 = new Movie("Boo! A Madea Halloween");
        m2.addAllActors("data/nextBechdel_castGender.txt");
        m2.setTestResults("0,0,1,1,1,1,0,1,0,0,1,1,1");
        System.out.println(m2); 
        Movie m3 = new Movie("Hidden Figures");
        m3.addAllActors("data/nextBechdel_castGender.txt");
        System.out.println(m3); 
        m3.setTestResults("0,0,0,0,0,1,0,1,0,1,1,1,1");
        System.out.println(m3); 
        System.out.println("Comparing Alpha to Hidden Figures (expected: 1): " + m1.compareTo(m3));
        System.out.println("Comparing Boo! A Madea Halloween to Hidden Figures (expected: -1): " + m2.compareTo(m3));
        
    }
}
