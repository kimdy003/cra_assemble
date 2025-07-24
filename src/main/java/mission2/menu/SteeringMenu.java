package mission2.menu;

import mission2.Car;
import mission2.Enum.Steering;
import mission2.Enum.Step;
import mission2.utils;

import java.util.Optional;

import static mission2.utils.checkNumeric;

public class SteeringMenu implements Menu {
    @Override
    public void showMenu() {
        System.out.println("어떤 조향장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. BOSCH");
        System.out.println("2. MOBIS");
        System.out.println("exit. 종료하기");
        System.out.println("===============================");
    }

    @Override
    public Optional<Step> input(String buf, Car.Builder builder) {
        if ("0".equals(buf)) return Optional.of(Step.BRAKE);
        if (checkNumeric(buf)) return Optional.of(Step.STEERING);

        int ans = Integer.parseInt(buf);
        if (Steering.values().length < ans) {
            System.out.println("ERROR :: 조향장치는 1 ~ 2 범위만 선택 가능");
            return Optional.of(Step.STEERING);
        }

        Steering s = Steering.values()[ans - 1];
        builder.steering(s);
        utils.delay(500);
        return Optional.of(Step.RUN_TEST);
    }
}
