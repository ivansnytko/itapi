package org.example.chaining;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class Example {

    @Test
    public void firstTest(ITestContext context) {
        context.setAttribute("user_id", 5);
    }

    @Test
    public void secondTest(ITestContext context) {
        System.out.println(context.getAttribute("user_id"));
    }

}
