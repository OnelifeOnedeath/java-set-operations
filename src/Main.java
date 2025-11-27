import java.util.*;
import java.util.function.BinaryOperator;

/**
 * Решение задания: два целочисленных множества + бинарная операция
 */
public class Main {
    
    /**
     * Применяет бинарную операцию ко всем парам элементов из двух множеств
     */
    public static <T> Set<T> applyBinaryOperation(Set<T> setA, Set<T> setB, BinaryOperator<T> operation) {
        Set<T> result = new HashSet<>();
        for (T a : setA) {
            for (T b : setB) {
                result.add(operation.apply(a, b));
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        // 1. Создаем два целочисленных множества
        Set<Integer> A = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> B = new HashSet<>(Arrays.asList(3, 4, 5, 6, 7));
        
        System.out.println("🎯 Задание: два множества + бинарная операция");
        System.out.println("Множество A: " + A);
        System.out.println("Множество B: " + B);
        System.out.println();
        
        // 2. Демонстрация разных бинарных операций
        
        // Операция 1: a * b + 1
        BinaryOperator<Integer> op1 = (a, b) -> a * b + 1;
        Set<Integer> result1 = applyBinaryOperation(A, B, op1);
        System.out.println("1. Операция (a * b + 1): " + result1);
        
        // Операция 2: a² + b²
        BinaryOperator<Integer> op2 = (a, b) -> a*a + b*b;
        Set<Integer> result2 = applyBinaryOperation(A, B, op2);
        System.out.println("2. Операция (a² + b²): " + result2);
        
        // Операция 3: |a - b|
        BinaryOperator<Integer> op3 = (a, b) -> Math.abs(a - b);
        Set<Integer> result3 = applyBinaryOperation(A, B, op3);
        System.out.println("3. Операция |a - b|: " + result3);
    }
}
