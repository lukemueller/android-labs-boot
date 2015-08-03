package io.pivotal.labsboot.example;

public class Hero {
    private String name;
    private String detailUrl;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDetailUrl() { return detailUrl; }

    public void setDetailUrl(String detailUrl) { this.detailUrl = detailUrl; }
}
