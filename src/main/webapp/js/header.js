function language(locale) {
    Cookies.set("_locale", locale);
    location.href=$.delPara("_locale");
}