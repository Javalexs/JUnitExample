import com.codeborne.selenide.CollectionCondition;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class TestMethodSource {
    @BeforeEach
    void dnsOpen() {
        Configuration.browserSize = "1920x1080";
        open("https://www.dns-shop.ru/catalog/2ae416863baa7fd7/brite/");
        Configuration.holdBrowserOpen = true;
    }

    static Stream<Arguments> dnsSearch() {
        return Stream.of(
                Arguments.of("Триммеры", List.of("Для бороды и усов", "Для носа и ушей", "Для волос на теле", "Профессиональные", "Аккумуляторные", "Для бровей")),
                Arguments.of("Мужские электробритвы", List.of("", "Роторные", "Сеточные", "Влажное бритье", "Сухое бритье", "С насадкой для стрижки волос"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Отображение списка фильтров {1} " + ", на странице {0}")
    @Tag("BLOCKER")
    void dnsMeSourceTest(String category, List<String> filter){
        $(".subcategory__item-container").find(byText(category)).click();
        $$(".receipts a").filter(visible).shouldHave(CollectionCondition.texts(filter));
    }


}
