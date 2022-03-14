package ro.ubb.catalog.web.dto;

import lombok.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Dtos<T> {
    private Set<T> dtos;
}
