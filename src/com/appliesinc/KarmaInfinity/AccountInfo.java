package com.appliesinc.KarmaInfinity;

import java.io.IOException;
import java.net.URL;
import java.util.Random;

import org.apache.commons.io.IOUtils;

public class AccountInfo {

	/**
	 * String to hold Username of account.
	 */
	private String username;
	
	/**
	 * String to hold Password of account.
	 */
	private String password;
	
	/**
	 * String to hold Email of account.
	 */
	private String email;
	
	/**
	 * String to hold Proxy IP Address and Port for account. Formatted:
	 * 192.168.0.1:1234
	 */
	private String proxyIPPort;


	/**
	 * Generate new AccountInfo object with parameters.
	 * @param username Used to login to account and displayed throughout reddit.
	 * @param password Used to login to account.
	 * @param email Used to recover password for the account.
	 * @param ipport Formatted String "192.168.0.1:1234" for the users permanent proxy.
	 */
	public AccountInfo(String username, String password, String email,
			String ipport) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.proxyIPPort = ipport;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AccountInfo [username=" + username + ", password=" + password
				+ ", email=" + email + ", proxyIPPort=" + proxyIPPort + "]";
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the proxyIPPort
	 */
	public String getProxyIPPort() {
		return proxyIPPort;
	}

	/**
	 * @param proxyIPPort the proxyIPPort to set
	 */
	public void setProxyIPPort(String proxyIPPort) {
		this.proxyIPPort = proxyIPPort;
	}
	
}
