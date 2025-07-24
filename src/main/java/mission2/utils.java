package mission2;

public class utils {
    public static boolean checkNumeric(String str) {
        if (!str.chars().allMatch(Character::isDigit)) {
            System.out.println("ERROR :: 0 이상의 정수만 입력 가능");
            delay(800);
            return true;
        }
        return false;
    }

    public static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}
