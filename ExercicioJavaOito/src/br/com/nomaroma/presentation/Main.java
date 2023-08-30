package br.com.nomaroma.presentation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import br.com.nomaroma.entities.Account;
import br.com.nomaroma.entities.AccountEnum;
import br.com.nomaroma.entities.Client;
import br.com.nomaroma.service.BankService;
import br.com.nomaroma.service.ServiceFactory;

/**
 * OBSERVAçõES: 
 * NãO é permitido o uso de nenhuma estrutura de repetição (for, while, do-while).
 * Tente, ao máximo, evitar o uso das estruturas if, else if, else e switch-case
 * 
 * @author VictorLira
 *
 */
public class Main {
	
	private static BankService service = ServiceFactory.getService();
	
	public static void main(String[] args) {
		//1:
		//imprimirNomesClientes();
		
		//2:
		//imprimirMediaSaldos();
		
		//3:
		//imprimirPaisClienteMaisRico();
		
		//4:
		//imprimirSaldoMedio(6);
		
		//5:
		//imprimirClientesComPoupanca();
		
		//6:
        //System.out.println(getEstadoClientes(25));
		
		//7:
        //System.out.println(getNumerosContas("Brazil"));
		
		//8:
	   	//System.out.println(getMaiorSaldo("client39@bank.com"));
		
		//9:
		//sacar(190,570,200);
		
		//10:
		//depositar("Brazil",200);
		
		//11:
		/*transferir(198, 576, 594, 200);*/
		
		//13:
		/*System.out.println(getSomaContasEstado("State 6"));*/
		
		//14:
		/*System.out.println(getEmailsClientesContasConjuntas());*/
		
		//15:
		/*System.out.println(isPrimo(3));*/
		
		//16:
		/*System.out.println(getFatorial(7));*/
		
	}
	
	/**
	 * 1. Imprima na tela o nome e e-mail de todos os clientes (sem repeti��o), usando o seguinte formato:
	 * Victor Lira - vl@cin.ufpe.br
	 */
	public static void imprimirNomesClientes() {
		service
			.listClients()
			.stream()
			.distinct()
			.forEach(c-> System.out.println(c.getName()  +" - "+ c.getEmail()));
	}
	
	/**
	 * 2. Imprima na tela o nome do cliente e a m�dia do saldo de suas contas, ex:
	 * Victor Lira - 352
	 */
	public static void imprimirMediaSaldos() {
		service 
			.listClients()
			.stream()
			.distinct()
			.forEach(c -> System.out.println(c.getName()+" - "+c.getAccounts()
					.stream()
					.mapToDouble(s -> s.getBalance())
					.average()
					.orElse(0)))
			;
	}
	
	/**
	 * 3. Considerando que s� existem os pa�ses "Brazil" e "United States", 
	 * imprima na tela qual deles possui o cliente mais rico, ou seja,
	 * com o maior saldo somando todas as suas contas.
	 */
	public static void imprimirPaisClienteMaisRico() {
		List<Client> clientes = service.listClients();

		double saldoBR = clientes.stream()
				.filter(client -> "Brazil".equals(client.getAddress().getCountry()))
				.mapToDouble(client -> client.getAccounts()
						.stream()
						.mapToDouble(account -> account.getBalance())
						.sum())
				.sum();
		double saldoEUA = clientes.stream()
				.filter(client -> "United States".equals(client.getAddress().getCountry()))
				.mapToDouble(client -> client.getAccounts()
						.stream()
						.mapToDouble(account -> account.getBalance())
						.sum())
				.sum();

		if (saldoBR > saldoEUA) {
			System.out.println("Brazil");
		}else {
			System.out.println("United States");
		}
	}
	
	/**
	 * 4. Imprime na tela o saldo m�dio das contas da ag�ncia
	 * @param agency
	 */
	public static void imprimirSaldoMedio(int agency) {
		service
			.listClients()
			.stream()
			.distinct()
			.forEach(c -> System.out.println("Saldo medio das contas da agencia: "+c.getAccounts() 
					.stream()
					.mapToDouble(s -> s.getBalance())
					.average()
					.orElse(0)));
		
	}

	/**
	 * 5. Imprime na tela o nome de todos os clientes que possuem conta poupan�a (tipo SAVING)
	 */
	public static void imprimirClientesComPoupanca() {
		service
			.listClients()
			.stream() 
			.filter(c -> c.getAccounts()
					.stream()
					.anyMatch(conta -> conta.getType() == AccountEnum.SAVING))
			.forEach(c -> System.out.println("Nome: "+c.getName()))
			;
	}
	
