package ir.serenade.minerva.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name="authority")
	private String authority;


	public Role() {
	}

	public Role(String authority) {
		this.authority = authority;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	@Override
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Role role = (Role) o;

		if (id != role.id) return false;
		return authority != null ? authority.equals(role.authority) : role.authority == null;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (authority != null ? authority.hashCode() : 0);
		return result;
	}
}
