import java.util.LinkedList;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import javafoundations.PriorityQueue;

/**
 * Represents a collection of Movies. Uses a LinkedList to hold the 
 * movie objects. Movie data (title and test results) are coming from a 
 * file named "nextBechdel_allTests.txt". Data regarding actors who 
 * participated in each movie is read from a file named 
 * "nextBechdel_castGender.txt". Both files are provided in the "data" 
 * folder.
 *
 * @author Kelly Cao, Alexa Halim, Marleigh Ausbrooks 
 * @version December 8, 2022
 */
public class MovieCollection
{
    private LinkedList<Movie> movies;
    private LinkedList<Actor> actors;
    private String testsFileName;
    private String castsFileName;

    /**
     * Constructor for objects of class MovieCollection
     */
    public MovieCollection(String testsFileName, String castsFileName)
    {
        // initialise instance variables
        movies = new LinkedList<Movie>();
        actors = new LinkedList<Actor>();
        this.testsFileName = testsFileName;
        this.castsFileName = castsFileName;
    }

    /**
     * Returns the names of all actors in the collection
     * 
     * @returns a LinkedList with the names of all actors
     */
    public LinkedList<String> getActorNames() {
        LinkedList<String> actorNames = new LinkedList<String>();
        for (int i = 0; i < actors.size(); i++) {
            actorNames.add(actors.get(i).getName());
        }
        return actorNames;
    }

    /**
     * Returns all the Actors in the collection
     * 
     * @returns a LinkedList with all the Actors, each complete with 
     * their name and gender.
     */
    public LinkedList<Actor> getActors() {
        return actors;
    }

    /**
     * Returns all the movies in a LinkedList
     * 
     * @returns a LinkedList with all the movies, each complete with 
     * its title, actors and Bechdel test results.
     */
    public LinkedList<Movie> getMovies() {
        return movies;
    }

    /**
     * Returns the titles of all movies in the collection
     * 
     * @returns a LinkedList with the titles of all the movies
     */
    public LinkedList<String> getMovieTitles() {
        LinkedList<String> movieTitles = new LinkedList<String>();
        for (int i = 0; i < movies.size(); i++) {
            movieTitles.add(movies.get(i).getTitle());
        }
        return movieTitles;
    }

    /**
     * Reads the input file, and uses its first column (movie title) to 
     * create all movie objects. Adds the included information on the 
     * Bachdel test results to each movie. It is perhaps better to make 
     * this method private
     */
    public void readMovies() {
        try{
            Scanner fileScan = new Scanner (new File(testsFileName));
            fileScan.nextLine(); //header
            while(fileScan.hasNext()){
                String line = fileScan.nextLine(); 
                int comma = line.indexOf(","); 
                String movieTitle = line.substring(0, comma);
                Movie m1 = new Movie(movieTitle);
                String testResults = line.substring(comma+1); 
                m1.setTestResults(testResults);
                movies.add(m1); 
            }
            fileScan.close(); 
        } catch(IOException ex){
            System.out.println(ex); 
        }
    }

    /**
     * Reads the casts for each movie, from input casts file; Assume 
     * lines in this file are formatted as followes: "MOVIE","ACTOR",
     * "CHARACTER_NAME","TYPE","BILLING","GENDER" For example: "Trolls",
     * "Ricky Dillon","Aspen Heitz","Supporting","18","Male". Notes: 1) 
     * each movie will appear in (potentially) many consecutive lines, 
     * one line per actor. 2) Each token (title, actor name, etc) appears 
     * in double quotes, which need to be removed as soon as the tokes 
     * are read. 3) If a movie does not have any test results, it is 
     * ignored and not included in the collection. (There is actually one 
     * such movie) It is perhaps better to make this method private
     */
    public void readCasts(){

        try{
            Scanner fileScan = new Scanner (new File(castsFileName)); 
            while(fileScan.hasNext()){
                String actor = fileScan.nextLine(); 
                int comma = actor.indexOf(","); 
                String movieTitle = actor.substring(1, comma-1);
                String nameRest = actor.substring(comma+2);
                for (int i = 0; i < movies.size(); i++) {
                    movies.get(i).addAllActors(castsFileName); //populates the cast
                    if(movies.get(i).getTitle().equals(movieTitle)){

                        comma = nameRest.indexOf(","); 
                        String name = nameRest.substring(0, comma-1);  
                        int lastComma = nameRest.lastIndexOf(",");
                        String gender = nameRest.substring(lastComma+2, 
                                nameRest.length()-1);
                        Actor a1 = new Actor(name,gender);
                        actors.add(a1); 
                    } 
                }
            }
            fileScan.close(); 
        } catch(IOException ex){
            System.out.println(ex); 
        }

    }

    
    /**
     * Returns a list of all Movies that pass the n-th Bechdel test
     * 
     * @param n integer identifying the n-th test in the list of 12 
     *          Bechdel alternatives, starting from zero
     * @returns A list of all Movies which have passed the n-th test
     * 
     */
    public LinkedList<Movie> findAllMoviesPassedTestNum(int n) {
        LinkedList<Movie> passedTest = new LinkedList<Movie>();
        for(int i = 0; i < movies.size(); i++ ){
            String result = movies.get(i).getAllTestResults().get(n);
            if (result.equals("0")){
                passedTest.add(movies.get(i));
            }
        }
        return passedTest;
    }
    
