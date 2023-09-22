package jp.ac.u_aizu.ta_report_system.model.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jp.ac.u_aizu.ta_report_system.entity.Account;
import jp.ac.u_aizu.ta_report_system.entity.Authority;

public class AccountDetails implements UserDetails {

	private Account account;
	private Authority auth;

	public AccountDetails(Account account, Authority auth) {
		this.account = account;
		this.auth = auth;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
    list.add(new SimpleGrantedAuthority(auth.getName()));
    return list;
	}

  @Override
  public String getPassword() {
    return account.getPassword();
  }

  @Override
  public String getUsername() {
    return account.getUsername();
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
    return !account.getDeleted();
  }
}
