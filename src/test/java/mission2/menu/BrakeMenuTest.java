package mission2.menu;

import mission2.Car;
import mission2.Enum.Brake;
import mission2.Enum.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrakeMenuTest {
    @Mock
    Car.Builder builder;
    private BrakeMenu menu;

    @BeforeEach
    void setUp() {
        menu = new BrakeMenu();
    }

    @Test
    @DisplayName("BRAKE | 0 선택, 뒤로 가기(ENGINE)")
    void brake_select_0() {
        Optional<Step> next = menu.input("0", builder);

        verify(builder, never()).brake(any());
        assertEquals(Optional.of(Step.ENGINE), next);
    }

    @Test
    @DisplayName("BRAKE | 1 선택, MANDO")
    void brake_select_1() {
        Optional<Step> next = menu.input("1", builder);

        verify(builder).brake(Brake.MANDO);
        assertEquals(Optional.of(Step.STEERING), next);
    }

    @Test
    @DisplayName("BRAKE | 범위 밖 숫자 선택")
    void brake_select_9() {
        Optional<Step> next = menu.input("9", builder);

        verify(builder, never()).brake(any());
        assertEquals(Optional.of(Step.BRAKE), next);
    }

    @Test
    @DisplayName("BRAKE | 문자 선택")
    void brake_select_string() {
        Optional<Step> next = menu.input("test", builder);

        verify(builder, never()).brake(any());
        assertEquals(Optional.of(Step.BRAKE), next);
    }
}
