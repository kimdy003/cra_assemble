package mission2.Produced;

import mission2.Car;
import mission2.Enum.Engine;
import mission2.utils;

public class RunProduced implements Produced {
    @Override
    public void execute(Car car) {
        if (car.getEngine() == Engine.BROKEN) {
            System.out.println("엔진이 고장나있습니다.");
            System.out.println("자동차가 움직이지 않습니다.");
            return;
        }
        if (car.isValidCombination()) {
            System.out.println("자동차가 동작되지 않습니다");
            return;
        }
        System.out.printf("Car Type : %s\n", car.getType());
        System.out.printf("Engine   : %s\n", car.getEngine());
        System.out.printf("Brake    : %s\n", car.getBrake());
        System.out.printf("Steering : %s\n", car.getSteering());
        System.out.println("자동차가 동작됩니다.");

        utils.delay(2000);
    }
}
