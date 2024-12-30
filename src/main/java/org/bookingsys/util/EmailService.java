package org.bookingsys.util;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;

public class EmailService {
    private static final EmailService instance = new EmailService();

    private EmailService() {}

    public static EmailService getInstance() {
        return instance;
    }

    public void sendConfirmationEmail(String clientAddress, String customerName, String room, String checkinDate, String checkoutDate, double totalPayment) {

        // SMTP Server data
        final String smtpHost = "smtp.gmail.com";
        final String smtpPort = "587";
        final String username = "redicj55@gmail.com"; // Replace with your email
        final String password = "irro izbo oopn fdhh"; // Replace with your app-specific password

        // Setting up email properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a new email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("redicj55@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(clientAddress)
            );
            message.setSubject("Booking Confirmation");

            // HTML content for the email body
            String htmlContent = """
                <!DOCTYPE html>
                <html>
                <head>
                  <style>
                    body { font-family: Arial, sans-serif; line-height: 1.6; margin: 0; padding: 0; background-color: #f4f4f4; }
                    .email-container { max-width: 600px; margin: 20px auto; padding: 20px; background: #ffffff; border-radius: 10px; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); }
                    .header { background-color: #007bff; color: white; padding: 10px; text-align: center; border-radius: 10px 10px 0 0; }
                    .content { padding: 20px; }
                    .content h1 { color: #333333; }
                    .footer { text-align: center; margin-top: 20px; font-size: 0.9em; color: #777777; }
                    .button { display: inline-block; margin: 20px 0; padding: 10px 20px; background-color: #007bff; color: #ffffff; text-decoration: none; border-radius: 5px; }
                  </style>
                </head>
                <body>
                  <div class="email-container">
                    <div class="header">
                      <h2>Thank You for Your Booking!</h2>
                    </div>
                    <div class="content">
                      <h1>Hello, %s!</h1>
                      <p>Your booking has been successfully confirmed. Below are the details:</p>
                      <ul>
                        <li><strong>Room:</strong> %s</li>
                        <li><strong>Check-in:</strong> %s</li>
                        <li><strong>Check-out:</strong> %s</li>
                        <li><strong>Total Payment:</strong> $%.2f</li>
                      </ul>
                    </div>
                    <div class="footer">
                      <p>If you have any questions, feel free to contact us at redicj55@gmail.com</p>
                    </div>
                  </div>
                </body>
                </html>
            """.formatted(customerName, room, checkinDate, checkoutDate, totalPayment);

            message.setContent(htmlContent, "text/html");

            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
