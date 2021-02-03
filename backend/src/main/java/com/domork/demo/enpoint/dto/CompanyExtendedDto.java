package com.domork.demo.enpoint.dto;

import java.util.Objects;

public class CompanyExtendedDto {
    private Long id;
    private String name;
    private String website;
    private String description;
    private String basedIn;

    public CompanyExtendedDto() {
    }

    public CompanyExtendedDto(Long id, String name, String website,String description, String basedIn) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyExtendedDto that = (CompanyExtendedDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(basedIn, that.basedIn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, basedIn);
    }

    @Override
    public String toString() {
        return "CompanyExtendedDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", website='" + website + '\'' +
                ", description='" + description + '\'' +
                ", basedIn='" + basedIn + '\'' +
                '}';
    }
}
