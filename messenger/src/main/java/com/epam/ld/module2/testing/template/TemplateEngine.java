package com.epam.ld.module2.testing.template;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Template engine.
 */
public class TemplateEngine {

    private final Pattern pattern = Pattern.compile("#\\{\\w*}");

    /**
     * Generate message string.
     *
     * @param template the template
     * @return the string
     */
    public String generateMessage(Template template, Map<String, String> replacementMap) throws Exception{
        String result = template.getMessage();
        for (Map.Entry<String, String> entry : replacementMap.entrySet()) {
            result = result.replace(entry.getKey(), entry.getValue());
        }

        Matcher matcher = pattern.matcher(result);
        boolean matchFound = matcher.find();
        if(matchFound) {
            throw new Exception("Exception - missed placeholder.");
        }

        return result;
    }
}
