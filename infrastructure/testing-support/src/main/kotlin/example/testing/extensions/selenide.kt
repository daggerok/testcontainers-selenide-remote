package example.testing.extensions

import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.Selenide.`$`
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

fun s(webElement: WebElement) = `$`(webElement)

fun s(cssSelector: String) = `$`(cssSelector)

fun s(cssSelector: String, index: Int) = `$`(cssSelector, index)

fun s(seleniumSelector: By) = `$`(seleniumSelector)

fun s(seleniumSelector: By, index: Int) = `$`(seleniumSelector, index)

fun <ELEMENT : MutableCollection<out WebElement>> ss(elements: ELEMENT) = `$$`(elements)

fun ss(cssSelector: String) = `$$`(cssSelector)

fun ss(seleniumSelector: By) = `$$`(seleniumSelector)
