package Models;

import java.util.UUID;

public class Pessoa {

    public Pessoa(String nome, String documento) {
        this.nome = nome;
        this.documento = documento;
        this.id = UUID.randomUUID();
    }
    
    public Pessoa(UUID id, String nome, String documento){
        this.id =id;
        this.nome = nome;
        this.documento = documento;
    }
    
    private String nome;
    private UUID id;
    private String documento;

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
            
    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
  
    public boolean validaDocumento(String documento){
        return true;
    }
    
}
