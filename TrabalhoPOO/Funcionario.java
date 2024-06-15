import java.io.*;
import java.util.ArrayList;

public class Funcionario extends Pessoa {
    private String setor;
    private static final String FILE_PATH = "Funcionario.txt"; // Define o caminho do arquivo

    // Construtor
    public Funcionario(String cpf, String nome, String email, String setor) {
        super(cpf, nome, email);
        this.setor = setor;
    }

    public Funcionario() {}

    // Getters e Setters
    public String getSetor() {
        return setor;
    }
    public void setSetor(String setor) {
        this.setor = setor;
    }

    // Método cadastrar que salva em arquivo
    public String cadastrar(Funcionario f) throws IOException {
        // Validações
        if (f.getCpf() == null || f.getCpf().trim().isEmpty()) {
            return "Erro: CPF não pode estar vazio.";
        }
        if (f.getNome() == null || f.getNome().trim().isEmpty()) {
            return "Erro: Nome não pode estar vazio.";
        }
        if (f.getEmail() == null || f.getEmail().trim().isEmpty()) {
            return "Erro: Email não pode estar vazio.";
        }
        if (f.getSetor() == null || f.getSetor().trim().isEmpty()) {
            return "Erro: Setor não pode estar vazio.";
        }

        FileWriter fw = new FileWriter(FILE_PATH, true);
        BufferedWriter bw = new BufferedWriter(fw);

        FileReader fr = new FileReader(FILE_PATH);
        BufferedReader br = new BufferedReader(fr);

        String linha = null;
        boolean funcionarioExiste = false;

        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(";");
            if (dados[0].equals(f.getCpf())) {
                funcionarioExiste = true;
                break;
            }
        }
        if (!funcionarioExiste) {
            bw.write(f.getCpf() + ";" + f.getNome() + ";" + f.getEmail() + ";" + f.getSetor());
            bw.newLine();
            bw.close();
            return "Funcionário cadastrado com sucesso.";
        } else {
            bw.close();
            return "Erro: Funcionário com esse CPF já existe.";
        }
    }

    // Método editar que edita em arquivo
    public void editar(Funcionario funcionarioParaEditar, String novoCpf, String novoNome, String novoEmail, String novoSetor) throws IOException {
        File inputFile = new File(FILE_PATH);
        
        // Carrega todo o conteúdo do arquivo
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] dados = currentLine.split(";");
                String cpf = dados[0];

                if (cpf.equals(funcionarioParaEditar.getCpf())) {
                    lines.add(novoCpf + ";" + novoNome + ";" + novoEmail + ";" + novoSetor);
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

    // Método consultar que consulta em arquivo usando um objeto Funcionario e imprime os dados no console
    public Funcionario consultar(String cpfFuncionario) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados[0].equals(cpfFuncionario)) {
                    Funcionario funcionario = new Funcionario(dados[0], dados[1], dados[2], dados[3]);
                    System.out.println("CPF: " + funcionario.getCpf());
                    System.out.println("Nome: " + funcionario.getNome());
                    System.out.println("Email: " + funcionario.getEmail());
                    System.out.println("Setor: " + funcionario.getSetor());
                    return funcionario;
                }
            }
        } catch (IOException e) {
            throw new IOException("Erro ao consultar funcionário: " + e.getMessage());
        }
        System.out.println("Funcionário com CPF " + cpfFuncionario + " não encontrado.");
        return null; // Retorna null se o funcionário não for encontrado
    }


    // Método listar que lista todos os funcionários do arquivo e imprime os dados no console
    public void listar() throws IOException {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                Funcionario funcionario = new Funcionario();
                funcionario.setCpf(dados[0]);
                funcionario.setNome(dados[1]);
                funcionario.setEmail(dados[2]);
                funcionario.setSetor(dados[3]);
                funcionarios.add(funcionario);
            }
        } catch (IOException e) {
            throw new IOException("Erro ao listar funcionários: " + e.getMessage());
        }

        // Imprime os funcionários no console
        for (Funcionario funcionario : funcionarios) {
            System.out.println("CPF: " + funcionario.getCpf());
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Email: " + funcionario.getEmail());
            System.out.println("Setor: " + funcionario.getSetor());
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return getCpf() + ";" + getNome() + ";" + getEmail() + ";" + setor;
    }
}
