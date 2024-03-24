public class Movie {
    final private String title;
    final private String cast;
    final private String director;
    final private String overview;
    final private String runtime;
    final private String userRating;
    public Movie(String title, String cast, String director, String overview, String runtime, String userRating) {
        this.title = title;
        this.cast = cast;
        this.director = director;
        this.overview = overview;
        this.runtime = runtime;
        this.userRating = userRating;
    }

    public String getTitle() {
        return title;
    }

    public String getCast() {return cast;}

    public String getDirector() {return director;}

    public String getOverview() {return overview;}

    public String getRuntime() {return runtime;}

    public String getUserRating() {return userRating;}

    public void printInfo() {
        System.out.println("\nTitle: " + getTitle() + "\nRuntime: " + getRuntime() + " min\nDirected by: " + getDirector() + director + "\nCast: " + cast + "\nOverview: " + overview + "\nUser rating: " + userRating + "\n");
    }

}
