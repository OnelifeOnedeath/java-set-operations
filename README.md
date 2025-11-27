#  Java Set Operations Lab

[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://java.com)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

**Практическая реализация операций над множествами и автоморфных отображений на Java**

Проект содержит решения учебных заданий и исследование математических концепций через программный код.

## Текущее задание

**Задача:** Задать два целочисленных множества и построить функцию над бинарной операцией.

### Быстрое решение

```java
// Основная реализация в src/Main.java
public class Main {
    public static void main(String[] args) {
        // Два целочисленных множества
        Set<Integer> A = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> B = new HashSet<>(Arrays.asList(3, 4, 5, 6, 7));
        
        // Бинарная операция: (a, b) -> a * b + 1
        BinaryOperator<Integer> operation = (a, b) -> a * b + 1;
        
        // Применение операции ко всем парам элементов
        Set<Integer> result = applyBinaryOperation(A, B, operation);
        
        System.out.println("Множество A: " + A);
        System.out.println("Множество B: " + B);
        System.out.println("Результат бинарной операции (a*b+1): " + result);
    }
}# java-set-operations
