package com.crisper.server.googleSearch.model;

public class GoogleSearchRequest {

    String textToSearch;

    public static GoogleSearchRequest getObject() {
        return new GoogleSearchRequest();
    }

    public String getTextToSearch() {
        return textToSearch;
    }

    public void setTextToSearch(String textToSearch) {
        this.textToSearch = textToSearch;
    }
}
