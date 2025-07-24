package mission1;

import java.util.Scanner;

public class Assemble {
    private static final String CLEAR_SCREEN = "\033[H\033[2J";

    private static final int CAR_TYPE_Q = 0;
    private static final int ENGINE_Q = 1;
    private static final int BRAKE_SYSTEM_Q = 2;
    private static final int STEERING_SYSTEM_Q = 3;
    private static final int RUN_TEST = 4;

    private static final int SEDAN = 1, SUV = 2, TRUCK = 3;
    private static final int GM = 1, TOYOTA = 2, WIA = 3;
    private static final int MANDO = 1, CONTINENTAL = 2, BOSCH_B = 3;
    private static final int BOSCH_S = 1, MOBIS = 2;

    public static final String[] CAR_NAMES = {"", "Sedan", "SUV", "Truck"};
    public static final String[] ENG_NAMES = {"", "GM", "TOYOTA", "WIA"};
    public static final String[] BRAKE_SYS_NAME = {"", "Mando", "Continental", "Bosch"};
    public static final String[] STEERING_SYS_NAME = {"", "Bosch", "Mobis"};

    private static int[] carInfo = new int[5];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int step = CAR_TYPE_Q;
        while (true) {
            clearScreen();

            showMenu(step);

            String buf = inputAndExit(sc);
            if (buf == null) break;

            if (!isNumeric(buf)) {
                System.out.println("ERROR :: 0 이상의 정수만 입력 가능");
                delay(800);
                continue;
            }

            int answer = Integer.parseInt(buf);
            if (answer == 0) {
                if (step == RUN_TEST) {
                    step = CAR_TYPE_Q;
                } else if (step > CAR_TYPE_Q) {
                    step--;
                }
                continue;
            }

            if (!isValidRange(step, answer)) {
                delay(800);
                continue;
            }

            step = selectCarInfoAndNextStep(step, answer);
        }