    /**
     * Returns a list of all Movies that pass the Bechdel test
     * 
     * @returns A list of all Movies which have passed the Bechdel test
     */
    public LinkedList<Movie> findAllMoviesPassedBechdel() {
        LinkedList<Movie> passBechdel = this.findAllMoviesPassedTestNum(0);
        return passBechdel;
    }
    
    /**
     * Returns a list of all Movies that pass the either of two given tests
     * 
     * @param test1 integer identifying the test1-th test in the list of 12 
     *          Bechdel alternatives, starting from zero
     * @param test2 integer identifying the test2-th test in the list of 12 
     *          Bechdel alternatives, starting from zero
     * @returns A list of all Movies which have passed either test1 or test2
     */
    public LinkedList<Movie> findAllMoviesPassedEither(int test1, int test2) {
        LinkedList<Movie> passEither = new LinkedList<Movie>();
        for (int i = 0; i < this.getMovies().size(); i++) {
            // get each movie (which contains name and test results in passWhite
            // check test result for Rees-Davies and if "1", add to whiteNotRees
            if (this.getMovies().get(i).getAllTestResults().get(test1).equals("0") 
                || this.getMovies().get(i).getAllTestResults().get(test2).equals("0")) {
                passEither.add(this.getMovies().get(i));
            }
        }
        return passEither;
    }
    
    /**
     * Returns a list of all Movies that pass the first given test but not the
     * second test
     * 
     * @param test1 integer identifying the test1-th test in the list of 12 
     *          Bechdel alternatives, starting from zero
     * @param test2 integer identifying the test2-th test in the list of 12 
     *          Bechdel alternatives, starting from zero
     * @returns A list of all Movies which have passed test1 but not test2
     */
    public LinkedList<Movie> findAllMoviesPassedFirstOne(int test1, int test2) {
        LinkedList<Movie> passFirst = this.findAllMoviesPassedTestNum(test1);
        LinkedList<Movie> firstNotSecond = new LinkedList<Movie>();
        for (int i = 0; i < passFirst.size(); i++) {
            // get each movie (which contains name and test results in passWhite
            // check test result for Rees-Davies and if "1", add to whiteNotRees
            if (passFirst.get(i).getAllTestResults().get(test2).equals("1")) {
                firstNotSecond.add(passFirst.get(i));
            }
        }
        return firstNotSecond;
    }
    
    /**
     * Returns a PriorityQueue of movies in the provided data based on 
     * their feminist score. That is, if you enqueue all the movies, 
     * they will be dequeued in priority order: from most feminist to 
     * least feminist.
    
     */
    public PriorityQueue<Movie> rankMovies() {
        PriorityQueue<Movie> results = new PriorityQueue<Movie>(); 
        for(int i = 0; i < movies.size(); i++){
            results.enqueue(movies.get(i)); 
        }
        return results; 
    }
    
