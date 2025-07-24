package mission2.menu;

import mission2.Car;
import mission2.Enum.CarType;
import mission2.Enum.Step;
import mission2.utils;

import java.util.Optional;

import static mission2.utils.checkNumeric;

public class CarTypeMenu implements Menu {
    @Override
    public void showMenu() {
        System.out.println("        ______________");
        System.out.println("       /|            |");
        System.out.println("  ____/_|_____________|____");
        System.out.println(" |                      O  |");
        System.out.println(" '-(@)----------------(@)--'");
        System.out.println("===============================");
        System.out.println("어떤 차량 타입을 선택할까요?");
        System.out.println("1. Sedan");
        System.out.println("2. SUV");
        System.out.println("3. Truck");
        System.out.println("exit. 종료하기");
        System.out.println("===============================");
    }

    @Override
    public Optional<Step> input(String buf, Car.Builder builder) {
        if ("0".equals(buf)) {
            System.out.println("ERROR :: 0 이상의 정수만 입력 가능");
            utils.delay(800);
            return Optional.of(Step.CAR_TYPE);
        }
        if (checkNumeric(buf)) return Optional.of(Step.CAR_TYPE);

        int ans = Integer.parseInt(buf);
        if (CarType.values().length < ans) {
            System.out.println("ERROR :: 차량 타입은 1 ~ 3 범위만 선택 가능");
            return Optional.of(Step.CAR_TYPE);
        }

        CarType ct = CarType.values()[ans - 1];
        builder.type(ct);
        utils.delay(500);
        return Optional.of(Step.ENGINE);
    }
}
