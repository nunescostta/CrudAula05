package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import entities.Cliente;
import factories.ConnectionFactory;

public class ClienteRepository {

	public void inserir(Cliente cliente) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection
				.prepareStatement("insert into cliente (id, nome, email, cpf, telefone) values(?,?,?,?,?)");

		statement.setObject(1, cliente.getId());
		statement.setString(2, cliente.getNome());
		statement.setString(3, cliente.getEmail());
		statement.setString(4, cliente.getCpf());
		statement.setString(5, cliente.getTelefone());
		statement.execute();

		connection.close();
	}

	public List<Cliente> obterTodos() throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from cliente order by nome");

		ResultSet resultSet = statement.executeQuery();

		List<Cliente> lista = new ArrayList<Cliente>();

		while (resultSet.next()) {

			Cliente cliente = new Cliente();
			cliente.setId(UUID.fromString(resultSet.getString("id")));
			cliente.setNome(resultSet.getString("nome"));
			cliente.setCpf(resultSet.getString("cpf"));
			cliente.setTelefone(resultSet.getString("telefone"));

			lista.add(cliente);
		}

		connection.close();
		return lista;
	}

	public void editar(Cliente cliente) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection
				.prepareStatement("update cliente set nome=?, email=?, cpf=?, telefone=?");

		statement.setString(1, cliente.getNome());
		statement.setString(2, cliente.getEmail());
		statement.setString(3, cliente.getCpf());
		statement.setString(4, cliente.getTelefone());
		statement.execute();

		connection.close();

	}

	public void excluir(Cliente cliente) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("delete from cliente where id=?");
		statement.setObject(1, cliente.getId());
		statement.execute();

		connection.close();

	}

	public Cliente obterPorId(UUID id) throws Exception {

		Connection connection = ConnectionFactory.getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from cliente where id=?");

		statement.setObject(1, id);
		ResultSet resultSet = statement.executeQuery();

		Cliente cliente = null;

		if (resultSet.next()) {

			cliente = new Cliente();
			cliente.setId(UUID.fromString(resultSet.getString("id")));
			cliente.setNome(resultSet.getString("nome"));
			cliente.setEmail(resultSet.getString("email"));
			cliente.setCpf(resultSet.getString("cpf"));
			cliente.setTelefone(resultSet.getString("telefone"));

		}

		connection.close();
		return cliente;
	}

}
