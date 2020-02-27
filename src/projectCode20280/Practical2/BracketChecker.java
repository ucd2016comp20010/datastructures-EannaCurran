package projectCode20280.Practical2;

public class BracketChecker {

    public static boolean checkParentheses(String expression) {

        final String opening = "({[";
        final String closing = ")}]";

        Stack<Character> buffer = new LinkedStack<>( );
        for (char c : expression.toCharArray()) {

            if (opening.indexOf(c) != -1) {
                buffer.push(c);
            }

            else if (closing.indexOf(c) != -1) {
                if (buffer.isEmpty()) {
                    return false;
                }
                if (closing.indexOf(c) != opening.indexOf(buffer.pop())) {
                    return false;
                }
            }
        }
        return buffer.isEmpty();
    }

    public static void main(String[] args) {
        String [] inputs = {
                "[]]()()" ,
                "c[d]" ,
                "a{b[c]d}e" ,
                "a[b{c}d]e}" ,
                "a{b(c)"
        };

        for(String input: inputs){
            boolean balanced = BracketChecker.checkParentheses(input);
            System.out.println("Balance result for "+input + " " + balanced);
        }
    }

}
