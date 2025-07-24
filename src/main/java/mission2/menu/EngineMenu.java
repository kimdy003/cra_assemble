package mission2.menu;

import mission2.Car;
import mission2.Enum.Engine;
import mission2.Enum.Step;
import mission2.utils;

import java.util.Optional;

import static mission2.utils.checkNumeric;

public class EngineMenu implements Menu {
    @Override
    public void showMenu() {
        System.out.println("어떤 엔진을 탑재할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. GM");
        System.out.println("2. TOYOTA");
        System.out.println("3. WIA");
        System.out.println("4. 고장난 엔진");
        System.out.println("exit. 종료하기");
        System.out.println("===============================");
    }

    @Override
    public Optional<Step> input(String buf, Car.Builder builder) {
        if ("0".equals(buf)) return Optional.of(Step.CAR_TYPE);
        if (checkNumeric(buf)) return Optional.of(Step.ENGINE);

        int ans = Integer.parseInt(buf);
        if (Engine.values().length < ans) {
            System.out.println("ERROR :: 엔진은 1 ~ 4 범위만 선택 가능");
            return Optional.of(Step.ENGINE);
        }

        Engine e = Engine.values()[ans - 1];
        builder.engine(e);
        utils.delay(500);
        return Optional.of(Step.BRAKE);
    }
}
