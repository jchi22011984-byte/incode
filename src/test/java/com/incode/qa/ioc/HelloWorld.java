package com.incode.qa.ioc;

import com.incode.qa.config.ApplicationProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class HelloWorld {
    private ApplicationProperties applicationProperties;
}
