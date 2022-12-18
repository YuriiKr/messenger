package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.MailServer;

/**
 * The type Template engine.
 */
public class TemplateEngine {
    private MailServer mailServer = new MailServer();

    /**
     * Generate message string.
     *
     * @param template the template
     * @param client   the client
     * @return the string
     */
    public String generateMessage(Template template, Client client) {
        try {
            mailServer.send(client.getAddresses(), template.getMessage());
            return "Message successfully sent.";
        }
        catch (Exception e) {
            return "Message wasn't sent. Error: " + e.getMessage();
        }
    }
}
