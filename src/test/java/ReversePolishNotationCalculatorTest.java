import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

public class ReversePolishNotationCalculatorTest {

    private final ReversePolishNotationCalculator test = new ReversePolishNotationCalculator();

    @Test
    public void shouldCalculateAddition() {
        String str = "4 3 +";
        int result = test.calculatePolishNotation(str);
        assertEquals(7,result,"Результат должен быть 7");
        //Лучше указывать поясняющий комментарий последним параметром, как пример assertEquals(6,result,"Результат должен быть 7");
    }

    @Test
    public void shouldCalculateSubtraction() {
        String str = "4 3 -";
        int result = test.calculatePolishNotation(str);
        assertEquals(1,result,"Результат должен быть 1");
    }

    @Test
    public void shouldCalculateMultiplication() {
        String str = "1 2 *";
        int result = test.calculatePolishNotation(str);
        assertEquals(2,result,"Результат должен быть 2");
    }

    @Test
    public void shouldCalculateSpaces() {
        String str = "1   2   *";
        int result = test.calculatePolishNotation(str);
        assertEquals(2,result,"Результат должен быть 2");
    }

    @Test
    public void shouldCalculateNegativeNumbers() {
        String str = " -1   2   * ";
        int result = test.calculatePolishNotation(str);
        assertEquals(-2,result,"Результат должен быть -2");
    }


    //Не хватает негативных тестов, на тот случай чтобы не выдавался ноль, когда реально система не может посчтать результат
    @Test
    public void noNumbersShouldThrowException(){
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            String str = "/";
            int result = test.calculatePolishNotation(str);
        });

        String expectedMessage = "";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage),"Метод не выдал ошибку");
    }
    
    //Или вот такой вариант проверки на исключение
    @Test
    public void noActionsShouldThrowException(){
        String str = "2 +";
        Throwable exception = assertThrows(NoSuchElementException.class, () -> test.calculatePolishNotation(str));
        assertEquals(null, exception.getMessage());
    }

}
/*
Этот класс тут не нужен
class ReversePolishNotationCalculator {

    public int calculatePolishNotation(String str) {
        String[] parts = str.split(" ");
        Deque<Integer> numbers = new ArrayDeque<>();
        int index = 0;

        while (index != parts.length) {

            if (parts[index].isBlank()) {
                index++;
                continue;
            }

            if (isOperation(parts[index])) {
                int operandOne = numbers.pop();
                int operandTwo = numbers.pop();

                if (parts[index].equals("+")) {
                    numbers.push(operandOne + operandTwo);
                } else if (parts[index].equals("-")) {
                    numbers.push(operandTwo - operandOne);
                } else if (parts[index].equals("*")) {
                    numbers.push(operandOne * operandTwo);
                }

            } else {
                numbers.push(Integer.parseInt(parts[index]));
            }

            index++;
        }

        return numbers.pop();
    }

    private boolean isOperation(String part) {
        if (part.equals("+")
                || part.equals("-")
                || part.equals("*")) {
            return true;
        }

        return false;
    }
}
*/