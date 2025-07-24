package mission2.menu;

import mission2.Car;
import mission2.Enum.Engine;
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
class EngineMenuTest {
    @Mock
    Car.Builder builder;
    private EngineMenu menu;

    @BeforeEach
    void setUp() {
        menu = new EngineMenu();
    }

    @Test
    @DisplayName("ENGINE | 0 선택, 뒤로 가기(CAR_TYPE)")
    void engine_select_0() {
        Optional<Step> next = menu.input("0", builder);

        verify(builder, never()).engine(any());
        assertEquals(Optional.of(Step.CAR_TYPE), next);
    }

    @Test
    @DisplayName("ENGINE | 3 선택, WIA")
    void engine_select_3() {
        Optional<Step> next = menu.input("3", builder);

        verify(builder).engine(Engine.WIA);
        assertEquals(Optional.of(Step.BRAKE), next);
    }

    @Test
    @DisplayName("ENGINE | 범위 밖 숫자 선택")
    void engine_select_5() {
        Optional<Step> next = menu.input("5", builder);

        verify(builder, never()).engine(any());
        assertEquals(Optional.of(Step.ENGINE), next);
    }

    @Test
    @DisplayName("ENGINE | 문자 선택")
    void engine_select_string() {
        Optional<Step> next = menu.input("tes", builder);

        verify(builder, never()).engine(any());
        assertEquals(Optional.of(Step.ENGINE), next);
    }
}
