package mission2;

import mission2.Enum.Brake;
import mission2.Enum.CarType;
import mission2.Enum.Engine;
import mission2.Enum.Steering;

public class Car {
    private final CarType type;
    private final Engine engine;
    private final Brake brake;
    private final Steering steering;

    private Car(Builder b) {
        this.type = b.type;
        this.engine = b.engine;
        this.brake = b.brake;
        this.steering = b.steering;
    }

    public static class Builder {
        private CarType type;
        private Engine engine;
        private Brake brake;
        private Steering steering;

        public Builder type(CarType type) {
            this.type = type;
            return this;
        }

        public Builder engine(Engine engine) {
            this.engine = engine;
            return this;
        }

        public Builder brake(Brake brake) {
            this.brake = brake;
            return this;
        }

        public Builder steering(Steering s) {
            this.steering = s;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }

    public boolean isValidCombination() {
        if (type == CarType.SEDAN && brake == Brake.CONTINENTAL) return true;
        if (type == CarType.SUV && engine == Engine.TOYOTA) return true;
        if (type == CarType.TRUCK && engine == Engine.WIA) return true;
        if (type == CarType.TRUCK && brake == Brake.MANDO) return true;
        if (brake == Brake.BOSCH && steering != Steering.BOSCH) return true;
        return false;
    }

    public String invalidCombinationReason() {
        if (type == CarType.SEDAN && brake == Brake.CONTINENTAL)
            return "Sedan에는 Continental 제동장치 사용 불가";
        if (type == CarType.SUV && engine == Engine.TOYOTA)
            return "SUV에는 TOYOTA 엔진 사용 불가";
        if (type == CarType.TRUCK && engine == Engine.WIA)
            return "Truck에는 WIA 엔진 사용 불가";
        if (type == CarType.TRUCK && brake == Brake.MANDO)
            return "Truck에는 Mando 제동장치 사용 불가";
        if (brake == Brake.BOSCH && steering != Steering.BOSCH)
            return "Bosch 제동장치에는 Bosch 조향장치만 사용 가능";
        return "";
    }

    public CarType getType() {
        return type;
    }

    public Engine getEngine() {
        return engine;
    }

    public Brake getBrake() {
        return brake;
    }

    public Steering getSteering() {
        return steering;
    }

}
