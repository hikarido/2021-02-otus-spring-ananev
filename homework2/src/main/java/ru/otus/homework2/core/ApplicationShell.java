package ru.otus.homework2.core;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework2.i18n.LocalisationService;

@ShellComponent
public class ApplicationShell {

    private ConsoleExam consoleExam;
    private LocalisationService localisationService;

    public ApplicationShell(ConsoleExam consoleExam, LocalisationService localisationService) {
        this.consoleExam = consoleExam;
        this.localisationService = localisationService;
    }

    @ShellMethod(value = "Only logged users can take exam. It do this stuff.", key = {"l", "login"})
    public String login(
            @ShellOption({"-fn", "--firstName"}) String firstName,
            @ShellOption({"-sn", "--secondName"}) String secondName) {
        consoleExam.login(firstName, secondName);
        return frameByNewLineSymbolAroundText(localisationService.localize("console.welcome", consoleExam.getCurrentUserName()));
    }

    @ShellMethod(value = "Get next question of exam", key = {"n", "nextQuestion"})
    public String nextQuestion() {
        if (!consoleExam.wasUserLogged()) {
            return frameByNewLineSymbolAroundText(localisationService.localize("console.no-login-yet"));
        }

        if (!consoleExam.currentAnswerWasTaken()) {
            return frameByNewLineSymbolAroundText(localisationService.localize("console.unanswered-question-exists"));
        }

        if (consoleExam.hasNextExercise()) {
            return frameByNewLineSymbolAroundText(consoleExam.nextExerciseText());
        } else {
            String wasPassed = consoleExam.wasExamPassed() ?
                    localisationService.localize("console.pass-exam") :
                    localisationService.localize("console.nopass-exam");
            return frameByNewLineSymbolAroundText(localisationService.localize(
                    "console.end-of-exam-result",
                    wasPassed)
            );
        }
    }

    @ShellMethod(
            value = "Text like \"0 1\", 1, \"1 2 5\". Space separated string. For plural answer quotes are required.",
            key = {"a", "answer"}
    )
    public String answer(String answerText) {
        if (!consoleExam.wasUserLogged()) {
            return frameByNewLineSymbolAroundText(localisationService.localize("console.no-login-yet"));
        }

        if (consoleExam.currentAnswerWasTaken()) {
            return frameByNewLineSymbolAroundText(localisationService.localize("console.no-questions-yet"));
        }

        consoleExam.setAnswerOfUser(answerText);
    return frameByNewLineSymbolAroundText(localisationService.localize("console.user-input-was-accepted"));
    }

    private String frameByNewLineSymbolAroundText(String text) {
        return String.format("%n%s%n", text);
    }
}
