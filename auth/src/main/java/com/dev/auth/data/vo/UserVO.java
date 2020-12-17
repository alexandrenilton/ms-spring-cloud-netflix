package com.dev.auth.data.vo;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserVO implements Serializable {
    private static final long serialVersionUID = -8387882384234L;

    private String userName;
    private String password;
}
