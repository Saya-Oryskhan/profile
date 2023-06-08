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
import static com.codeborne.selenide.WebDriverRunner.*;  //использовано, чтобы тесты могли продолжить работу со ссылкой первого теста 

public class WikiTest {

    @Test
    public void WikiSearch() {
        open("https://www.wikipedia.org");
        $("#searchInput").setValue("Зигмунд Фрейд").pressEnter();  //находит поле поиска по айди и вводит запрос
    }


    @Test
    public void ZaglStr() {
        if ($(byText("Заглавная страница")).exists()) {            //проверяется существование ссылки для перехода в заглавную страницу
            System.out.println("Ссылка для перехода в заглавную страницу присутствует");
        } else {
            System.out.println("Ссылка для перехода в заглавную страницу отсутствует");
        }
    }

    @Test
    public void ParagraphCheck() {                               //проверка на наличие хотя бы одного параграфа/тэга р
        ElementsCollection articleParagraphs = $$(By.tagName("p")).shouldHave(CollectionCondition.sizeGreaterThan(0));
        if (articleParagraphs.size() > 0) {
            System.out.println("Состоит из параграфов");
        } else {
            System.out.println("Не содержит параграфов");
        }
    }

    @Test
    public void Soderzhanye() {                                 //проверка на существование содержания 
        SelenideElement el = $x("//*[text()='Содержание']");
        if (el.exists()) {
            System.out.println("Статья содержит содержание");
        } else {
            System.out.println("Статья без содержания");
        }
    }

    @Test
    public void TranslateToEn() {                   
        $(By.linkText("English")).click();                      //нажатие на ссылку дял перевода страницы на английский язык
        $(By.tagName("body")).shouldBe(visible);                //ждем пока страница заугрузится/переведется
        String url = getWebDriver().getCurrentUrl();            //получить настоящий адрес 
        if (url.contains("en")) {                               //проверка по адресу ссылки на перевод
            System.out.println("Язык успешно изменен");
        } else {
            System.out.println("Не удалось изменить язык");
        }
    }
}







