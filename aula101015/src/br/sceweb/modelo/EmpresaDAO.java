package br.sceweb.modelo;

import java.sql.Connection;
import java.sql.SQLException;

import java.sql.PreparedStatement;

public class EmpresaDAO {
	
	public int adiciona(Empresa empresa){
		
		PreparedStatement ps;
		int codigoRetorno = 0;
		
		
		try(Connection conn = new FabricaDeConexoes().getConection()){
			ps = conn.prepareStatement(
					"INSERT INTO EMPRESA (cnpj, nomeDaEmpresa, nomeFantasia, endereco, telefone) VALUES (?,?,?,?,?);" );//SQL que sera executado no banco pelo prepared estatemant
			ps.setString(1, empresa.getCnpj());
			ps.setString(2, empresa.getNomedaEmpresa());
			ps.setString(3,empresa.getNomeFantasia());
			ps.setString(4,empresa.getEndereco());
			ps.setString(5,empresa.getTelefone());
			codigoRetorno = ps.executeUpdate();// codigo de retorno do banco para validação do teste
			
			ps.close();
		
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return  codigoRetorno;

	}

	
	public int excluir (String cnpj){
		PreparedStatement ps;
		int codigoRetorno = 0;
		try(Connection conn = new FabricaDeConexoes().getConection()){
			ps = conn.prepareStatement(
			"delete from empresa where cnpj = ?;");//SQL que sera executado no banco pelo prepared estatemant
			ps.setString(1,cnpj);
			codigoRetorno = ps.executeUpdate();// codigo de retorno do banco para validação do teste
					
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return  codigoRetorno;
		
	}
}
