package lab06;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.*;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class HRDateUtilsTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void strToDate() throws DateTimeParseException {
        String date = HRDateUtils.dateToStr(LocalDate.now());
        LocalDate dExpected = HRDateUtils.strToDate(date);
        LocalDate dActual = LocalDate.now();
        assertEquals(dActual, dExpected);
    }

    @Test
    void dateToStr() {
        String sExpected = "2020-10-01";
        String sActual = HRDateUtils.dateToStr(LocalDate.parse(sExpected));
        assertEquals(sExpected, sActual);
    }

    @Test
    void DateTimeParseException(){
        String badDate = "Hello";
        assertThrows(DateTimeParseException.class, () -> HRDateUtils.strToDate(badDate));
    }
}