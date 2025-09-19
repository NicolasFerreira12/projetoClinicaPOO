package clinica.model;

public class Local
{
    private String nome;
    private String endereco;
    private String sala;

    public Local(String nome, String endereco, String sala)
    {
        this.nome = nome;
        this.endereco = endereco;
        this.sala = sala;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getEndereco()
    {
        return endereco;
    }

    public void setEndereco(String endereco)
    {
        this.endereco = endereco;
    }

    public String getSala()
    {
        return sala;
    }

    public void setSala(String sala)
    {
        this.sala = sala;
    }

    @Override
    public String toString()
    {
        return nome + " - " + endereco + " (Sala: " + sala + ")";
    }
}
