package mission2;

import mission2.Enum.Step;
import mission2.menu.*;

import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class AssembleApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Step step = Step.CAR_TYPE;
        Car.Builder builder = new Car.Builder();


        Map<Step, Menu> menus = Map.of(
                Step.CAR_TYPE, new CarTypeMenu(),
                Step.ENGINE, new EngineMenu(),
                Step.BRAKE, new BrakeMenu(),
                Step.STEERING, new SteeringMenu(),
                Step.RUN_TEST, new RunTestMenu()
        );

        while (true) {
            menus.get(step).showMenu();
            System.out.print("INPUT > ");
            String buf = sc.nextLine().trim();
            if (buf.equalsIgnoreCase("exit")) {
                System.out.println("바이바이");
                break;
            }

            Optional<Step> next = menus.get(step).input(buf, builder);
            if (next.isEmpty()) {
                System.out.println("바이바이");
                break;
            }
            if (next.get() == Step.CAR_TYPE) {
                builder = new Car.Builder();
            }

            step = next.get();
        }
        sc.close();
    }
}
