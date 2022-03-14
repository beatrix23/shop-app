package ro.ubb.catalog.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Builder
public class ProductDto extends BaseDto{
    private String name;
    private float price;
    private String comment;
}
