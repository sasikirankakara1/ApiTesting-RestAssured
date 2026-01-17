# 🚀 REST Assured GET API Automation

> **Automate APIs like a Pro** ⚡  
> Status Code Validation ✅ | Response Body Assertions 🔍 | Data Extraction 📦 | Hamcrest Power 💪

---

## 🧠 What’s Covered
- ✔ Automate GET Requests
- ✔ Assert HTTP Status Codes
- ✔ Validate Response Body
- ✔ Extract Response Data
- ✔ Perform Hamcrest Validations

---

## 🛠 Tech Stack
- **Java**
- **Rest Assured**
- **TestNG / JUnit**
- **Hamcrest Matchers**

---

## 📌 Automate GET Request & Assert Status Code

```java
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class GetRequestTest {

    @Test
    public void Automate_GET_Request_Assert_Status_Code() {

        RestAssured.baseURI = "https://reqres.in/api";

        given()
        .when()
            .get("/users/2")
        .then()
            .assertThat()
            .statusCode(200)
            .log().all();
    }
}
