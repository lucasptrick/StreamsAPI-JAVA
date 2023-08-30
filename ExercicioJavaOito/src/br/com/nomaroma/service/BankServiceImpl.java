package br.com.nomaroma.service;

import java.util.List;

import br.com.nomaroma.entities.Account;
import br.com.nomaroma.entities.Client;
import br.com.nomaroma.helper.LoadEntities;

public class BankServiceImpl implements BankService {

	protected BankServiceImpl() { }
	
	@Override
	public List<Account> listAccounts() {
		return LoadEntities.ACCOUNTS;
	}

	@Override
	public List<Client> listClients() {
		return LoadEntities.CLIENTS;
	}
	
}
