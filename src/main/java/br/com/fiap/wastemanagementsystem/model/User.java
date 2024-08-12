package br.com.fiap.wastemanagementsystem.model;

import br.com.fiap.wastemanagementsystem.dto.user.UserDtoAdd;
import br.com.fiap.wastemanagementsystem.exception.NotAllowedActionException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tbl_users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqc_user_id")
    @SequenceGenerator(name = "sqc_user_id", sequenceName = "sqc_user_id", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Getter
    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;


    public User (UserDtoAdd dto) {
        this.name = dto.name();
        this.email = dto.email();
        this.password = dto.password();
        this.userRole = dto.userRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return switch (this.userRole) {
            case ADMIN -> List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER"),
                    new SimpleGrantedAuthority("ROLE_SYSTEM"),
                    new SimpleGrantedAuthority("ROLE_COLLECTOR")
            );
            case COLLECTOR -> List.of(
                    new SimpleGrantedAuthority("ROLE_COLLECTOR")
            );
            case SYSTEM -> List.of(
                    new SimpleGrantedAuthority("ROLE_SYSTEM")
            );
            case USER -> List.of(
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        };
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setToEncryptedPassword(String encryptedPassword) {
        this.password = encryptedPassword;
    }

    public void updateRole(UserRole userRole) {
        verifyIfUserIsATrashCan();
        this.userRole = userRole;
    }

    private void verifyIfUserIsATrashCan() {
        if (this.userRole == UserRole.SYSTEM) {
            throw new NotAllowedActionException("Trash cans user role can not be changed!");
        }
    }

}
