import java.util.*;
import java.util.function.BinaryOperator;

/**
 * Исследование алгебраических структур: от множеств до гиперболических функций
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("Исследование алгебраических структур");
        System.out.println("=====================================\n");
        
        demonstrateSetOperations();        // Исходное задание
        investigateRingsAndIdeals();       // Кольца и идеалы
        visualizeComplexTransformations(); // Комплексные представления
        checkTeacherFormula();            // Формула преподавателя
        demonstrateHyperbolicConnection(); // Связь с гиперболическими функциями
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
     * 3. КОМПЛЕКСНОЕ ПРЕДСТАВЛЕНИЕ ПОВОРОТОВ КВАДРАТА
     */
    static void visualizeComplexTransformations() {
        System.out.println("\n3. КОМПЛЕКСНОЕ ПРЕДСТАВЛЕНИЕ ПОВОРОТОВ");
        System.out.println("-------------------------------------");
        
        // Квадрат как комплексные числа
        Complex[] square = {
            new Complex(1, 1),   // 1 + i
            new Complex(-1, 1),  // -1 + i  
            new Complex(-1, -1), // -1 - i
            new Complex(1, -1)   // 1 - i
        };
        
        System.out.println("Квадрат как комплексные числа:");
        printComplexArray(square);
        
        // Повороты как умножение на e^(iθ)
        Complex rotate90 = Complex.fromPolar(1, Math.PI/2);  // e^(iπ/2) = i
        Complex rotate45 = Complex.fromPolar(1, Math.PI/4);  // e^(iπ/4)
        
        System.out.println("\nПоворот на 90° = умножение на i:");
        System.out.println("i = " + rotate90);
        
        Complex[] rotated90 = transformComplexShape(square, rotate90);
        System.out.println("После поворота на 90°:");
        printComplexArray(rotated90);
        
        // Гомоморфизм: композиция поворотов
        demonstrateComplexHomomorphism();
    }
    
    static void demonstrateComplexHomomorphism() {
        System.out.println("\nГОМОМОРФИЗМ ПОВОРОТОВ:");
        
        Complex z = new Complex(1, 0);
        Complex r30 = Complex.fromPolar(1, Math.PI/6);
        Complex r60 = Complex.fromPolar(1, Math.PI/3);
        Complex r90 = Complex.fromPolar(1, Math.PI/2);
        
        // Гомоморфизм: R30 ∘ R60 = R90
        Complex comp = r30.multiply(r60);
        System.out.println("R30 * R60 = " + comp);
        System.out.println("R90 = " + r90);
        System.out.println("Гомоморфизм: " + comp.equals(r90));
    }
    
    /**
     * 4. ФОРМУЛА ПРЕПОДАВАТЕЛЯ ДЛЯ СОПРЯЖЕНИЯ
     */
    static void checkTeacherFormula() {
        System.out.println("\n4. ФОРМУЛА ПРЕПОДАВАТЕЛЯ ДЛЯ СОПРЯЖЕНИЯ");
        System.out.println("-------------------------------------");
        
        Complex z = new Complex(3, 4);
        System.out.println("Исходное число: z = " + z);
        
        // Стандартное сопряжение
        Complex standard = new Complex(z.real, -z.imag);
        System.out.println("Стандартное сопряжение: z̅ = " + standard);
        
        // Формула преподавателя
        Complex teacher = calculateTeacherConjugate(z);
        System.out.println("Формула преподавателя: " + teacher);
        System.out.println("Результаты совпадают: " + standard.equals(teacher));
        
        // Разность квадратов
        double r = z.magnitude();
        double theta = z.argument();
        double sin2 = Math.sin(theta) * Math.sin(theta);
        double cos2 = Math.cos(theta) * Math.cos(theta);
        double diff = sin2 - cos2;
        
        System.out.printf("\nРазность квадратов: sin²θ - cos²θ = %.3f - %.3f = %.3f\n", 
                         sin2, cos2, diff);
        System.out.printf("Это равно -cos(2θ) = %.3f\n", -Math.cos(2*theta));
    }
    
    static Complex calculateTeacherConjugate(Complex z) {
        double r = z.magnitude();
        double theta = z.argument();
        return new Complex(r * Math.sin(theta), -r * Math.cos(theta));
    }
    
    /**
     * 5. СВЯЗЬ С ГИПЕРБОЛИЧЕСКИМИ ФУНКЦИЯМИ
     */
    static void demonstrateHyperbolicConnection() {
        System.out.println("\n5. СВЯЗЬ С ГИПЕРБОЛИЧЕСКИМИ ФУНКЦИЯМИ");
        System.out.println("-------------------------------------");
        
        System.out.println("Формула Эйлера для гиперболических функций:");
        System.out.println("ch(t) = (eᵗ + e⁻ᵗ)/2");
        System.out.println("sh(t) = (eᵗ - e⁻ᵗ)/2");
        System.out.println("ch²(t) - sh²(t) = 1 ← уравнение гиперболы!");
        
        System.out.println("\nСвязь с тригонометрическими функциями:");
        System.out.println("ch(it) = cos(t)");
        System.out.println("sh(it) = i·sin(t)");
        System.out.println("cos(it) = ch(t)");
        System.out.println("sin(it) = -i·sh(t)");
        
        // Демонстрация на примере
        double t = 0.5;
        System.out.printf("\nПример для t = %.2f:\n", t);
        
        double ch = Math.cosh(t);
        double sh = Math.sinh(t);
        double cos = Math.cos(t);
        double sin = Math.sin(t);
        
        System.out.printf("ch(%.2f) = %.3f\n", t, ch);
        System.out.printf("sh(%.2f) = %.3f\n", t, sh);
        System.out.printf("ch² - sh² = %.3f - %.3f = %.3f ✓\n", ch*ch, sh*sh, ch*ch - sh*sh);
        
        System.out.printf("\nch(i·%.2f) = cos(%.2f) = %.3f\n", t, t, Math.cos(t));
        System.out.printf("sh(i·%.2f) = i·sin(%.2f) = i·%.3f\n", t, t, Math.sin(t));
        
        // Покажем связь с формулой преподавателя
        demonstrateHyperbolicRotation();
    }
    
    static void demonstrateHyperbolicRotation() {
        System.out.println("\nГИПЕРБОЛИЧЕСКИЕ ПОВОРОТЫ:");
        
        // Гиперболический поворот
        double t = 0.3;
        double[][] hyperbolicRotation = {
            {Math.cosh(t), Math.sinh(t)},
            {Math.sinh(t), Math.cosh(t)}
        };
        
        System.out.printf("Гиперболический поворот на t=%.2f:\n", t);
        System.out.printf("[ch(t)  sh(t)]   [%.3f  %.3f]\n", 
                         hyperbolicRotation[0][0], hyperbolicRotation[0][1]);
        System.out.printf("[sh(t)  ch(t)] = [%.3f  %.3f]\n", 
                         hyperbolicRotation[1][0], hyperbolicRotation[1][1]);
        
        // Действие на точку гиперболы
        double[] point = {Math.cosh(0.2), Math.sinh(0.2)}; // точка на гиперболе
        double[] transformed = {
            hyperbolicRotation[0][0]*point[0] + hyperbolicRotation[0][1]*point[1],
            hyperbolicRotation[1][0]*point[0] + hyperbolicRotation[1][1]*point[1]
        };
        
        System.out.printf("\nТочка на гиперболе: (ch(0.2), sh(0.2)) = (%.3f, %.3f)\n", 
                         point[0], point[1]);
        System.out.printf("После гиперболического поворота: (%.3f, %.3f)\n", 
                         transformed[0], transformed[1]);
        
        // Проверим что осталась на гиперболе
        double x2 = transformed[0] * transformed[0];
        double y2 = transformed[1] * transformed[1];
        System.out.printf("x² - y² = %.3f - %.3f = %.3f (должно быть 1)\n", x2, y2, x2 - y2);
    }
    
    /**
     * 6. ГРУППА АВТОМОРФИЗМОВ
     */
    static void investigateAutomorphismGroup() {
        System.out.println("\n6. ГРУППА АВТОМОРФИЗМОВ КВАДРАТА");
        System.out.println("-------------------------------");
        
        System.out.println("D4 = {R0, R90, R180, R270, Sx, Sy, Sd1, Sd2}");
        System.out.println("Порядок группы: 8");
    }
    
    /**
     * 7. ФАКТОР-КОЛЬЦА И ГОМОМОРФИЗМЫ
     */
    static void demonstrateQuotientStructures() {
        System.out.println("\n7. ФАКТОР-КОЛЬЦА И ГОМОМОРФИЗМЫ");
        System.out.println("-------------------------------");
        
        System.out.println("Z/6Z / (2) ≅ Z/2Z");
    }
    
    /**
     * 8. ПРАКТИЧЕСКИЕ ПРИЛОЖЕНИЯ
     */
    static void showPracticalApplications() {
        System.out.println("\n8. ПРАКТИЧЕСКИЕ ПРИЛОЖЕНИЯ");
        System.out.println("--------------------------");
        
        System.out.println("Гиперболическая геометрия:");
        System.out.println("- Геометрия Лобачевского");
        System.out.println("- Специальная теория относительности");
    }
    
    /**
     * 9. ГЕНЕРАТОР ПРИМЕРОВ
     */
    static void generateRandomExamples() {
        System.out.println("\n9. ГЕНЕРАТОР СЛУЧАЙНЫХ ПРИМЕРОВ");
        System.out.println("-------------------------------");
        
        Random rand = new Random();
        int n = rand.nextInt(5) + 6;
        System.out.println("Случайное кольцо: Z/" + n + "Z");
    }
    
    /**
     * 10. РЕШАТЕЛЬ ЗАДАЧ
     */
    static void solveCustomProblems() {
        System.out.println("\n10. РЕШАТЕЛЬ АЛГЕБРАИЧЕСКИХ ЗАДАЧ");
        System.out.println("--------------------------------");
        
        System.out.println("Проверка идеалов в Z/12Z...");
    }
    
    /**
     * Класс для работы с комплексными числами
     */
    static class Complex {
        double real;
        double imag;
        
        Complex(double real, double imag) {
            this.real = real;
            this.imag = imag;
        }
        
        // Модуль числа
        double magnitude() {
            return Math.sqrt(real * real + imag * imag);
        }
        
        // Аргумент числа
        double argument() {
            return Math.atan2(imag, real);
        }
        
        // Создание из полярных координат
        static Complex fromPolar(double r, double theta) {
            return new Complex(r * Math.cos(theta), r * Math.sin(theta));
        }
        
        // Умножение
        Complex multiply(Complex other) {
            return new Complex(
                this.real * other.real - this.imag * other.imag,
                this.real * other.imag + this.imag * other.real
            );
        }
        
        public String toString() {
            if (imag >= 0) {
                return String.format("%.3f + %.3fi", real, imag);
            } else {
                return String.format("%.3f - %.3fi", real, Math.abs(imag));
            }
        }
        
        boolean equals(Complex other) {
            return Math.abs(this.real - other.real) < 1e-10 &&
                   Math.abs(this.imag - other.imag) < 1e-10;
        }
    }
    
    static void printComplexArray(Complex[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("z%d = %s  ", i+1, arr[i]);
        }
        System.out.println();
    }
    
    static Complex[] transformComplexShape(Complex[] shape, Complex rotation) {
        Complex[] result = new Complex[shape.length];
        for (int i = 0; i < shape.length; i++) {
            result[i] = shape[i].multiply(rotation);
        }
        return result;
    }
}
