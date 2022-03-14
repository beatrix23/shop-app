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
public class Category extends BaseEntity<Integer>{
    private String name;
    private String description;
}
