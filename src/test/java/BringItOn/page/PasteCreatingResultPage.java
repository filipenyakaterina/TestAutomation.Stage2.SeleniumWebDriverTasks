package BringItOn.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PasteCreatingResultPage extends AbstractPage {
    @FindBy(className = "paste_box_line1")
    private WebElement title;

    @FindBy(className = "bash")
    private WebElement codeText;

    @FindBy(className = "kw2")
    private List<WebElement> bashCommands;

    protected PasteCreatingResultPage(WebDriver driver) {
        super(driver);
        if (!driver.getCurrentUrl().contains(PastebinHomePage.HOMEPAGE_URL)) {
            throw new IllegalStateException("This is not the Pastebin page!");
        }
    }

    protected AbstractPage openPage() {
        throw new RuntimeException("Cannot open this page without creating a new paste");
    }

    public String getTitle() {
        return title.getText();
    }

    public String getCodeText() {
        return codeText.getText();
    }

    public List<FontCssStyle> getBashCommandsCssStyles() {
        List<FontCssStyle> fontCssStyles = new ArrayList<>();

        for (WebElement command : bashCommands) {
            fontCssStyles.add(new FontCssStyle(command.getCssValue(FontCssStyle.COLOR_ATTRIBUTE),
                    command.getCssValue(FontCssStyle.FONT_WEIGHT_ATTRIBUTE)));
        }

        return fontCssStyles;
    }

    public static class FontCssStyle {
        private static final String COLOR_ATTRIBUTE = "color";
        private static final String FONT_WEIGHT_ATTRIBUTE = "font-weight";

        private String color;
        private String weight;

        public FontCssStyle(String color, String weight) {
            this.color = color;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FontCssStyle cssStyle = (FontCssStyle) o;
            return Objects.equals(color, cssStyle.color) &&
                    Objects.equals(weight, cssStyle.weight);
        }

        @Override
        public int hashCode() {
            return Objects.hash(color, weight);
        }
    }
}
