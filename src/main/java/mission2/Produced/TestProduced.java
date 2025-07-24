package mission2.Produced;

import mission2.Car;
import mission2.utils;

import java.util.Objects;

public class TestProduced implements Produced {
    @Override
    public void execute(Car car) {
        System.out.println("Test...");
        utils.delay(1500);
        if (car.isValidCombination()) {
            fail(car.invalidCombinationReason());
        } else {
            System.out.println("자동차 부품 조합 테스트 결과 : PASS");
        }
        utils.delay(2000);
    }

    private static void fail(String msg) {
        System.out.println("자동차 부품 조합 테스트 결과 : FAIL");
        if (!Objects.equals(msg, ""))
            System.out.println(msg);
    }
}
