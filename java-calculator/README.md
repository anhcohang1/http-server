# Calculatrice Java 25

Calculatrice graphique basique réalisée en Java 25 avec Swing.

## Fonctions

- Addition `+`
- Soustraction `-`
- Multiplication `×`
- Division `÷`
- Bouton spécial **F**

Pour un entier `n`, le bouton **F** calcule :

`F(n) = somme des chiffres de (3n² + n + 1)`

Exemple : pour `n = 5`, `3×5² + 5 + 1 = 81`, donc `F(5) = 8 + 1 = 9`.

## Prérequis

- JDK 25
- Maven 3.9+ (ou compilation directe avec `javac`)

## Compiler avec Maven

```bash
cd java-calculator
mvn clean package
```

## Lancer

```bash
java -cp target/classes fr.calculatrice.CalculatorApp
```

## Sans Maven

```bash
cd java-calculator
javac -d out src/main/java/fr/calculatrice/*.java
java -cp out fr.calculatrice.CalculatorApp
```
