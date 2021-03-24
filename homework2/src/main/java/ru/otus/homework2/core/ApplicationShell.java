package ru.otus.homework2.core;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ApplicationShell {

    private ConsoleExam consoleExam;

    public ApplicationShell(ConsoleExam consoleExam) {
        this.consoleExam = consoleExam;
    }

    @ShellMethod(value = "Only logged users can take exam. It do this stuff.", key = {"l", "login"})
    public String login(
            @ShellOption({"-fn", "--firstName"}) String firstName,
            @ShellOption({"-sn", "--secondName"}) String secondName) {
        consoleExam.login(firstName, secondName);
        return frameByNewLineSymbolAroundText("Hello " + consoleExam.getCurrentUserName());
    }

    @ShellMethod(value = "Get next question of exam", key = {"n", "nextQuestion"})
    public String nextQuestion() {
        if (!consoleExam.wasUserLogged()) {
            return frameByNewLineSymbolAroundText("User have not been logged yet!");
        }

        if (!consoleExam.currentAnswerWasTaken()) {
            return frameByNewLineSymbolAroundText("First answer to previous question. Then try get next.");
        }

        if (consoleExam.hasNextExercise()) {
            return frameByNewLineSymbolAroundText(consoleExam.nextExerciseText());
        } else {
            String wasPassed = consoleExam.wasExamPassed() ? "passed" : "not passed";
            return frameByNewLineSymbolAroundText("Exam already done. You " + wasPassed + " exam");
        }
    }

    @ShellMethod(
            value = "Text like \"0 1\", 1, \"1 2 5\". Space separated string. For plural answer quotes are required.",
            key = {"a", "answer"}
    )
    public String answer(String answerText) {
        if (!consoleExam.wasUserLogged()) {
            return frameByNewLineSymbolAroundText("User have not been logged yet!");
        }

        if (consoleExam.currentAnswerWasTaken()) {
            return frameByNewLineSymbolAroundText("There is no question to answer");
        }

        consoleExam.setAnswerOfUser(answerText);
        return frameByNewLineSymbolAroundText("ok");
    }

    private String frameByNewLineSymbolAroundText(String text) {
        return String.format("%n%s%n", text);
    }
}
