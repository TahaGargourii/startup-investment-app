package design.startupInvestment.springboot.security.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * Created on Ağustos, 2020
 *
 * @author taha
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioRequest {

    @NotEmpty(message = "{size_not_empty}")
    private int size ;


}



