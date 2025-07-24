package mission2.menu;

import mission2.Car;
import mission2.Enum.Brake;
import mission2.Enum.Step;
import mission2.utils;

import java.util.Optional;

import static mission2.utils.checkNumeric;

public class BrakeMenu implements Menu {
    @Override
    public void showMenu() {
        System.out.println("어떤 제동장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. MANDO");
        System.out.println("2. CONTINENTAL");
        System.out.println("3. BOSCH");
        System.out.println("exit. 종료하기");
        System.out.println("===============================");
    }

    @Override
    public Optional<Step> input(String buf, Car.Builder builder) {
        if ("0".equals(buf)) return Optional.of(Step.ENGINE);
        if (checkNumeric(buf)) return Optional.of(Step.BRAKE);

        int ans = Integer.parseInt(buf);
        if (Brake.values().length < ans) {
            System.out.println("ERROR :: 제동장치는 1 ~ 3 범위만 선택 가능");
            return Optional.of(Step.BRAKE);
        }

        Brake b = Brake.values()[ans - 1];
        builder.brake(b);
        utils.delay(500);
        return Optional.of(Step.STEERING);
    }
}
