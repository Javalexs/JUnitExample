import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestCsVSource {

    @BeforeEach
    void dnsOpen() {
        Configuration.browserSize = "1920x1080";
        open("https://www.dns-shop.ru/");
        Configuration.holdBrowserOpen = true;
    }
    @CsvSource({
            "Телефон, Apple iPhone 13",
            "Ноутбук, HUAWEI",
            "Холодильник, Indesit"

    })

    @ParameterizedTest(name = "Проверка наличия на сайте названия товара  {1} " + ", в результате ввода запроса {0}")
    @Tag("BLOCKER")
    void dnsCsVTest(String searchWord, String categoryWord) {
        $(".presearch__input").setValue(searchWord).pressEnter();
        $(".products-list").shouldHave(text(categoryWord));
    }
}
