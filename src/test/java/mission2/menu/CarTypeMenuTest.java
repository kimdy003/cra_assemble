package mission2.menu;

import mission2.Car;
import mission2.Enum.CarType;
import mission2.Enum.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CarTypeMenuTest {
    @Mock
    Car.Builder builder;
    private CarTypeMenu menu;

    @BeforeEach
    void setUp() {
        menu = new CarTypeMenu();
    }

    @Test
    @DisplayName("CAR_TYPE | 2 선택, SUV")
    void catType_select_2() {
        Optional<Step> next = menu.input("2", builder);

        verify(builder).type(CarType.SUV);
        assertEquals(Optional.of(Step.ENGINE), next);
    }

    @Test
    @DisplayName("CAR_TYPE | 범위 밖 숫자 선택")
    void catType_select_9() {
        Optional<Step> next = menu.input("9", builder);

        verify(builder, never()).type(any());
        assertEquals(Optional.of(Step.CAR_TYPE), next);
    }

    @Test
    @DisplayName("CAR_TYPE | 문자 선택")
    void catType_select_string() {
        Optional<Step> next = menu.input("test", builder);

        verify(builder, never()).type(any());
        assertEquals(Optional.of(Step.CAR_TYPE), next);
    }
}
