package com.gugawag.pdist.ejbs;

import com.gugawag.pdist.model.Mensagem;
import com.gugawag.pdist.model.Usuario;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import java.util.List;

@Stateless(name = "usuarioService")
@Remote
public class UsuarioService {

    @EJB
    private UsuarioDAO usuarioDao;

    @EJB
    private MensagemDAO mensagemDao;

    public List<Usuario> listar() {
        return usuarioDao.listar();
    }

    public List<Mensagem> listarMensagens(){
        return mensagemDao.listar();
    }

    public void inserir(long id, String nome, String msg) {
        Usuario novoUsuario = new Usuario(id, nome);
        usuarioDao.inserir(novoUsuario);

        Mensagem mensagem = new Mensagem(id, msg);
        mensagemDao.inserir(mensagem);
        if (id==3L) {
            throw new RuntimeException("Menor de idade não permitido!");
        }
        if (id == 4L) {
            novoUsuario.setNome(nome + " alterado");
        }
        if (hasPalavrao(msg)){
            throw new RuntimeException("Mensagem contém palavrão!");
        }
    }

    boolean hasPalavrao(String mensagem){
        System.out.println("Mensagem que chegou para ser verificada se há palavrão: " + mensagem);
        String[] palavras = mensagem.split(" ");
        for (String palavra : palavras){
            System.out.println(palavra);
            System.out.println(palavra.length());
            // Verifica se existe uma palavra muito grande...
            if (palavra.length() > 10){
                return true;
            }
        }

        return false;
    }
}
