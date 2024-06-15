import java.io.*;
import java.util.ArrayList;

public class Servico {
    private int codigo;
    private String descricao;
    private double valor;
    private static final String FILE_PATH = "Servico.txt"; // Define o caminho do arquivo
    
    public Servico(int codigo, String descricao, double valor){
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Servico() {}

    // Getters e Setters com encapsulamento
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }

    // Método cadastrar que salva em arquivo
    public String cadastrar(Servico s) throws IOException {
        // Validações
        if (s.codigo < 0) {
            return "Erro: Código deve ser positivo.";
        }
        if (s.descricao == null || s.descricao.trim().isEmpty()) {
            return "Erro: Descrição não pode estar vazia.";
        }
        if (s.valor <= 0) {
            return "Erro: Valor deve ser positivo.";
        }

        FileWriter fw = new FileWriter(FILE_PATH, true);
        BufferedWriter bw = new BufferedWriter(fw);

        FileReader fr = new FileReader(FILE_PATH);
        BufferedReader br = new BufferedReader(fr);

        String linha = null;
        boolean servicoExiste = false;

        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(";");
            if (Integer.parseInt(dados[0]) == s.codigo) {
                servicoExiste = true;
                break;
            }
        }
        if (!servicoExiste) {
            bw.write(s.codigo + ";" + s.descricao + ";" + s.valor);
            bw.newLine();
            bw.close();
            return "Serviço cadastrado com sucesso.";
        } else {
            bw.close();
            return "Erro: Serviço com esse código já existe.";
        }
    }

    // Método editar que edita em arquivo
    public void editar(Servico servicoParaEditar, int novoCodigo, String novaDescricao, double novoValor) throws IOException {
        File inputFile = new File(FILE_PATH);
        
        // Carrega todo o conteúdo do arquivo
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] dados = currentLine.split(";");
                int codigo = Integer.parseInt(dados[0]);

                if (codigo == servicoParaEditar.getCodigo()) {
                    lines.add(novoCodigo + ";" + novaDescricao + ";" + novoValor);
                } else {
                    lines.add(currentLine);
                }
            }
        } catch (IOException e) {
            throw new IOException("Erro ao ler o arquivo: " + e.getMessage());
        }
        
        // Reescreve o conteúdo do arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new IOException("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    // Método consultar que consulta em arquivo usando um objeto Servico e imprime os dados no console
    public void consultar(Servico servicoConsultado) throws IOException {
        int codigoConsultado = servicoConsultado.getCodigo();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            boolean servicoEncontrado = false;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (Integer.parseInt(dados[0]) == codigoConsultado) {
                    System.out.println("Serviço encontrado:");
                    System.out.println("Código: " + dados[0]);
                    System.out.println("Descrição: " + dados[1]);
                    System.out.println("Valor: " + dados[2]);
                    servicoEncontrado = true;
                    break;
                }
            }

            if (!servicoEncontrado) {
                System.out.println("");
            }
        } catch (IOException e) {
            throw new IOException("Erro ao consultar serviço: " + e.getMessage());
        }
    }

    // Método listar que lista todos os serviços do arquivo e imprime os dados no console
    public void listar() throws IOException {
        ArrayList<Servico> servicos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                Servico servico = new Servico();
                servico.setCodigo(Integer.parseInt(dados[0]));
                servico.setDescricao(dados[1]);
                servico.setValor(Double.parseDouble(dados[2]));
                servicos.add(servico);
            }
        } catch (IOException e) {
            throw new IOException("Erro ao listar serviços: " + e.getMessage());
        }

        // Imprime os serviços no console
        for (Servico servico : servicos) {
            System.out.println("Código: " + servico.getCodigo());
            System.out.println("Descrição: " + servico.getDescricao());
            System.out.println("Valor: " + servico.getValor());
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return codigo + ";" + descricao + ";" + valor;
    }
}
