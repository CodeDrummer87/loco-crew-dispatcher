package x.team.loco_crew_dispather.util;

import org.apache.tika.Tika;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CrewOrderParser {

    private List<String> orders = new ArrayList<>();
    private String filePath = "src/main/resources/static/current_crew_order/";

    private String getTomorrowDate() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy");
        return tomorrow.format(formatter);
    }

    private boolean isValidFile() {
        // checking if the file exists
        File file;
        try {
            File path = new File(filePath);
            File[] matchingFiles = path.listFiles((dir, name) ->
                    name.startsWith(getTomorrowDate()));

            if (matchingFiles == null || matchingFiles.length == 0) {
                System.out.println("Отсутствует файл наряда или неверная дата наряда");
                return false;
            }

            file = matchingFiles[0];
            filePath = file.getAbsolutePath();
        } catch (Exception e) {
            System.out.println("Ошибка при поиске файла наряда: " + e.getMessage());
            return false;
        }

        // checking that the file extension is .txt
        try {
            if (!file.getName().endsWith(".txt")) {
                System.out.println("Файл наряда имеет расширение, отличное от .txt");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Ошибка при проверке расширения файла наряда: " + e.getMessage());
            return false;
        }

        // checking if the file is empty
        try {
            if (file.length() == 0) {
                System.out.println("Файл наряда пуст");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Ошибка при проверке содержимого файла наряда: " + e.getMessage());
            return false;
        }

        // checking the MIME-type of the file
        try {
            Tika tika = new Tika();
            String mimeType = tika.detect(new File(filePath));

            if (!mimeType.equals("text/plain")) {
                System.out.println("Файл наряда не является текстовым (text/plain)");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Ошибка при проверке MIME-типа файла наряда: " + e.getMessage());
            return false;
        }

        return true;
    }

    public void readFromFile() {
        if (!isValidFile()) {
            System.out.println("Чтение файла наряда прервано: ошибка валидации");
            return;
        }

        try {
            orders.clear();
            orders.addAll(Files.readAllLines(Paths.get(filePath)));
        } catch (Exception e) {
            System.out.println("Ошибка при чтении файла наряда: " + e.getMessage());
        }
    }

    public void displayOrders(int count) {
        try {
            for (int i = 0; i < count; i++) {
                String order = orders.get(i);
                String[] parts = order.split("/");

                String time = parts[0];
                String reportingPoint = parts[1];
                String district = parts[2];
                String serviceType = parts[3];

                switch (serviceType) {
                    case "груз.":
                        serviceType = "грузовой поезд";
                        break;
                    case "пасс.":
                        serviceType = "пассажирский поезд";
                        break;
                    case "пассажиром":
                        serviceType = "пассажиром";
                        break;
                    case "хоз.":
                        serviceType = "хозяйственный поезд";
                        break;
                }

                System.out.println("Явка: " + getTomorrowDate() + " в " + time);
                System.out.println("Пункт явки: " + reportingPoint);
                System.out.println("Направление: " + district);
                System.out.println("Тип поездки: " + serviceType);
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Ошибка отображения содержимого файла наряда");
        }
    }
}
