package com.example.mypetshub;

public class Pet2 {
    private int petId;
    private int userId;
    private String name;
    private String imageUrl;

    public Pet2(int petId, int userId, String name, String imageUrl) {
        this.petId = petId;
        this.userId = userId;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public int getPetId() {
        return petId;
    }

    public int getUserId() {
        return userId;
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
                "pet_id=" + petId +
                ", user_id=" + userId +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
