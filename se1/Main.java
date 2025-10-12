package mainpack;

import com.Human;
import com.Music;
import com.University;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Human (Setters) ===");
        Human human = new Human();
        human.setName("Alice");
        human.setAge(30);
        human.introduce();

        System.out.println("\n=== Music (Constructor) ===");
        Music song = new Music("Bohemian Rhapsody", "Queen");
        song.play();

        System.out.println("\n=== University (Constructor + Setter) ===");
        University uni = new University("Harvard University");
        uni.setEstablishedYear(1636);
        uni.info();
    }
}