    /**
     * Returns a String representing this MovieCollection
     * 
     * @returns a String representation of this collection, including 
     * the number of movies and the movies themselves.
     */
    public String toString() {
        String s = "This movie collection contains " + movies.size() + 
            " movies:\n";
        for (int i = 0; i < movies.size(); i++) {
            s += movies.get(i) + "\n";
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println("*---------------------*");
        System.out.println("Testing MovieCollection Class on Small Data File");
        System.out.println("*---------------------*");
        MovieCollection mc1 = new MovieCollection("data/small_allTests.txt", "data/small_castGender.txt");
        mc1.readMovies();
        mc1.readCasts();
        System.out.println(mc1);
        
        //find all movies that passed the Bechdel test. (1st test) 
        LinkedList<Movie> passBechdel = mc1.findAllMoviesPassedBechdel();
        System.out.println("\nThere are " + passBechdel.size() + " movies"
            + " that pass the Bechdel test:");
        for (int i = 0; i < passBechdel.size(); i++) {
            System.out.println(passBechdel.get(i).getTitle());
        }
        
        //find all movies that passed either the Peirce (2nd test) or the Landau test. (3rd test) 
        LinkedList<Movie> passPeirceOrLandau = mc1.findAllMoviesPassedEither(1, 2);
        System.out.println("\nThere are " + passPeirceOrLandau.size() + " movies"
            + " that pass either Peirce or Landau tests:");
        for (int i = 0; i < passPeirceOrLandau.size(); i++) {
            System.out.println(passPeirceOrLandau.get(i).getTitle());
        }
        
        //find all movies that passed the White test (12th test) but 
        // did *not* pass the Rees-Davies test. (13th test)
        LinkedList<Movie> whiteNotRees = mc1.findAllMoviesPassedFirstOne(11, 12);
        System.out.println("\nThere are " + whiteNotRees.size() + " movies"
            + " that pass the White test but fail the Rees-Davies test:");
        for (int i = 0; i < whiteNotRees.size(); i++) {
            System.out.println(whiteNotRees.get(i).getTitle());
        }
        
        System.out.println("\nRanking movie collection from most feminist to least:");
        PriorityQueue<Movie> feministScores1 = mc1.rankMovies();
        int numMovies1 = feministScores1.size();
        for (int i = 0; i < numMovies1; i++) {
            System.out.println(feministScores1.dequeue());
        }
        
        
        System.out.println("\n*---------------------*");
        System.out.println("Testing MovieCollection Class on nextBechdel Files");
        System.out.println("*---------------------*");
        MovieCollection mc2 = new MovieCollection("data/nextBechdel_allTests.txt", "data/nextBechdel_castGender.txt");
        mc2.readMovies();
        mc2.readCasts();
        System.out.println(mc2);
        
        //find all movies that passed the Bechdel test. (1st test) 
        LinkedList<Movie> passBechdel2 = mc2.findAllMoviesPassedBechdel();
        System.out.println("\nThere are " + passBechdel2.size() + " movies"
            + " that pass the Bechdel test:");
        for (int i = 0; i < passBechdel2.size(); i++) {
            System.out.println(passBechdel2.get(i).getTitle());
        }
        
        //find all movies that passed either the Peirce (2nd test) or the Landau test. (3rd test) 
        LinkedList<Movie> passPeirceOrLandau2 = mc2.findAllMoviesPassedEither(1, 2);
        System.out.println("\nThere are " + passPeirceOrLandau2.size() + " movies"
            + " that pass either Peirce or Landau tests:");
        for (int i = 0; i < passPeirceOrLandau2.size(); i++) {
            System.out.println(passPeirceOrLandau2.get(i).getTitle());
        }
        
        //find all movies that passed the White test (12th test) but 
        // did *not* pass the Rees-Davies test. (13th test)
        LinkedList<Movie> whiteNotRees2 = mc2.findAllMoviesPassedFirstOne(11, 12);
        System.out.println("\nThere are " + whiteNotRees2.size() + " movies"
            + " that pass the White test but fail the Rees-Davies test:");
        for (int i = 0; i < whiteNotRees2.size(); i++) {
            System.out.println(whiteNotRees2.get(i).getTitle());
        }
        
        System.out.println("\nRanking movie collection from most feminist to least:");
        PriorityQueue<Movie> feministScores = mc2.rankMovies();
        int numMovies = feministScores.size();
        for (int i = 0; i < numMovies; i++) {
            System.out.println(feministScores.dequeue());
        }
        
    }
}
