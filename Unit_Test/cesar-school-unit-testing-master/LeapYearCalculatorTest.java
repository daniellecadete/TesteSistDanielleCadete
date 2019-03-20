package school.cesar.unit.exercises;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeapYearCalculatorTest {

    private Integer currentYear;
    private LeapYearCalculator leapYearCalculator;

    @BeforeEach
    public void suiteSetUp(){
        currentYear = null;
        leapYearCalculator = new LeapYearCalculator(){
            @Override
            public int getCurrentYear(){
                return currentYear;
            }
        };
    }

    @Test
    public void getCurrentYear(){
        assertEquals(Year.now().getValue(), new LeapYearCalculator().getCurrentYear());
    }

    @Test
    public void yearNotDivisibleByFour(){
        currentYear = 2019;
        assertFalse(leapYearCalculator.isCurrentYearLeapYear());
    }

    @Test
    public void yearDivisibleByFourButNotByAHundred(){
        currentYear = 2016;
        assertTrue(leapYearCalculator.isCurrentYearLeapYear());
    }

    @Test
    public void yearDivisibleByFourAndByOneHundredButNotByFourHundreds(){
        currentYear = 100;
        assertFalse(leapYearCalculator.isCurrentYearLeapYear());
    }

    @Test
    public void yearDivisibleByFourByOneHundredButAndByFourHundreds(){
        currentYear = 400;
        assertTrue(leapYearCalculator.isCurrentYearLeapYear());
    }
}
