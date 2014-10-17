package pl.java.scalatech.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Builder;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@Builder
public class User extends PKEntity implements UserDetails{

    /**
     * 
     */
    private static final long serialVersionUID = -2181703844979860927L;

    @NotNull
    @Size(min = 2, max = 30)
    private String login;

    @NotNull
    @Size(min = 2, max = 50)
    private String lastName;

    @NotNull
    @Size(min = 2, max = 50)
    private String firstName;

    @Transient
    private String fullName;

    @NotNull
    @Min(6)
    @Column(nullable = false, length = 20)
    private String password;
    
    private boolean enabled = true;
    
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns = { @JoinColumn(name = "roleId") })
    //@IndexColumn(base = 0, name = "idx", nullable = false)
    @Valid
    private List<Role> roles = new LinkedList<>();

    @Override
    @XmlTransient
    public Collection<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grantedAuthoritiesSet = new HashSet<>(getRoles().size());
        for (Role role : getRoles()) {
            grantedAuthoritiesSet.add(new GrantedAuthorityImpl(role.getName()));
        }
        return grantedAuthoritiesSet;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
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
        return this.enabled;
    }

}
