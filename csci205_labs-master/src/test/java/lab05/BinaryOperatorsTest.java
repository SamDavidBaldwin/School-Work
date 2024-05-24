package lab05;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryOperatorsTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void permutationTest(){
        MathOp op = BinaryOperators.perm;
        assertEquals(7, op.func(7,1));
        assertEquals(840, op.func(7,4));
        assertEquals(5040, op.func(7,7));
    }

    @Test
    void gcdTest(){
        MathOp op = BinaryOperators.gcd;
        assertEquals(1, op.func(1,10));
        assertEquals(1,op.func(10,1));
        assertEquals(1,op.func(3,11));
        assertEquals(1, op.func(11,3));
        assertEquals(9, op.func(243,45));
        assertEquals(9,op.func(45,243));

    }

    @Test
    void lcmTest() {
        MathOp op = BinaryOperators.lcm;
        assertEquals(1, op.func(1, 1));
        assertEquals(200, op.func(10, 200));
        assertEquals(33, op.func(11, 33));
        assertEquals(50, op.func(10, 25));
    }

    @Test
    void combinatorialTest(){
        MathOp op = BinaryOperators.comb;
        assertEquals(220, op.func(12,3));
        assertEquals(12, op.func(12,11));
        assertEquals(1365, op.func(15,11));


    }
}