package com.leadsphere.crm.patterns;

import com.iluwatar.onion.application.usecase.GetPersonUseCase;
import com.iluwatar.onion.application.usecase.SavePersonUseCase;
import com.iluwatar.onion.domain.repository.PersonRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

  @Bean
  public SavePersonUseCase savePersonUseCase(PersonRepository repository) {
    return new SavePersonUseCase(repository);
  }

  @Bean
  public GetPersonUseCase getPersonUseCase(PersonRepository repository) {
    return new GetPersonUseCase(repository);
  }
}
