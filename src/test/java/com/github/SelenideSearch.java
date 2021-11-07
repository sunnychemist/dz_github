package com.github;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class SelenideSearch {

    private static final SelenideElement searchFieldInput = $("[data-test-selector=nav-search-input]");
    private static final ElementsCollection repositoryResults = $$(".repo-list li");
    private static final SelenideElement wikiClick = $("#wiki-tab > span:nth-child(2)");
    private static final SelenideElement searchButton = $x("//ul[@data-filterable-for='wiki-pages-filter']//button");
    private static final ElementsCollection wikiRightBar = $$(".wiki-rightbar a");
    private static final SelenideElement textField = $("#wiki-body");

    private final String searchValue = "selenide";

    @BeforeAll
    public static void before() {
        Configuration.startMaximized = true;
    }

    @Test
    void showCodeJUnit5 () {
        open("https://github.com");
        searchFieldInput
                .val(searchValue)
                .pressEnter();
        repositoryResults
                .filter(text("selenide/selenide"))
                .first()
                .$("a").click();
        wikiClick.click();
        searchButton
                .scrollTo()
                .click();
        wikiRightBar
                .filter(text("SoftAssertions"))
                .first()
                .click();
        textField.shouldHave(text("JUnit5 extension - com.codeborne.selenide.junit5.SoftAssertsExtension"));

    }
}
