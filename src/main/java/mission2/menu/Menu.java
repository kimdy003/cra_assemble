package mission2.menu;

import mission2.Car;
import mission2.Enum.Step;

import java.util.Optional;

public interface Menu {
    void showMenu();

    Optional<Step> input(String buf, Car.Builder builder);
}
