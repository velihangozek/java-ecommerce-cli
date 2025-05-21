package org.velihangozek.javaecommercecli.model;

public class Category extends BaseModel {

    private String name;

    public Category() {
    }

    public Category(Long id, String name) {
        this.setId(id);
        this.name = name;
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, User createdByUser, User updatedByUser) {
        this.name = name;
        this.setCreatedByUser(createdByUser);
        this.setUpdatedByUser(updatedByUser);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + getId() + '\'' +
                "name='" + name + '\'' +
                '}';
    }
}