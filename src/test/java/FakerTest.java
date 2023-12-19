import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FakerTest {
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
        $("[data-test-id=success-notification] .notification__title").shouldBe(visible).shouldHave(Condition.exactText("Успешно!"));
        $("[data-test-id=success-notification] .notification__content").shouldBe(visible).shouldHave(Condition.exactText("Встреча успешно запланирована на " + firstMeeting));
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "A", Keys.DELETE);
        $("[data-test-id=date] input").setValue(secondMeeting);
        $(".grid-col .button").click();
        $("[data-test-id=replan-notification] .button").click();
        $("[data-test-id=success-notification] .notification__title").shouldBe(visible).shouldHave(Condition.exactText("Успешно!"));
        $("[data-test-id=success-notification] .notification__content").shouldBe(visible).shouldHave(Condition.exactText("Встреча успешно запланирована на " + secondMeeting));
    }
}