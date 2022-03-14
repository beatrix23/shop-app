package ro.ubb.catalog.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Builder
public class CustomerDto extends BaseDto {
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;
}
