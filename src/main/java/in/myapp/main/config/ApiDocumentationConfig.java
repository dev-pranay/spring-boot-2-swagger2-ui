package in.myapp.main.config;

import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;

/**
 * Configuration for Swagger 2 documentation.
 * 
 * @author PranaySK
 */

@SwaggerDefinition(info = @Info(description = "API for Swagger2 UI demo", version = "1.0", title = "Swagger 2 UI demo", 
		contact = @Contact(name = "Pranay", email = "pranaysk12@gmail.com", url = ""), 
		license = @License(name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0")), 
	consumes = {"application/json", "application/xml" },
	produces = { "application/json", "application/xml" },
	schemes = {SwaggerDefinition.Scheme.HTTP,SwaggerDefinition.Scheme.HTTPS })
public interface ApiDocumentationConfig {

}
