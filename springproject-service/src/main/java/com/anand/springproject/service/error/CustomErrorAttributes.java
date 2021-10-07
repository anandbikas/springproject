package com.anand.springproject.service.error;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @Value("${build.version:}")
    private String serviceVersion;

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions errorAttributeOptions) {

        Map<String, Object> errorAttributes =
                super.getErrorAttributes(webRequest, errorAttributeOptions);

        // Format and update timestamp
        Object timestamp = errorAttributes.get("timestamp");
        errorAttributes.put("timestamp",  dateFormat.format(timestamp == null  ? new Date(): (Date)timestamp));

        // Insert new key version
        errorAttributes.put("version", serviceVersion);

        return errorAttributes;
    }
}