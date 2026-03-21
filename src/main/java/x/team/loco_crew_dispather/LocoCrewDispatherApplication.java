package x.team.loco_crew_dispather;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import x.team.loco_crew_dispather.util.CrewOrderParser;

import java.io.IOException;

@SpringBootApplication
public class LocoCrewDispatherApplication implements CommandLineRunner {

    public static void main(String[] args) { SpringApplication.run(LocoCrewDispatherApplication.class, args); }

    @Override
    public void run(String[] args) {
        try {
            CrewOrderParser crewOrderParser = new CrewOrderParser();
            crewOrderParser.readFromFile();
            crewOrderParser.displayOrders(5);
        } catch (Exception e) {
            System.err.println("Ошибка при работе с файлом наряда: " + e.getMessage());
        }
    }

}
