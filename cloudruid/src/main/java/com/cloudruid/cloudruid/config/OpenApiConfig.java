package com.cloudruid.cloudruid.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Marian",
                        email = "marian.valchinov@gmail.com",
                        url = "https://github.com/MV4Tech/developer-excercise"
                        ),
                description = "Open API for scanning fruits and vegetables to calculate the total bill",
                title = "Simple Till System",
                version = "1.0",
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"
                )
        }

)
public class OpenApiConfig {
}
