package design.startupInvestment.springboot.security.dto.request;

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
public class StartupRequest {

    @NotEmpty(message = "{startupName_not_empty}")
    private String name;

    private StartupperRequest startupper;

}



