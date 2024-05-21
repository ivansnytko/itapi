package org.example;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

public class FakerData {

    @Test
    public void fake() {
        Faker faker = new Faker();
        faker.name().firstName();
    }
}
