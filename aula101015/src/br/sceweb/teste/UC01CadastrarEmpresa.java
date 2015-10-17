package br.sceweb.teste;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaDAO;

public class UC01CadastrarEmpresa {
	
	static EmpresaDAO empresaDAO;
	static Empresa empresa;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		empresaDAO = new EmpresaDAO();
		empresa = new Empresa();
		
		//89.424.232/0001-80
		empresa.setNomedaEmpresa("empresa x");
		empresa.setCnpj("89424232000180");
		empresa.setNomeFantasia("empresa x");
		empresa.setEndereco("rua taquari");
		empresa.setTelefone("2222");
	
	}

	@Test
	public void CT01UC01FBCadastra_com_sucesso() {
		empresaDAO.excluir("89424232000180");
		assertEquals(1, empresaDAO.adiciona(empresa));// primeiro a saida esperada, segundo o metodo que sera executado para aquela saida
		empresaDAO.excluir("89424232000180");
	}

	@Test(expected = RuntimeException.class)//passa se der exception
	public void CT02UC01A2Cadastra_empresa_cnpj_ja_cadastrado() {
		empresaDAO.adiciona(empresa);
		assertEquals(0, empresaDAO.adiciona(empresa));
	}
	
	
	@Test
	public void CT03UC01A3Cadastra_empresa_cnpj_invalido(){
		Empresa empresa2 = new Empresa();

		try{
			empresa2.setCnpj("8942423200018");
			fail("deveria disparar um exception");
		}	
		
		catch(Exception e){
			assertEquals("CNPJ INVALIDO!", e.getMessage());// o retorno esperado, a classe que gera esse retorno
		}
	}
	
	
	
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
}
