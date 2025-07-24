package mission2.menu;

import mission2.Car;
import mission2.Enum.Step;
import mission2.Produced.Produced;
import mission2.Produced.RunProduced;
import mission2.Produced.TestProduced;

import java.util.Map;
import java.util.Optional;

import static mission2.utils.checkNumeric;

public class RunTestMenu implements Menu {
    private final Map<Integer, Produced> produce = Map.of(
            1, new RunProduced(),
            2, new TestProduced()
    );

    @Override
    public void showMenu() {
        System.out.println("멋진 차량이 완성되었습니다.");
        System.out.println("어떤 동작을 할까요?");
        System.out.println("0. 처음 화면으로 돌아가기");
        System.out.println("1. RUN");
        System.out.println("2. Test");
        System.out.println("exit. 종료하기");
        System.out.println("===============================");
    }

    @Override
    public Optional<Step> input(String buf, Car.Builder builder) {
        if ("0".equals(buf)) return Optional.of(Step.CAR_TYPE);
        if (checkNumeric(buf)) return Optional.of(Step.RUN_TEST);

        int choice = Integer.parseInt(buf);
        Car car = builder.build();
        Produced produced = produce.get(choice);
        if (produced != null) {
            produced.execute(car);
        } else {
            System.out.println("ERROR :: 1 또는 2만 선택 가능합니다");
        }
        return Optional.of(Step.RUN_TEST);
    }
}
