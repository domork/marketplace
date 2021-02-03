package com.domork.demo.entity;public class CompanyExtended {
    private Long id;
    private String name;
    private String website;
    private String description;
    private String basedIn;

    public CompanyExtended() {
    }

    public CompanyExtended(Long id, String name,String website, String description, String basedIn) {
        this.id = id;
        this.name = name;
        this.website=website;
        this.description = description;
        this.basedIn = basedIn;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBasedIn() {
        return basedIn;
    }

    public void setBasedIn(String basedIn) {
        this.basedIn = basedIn;
    }

    @Override
    public String toString() {
        return "CompanyExtended{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", website='" + website + '\'' +
                ", description='" + description + '\'' +
                ", basedIn='" + basedIn + '\'' +
                '}';
    }
}
