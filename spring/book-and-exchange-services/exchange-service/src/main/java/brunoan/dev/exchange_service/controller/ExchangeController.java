package brunoan.dev.exchange_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import brunoan.dev.exchange_service.environment.InstanceInformationService;
import brunoan.dev.exchange_service.model.Exchange;

import java.math.BigDecimal;

@RestController
@RequestMapping("exchange-service")
public class ExchangeController {

  private final InstanceInformationService instanceInformationService;

  ExchangeController(InstanceInformationService instanceInformationService) {
    this.instanceInformationService = instanceInformationService;
  }

  @GetMapping(value = "/{amount}/{from}/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Exchange Exchange(
      @PathVariable("amount") BigDecimal amount,
      @PathVariable("from") String from,
      @PathVariable("to") String to) {
    return new Exchange(
        1L,
        from,
        to,
        "PORT " + instanceInformationService.retrievePort(),
        BigDecimal.ONE,
        BigDecimal.ONE);
  }
}
