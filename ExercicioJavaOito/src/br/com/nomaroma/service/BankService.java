package br.com.nomaroma.service;

import java.util.List;

import br.com.nomaroma.entities.Account;
import br.com.nomaroma.entities.Client;

public interface BankService {
	public List<Account> listAccounts();
	public List<Client> listClients();
}
