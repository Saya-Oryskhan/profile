package org.selenide.works;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;

public class WikiTest {

    @Test
    public void WikiSearch() {
        open("https://www.wikipedia.org");
        $("#searchInput").setValue("Зигмунд Фрейд").pressEnter();
    }


    @Test
    public void ZaglStr() {
        if ($(byText("Заглавная страница")).exists()) {
            System.out.println("Ссылка для перехода в заглавную страницу присутствует");
        } else {
            System.out.println("Ссылка для перехода в заглавную страницу отсутствует");
        }
    }

    @Test
    public void ParagraphCheck() {
        ElementsCollection articleParagraphs = $$(By.tagName("p")).shouldHave(CollectionCondition.sizeGreaterThan(0));
        if (articleParagraphs.size() > 0) {
            System.out.println("Состоит из параграфов");
        } else {
            System.out.println("Не содержит параграфов");
        }
    }

    @Test
    public void Soderzhanye() {
        SelenideElement el = $x("//*[text()='Содержание']");
        if (el.exists()) {
            System.out.println("Статья содержит содержание");
        } else {
            System.out.println("Статья без содержания");
        }
    }

    @Test
    public void TranslateToEn() {
        $(By.linkText("English")).click();
        $(By.tagName("body")).shouldBe(visible);
        String url = getWebDriver().getCurrentUrl();
        if (url.contains("en")) {
            System.out.println("Язык успешно изменен");
        } else {
            System.out.println("Не удалось изменить язык");
        }
    }
}







