//package model;
//
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Scanner;
//import java.util.logging.Level;
//
//public class SMS {
//
//    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//    Date date = new Date();
//    String currentDateTime = dateFormat.format(date);
//    private static final String AUTH = "2478|7WlmsJihUzB7rcpHR9hfQmrFfpefNgpDUrkiFbFy";
//    private static final String API_URL = "https://sms.send.lk/api/v3/sms/send";
//    private static final String SRC = "SendTest"; // The sender_id
//
//    public void sendPartyRegistrationmsg(String mobile, String name, String regi_nu) {
//
//        try {
//
//            String message = String.format("Hello %s, your %s. Thank you for registering with us!", name, regi_nu);
//
//            String jsonPayload = String.format(
//                    "{\"recipient\":\"%s\", \"sender_id\":\"%s\", \"message\":\"%s\"}",
//                    mobile.trim(), SRC.trim(), message.trim()
//            );
//
//            System.out.println("Sending SMS with payload: " + jsonPayload);
//
//            URL url = new URL(API_URL);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("accept", "application/json");
//            conn.setRequestProperty("authorization", "Bearer " + AUTH);
//            conn.setRequestProperty("cache-control", "no-cache");
//            conn.setRequestProperty("content-type", "application/json");
//
//            conn.setDoOutput(true);
//
//            try (OutputStream os = conn.getOutputStream()) {
//                os.write(jsonPayload.getBytes());
//                os.flush();
//            }
//
//            int responseCode = conn.getResponseCode();
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//
//                try (Scanner scanner = new Scanner(conn.getInputStream())) {
//                    String response = scanner.useDelimiter("\\A").next();
//                    System.out.println("SMS sent successfully! Response: " + response);
//
//                    try {
//                        MySQL.execute(
//                                "INSERT INTO `massage_send_status` (`status_id`, `pations_reg_no`,`mobile`, `massage`, `date`, `send_cat_id`) "
//                                        + "VALUES ('1', '" + regi_nu + "','" + mobile + "', '" + message + "','" + currentDateTime + "','1')"
//                        );
//                    } catch (Exception dbException) {
//                        System.err.println("Database error while inserting message status: " + dbException.getMessage());
//                    }
//
//                }
//
//            } else {
//
//                System.out.println("Failed to send SMS. HTTP Response Code: " + responseCode);
//                try (Scanner scanner = new Scanner(conn.getErrorStream())) {
//                    String errorResponse = scanner.useDelimiter("\\A").next();
//                    System.out.println("Error Response: " + errorResponse);
//                }
//            }
//
//            conn.disconnect();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error while sending SMS: " + e.getMessage());
//
//            logs.logger.log(Level.SEVERE, String.valueOf(e));
//            try {
//                String message = null;
//                MySQL.execute(
//                        "INSERT INTO `massage_send_status` (`status_id`, `pations_reg_no`,`mobile` `massage`, `date`, `send_cat_id`) "
//                                + "VALUES ('2', '" + regi_nu + "','" + mobile + "', '" + message + "','" + currentDateTime + "','1')"
//                );
//            } catch (Exception dbException) {
//                System.err.println("Database error while inserting message status: " + dbException.getMessage());
//            }
//        }
//
//    }
//
//    public String sendAddBooking(String mobile, String name, String booking_nu, String booking_date_S, String booking_id, String regi_num) {
//
//        try {
//
//            String message = String.format("Hello %s, your Booking Id Is %s "
//                    + "%s "
//                    + System.getProperty("line.separator")
//                    + " %s "
//                    + " "
//                    + " Thank you for choosing with us!", name, booking_id, booking_date_S, booking_nu);
//
//            String jsonPayload = String.format(
//                    "{\"recipient\":\"%s\", \"sender_id\":\"%s\", \"message\":\"%s\"}",
//                    mobile.trim(), SRC.trim(), message.trim()
//            );
//
//            System.out.println("Sending SMS with payload: " + jsonPayload);
//
//            URL url = new URL(API_URL);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("accept", "application/json");
//            conn.setRequestProperty("authorization", "Bearer " + AUTH);
//            conn.setRequestProperty("cache-control", "no-cache");
//            conn.setRequestProperty("content-type", "application/json");
//
//            conn.setDoOutput(true);
//
//            try (OutputStream os = conn.getOutputStream()) {
//                os.write(jsonPayload.getBytes());
//                os.flush();
//            }
//
//            int responseCode = conn.getResponseCode();
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//
//                try (Scanner scanner = new Scanner(conn.getInputStream())) {
//                    String response = scanner.useDelimiter("\\A").next();
//                    System.out.println("SMS sent successfully! Response: " + response);
//
//                    try {
//                        MySQL.execute("INSERT INTO `massage_send_status` (`status_id`, `pations_reg_no`,`mobile`, `massage`, `date`, `send_cat_id`) "
//                                + "VALUES ('1', '" + regi_num + "','" + mobile + "', '" + message + "','" + currentDateTime + "','2')");
//                    } catch (Exception e) {
//                        System.err.println("Database error while inserting message status: " + e.getMessage());
//                        logs.logger.log(Level.SEVERE, String.valueOf(e));
//                    }
//
//                    return currentDateTime;
//
//                }
//            } else {
//
//                System.out.println("Failed to send SMS. HTTP Response Code: " + responseCode);
//                try (Scanner scanner = new Scanner(conn.getErrorStream())) {
//                    String errorResponse = scanner.useDelimiter("\\A").next();
//                    System.out.println("Error Response: " + errorResponse);
//
//                }
//
//            }
//
//            conn.disconnect();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error while sending SMS: " + e.getMessage());
//
//            try {
//                String message = null;
//                MySQL.execute("INSERT INTO `massage_send_status` (`status_id`, `pations_reg_no`,`mobile` `massage`, `date`, `send_cat_id`) "
//                        + "VALUES ('2', '" + regi_num + "','" + mobile + "', '" + message + "','" + currentDateTime + "','2')");
//            } catch (Exception dbException) {
//                System.err.println("Database error while inserting message status: " + dbException.getMessage());
//                logs.logger.log(Level.SEVERE, String.valueOf(e));
//            }
//
//        }
//        return null;
//
//    }
//
//    public String sendUpdateBooking(String mobile, String name, String booking_nu, String booking_date_S, String booking_id, String patient_reg_no) {
//
//        try {
//
//            String message = String.format("Hello %s , "
//                    + "Your Booking Id : %s "
//                    + " "
//                    + "%s "
//                    + " "
//                    + " %s "
//                    + " "
//                    + " Thank you for choosing with us!", name, booking_id, booking_date_S, booking_nu);
//
//            String jsonPayload = String.format(
//                    "{\"recipient\":\"%s\", \"sender_id\":\"%s\", \"message\":\"%s\"}",
//                    mobile.trim(), SRC.trim(), message.trim()
//            );
//
//            System.out.println("Sending SMS with payload: " + jsonPayload);
//
//            URL url = new URL(API_URL);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("accept", "application/json");
//            conn.setRequestProperty("authorization", "Bearer " + AUTH);
//            conn.setRequestProperty("cache-control", "no-cache");
//            conn.setRequestProperty("content-type", "application/json");
//
//            conn.setDoOutput(true);
//
//            try (OutputStream os = conn.getOutputStream()) {
//                os.write(jsonPayload.getBytes());
//                os.flush();
//            }
//
//            int responseCode = conn.getResponseCode();
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//
//                try (Scanner scanner = new Scanner(conn.getInputStream())) {
//                    String response = scanner.useDelimiter("\\A").next();
//                    System.out.println("SMS sent successfully! Response: " + response);
//
//                    try {
//                        MySQL.execute("INSERT INTO `massage_send_status` (`status_id`, `pations_reg_no`,`mobile`, `massage`, `date`, `send_cat_id`) "
//                                + "VALUES ('1', '" + patient_reg_no + "','" + mobile + "', '" + message + "','" + currentDateTime + "','2')");
//                    } catch (Exception e) {
//                        System.err.println("Database error while inserting message status: " + e.getMessage());
//                        logs.logger.log(Level.SEVERE, String.valueOf(e));
//                    }
//
//                    return currentDateTime;
//
//                }
//            } else {
//
//                System.out.println("Failed to send SMS. HTTP Response Code: " + responseCode);
//                try (Scanner scanner = new Scanner(conn.getErrorStream())) {
//                    String errorResponse = scanner.useDelimiter("\\A").next();
//                    System.out.println("Error Response: " + errorResponse);
//
//                }
//
//            }
//
//            conn.disconnect();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error while sending SMS: " + e.getMessage());
//
//            try {
//                String message = null;
//
//                MySQL.execute("INSERT INTO `massage_send_status` (`status_id`, `pations_reg_no`,`mobile` `massage`, `date`, `send_cat_id`) "
//                        + "VALUES ('2', '" + patient_reg_no + "','" + mobile + "', '" + message + "','" + currentDateTime + "','3')");
//
//            } catch (Exception dbException) {
//                System.err.println("Database error while inserting message status: " + dbException.getMessage());
//                logs.logger.log(Level.SEVERE, String.valueOf(e));
//            }
//
//        }
//        return null;
//
//    }
//
//    public String sendcancelBooking(String mobile, String name, String booking_nu, String booking_id, String booking_date_S, String patient_reg_no) {
//
//        try {
//
//            String message = String.format("Hello %s , "
//                    + "Your Booking Id : %s "
//                    + " "
//                    + "%s "
//                    + " "
//                    + " %s "
//                    + " "
//                    + " Your Booking Is Cancel !"
//                    + "    If you have any questions or need further support, feel free to contact us at:  "
//                    + "     +94 212 220 507   "
//                    + " Thank you for choosing with us!", name, booking_id, booking_date_S, booking_nu);
//
//            String jsonPayload = String.format(
//                    "{\"recipient\":\"%s\", \"sender_id\":\"%s\", \"message\":\"%s\"}",
//                    mobile.trim(), SRC.trim(), message.trim()
//            );
//
//            System.out.println("Sending SMS with payload: " + jsonPayload);
//
//            URL url = new URL(API_URL);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("accept", "application/json");
//            conn.setRequestProperty("authorization", "Bearer " + AUTH);
//            conn.setRequestProperty("cache-control", "no-cache");
//            conn.setRequestProperty("content-type", "application/json");
//
//            conn.setDoOutput(true);
//
//            try (OutputStream os = conn.getOutputStream()) {
//                os.write(jsonPayload.getBytes());
//                os.flush();
//            }
//
//            int responseCode = conn.getResponseCode();
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//
//                try (Scanner scanner = new Scanner(conn.getInputStream())) {
//                    String response = scanner.useDelimiter("\\A").next();
//                    System.out.println("SMS sent successfully! Response: " + response);
//
//                    try {
//                        MySQL.execute("INSERT INTO `massage_send_status` (`status_id`, `pations_reg_no`,`mobile`, `massage`, `date`, `send_cat_id`) "
//                                + "VALUES ('1', '" + patient_reg_no + "','" + mobile + "', '" + message + "','" + currentDateTime + "','2')");
//                    } catch (Exception e) {
//                        System.err.println("Database error while inserting message status: " + e.getMessage());
//                        logs.logger.log(Level.SEVERE, String.valueOf(e));
//                    }
//
//                    return currentDateTime;
//
//                }
//            } else {
//
//                System.out.println("Failed to send SMS. HTTP Response Code: " + responseCode);
//                try (Scanner scanner = new Scanner(conn.getErrorStream())) {
//                    String errorResponse = scanner.useDelimiter("\\A").next();
//                    System.out.println("Error Response: " + errorResponse);
//
//                }
//
//            }
//
//            conn.disconnect();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error while sending SMS: " + e.getMessage());
//
//            try {
//                String message = null;
//
//                MySQL.execute("INSERT INTO `massage_send_status` (`status_id`, `pations_reg_no`,`mobile` `massage`, `date`, `send_cat_id`) "
//                        + "VALUES ('2', '" + patient_reg_no + "','" + mobile + "', '" + message + "','" + currentDateTime + "','3')");
//
//            } catch (Exception dbException) {
//                System.err.println("Database error while inserting message status: " + dbException.getMessage());
//                logs.logger.log(Level.SEVERE, String.valueOf(e));
//            }
//
//        }
//        return null;
//
//    }
//
//}
package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.logging.Level;

