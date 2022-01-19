package com.coderhouse.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Message {
    private Long id;
    private String description;

}
