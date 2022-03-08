package com.tsn;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ParameterizedTestCanva {
    @BeforeEach
    void precondition() {
        open("https://www.canva.com/en/");
        Configuration.browserSize = "1920x1080";
    }

    @AfterEach
    void closeBrowser() {
        closeWebDriver();
    }

    @ValueSource(strings = {"123@123", "@123.com"})
    @ParameterizedTest(name = "Проверка ввода почты некорректного формата при авторизации \"{0}\"")
    void incorrectEmailFormatTest(String testData) {
        $(byText("Log in")).click();
        $(byText("Continue with email")).click();
        $(".Wrk03w").lastChild().setValue(testData);
        $(byText("Continue")).click();
        $(".K33kDA").shouldHave(text("Please enter a valid email."));
    }

    @CsvSource(value = {
            "123@123, Please enter a valid email.",
            "@123.com, Please enter a valid email."
    })
    @ParameterizedTest(name = "Проверка ввода почты некорректного формата при авторизации 2 \"{0}\"")
    void newIncorrectEmailFormatTest(String testData, String expectedText) {
        $(byText("Log in")).click();
        $(byText("Continue with email")).click();
        $(".Wrk03w").lastChild().setValue(testData);
        $(byText("Continue")).click();
        $(".K33kDA").shouldHave(text(expectedText));
    }
}

