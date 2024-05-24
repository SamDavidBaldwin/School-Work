package lab05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calc;

    @BeforeEach
    void setUp(){
        this.calc = new Calculator(BinaryOperators.perm, "perm");
    }

    @Test
    void setOperands() {
        this.calc.setOperands(1,7);
        assertEquals(1, this.calc.getOperand1());
        assertEquals(7, this.calc.getOperand2());

    }

    @Test
    void setOperator() {
        this.calc.setOperator(BinaryOperators.comb);
        assertEquals(this.calc.getOperator(), BinaryOperators.comb);
    }

    @Test
    void testToString() {
        assertEquals(this.calc.toString(), "name(?, ?) = ?");
        this.calc.setOperator(BinaryOperators.comb);
        this.calc.setOperands(1,1);
        assertEquals(this.calc.toString(), "comb(1, 1) = 1");


    }

    @Test
    void getAnswer(){
        this.calc.setOperands(7,4);
        assertEquals(840, this.calc.getAnswer());
    }

    /**
     * Verify that getAnswer throws an ArithmeticExeption if operands have not been set
     */
    @Test
    void GetAnswerException(){
        assertThrows(ArithmeticException.class, () -> calc.getAnswer());
    }

}