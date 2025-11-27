import java.util.*;
import java.util.function.BinaryOperator;

/**
 * РЕШЕНИЕ ЗАДАНИЯ ОТ ЮРИЯ СИМАКОВА
 * 
 * Задание: Задать два целочисленных множества 
 * и построить функцию над бинарной операцией
 * 
 * Автор: OnelifeOnedeath, студент спбгу 1-го курса мкн Группы 25.Б09-мкн
 * Дата: 27.11.2025
 * 
 * Особенности реализации:
 * - Поддержка любых бинарных операций через BinaryOperator
 * - Проверка свойств операций
 * - Табличное представление
 * - Тестовые примеры
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
    
    /**
     * Демонстрация базовых операций из задания
     */
    static void demonstrateBasicOperations() {
        System.out.println("🎯 ЗАДАНИЕ: два множества + бинарная операция");
        
        // 1. Создаем два целочисленных множества
        Set<Integer> A = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> B = new HashSet<>(Arrays.asList(3, 4, 5, 6, 7));
        
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
    
    /**
     * Проверка свойств замкнутости операции
     */
    static void demonstrateClosureProperties() {
        System.out.println("\n🔍 ПРОВЕРКА СВОЙСТВ ОПЕРАЦИИ:");
        Set<Integer> A = new HashSet<>(Arrays.asList(1, 2, 3));
        
        // Проверяем замкнутость операции a * b + 1
        BinaryOperator<Integer> op = (a, b) -> a * b + 1;
        Set<Integer> result = applyBinaryOperation(A, A, op);
        
        System.out.println("Множество A = " + A);
        System.out.println("Результат A * A + 1 = " + result);
        
        // Проверяем, содержится ли исходное множество в результате
        boolean isClosed = result.containsAll(A);
        System.out.println("Замыкание: Множество A " + (isClosed ? "содержится" : "НЕ содержится") + " в результате операции");
        
        // Дополнительная проверка - все ли результаты принадлежат целым числам
        boolean allIntegers = result.stream().allMatch(n -> n instanceof Integer);
        System.out.println("Все результаты - целые числа: " + (allIntegers ? "ДА" : "НЕТ"));
    }
    
    /**
     * Построение таблицы Кэли для операции
     */
    static void demonstrateOperationTable() {
        System.out.println("\n📊 ТАБЛИЦА ОПЕРАЦИИ (a * b + 1):");
        Set<Integer> A = new TreeSet<>(Arrays.asList(1, 2, 3)); // TreeSet для упорядочивания
        
        // Заголовок таблицы
        System.out.print("   ");
        for (int b : A) {
            System.out.print(" " + b + "  ");
        }
        System.out.println();
        
        // Разделительная линия
        System.out.print("  +");
        for (int i = 0; i < A.size(); i++) {
            System.out.print("----");
        }
        System.out.println();
        
        // Тело таблицы
        for (int a : A) {
            System.out.print(a + " |");
            for (int b : A) {
                int result = a * b + 1;
                System.out.print(" " + result + " ");
            }
            System.out.println();
        }
        
        // Анализ таблицы
        System.out.println("\n📈 АНАЛИЗ ТАБЛИЦЫ:");
        System.out.println("- Коммутативность: " + checkCommutativity(A, (x, y) -> x * y + 1));
        System.out.println("- Есть нейтральный элемент: " + hasIdentityElement(A, (x, y) -> x * y + 1));
    }
    
    /**
     * Проверка коммутативности операции
     */
    static boolean checkCommutativity(Set<Integer> set, BinaryOperator<Integer> operation) {
        for (int a : set) {
            for (int b : set) {
                if (!operation.apply(a, b).equals(operation.apply(b, a))) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Проверка наличия нейтрального элемента
     */
    static boolean hasIdentityElement(Set<Integer> set, BinaryOperator<Integer> operation) {
        for (int candidate : set) {
            boolean isIdentity = true;
            for (int element : set) {
                if (!operation.apply(element, candidate).equals(element) ||
                    !operation.apply(candidate, element).equals(element)) {
                    isIdentity = false;
                    break;
                }
            }
            if (isIdentity) return true;
        }
        return false;
    }
    
    /**
     * Простые тесты для проверки корректности
     */
    static void runTests() {
        System.out.println("\n🧪 ТЕСТИРОВАНИЕ:");
        
        // Тест 1: Базовая функциональность
        Set<Integer> testA = new HashSet<>(Arrays.asList(1, 2));
        Set<Integer> testB = new HashSet<>(Arrays.asList(2, 3));
        
        BinaryOperator<Integer> testOp = (a, b) -> a + b;
        Set<Integer> result = applyBinaryOperation(testA, testB, testOp);
        
        Set<Integer> expected = new HashSet<>(Arrays.asList(3, 4, 5));
        boolean test1Passed = result.equals(expected);
        
        System.out.println("ТЕСТ 1 - Базовая операция сложения:");
        System.out.println("  A={1,2}, B={2,3}, операция: a+b");
        System.out.println("  Ожидаемый результат: {3,4,5}");
        System.out.println("  Фактический результат: " + result);
        System.out.println("  Статус: " + (test1Passed ? "ПРОЙДЕН ✅" : "ПРОВАЛЕН ❌"));
        
        // Тест 2: Пустые множества
        Set<Integer> emptySet = new HashSet<>();
        Set<Integer> resultEmpty = applyBinaryOperation(testA, emptySet, testOp);
        boolean test2Passed = resultEmpty.isEmpty();
        
        System.out.println("\nТЕСТ 2 - Операция с пустым множеством:");
        System.out.println("  A={1,2}, B={}, операция: a+b");
        System.out.println("  Ожидаемый результат: {}");
        System.out.println("  Фактический результат: " + resultEmpty);
        System.out.println("  Статус: " + (test2Passed ? "ПРОЙДЕН ✅" : "ПРОВАЛЕН ❌"));
        
        // Тест 3: Одинаковые множества
        Set<Integer> resultSame = applyBinaryOperation(testA, testA, testOp);
        Set<Integer> expectedSame = new HashSet<>(Arrays.asList(2, 3, 4));
        boolean test3Passed = resultSame.equals(expectedSame);
        
        System.out.println("\nТЕСТ 3 - Операция с одинаковыми множествами:");
        System.out.println("  A={1,2}, B={1,2}, операция: a+b");
        System.out.println("  Ожидаемый результат: {2,3,4}");
        System.out.println("  Фактический результат: " + resultSame);
        System.out.println("  Статус: " + (test3Passed ? "ПРОЙДЕН ✅" : "ПРОВАЛЕН ❌"));
        
        // Итог тестирования
        System.out.println("\n📊 ИТОГ ТЕСТИРОВАНИЯ:");
        int passedTests = (test1Passed ? 1 : 0) + (test2Passed ? 1 : 0) + (test3Passed ? 1 : 0);
        System.out.println("Пройдено тестов: " + passedTests + "/3");
    }
    
    /**
     * Главный метод с обработкой ошибок
     */
    public static void main(String[] args) {
        try {
            System.out.println("=" .repeat(50));
            System.out.println("🚀 JAVA SET OPERATIONS - РЕШЕНИЕ ЗАДАНИЯ");
            System.out.println("=" .repeat(50));
            
            // Мой код
            demonstrateBasicOperations();
            
            // ДОБАВЛЯЕМ:
            demonstrateClosureProperties();  // Проверка замкнутости
            demonstrateOperationTable();     // Таблица операции  
            runTests();                      // Простые тесты
            
            System.out.println("\n" + "=" .repeat(50));
            System.out.println("✅ ВЫПОЛНЕНИЕ ЗАВЕРШЕНО УСПЕШНО!");
            System.out.println("=" .repeat(50));
            
        } catch (Exception e) {
            System.out.println("❌ ОШИБКА: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
