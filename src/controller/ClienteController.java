package controller;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import entities.Cliente;
import repositories.ClienteRepository;

public class ClienteController {

	public void cadastrarCliente() {

		Scanner scanner = new Scanner(System.in);

		try {

			System.out.println("***\nCADASTRO DE CLIENTES***");

			Cliente cliente = new Cliente();

			cliente.setId(UUID.randomUUID());

			System.out.print("Nome.....:");
			cliente.setNome(scanner.nextLine());

			System.out.print("Email....:");
			cliente.setEmail(scanner.nextLine());

			System.out.print("Cpf......:");
			cliente.setCpf(scanner.nextLine());

			System.out.print("Telefone.:");
			cliente.setTelefone(scanner.nextLine());

			ClienteRepository clienteRepository = new ClienteRepository();
			clienteRepository.inserir(cliente);
			
			System.out.println("Cliente Cadastrado com SUCESSO");

		} catch (Exception e) {
			System.out.println("ERRO NO CADASTRO");
			System.out.println(e.getMessage());

		}
		scanner.close();
	}

	public void consultarCliente() {

		try {

			System.err.println("\nCONSULTA DE CLIENTES\n");

			ClienteRepository clienteRepository = new ClienteRepository();
			List<Cliente> lista = clienteRepository.obterTodos();

			for (Cliente cliente : lista) {

				System.out.println("\nID...........:" + cliente.getId());
				System.out.println("\nNome.........:" + cliente.getNome());
				System.out.println("\nEmail........:" + cliente.getEmail());
				System.out.println("\nCpf..........:" + cliente.getCpf());
				System.out.println("\nTelefone.....:" + cliente.getTelefone());
				System.out.println("\n");
			}

		}

		catch (Exception e) {
			System.out.println("***FALHA AO ENCONTRAR CLIENTE***");
			System.out.println(e.getMessage());
		}

	}

	public void atualizarCliente() {

		Scanner scanner = new Scanner(System.in);

		try {

			System.out.println("***ATUALIZAR CLIENTES***");

			System.out.println("Entre com o ID do Cliente....:");
			UUID id = UUID.fromString(scanner.nextLine());

			ClienteRepository clienteRepository = new ClienteRepository();
			Cliente cliente = clienteRepository.obterPorId(id);

			if (cliente != null) {

				System.out.print("Nome do Cliente......:");
				cliente.setNome(scanner.nextLine());

				System.err.println("Email do Cliente...:");
				cliente.setEmail(scanner.nextLine());

				System.out.print("Cpf do Cliente.......:");
				cliente.setCpf(scanner.nextLine());

				System.out.print("Telefone do Cliente..:");
				cliente.setTelefone(scanner.nextLine());

				clienteRepository.editar(cliente);

				System.out.println("\nCLIENTE ATUALIZADA COM SUCESSO");

			} else {
				System.out.println("CLIENTE NAO ENCONTRADO");
			}

		}

		catch (Exception e) {
			System.out.println("ERRO AO ATUALIZAR CLIENTE");
		}

		scanner.close();

	}

	public void excluirCliente() {

		Scanner scanner = new Scanner(System.in);
		try {

			System.out.println("EXCLUIR CLIENTES");

			System.out.println("Entre com a ID do Cliente...:");
			UUID id = UUID.fromString(scanner.nextLine());

			ClienteRepository clienteRepository = new ClienteRepository();
			Cliente cliente = clienteRepository.obterPorId(id);

			if (cliente != null) {

				System.out.print("Nome do Cliente......:");
				cliente.setNome(scanner.nextLine());

				System.err.println("Email do Cliente...:");
				cliente.setEmail(scanner.nextLine());

				System.out.print("Cpf do Cliente.......:");
				cliente.setCpf(scanner.nextLine());

				System.out.print("Telefone do Cliente..:");
				cliente.setTelefone(scanner.nextLine());

				clienteRepository.excluir(cliente);

				System.out.println("\nCLIENTE EXCLUIDO COM SUCESSO");

			} else {
				System.out.println("CLIENTE NAO ENCONTRADO");
			}

		} catch (Exception e) {
			System.out.println("ERRO AO EXLUIR CLIENTE");
			System.out.println(e.getMessage());
		}

		scanner.close();

	}

}
