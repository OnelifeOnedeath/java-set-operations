import java.util.*;
import java.util.function.BinaryOperator;

/**
 * Расширенные примеры бинарных операций
 */
public class AdvancedOperations {
    
    public static void demonstrateAdvancedOperations() {
        Set<Integer> X = Set.of(1, 2, 3);
        Set<Integer> Y = Set.of(2, 3, 4);
        
        System.out.println("🧪 Расширенные операции");
        System.out.println("X: " + X);
        System.out.println("Y: " + Y);
        
        // Модульная арифметика
        BinaryOperator<Integer> modOp = (a, b) -> (a + b) % 5;
        System.out.println("(a + b) mod 5: " + Main.applyBinaryOperation(X, Y, modOp));
        
        // Битовые операции
        BinaryOperator<Integer> bitwiseOp = (a, b) -> a & b;
        System.out.println("a AND b: " + Main.applyBinaryOperation(X, Y, bitwiseOp));
    }
    
    public static void main(String[] args) {
        demonstrateAdvancedOperations();
    }
}
