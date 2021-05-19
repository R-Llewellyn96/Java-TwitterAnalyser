package Twitter4J.Utils;

public class TextFormatting {

    /**
     * Replace newlines and tabs in text with escaped versions to making printing cleaner
     *
     * @param text	The text of a tweet, sometimes with embedded newlines and tabs
     * @return		The text passed in, but with the newlines and tabs replaced
     */
    public static String cleanText(String text)
    {
        text = text.replace("\n", "\\n");
        text = text.replace("\t", "\\t");
        text = text.replace("\"", "");

        return text;
    }

}
