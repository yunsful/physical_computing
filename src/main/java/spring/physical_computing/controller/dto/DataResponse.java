package spring.physical_computing.controller.dto;

import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class DataResponse {
    
    private Boolean controlWindowOpen;
    private Boolean controlWindowClosed;
    private Boolean temperatureToggling;
    private Boolean humidityToggling;
    private Boolean soundToggling;
    
}
