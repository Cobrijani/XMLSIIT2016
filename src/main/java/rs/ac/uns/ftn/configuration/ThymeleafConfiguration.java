package rs.ac.uns.ftn.configuration;

import org.apache.commons.lang.CharEncoding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * Created by SBratic on 1/18/2017.
 */
@Configuration
public class ThymeleafConfiguration {

  @Bean
  @Description("Thymeleaf template resolver serving documents")
  public ClassLoaderTemplateResolver documentTemplateResolver() {
    ClassLoaderTemplateResolver emailTemplateResolver = new ClassLoaderTemplateResolver();
    emailTemplateResolver.setPrefix("documents/");
    emailTemplateResolver.setSuffix(".html");
    emailTemplateResolver.setTemplateMode("HTML5");
    emailTemplateResolver.setCharacterEncoding(CharEncoding.UTF_8);
    emailTemplateResolver.setOrder(1);
    return emailTemplateResolver;
  }
}