public class SMS {

    private static final String TOKEN = "2478|7WlmsJihUzB7rcpHR9hfQmrFfpefNgpDUrkiFbFy";
    private static final String FROM = "SendTest";
    private static final String API_URL = "https://send.lk/sms/send.php";

    public String sendSMS(String mobile, String message, String patientRegNo, String currentDateTime, String sendCatId) {
        HttpURLConnection conn = null;
        BufferedReader in = null;

        try {
            // Encode message for URL
            String encodedMessage = URLEncoder.encode(message, "UTF-8");

            // Construct the URL
            String apiUrl = API_URL + "?token=" + TOKEN
                    + "&to=" + URLEncoder.encode(mobile.trim(), "UTF-8")
                    + "&from=" + URLEncoder.encode(FROM, "UTF-8")
                    + "&message=" + encodedMessage;

            // Initialize HTTP connection
            URL url = new URL(apiUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // Get the response code
            int responseCode = conn.getResponseCode();

            // Read the response
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            System.out.println("Response Code: " + responseCode);
            System.out.println("Response: " + response);

            // Determine status and log to the database
            String statusId = (responseCode == HttpURLConnection.HTTP_OK) ? "1" : "0";
            logMessageStatus(statusId, patientRegNo, mobile, message, currentDateTime, sendCatId);

            return (responseCode == HttpURLConnection.HTTP_OK) ? "Message sent successfully" : "Failed to send message";
        } catch (Exception e) {
            e.printStackTrace();
            logMessageStatus("0", patientRegNo, mobile, message, currentDateTime, sendCatId);
            return "Error while sending SMS: " + e.getMessage();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (Exception e) {
                System.err.println("Error while closing resources: " + e.getMessage());
            }
        }
    }

    private void logMessageStatus(String statusId, String patientRegNo, String mobile, String message, String currentDateTime, String sendCatId) {
        try {
            String sql = String.format(
                    "INSERT INTO `massage_send_status` (`status_id`, `pations_reg_no`, `mobile`, `massage`, `date`, `send_cat_id`) "
                    + "VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",
                    statusId, patientRegNo, mobile, message, currentDateTime, sendCatId
            );
            MySQL.execute(sql);
        } catch (Exception e) {
            System.err.println("Database error while inserting message status: " + e.getMessage());
            logs.logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public String sendAddBooking(String mobile, String name, String bookingNu, String bookingDate, String bookingId, String patientRegNo) {
        String message = String.format(
                "Hello %s,\nYour Booking Id Is %s\n%s.\n%s.\nThank you for choosing with us!️",
                name, bookingId, bookingDate, bookingNu
        );
        return sendSMS(mobile, message, patientRegNo, LocalDate.now().toString(), "2");
    }

    public String sendUpdateBooking(String mobile, String name, String bookingNu, String bookingDate, String bookingId, String patientRegNo) {
        String message = String.format(
                "Hello %s,\nYour Booking Id: %s\nHas been updated to\n%s on %s.\nThank you for choosing with us!",
                name, bookingId, bookingDate, bookingNu
        );
        return sendSMS(mobile, message, patientRegNo, LocalDate.now().toString(), "3");
    }

    public String sendPatientRegisterMsg(String mobile, String name, String patientRegNo) {
        String message = String.format(
                "Dear %s,\n\nWelcome to Surabe Clinic! Your registration has been successfully completed. Below are your details for reference:\n"
                + "Registration Number: %s\n"
                + "If you have any questions, feel free to contact us.\n"
                + "www.surabeclinic.com\n"
                + "Thank you for registering with us!",
                name, patientRegNo
        );
        return sendSMS(mobile, message, patientRegNo, LocalDate.now().toString(), "1");
    }

    public String sendcancelBooking(String mobile, String name, String booking_date_s, String booking_id, String booking_nu, String patientRegNo) {
        String message = String.format(
                "Hello %s,\nYour Booking Id: %s on %s with %s has been canceled.\nThank you for choosing with us!",
                name, booking_id, booking_date_s, booking_nu
        );
        return sendSMS(mobile, message, patientRegNo, LocalDate.now().toString(), "4");
    }
}
