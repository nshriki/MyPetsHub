package com.example.mypetshub;

public class Pet {
    private int petId;
    private String name;
    private String imageUrl;

    public Pet(int petId, String name, String imageUrl) {
        this.petId = petId;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public int getPetId() {
        return petId;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "petId=" + petId +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