	/**
	 * 6.
	 * @param agency
	 * @return Retorna uma lista de Strings com o "estado" de todos os clientes da ag�ncia
	 */
	public static List<String> getEstadoClientes(int agency) {
		return service 
			.listAccounts()
			.stream()
			.filter(a -> a.getAgency()==agency)
			.map(a -> a.getClient())
			.map(c -> c.getAddress().getState())
			.collect(Collectors.toList());
		
		//throw new UnsupportedOperationException();
	}
	
	/**
	 * 7.
	 * @param country
	 * @return Retorna uma lista de inteiros com os n�meros das contas daquele pa�s
	 */
	public static List<Integer> getNumerosContas(String country) {
		return service 
			.listClients()
			.stream()
			.filter(c -> c.getAddress().getCountry().equals(country))
			.flatMap(a -> a.getAccounts()
					.stream()
					.map(s -> s.getNumber()))
			.collect(Collectors.toList());
		
		//throw new UnsupportedOperationException();
	}
	
	/**
	 * 8.
	 * Retorna o somat�rio dos saldos das contas do cliente em quest�o 
	 * @param clientEmail
	 * @return
	 */
	public static double getMaiorSaldo(String clientEmail) {
		return service 
		.listClients()
		.stream()
		.filter(c -> c.getEmail().equals(clientEmail))
		.flatMap(cliente -> cliente.getAccounts()
				.stream())
				.mapToDouble(conta -> conta.getBalance())
				.sum();
		

		//throw new UnsupportedOperationException();
	}
	
	/**
	 * 9.
	 * Realiza uma opera��o de saque na conta de acordo com os par�metros recebidos
	 * @param agency
	 * @param number
	 * @param value
	 */
	public static void sacar(int agency, int number, double value) {
		List<Account> contas = service.listAccounts();
		contas.stream()
		.filter(conta -> conta.getAgency()==agency && conta.getNumber()==number)
		.forEach(conta -> {
			double saldoAtual = conta.getBalance();
			conta.setBalance(saldoAtual-value);
			System.out.println("Foi sacado R$"+value+"\nAgencia:"+conta.getAgency()+" - Numero:"+number);
		});
		;
		
		//Segunda Opção de resolução
		
//		List<Account> contas = service.listAccounts();
//			Optional<Account> contaOptional = contas.stream()
//			.filter(conta -> conta.getAgency()==agency && conta.getNumber()==number)
//			.findFirst();
//			
//			if (contaOptional.isPresent()) {
//				Account conta = contaOptional.get();
//				double saldoAtual = conta.getBalance();
//				
//				if (saldoAtual >= value) {
//					double novoSaldo = saldoAtual - value;
//					conta.setBalance(novoSaldo);
//					System.out.println("Novo saldo: R$"+novoSaldo);
//				}else {
//					System.out.println("Saldo insuficiente pra saque!");
//				}
//			}else {
//				System.out.println("Conta não encontrada!");
//			}
	}
	/**
	 * 10. Realiza um deposito para todos os clientes do pa�s em quest�o	
	 * @param country
	 * @param value
	 */
	public static void depositar(String country, double value) {
		List<Account> contas = service.listAccounts();

	    contas.stream()
	            .filter(conta -> conta.getClient().getAddress().getCountry().equals(country))
	            .forEach(conta -> {
	            	double saldoAnterior = conta.getBalance();
	            	conta.setBalance(saldoAnterior+value);
	            	System.out.println("Valor "+value+" depositado na conta "+conta.getNumber()+ " do pais "+country);
	            });
	}
	
	/**
	 * 11. Realiza uma transfer�ncia entre duas contas de uma ag�ncia.
	 * @param agency - ag�ncia das duas contas
	 * @param numberSource - conta a ser debitado o dinheiro
	 * @param numberTarget - conta a ser creditado o dinheiro
	 * @param value - valor da transfer�ncia
	 */
	public static void transferir(int agency, int numberSource, int numberTarget, double value) {
	}
	
	/**
	 * 12.
	 * @param clients
	 * @return Retorna uma lista com todas as contas conjuntas (JOINT) dos clientes
	 */
	public static List<Account> getContasConjuntas(List<Client> clients) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * 13.
	 * @param state
	 * @return Retorna uma lista com o somat�rio dos saldos de todas as contas do estado 
	 */
	public static double getSomaContasEstado(String state) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * 14.
	 * @return Retorna um array com os e-mails de todos os clientes que possuem contas conjuntas
	 */
	public static String[] getEmailsClientesContasConjuntas() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * 15.
	 * @param number
	 * @return Retorna se o n�mero � primo ou n�o
	 */
	public static boolean isPrimo(int number) {
		throw new UnsupportedOperationException();
	}
	
	
	/**
	 * 16.
	 * @param number
	 * @return Retorna o fatorial do n�mero
	 */
	public static int getFatorial(int number) {
		throw new UnsupportedOperationException();
	}
}