        sc.close();
    }

    private static void showMenu(int step) {
        switch (step) {
            case CAR_TYPE_Q -> showCarTypeMenu();
            case ENGINE_Q -> showEngineMenu();
            case BRAKE_SYSTEM_Q -> showBrakeMenu();
            case STEERING_SYSTEM_Q -> showSteeringMenu();
            case RUN_TEST -> showRunTestMenu();
        }
    }

    private static void showCarTypeMenu() {
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

    private static void showEngineMenu() {
        System.out.println("어떤 엔진을 탑재할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. GM");
        System.out.println("2. TOYOTA");
        System.out.println("3. WIA");
        System.out.println("4. 고장난 엔진");
        System.out.println("exit. 종료하기");
        System.out.println("===============================");
    }

    private static void showBrakeMenu() {
        System.out.println("어떤 제동장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. MANDO");
        System.out.println("2. CONTINENTAL");
        System.out.println("3. BOSCH");
        System.out.println("exit. 종료하기");
        System.out.println("===============================");
    }

    private static void showSteeringMenu() {
        System.out.println("어떤 조향장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. BOSCH");
        System.out.println("2. MOBIS");
        System.out.println("exit. 종료하기");
        System.out.println("===============================");
    }

    private static void showRunTestMenu() {
        System.out.println("멋진 차량이 완성되었습니다.");
        System.out.println("어떤 동작을 할까요?");
        System.out.println("0. 처음 화면으로 돌아가기");
        System.out.println("1. RUN");
        System.out.println("2. Test");
        System.out.println("exit. 종료하기");
        System.out.println("===============================");
    }

    private static String inputAndExit(Scanner sc) {
        System.out.print("INPUT > ");
        String buf = sc.nextLine().trim();

        if (buf.equalsIgnoreCase("exit")) {
            System.out.println("바이바이");
            return null;
        }
        return buf;
    }

    public static boolean isNumeric(String str) {
        return str.chars().allMatch(Character::isDigit);
    }

    private static boolean isValidRange(int step, int ans) {
        if (step == CAR_TYPE_Q) {
            if (ans < CAR_NAMES.length) return true;
            System.out.println("ERROR :: 차량 타입은 1 ~ 3 범위만 선택 가능");
        }
        if (step == ENGINE_Q) {
            if (ans < ENG_NAMES.length) return true;
            System.out.println("ERROR :: 엔진은 1 ~ 4 범위만 선택 가능");
        }
        if (step == BRAKE_SYSTEM_Q) {
            if (ans < BRAKE_SYS_NAME.length) return true;
            System.out.println("ERROR :: 제동장치는 1 ~ 3 범위만 선택 가능");
        }
        if (step == STEERING_SYSTEM_Q) {
            if (ans < STEERING_SYS_NAME.length) return true;
            System.out.println("ERROR :: 조향장치는 1 ~ 2 범위만 선택 가능");
        }
        if (step == RUN_TEST) {
            if (ans == 1 || ans == 2) return true;
            System.out.println("ERROR :: Run 또는 Test 중 하나를 선택 필요");
        }

        return false;
    }

    private static int selectCarInfoAndNextStep(int step, int answer) {
        switch (step) {
            case CAR_TYPE_Q -> {
                selectCarType(answer);
                delay(800);
                step = ENGINE_Q;
            }
            case ENGINE_Q -> {
                selectEngine(answer);
                delay(800);
                step = BRAKE_SYSTEM_Q;
            }
            case BRAKE_SYSTEM_Q -> {
                selectBrakeSystem(answer);
                delay(800);
                step = STEERING_SYSTEM_Q;
            }
            case STEERING_SYSTEM_Q -> {
                selectSteeringSystem(answer);
                delay(800);
                step = RUN_TEST;
            }
            case RUN_TEST -> {
                if (answer == 1) {
                    runProducedCar();
                    delay(2000);
                } else if (answer == 2) {
                    System.out.println("Test...");
                    delay(1500);
                    testProducedCar();
                    delay(2000);
                }
            }
        }
        return step;
    }

    private static void selectCarType(int carType) {
        carInfo[CAR_TYPE_Q] = carType;
        String selectCarType;
        switch (carType) {
            case 1 -> selectCarType = "Sedan";
            case 2 -> selectCarType = "SUV";
            default -> selectCarType = "Truck";
        }
        System.out.printf("차량 타입으로 %s을 선택하셨습니다.\n", selectCarType);
    }

    private static void selectEngine(int carEngine) {
        carInfo[ENGINE_Q] = carEngine;
        String selectCarEngine;
        switch (carEngine) {
            case 1 -> selectCarEngine = "GM";
            case 2 -> selectCarEngine = "TOYOTA";
            case 3 -> selectCarEngine = "WIA";
            default -> selectCarEngine = "고장난 엔진";
        }
        System.out.printf("%s 엔진을 선택하셨습니다.\n", selectCarEngine);
    }

    private static void selectBrakeSystem(int brakeSystem) {
        carInfo[BRAKE_SYSTEM_Q] = brakeSystem;
        String selectBrakeSystem;
        switch (brakeSystem) {
            case 1 -> selectBrakeSystem = "MANDO";
            case 2 -> selectBrakeSystem = "CONTINENTAL";
            default -> selectBrakeSystem = "BOSCH";
        }
        System.out.printf("%s 제동장치를 선택하셨습니다.\n", selectBrakeSystem);
    }

    private static void selectSteeringSystem(int steeringSystem) {
        carInfo[STEERING_SYSTEM_Q] = steeringSystem;
        String selectSteeringSystem;
        switch (steeringSystem) {
            case 1 -> selectSteeringSystem = "BOSCH";
            default -> selectSteeringSystem = "MOBIS";
        }
        System.out.printf("%s 조향장치를 선택하셨습니다.\n", selectSteeringSystem);
    }


    private static void runProducedCar() {
        if (!isValidCheck()) {
            System.out.println("자동차가 동작되지 않습니다");
            return;
        }
        if (carInfo[ENGINE_Q] == 4) {
            System.out.println("엔진이 고장나있습니다.");
            System.out.println("자동차가 움직이지 않습니다.");
            return;
        }

        System.out.printf("Car Type : %s\n", CAR_NAMES[carInfo[CAR_TYPE_Q]]);
        System.out.printf("Engine   : %s\n", ENG_NAMES[carInfo[ENGINE_Q]]);
        System.out.printf("Brake    : %s\n", BRAKE_SYS_NAME[carInfo[BRAKE_SYSTEM_Q]]);
        System.out.printf("Steering : %s\n", STEERING_SYS_NAME[carInfo[STEERING_SYSTEM_Q]]);
        System.out.println("자동차가 동작됩니다.");
    }

    private static boolean isValidCheck() {
        if (carInfo[CAR_TYPE_Q] == SEDAN && carInfo[BRAKE_SYSTEM_Q] == CONTINENTAL) return false;
        if (carInfo[CAR_TYPE_Q] == SUV && carInfo[ENGINE_Q] == TOYOTA) return false;
        if (carInfo[CAR_TYPE_Q] == TRUCK && carInfo[ENGINE_Q] == WIA) return false;
        if (carInfo[CAR_TYPE_Q] == TRUCK && carInfo[BRAKE_SYSTEM_Q] == MANDO) return false;
        if (carInfo[BRAKE_SYSTEM_Q] == BOSCH_B && carInfo[STEERING_SYSTEM_Q] != BOSCH_S) return false;
        return true;
    }

    private static void testProducedCar() {
        if (carInfo[CAR_TYPE_Q] == SEDAN && carInfo[BRAKE_SYSTEM_Q] == CONTINENTAL) {
            fail("Sedan에는 Continental제동장치 사용 불가");
        } else if (carInfo[CAR_TYPE_Q] == SUV && carInfo[ENGINE_Q] == TOYOTA) {
            fail("SUV에는 TOYOTA엔진 사용 불가");
        } else if (carInfo[CAR_TYPE_Q] == TRUCK && carInfo[ENGINE_Q] == WIA) {
            fail("Truck에는 WIA엔진 사용 불가");
        } else if (carInfo[CAR_TYPE_Q] == TRUCK && carInfo[BRAKE_SYSTEM_Q] == MANDO) {
            fail("Truck에는 Mando제동장치 사용 불가");
        } else if (carInfo[BRAKE_SYSTEM_Q] == BOSCH_B && carInfo[STEERING_SYSTEM_Q] != BOSCH_S) {
            fail("Bosch제동장치에는 Bosch조향장치 이외 사용 불가");
        } else {
            System.out.println("자동차 부품 조합 테스트 결과 : PASS");
        }
    }

    private static void fail(String msg) {
        System.out.println("자동차 부품 조합 테스트 결과 : FAIL");
        System.out.println(msg);
    }

    private static void clearScreen() {
        System.out.print(CLEAR_SCREEN);
        System.out.flush();
    }

    private static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}