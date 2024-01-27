package streampowered.optionnames;

public enum LanguageNames {
    RUSSIAN("Русский", "ru"),
    ENGLISH("English", "en");
    
    private final String languageName;
    private final String languageAbbreviation;
    
    LanguageNames(String languageName, String languageAbbreviation) {
        this.languageName = languageName;
        this.languageAbbreviation = languageAbbreviation;
    }
    
    public String getLanguageName() {
        return languageName;
    }
    
    public String getLanguageAbbreviation() {
        return languageAbbreviation;
    }
}
