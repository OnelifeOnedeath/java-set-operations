import java.util.*;
import java.util.function.BinaryOperator;

/**
 * Исследование алгебраических структур: от множеств до матриц преобразований
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("Исследование алгебраических структур");
        System.out.println("=====================================\n");
        
        demonstrateSetOperations();        // Исходное задание
        investigateRingsAndIdeals();       // Кольца и идеалы
        visualizeMatrixTransformations();  // Матрицы поворотов квадрата
        investigateAutomorphismGroup();    // Группа автоморфизмов
        demonstrateQuotientStructures();   // Фактор-кольца
        showPracticalApplications();       // Приложения
        generateRandomExamples();          // Генератор примеров
        solveCustomProblems();             // Решатель задач
    }
    
    /**
     * 1. ОПЕРАЦИИ НАД МНОЖЕСТВАМИ - исходное задание
     */
    static void demonstrateSetOperations() {
        System.out.println("1. ОПЕРАЦИИ НАД МНОЖЕСТВАМИ");
        System.out.println("---------------------------");
        
        Set<Integer> A = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> B = new HashSet<>(Arrays.asList(3, 4, 5, 6, 7));
        
        System.out.println("Множество A: " + A);
        System.out.println("Множество B: " + B);
        
        BinaryOperator<Integer> customOp = (a, b) -> a * b + 1;
        Set<Integer> result = applyBinaryOperation(A, B, customOp);
        
        System.out.println("A ⊗ B (a*b+1): " + result);
        System.out.println();
    }
    
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
     * 2. КОЛЬЦА И ИДЕАЛЫ
     */
    static void investigateRingsAndIdeals() {
        System.out.println("2. КОЛЬЦА И ЦИКЛИЧЕСКИЕ ИДЕАЛЫ");
        System.out.println("------------------------------");
        
        investigateZModN(12);
        investigateZModN(8);
    }
    
    static void investigateZModN(int n) {
        System.out.println("\nКольцо Z/" + n + "Z:");
        Set<Integer> ring = generateZModN(n);
        System.out.println("Элементы: " + ring);
        
        // Находим идеалы
        System.out.println("Идеалы:");
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                Set<Integer> ideal = generateIdeal(ring, i, n);
                System.out.println("  (" + i + ") = " + ideal);
            }
        }
    }
    
    static Set<Integer> generateZModN(int n) {
        Set<Integer> result = new TreeSet<>();
        for (int i = 0; i < n; i++) result.add(i);
        return result;
    }
    
    static Set<Integer> generateIdeal(Set<Integer> ring, int generator, int mod) {
        Set<Integer> ideal = new TreeSet<>();
        for (int r : ring) {
            ideal.add((generator * r) % mod);
        }
        return ideal;
    }
    
    /**
     * 3. МАТРИЦЫ ПОВОРОТОВ КВАДРАТА - как просил преподаватель
     */
    static void visualizeMatrixTransformations() {
        System.out.println("\n3. МАТРИЦЫ ПОВОРОТОВ КВАДРАТА");
        System.out.println("-----------------------------");
        
        // Матрицы поворотов для квадрата
        double[][] rotate0 = {{1,0},{0,1}};     // 0°
        double[][] rotate90 = {{0,-1},{1,0}};   // 90°
        double[][] rotate180 = {{-1,0},{0,-1}}; // 180°
        double[][] rotate270 = {{0,1},{-1,0}};  // 270°
        
        // Матрицы отражений
        double[][] reflectX = {{1,0},{0,-1}};   // Отражение по X
        double[][] reflectY = {{-1,0},{0,1}};   // Отражение по Y
        double[][] reflectD1 = {{0,1},{1,0}};   // Отражение по диагонали
        double[][] reflectD2 = {{0,-1},{-1,0}}; // Отражение по другой диагонали
        
        System.out.println("Группа диэдра D4 - преобразования квадрата:");
        System.out.println("Повороты:");
        printMatrix("R0 (0°):   ", rotate0);
        printMatrix("R90 (90°): ", rotate90);
        printMatrix("R180 (180°):", rotate180);
        printMatrix("R270 (270°):", rotate270);
        
        System.out.println("\nОтражения:");
        printMatrix("Sx (отр.X):", reflectX);
        printMatrix("Sy (отр.Y):", reflectY);
        printMatrix("Sd1 (диаг1):", reflectD1);
        printMatrix("Sd2 (диаг2):", reflectD2);
        
        // Покажем композицию преобразований
        System.out.println("\nКомпозиция преобразований:");
        double[][] comp1 = multiplyMatrices(rotate90, rotate90);
        printMatrix("R90 ∘ R90 = R180:", comp1);
        
        double[][] comp2 = multiplyMatrices(rotate90, reflectX);
        printMatrix("R90 ∘ Sx = Sd1:  ", comp2);
    }
    
    static void printMatrix(String label, double[][] matrix) {
        System.out.print(label + " [");
        System.out.printf("%2.0f %2.0f", matrix[0][0], matrix[0][1]);
        System.out.print("] [");
        System.out.printf("%2.0f %2.0f", matrix[1][0], matrix[1][1]);
        System.out.println("]");
    }
    
    static double[][] multiplyMatrices(double[][] a, double[][] b) {
        double[][] result = new double[2][2];
        result[0][0] = a[0][0]*b[0][0] + a[0][1]*b[1][0];
        result[0][1] = a[0][0]*b[0][1] + a[0][1]*b[1][1];
        result[1][0] = a[1][0]*b[0][0] + a[1][1]*b[1][0];
        result[1][1] = a[1][0]*b[0][1] + a[1][1]*b[1][1];
        return result;
    }
    
    /**
     * 4. ГРУППА АВТОМОРФИЗМОВ
     */
    static void investigateAutomorphismGroup() {
        System.out.println("\n4. ГРУППА АВТОМОРФИЗМОВ КВАДРАТА");
        System.out.println("-------------------------------");
        
        System.out.println("D4 = {R0, R90, R180, R270, Sx, Sy, Sd1, Sd2}");
        System.out.println("Порядок группы: 8");
        System.out.println("Таблица умножения (частично):");
        System.out.println("R90 ∘ R90 = R180");
        System.out.println("R90 ∘ Sx = Sd1");
        System.out.println("Sx ∘ Sx = R0");
        System.out.println("R90 ∘ R270 = R0");
        
        System.out.println("\nСтруктура группы:");
        System.out.println("D4 ≅ Z/4Z ⋊ Z/2Z - полупрямое произведение");
        System.out.println("Подгруппа поворотов: {R0, R90, R180, R270} ≅ Z/4Z");
        System.out.println("Это нормальная подгруппа порядка 4");
    }
    
    /**
     * 5. ФАКТОР-КОЛЬЦА И ГОМОМОРФИЗМЫ
     */
    static void demonstrateQuotientStructures() {
        System.out.println("\n5. ФАКТОР-КОЛЬЦА И ГОМОМОРФИЗМЫ");
        System.out.println("-------------------------------");
        
        System.out.println("Пример: Z/6Z / (2) ≅ Z/2Z");
        System.out.println("Идеал: (2) = {0, 2, 4}");
        System.out.println("Классы смежности: {0,2,4} + {1,3,5}");
        System.out.println("Фактор-кольцо имеет 2 элемента: [0] и [1]");
        
        System.out.println("\nГомоморфизм φ: Z/6Z → Z/2Z");
        System.out.println("φ(x) = x mod 2");
        System.out.println("Ядро: Ker φ = {0, 2, 4} = (2)");
        System.out.println("Образ: Im φ = Z/2Z");
    }
    
    /**
     * 6. ПРАКТИЧЕСКИЕ ПРИЛОЖЕНИЯ
     */
    static void showPracticalApplications() {
        System.out.println("\n6. ПРАКТИЧЕСКИЕ ПРИЛОЖЕНИЯ");
        System.out.println("--------------------------");
        
        System.out.println("Криптография:");
        System.out.println("- RSA: умножение в Z/nZ");
        System.out.println("- Эллиптические кривые: групповой закон");
        
        System.out.println("\nКомпьютерная графика:");
        System.out.println("- Повороты и отражения: матрицы 2x2");
        System.out.println("- 3D преобразования: матрицы 4x4");
        
        System.out.println("\nКодирование:");
        System.out.println("- Коды с исправлением ошибок: поля Галуа");
        System.out.println("- Контрольные суммы: арифметика по модулю");
    }
    
    /**
     * 7. ГЕНЕРАТОР ПРИМЕРОВ
     */
    static void generateRandomExamples() {
        System.out.println("\n7. ГЕНЕРАТОР СЛУЧАЙНЫХ ПРИМЕРОВ");
        System.out.println("-------------------------------");
        
        Random rand = new Random();
        int n = rand.nextInt(5) + 6; // 6-10
        System.out.println("Случайное кольцо: Z/" + n + "Z");
        
        Set<Integer> ring = generateZModN(n);
        System.out.println("Элементы: " + ring);
        
        // Найдем нетривиальные идеалы
        List<Integer> ideals = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            if (n % i == 0) ideals.add(i);
        }
        
        if (!ideals.isEmpty()) {
            int idealGen = ideals.get(rand.nextInt(ideals.size()));
            Set<Integer> ideal = generateIdeal(ring, idealGen, n);
            System.out.println("Идеал: (" + idealGen + ") = " + ideal);
        } else {
            System.out.println("Простое кольцо - только тривиальные идеалы");
        }
    }
    
    /**
     * 8. РЕШАТЕЛЬ ЗАДАЧ
     */
    static void solveCustomProblems() {
        System.out.println("\n8. РЕШАТЕЛЬ АЛГЕБРАИЧЕСКИХ ЗАДАЧ");
        System.out.println("-------------------------------");
        
        // Проверим, является ли множество {0,3,6,9} идеалом в Z/12Z
        Set<Integer> candidate = new HashSet<>(Arrays.asList(0, 3, 6, 9));
        Set<Integer> Z12 = generateZModN(12);
        
        boolean isIdeal = true;
        for (int a : candidate) {
            for (int r : Z12) {
                int product = (a * r) % 12;
                if (!candidate.contains(product)) {
                    isIdeal = false;
                    break;
                }
            }
            if (!isIdeal) break;
        }
        
        System.out.println("Задача: {0,3,6,9} - идеал в Z/12Z?");
        System.out.println("Ответ: " + (isIdeal ? "Да" : "Нет"));
        
        if (isIdeal) {
            System.out.println("Это главный идеал (3)");
        }
    }
}
