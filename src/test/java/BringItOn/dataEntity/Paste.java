package BringItOn.dataEntity;

public class Paste {
    private String Code;
    private String SyntaxHighlighting;
    private String PasteExpiration;
    private String Title;

    public Paste(String code, String syntaxHighlighting, String pasteExpiration, String title) {
        Code = code;
        SyntaxHighlighting = syntaxHighlighting;
        PasteExpiration = pasteExpiration;
        Title = title;
    }

    public String getCode() {
        return Code;
    }

    public String getSyntaxHighlighting() {
        return SyntaxHighlighting;
    }

    public String getPasteExpiration() {
        return PasteExpiration;
    }

    public String getTitle() {
        return Title;
    }
}
