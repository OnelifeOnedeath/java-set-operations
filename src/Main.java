import java.util.*;
import java.util.function.BinaryOperator;

/**
 * Исследование алгебраических структур: от множеств до колец
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("Исследование алгебраических структур");
        System.out.println("=====================================\n");
        
        // Часть 1: Базовое задание - операции над множествами
        demonstrateSetOperations();
        
        // Часть 2: Исследование колец и идеалов
        investigateRingsAndIdeals();
    }
    
    /**
     * ЧАСТЬ 1: Базовое задание - операции над множествами
     */
    static void demonstrateSetOperations() {
        System.out.println("1. ОПЕРАЦИИ НАД МНОЖЕСТВАМИ");
        System.out.println("---------------------------");
        
        Set<Integer> A = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> B = new HashSet<>(Arrays.asList(3, 4, 5, 6, 7));
        
        System.out.println("Множество A: " + A);
        System.out.println("Множество B: " + B);
        System.out.println();
        
        // Различные бинарные операции
        BinaryOperator<Integer> op1 = (a, b) -> a * b + 1;
        BinaryOperator<Integer> op2 = (a, b) -> a * a + b * b;
        BinaryOperator<Integer> op3 = (a, b) -> Math.abs(a - b);
        
        System.out.println("a * b + 1:      " + applyBinaryOperation(A, B, op1));
        System.out.println("a² + b²:        " + applyBinaryOperation(A, B, op2));
        System.out.println("|a - b|:        " + applyBinaryOperation(A, B, op3));
        System.out.println();
    }
    
    /**
     * Универсальная функция для бинарных операций над множествами
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
    
    /**
     * ЧАСТЬ 2: Исследование колец и циклических идеалов
     */
    static void investigateRingsAndIdeals() {
        System.out.println("2. КОЛЬЦА И ЦИКЛИЧЕСКИЕ ИДЕАЛЫ");
        System.out.println("------------------------------");
        
        // Исследуем разные кольца вычетов
        investigateZModN(12, "Z/12Z - кольцо с богатой структурой идеалов");
        investigateZModN(8, "Z/8Z - кольцо с идеалами степени 2");
        investigateZModN(7, "Z/7Z - простое поле");
        investigateZModN(6, "Z/6Z - не область целостности");
    }
    
    /**
     * Исследование кольца Z/nZ
     */
    static void investigateZModN(int n, String description) {
        System.out.println("\n" + description);
        System.out.println("Элементы: " + generateZModN(n));
        
        Set<Integer> ring = generateZModN(n);
        BinaryOperator<Integer> add = (a, b) -> (a + b) % n;
        BinaryOperator<Integer> mult = (a, b) -> (a * b) % n;
        
        if (verifyRing(ring, add, mult)) {
            System.out.println("✓ Является кольцом");
            findCyclicIdeals(ring, mult, n);
        }
        System.out.println();
    }
    
    /**
     * Генерация множества Z/nZ
     */
    static Set<Integer> generateZModN(int n) {
        Set<Integer> result = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            result.add(i);
        }
        return result;
    }
    
    /**
     * Проверка свойств кольца
     */
    static boolean verifyRing(Set<Integer> R, BinaryOperator<Integer> add, BinaryOperator<Integer> mult) {
        // Проверяем наличие нуля
        Integer zero = findZero(R, add);
        if (zero == null) return false;
        
        // Проверяем абелеву группу по сложению
        if (!verifyAbelianGroup(R, add, zero)) return false;
        
        // Проверяем ассоциативность умножения
        if (!verifyAssociativity(R, mult)) return false;
        
        // Проверяем дистрибутивность
        return verifyDistributivity(R, add, mult);
    }
    
    /**
     * Поиск нулевого элемента
     */
    static Integer findZero(Set<Integer> R, BinaryOperator<Integer> add) {
        for (Integer candidate : R) {
            boolean isZero = true;
            for (Integer x : R) {
                if (!add.apply(x, candidate).equals(x)) {
                    isZero = false;
                    break;
                }
            }
            if (isZero) return candidate;
        }
        return null;
    }
    
    /**
     * Проверка абелевой группы
     */
    static boolean verifyAbelianGroup(Set<Integer> R, BinaryOperator<Integer> add, Integer zero) {
        // Ассоциативность
        if (!verifyAssociativity(R, add)) return false;
        
        // Нулевой элемент
        for (Integer x : R) {
            if (!add.apply(x, zero).equals(x)) return false;
        }
        
        // Противоположные элементы
        for (Integer x : R) {
            boolean hasInverse = false;
            for (Integer y : R) {
                if (add.apply(x, y).equals(zero) && add.apply(y, x).equals(zero)) {
                    hasInverse = true;
                    break;
                }
            }
            if (!hasInverse) return false;
        }
        
        // Коммутативность
        for (Integer a : R) {
            for (Integer b : R) {
                if (!add.apply(a, b).equals(add.apply(b, a))) return false;
            }
        }
        
        return true;
    }
    
    /**
     * Проверка ассоциативности
     */
    static boolean verifyAssociativity(Set<Integer> R, BinaryOperator<Integer> op) {
        for (Integer a : R) {
            for (Integer b : R) {
                for (Integer c : R) {
                    Integer left = op.apply(op.apply(a, b), c);
                    Integer right = op.apply(a, op.apply(b, c));
                    if (!left.equals(right)) return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Проверка дистрибутивности
     */
    static boolean verifyDistributivity(Set<Integer> R, BinaryOperator<Integer> add, BinaryOperator<Integer> mult) {
        for (Integer a : R) {
            for (Integer b : R) {
                for (Integer c : R) {
                    Integer left = mult.apply(a, add.apply(b, c));
                    Integer right = add.apply(mult.apply(a, b), mult.apply(a, c));
                    if (!left.equals(right)) return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Поиск циклических идеалов
     */
    static void findCyclicIdeals(Set<Integer> R, BinaryOperator<Integer> mult, int n) {
        System.out.println("Циклические идеалы:");
        
        // Проверяем все возможные образующие
        for (int generator = 0; generator < n; generator++) {
            Set<Integer> ideal = generateIdeal(R, mult, generator);
            
            if (ideal.size() > 0 && ideal.size() < R.size()) {
                System.out.print("  (" + generator + ") = " + ideal);
                
                if (verifyIdeal(ideal, R, mult)) {
                    System.out.print(" - идеал");
                    
                    // Дополнительная информация
                    if (isPrimeIdeal(ideal, R, mult)) {
                        System.out.print(", простой");
                    }
                    if (isMaximalIdeal(ideal, R, n)) {
                        System.out.print(", максимальный");
                    }
                }
                System.out.println();
            }
        }
        
        // Особые случаи
        System.out.println("  (0) = {0} - нулевой идеал");
        System.out.println("  (1) = " + R + " - единичный идеал");
    }
    
    /**
     * Генерация идеала по образующему
     */
    static Set<Integer> generateIdeal(Set<Integer> R, BinaryOperator<Integer> mult, int generator) {
        Set<Integer> ideal = new TreeSet<>();
        ideal.add(generator);
        
        boolean changed;
        do {
            changed = false;
            Set<Integer> newElements = new HashSet<>();
            
            for (Integer a : ideal) {
                for (Integer r : R) {
                    Integer product = mult.apply(a, r);
                    if (!ideal.contains(product)) {
                        newElements.add(product);
                        changed = true;
                    }
                }
            }
            ideal.addAll(newElements);
        } while (changed);
        
        return ideal;
    }
    
    /**
     * Проверка свойств идеала
     */
    static boolean verifyIdeal(Set<Integer> ideal, Set<Integer> R, BinaryOperator<Integer> mult) {
        for (Integer a : ideal) {
            for (Integer r : R) {
                Integer left = mult.apply(a, r);
                Integer right = mult.apply(r, a);
                
                if (!ideal.contains(left) || !ideal.contains(right)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Проверка простого идеала
     */
    static boolean isPrimeIdeal(Set<Integer> ideal, Set<Integer> R, BinaryOperator<Integer> mult) {
        for (Integer a : R) {
            for (Integer b : R) {
                Integer product = mult.apply(a, b);
                if (ideal.contains(product) && !ideal.contains(a) && !ideal.contains(b)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Проверка максимального идеала
     */
    static boolean isMaximalIdeal(Set<Integer> ideal, Set<Integer> R, int n) {
        // В Z/nZ идеал максимален тогда и только тогда, когда n простое
        return ideal.size() > 1 && isPrime(n / ideal.size());
    }
    
    /**
     * Проверка простоты числа
     */
    static boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
