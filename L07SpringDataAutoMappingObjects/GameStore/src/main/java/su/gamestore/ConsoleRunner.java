package su.gamestore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import su.gamestore.constants.OutputMessages;
import su.gamestore.service.ExecutorService;

import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final String END ="End";

    private ExecutorService executorService;
    public ConsoleRunner(ExecutorService executorService) {
        this.executorService = executorService;
    }


    @Override
    public void run(String... args) throws Exception {
        Scanner scanner =new Scanner(System.in);

         while (true) {
            System.out.println(OutputMessages.ENTER_COMMAND);
            String command = scanner.nextLine();
             if (command.equals(END)) {
                 break;
             }

            String result;
            try {
                result = executorService.executeCommand(command);
            } catch (IllegalArgumentException | NullPointerException e) {
                result = e.getMessage();
            }
            System.out.println(result);
        }
    }
}
