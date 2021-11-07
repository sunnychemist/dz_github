package com.github;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DragDrop {

    private final SelenideElement elementA =  $("#column-a");
    private final SelenideElement elementB = $("#column-b");

    @BeforeAll
    public static void before() {
        Configuration.startMaximized = true;
    }

    @Test
    void changeRectangles () {
        open("https://the-internet.herokuapp.com/drag_and_drop");

        elementA.shouldHave(Condition.exactText("A"));
        elementB.shouldHave(Condition.exactText("B"));
        elementA.dragAndDropTo(elementB);
        elementA.shouldHave(Condition.exactText("B"));
        elementB.shouldHave(Condition.exactText("A"));

    }
}
