package jp.ac.u_aizu.ta_report_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import jp.ac.u_aizu.ta_report_system.entity.Account;
import jp.ac.u_aizu.ta_report_system.model.form.AccountDetails;
import jp.ac.u_aizu.ta_report_system.repository.AccountRepository;

public class AccountDetailsService implements UserDetailsService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByUsername(username);
		if (account == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new AccountDetails(account, account.getAuthority());
	}

}
