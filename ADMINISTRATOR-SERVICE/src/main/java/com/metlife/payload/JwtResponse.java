package com.metlife.payload;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Builder
public class JwtResponse {
    private String jwtToken;
    private  String username;
}
