package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.logging.Level;

public class SMS {

    private static final String TOKEN = "YOUR-TOKEN-ID";
    private static final String FROM = "SENDER-ID";
    private static final String API_URL = "https://send.lk/sms/send.php";

    public String sendSMS(String mobile, String message, String patientRegNo, String currentDateTime, String sendCatId) {
        HttpURLConnection conn = null;
        BufferedReader in = null;

        try {
       
            String encodedMessage = URLEncoder.encode(message, "UTF-8");

     
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
            String statusId = (responseCode == HttpURLConnection.HTTP_OK) ? "1" : "2";
            logMessageStatus(statusId, patientRegNo, mobile, message, currentDateTime, sendCatId);

            return (responseCode == HttpURLConnection.HTTP_OK) ? "Message sent successfully" : "Failed to send message";
        } catch (Exception e) {
            e.printStackTrace();
            logMessageStatus("2", patientRegNo, mobile, message, currentDateTime, sendCatId);
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


}
