import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.Thread.sleep;

public class TestValueSource {


        @BeforeEach
        void dnsOpen() {
                Configuration.browserSize = "1920x1080";
                open("https://www.dns-shop.ru/catalog/17a8bfb516404e77/tv-konsoli-i-audio/");
                Configuration.holdBrowserOpen = true;
        }

        @ValueSource(strings = {
                "Телевизоры и аксессуары",
                "Консоли и видеоигры",
                "Аудиотехника"
        })

        @ParameterizedTest(name = "Поиск раздела {0} на сайте dns-shop.ru")
        @Tag("BLOCKER")
        void dnsValueTest(String value) {
            $$(".subcategory__item-container").find(text(value));
        }
}
