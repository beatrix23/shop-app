package ro.ubb.catalog.web.dto;

import lombok.*;

import java.io.Serializable;

/**
 * Created by beatrix.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BaseDto implements Serializable {
    private Integer id;
}
