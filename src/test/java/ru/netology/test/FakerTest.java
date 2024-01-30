package ru.netology.test;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.FakerData;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FakerTest {

    @BeforeAll
    static void setupAllureReports() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeAllListeners();
    }

    @Test
    void shouldFake() {
        open("http://localhost:9999/");
        FakerData.User User = FakerData.Registration.generateUser();
        String firstMeeting = FakerData.fakeDate();
        String secondMeeting = FakerData.fakeDate();
        $("[data-test-id=city] input").setValue(User.getCity());
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "A", Keys.DELETE);
        $("[data-test-id=date] input").setValue(firstMeeting);
        $("[data-test-id=name] input").setValue(User.getName());
        $("[data-test-id=phone] input").setValue(User.getPhone());
        $("[data-test-id=agreement]").click();
        $(".grid-col .button").click();
        $("[data-test-id=success-notification] .notification__title").shouldBe(visible).shouldHave(exactText("Успешно!"));
        $("[data-test-id=success-notification] .notification__content").shouldBe(visible).shouldHave(exactText("Встреча успешно запланирована на " + firstMeeting));
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "A", Keys.DELETE);
        $("[data-test-id=date] input").setValue(secondMeeting);
        $(".grid-col .button").click();
        $("[data-test-id=replan-notification] .button").click();
        $("[data-test-id=success-notification] .notification__title").shouldBe(visible).shouldHave(exactText("Успешно!"));
        $("[data-test-id=success-notification] .notification__content").shouldBe(visible).shouldHave(exactText("Встреча успешно запланирована на " + secondMeeting));
    }

    @Test
    void shouldSetErrorIfWrongPhone() {
        open("http://localhost:9999/");
        FakerData.User User = FakerData.Registration.generateUser();
        String firstMeeting = FakerData.fakeDate();
        String secondMeeting = FakerData.fakeDate();
        $("[data-test-id=city] input").setValue(User.getCity());
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "A", Keys.DELETE);
        $("[data-test-id=date] input").setValue(firstMeeting);
        $("[data-test-id=name] input").setValue(User.getName());
        $("[data-test-id='phone'] input").setValue(FakerData.invalidPhone());
        $("[data-test-id='agreement']").click();
        $(".grid-col .button").click();
        $("[data-test-id='phone'] .input__sub")
                .shouldHave(exactText("Введен неверный номер телефона."));
    }
}