import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class searchSoftAssertions {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com/";
        Configuration.pageLoadStrategy = "eager";
    }


    @Test
    void softAssertionsTest() {
        //открыть страницу Selenide в Github
        open("/selenide/selenide");
        //Перейти в раздел Wiki проект
        $("#wiki-tab").click();
        //Убедиться, что в списке страниц (Pages) есть страница SoftAssertions
        // 1 вариант
        //$("#wiki-pages-filter").setValue("SoftAssertions"); // В строку поиска по страницам ввести SoftAssertions
        //$("#wiki-pages-box").shouldHave(text("SoftAssertions")); //проверка результата поиска
        // 2 вариант
        $(".Box-row wiki-more-pages-link").$("button").click(); // нажать "Show 3 more pages…"
        $("#wiki-pages-box").shouldHave(text("SoftAssertions")); //проверка: SoftAssertions есть на странице
        //Открыть страницу SoftAssertions/Клик по ссылке
        $("#wiki-pages-box").$(byText("SoftAssertions")).click();
        //Проверить, что внутри есть пример кода для JUnit5
        $("#wiki-body").shouldHave(Condition.text("@ExtendWith({SoftAssertsExtension.class}) class Tests { @Test void test() { Configuration.assertionMode = SOFT; open(\"page.html\");  $(\"#first\").should(visible).click(); $(\"#second\").should(visible).click(); } }"));

    }
}