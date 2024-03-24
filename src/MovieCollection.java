import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class MovieCollection {
    private Scanner sc;
    private ArrayList<Movie> movies;
    public MovieCollection() {
        movies = new ArrayList<>();
        readFile();
        printMenu();
    }

    private void readFile() {
        File file = new File("/Users/euhan/IdeaProjects/GavinMovieProject/src/movies");
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] str = sc.nextLine().split(",");
                movies.add(new Movie(str[0], str[1], str[2], str[3],str[4],str[5]));
            }
            sc.close();
        } catch (Exception ignored) { }
        System.out.println(movies.size());
    }

    public void printMenu() {
        sc = new Scanner(System.in);
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = sc.nextLine();

            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    private static void sortTitle(ArrayList<Movie> titles) {
        int count = 0;
        for (int j = 1; j < titles.size(); j++) {
            Movie temp = titles.get(j);
            int possibleIndex = j;
            while (possibleIndex > 0 && temp.getTitle().compareTo(titles.get(possibleIndex - 1).getTitle()) < 0) {
                count++;
                titles.set(possibleIndex, titles.get(possibleIndex - 1));
                possibleIndex--;
            }
            titles.set(possibleIndex, temp);
        }
    }

    private static void sortPeople(ArrayList<String> people) {
        int count = 0;
        for (int j = 1; j < people.size(); j++) {
            String temp = people.get(j);
            int possibleIndex = j;
            while (possibleIndex > 0 && temp.compareTo(people.get(possibleIndex - 1)) < 0) {
                count++;
                people.set(possibleIndex, people.get(possibleIndex - 1));
                possibleIndex--;
            }
            people.set(possibleIndex, temp);
        }
    }

    public void searchTitles() {
        System.out.print("Enter search title term: ");
        String str = sc.nextLine();
        int x = 1;
        ArrayList<Movie> toSort = new ArrayList<>();

        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(str.toLowerCase())) {
                toSort.add(movie);
            }
        }

        if (toSort.isEmpty()) {
            System.out.println("No movie titles match that search term!");
            printMenu();
        } else {
            sortTitle(toSort);
            for (Movie movie : toSort) {
                System.out.println(x + ". " + movie.getTitle());
                x++;
            }
            learnMoreAboutMovie(toSort);
        }
    }

    private  void learnMoreAboutMovie(ArrayList<Movie> movieList) {
        System.out.print("What movie would you like to learn more about? \nEnter number: ");
        String str = sc.nextLine();
        try {
            if (movies.contains(movieList.get(Integer.parseInt(str) - 1))) {
                movieList.get(Integer.parseInt(str) - 1).printInfo();
            }
            printMenu();
        } catch (Exception ignored) {
            System.out.println("Invalid Input\n");
            printMenu();
        }
    }

    private void searchCast() {
        System.out.print("Enter a person to search for (first or last name): ");
        String str = sc.nextLine();
        ArrayList<String> actors = new ArrayList<String>();
        for (Movie movie : movies) {
            String[] cast = movie.getCast().split("\\|");
            for (String string : cast) {
                if (string.toLowerCase().contains(str.toLowerCase()) && !(actors.contains(string))) {
                    actors.add(string);
                }
            }
        }
        sortPeople(actors);
        if (!actors.isEmpty()) {
            for (int i = 0; i < actors.size(); i++) {
                System.out.println(i + 1 + ". " + actors.get(i));
            }
            System.out.print("Which would you like to see all movies for?: ");
            int choice = Integer.parseInt(sc.nextLine());
            if (choice > 0 && choice <= actors.size()) {
                ArrayList<Movie> featuredIn = new ArrayList<Movie>();
                for (int i = 0; i < movies.size(); i++) {
                    if (movies.get(i).getCast().contains(actors.get(choice - 1))) {
                        featuredIn.add(movies.get(i));
                    }
                }
                sortTitle(featuredIn);
                for (int y = 0; y < featuredIn.size(); y++) {
                    System.out.println(y + 1 + ". " + featuredIn.get(y).getTitle());
                }
                learnMoreAboutMovie(featuredIn);
            } else {
                System.out.println("Invalid choice");
            }
        } else {
            System.out.println("No matches found");
        }
    }
}
