Here's a more creative and engaging README for your project:

---

# SMS REST API - Java (GET Method)

## 🚀 Welcome to the SMS REST API!

This Java-based API lets you easily send SMS messages via **Send.lk** using the GET method. It's perfect for automating SMS notifications, like sending appointment bookings, reminders, and more! The code also tracks the status of each SMS sent, saving a log to your database for easy reference.

---

## 🌟 Features

- **Send SMS Notifications**: Quickly send SMS to recipients through the Send.lk API.
- **Automated Booking Alerts**: Notify patients or customers about their booking details.
- **Logging**: Keeps track of all sent SMS messages and their status in a MySQL database.
- **Easy Integration**: Simple integration into your existing Java application.

---

## ⚙️ How to Get Started

1. **Clone the Repo**  
   Start by cloning the repository to your local machine.

   ```bash
   git clone https://github.com/lochana2005/sms-rest-api-java.git
   cd sms-rest-api-java
   ```

2. **Configure Your API Keys & Sender ID**  
   Open the `SMS.java` file and replace the following constants with your own credentials from **Send.lk**:
   - `TOKEN` - Your Send.lk API Token
   - `FROM` - Your Sender ID (this is what will appear as the sender for your messages)

   ```java
   private static final String TOKEN = "YOUR-TOKEN-ID";
   private static final String FROM = "SENDER-ID";
   ```

3. **Set Up Your Database**  
   You’ll need a MySQL database to log SMS statuses. Here’s the SQL you’ll need:

   ```sql
   CREATE TABLE `massage_send_status` (
       `id` INT AUTO_INCREMENT PRIMARY KEY,
       `status_id` VARCHAR(255),
       `pations_reg_no` VARCHAR(255),
       `mobile` VARCHAR(255),
       `massage` TEXT,
       `date` DATETIME,
       `send_cat_id` VARCHAR(255)
   );
   ```

4. **Add JDBC Connection**  
   Make sure your project has the **MySQL JDBC Driver** to connect to the database. You can add it via Maven or download it directly.

---

## 💡 How It Works

### 📞 Sending SMS

To send an SMS, call the `sendSMS` method with the necessary parameters: recipient’s mobile number, message, patient registration number, and category of the SMS.

Example:

```java
SMS sms = new SMS();
String mobile = "your-recipient-number";
String message = "This is a test message from your API!";
String patientRegNo = "12345";
String currentDateTime = LocalDate.now().toString();
String sendCatId = "1";  // Adjust category as needed

String response = sms.sendSMS(mobile, message, patientRegNo, currentDateTime, sendCatId);
System.out.println(response);
```

### 🗓 Booking SMS

You can also send custom booking messages by calling `sendAddBooking`:

```java
String mobile = "recipient-phone";
String name = "John Doe";
String bookingNu = "AB123";
String bookingDate = "2024-12-25";
String bookingId = "BKG-456";
String patientRegNo = "12345";

String response = sms.sendAddBooking(mobile, name, bookingNu, bookingDate, bookingId, patientRegNo);
System.out.println(response);
```

---

## 🛠 Logging and Error Handling

- **Success**: If the SMS is successfully sent, the response will let you know with a `"Message sent successfully"` message.
- **Failure**: If the API fails, an error message will be logged.
- **Database Logging**: Every time an SMS is sent, the system logs the status in your MySQL database to keep track of whether it succeeded or failed.

---

## 📦 Requirements

- **Java 8 or later**
- **MySQL Database**
- **Send.lk Account** (API Token and Sender ID)
- **JDBC Driver** for MySQL

---

## 🌍 Contributions

Want to contribute? Great! Feel free to fork the repo, make your changes, and create a pull request. If you find any bugs or have suggestions, feel free to open an issue.

---

## 📝 License

This project is open-source and available under the MIT License.

---

## 🎉 Enjoy coding and sending SMS messages with ease!

---

This README is designed to be a bit more creative and user-friendly. It provides a clear and concise guide to help users set up the project while maintaining a fun and welcoming tone. You can always customize it further to match the vibe of your project!
