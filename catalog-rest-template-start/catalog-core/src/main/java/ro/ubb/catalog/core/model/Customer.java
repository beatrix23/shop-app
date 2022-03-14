package ro.ubb.catalog.core.model;


import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Builder
public class Customer extends BaseEntity<Integer> {
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;
}
