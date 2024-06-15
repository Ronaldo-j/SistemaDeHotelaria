import java.io.*;
import java.util.ArrayList;

public class Hospede extends Pessoa {
    private String enderecoCompleto;
    private static final String FILE_PATH = "Hospede.txt"; // Caminho do arquivo

    // Construtor
    public Hospede(String cpf, String nome, String email, String endereco) {
        super(cpf, nome, email);
        this.enderecoCompleto = endereco;
    }

    public Hospede() {}

    // Getters e Setters
    public String getEnderecoCompleto() {
        return enderecoCompleto;
    }
    public void setEnderecoCompleto(String enderecoCompleto) {
        this.enderecoCompleto = enderecoCompleto;
    }

    // Método cadastrar que salva em arquivo
    public String cadastrar(Hospede h) throws IOException {
        // Validações
        if (h.getCpf() == null || h.getCpf().trim().isEmpty()) {
            return "Erro: CPF não pode estar vazio.";
        }
        if (h.getNome() == null || h.getNome().trim().isEmpty()) {
            return "Erro: Nome não pode estar vazio.";
        }
        if (h.getEmail() == null || h.getEmail().trim().isEmpty()) {
            return "Erro: Email não pode estar vazio.";
        }
        if (h.getEnderecoCompleto() == null || h.getEnderecoCompleto().trim().isEmpty()) {
            return "Erro: Endereço completo não pode estar vazio.";
        }

        FileWriter fw = new FileWriter(FILE_PATH, true);
        BufferedWriter bw = new BufferedWriter(fw);

        FileReader fr = new FileReader(FILE_PATH);
        BufferedReader br = new BufferedReader(fr);

        String linha = null;
        boolean hospedeExiste = false;

        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(";");
            if (dados[0].equals(h.getCpf())) {
                hospedeExiste = true;
                break;
            }
        }
        if (!hospedeExiste) {
            bw.write(h.getCpf() + ";" + h.getNome() + ";" + h.getEmail() + ";" + h.getEnderecoCompleto());
            bw.newLine();
            bw.close();
            return "Hóspede cadastrado com sucesso.";
        } else {
            bw.close();
            return "Erro: Hóspede com esse CPF já existe.";
        }
    }

    // Método editar que edita em arquivo
    public void editar(Hospede hospedeParaEditar, String novoCpf, String novoNome, String novoEmail, String novoEndereco) throws IOException {
        File inputFile = new File(FILE_PATH);
        
        // Carrega todo o conteúdo do arquivo
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] dados = currentLine.split(";");
                String cpf = dados[0];

                if (cpf.equals(hospedeParaEditar.getCpf())) {
                    lines.add(novoCpf + ";" + novoNome + ";" + novoEmail + ";" + novoEndereco);
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

    // Método consultar que consulta em arquivo usando um objeto Hospede e imprime os dados no console
    public Hospede consultar(String cpfHospede) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            boolean hospedeEncontrado = false;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados[0].equals(cpfHospede)) {
                    System.out.println("Hóspede encontrado:");
                    System.out.println("CPF: " + dados[0]);
                    System.out.println("Nome: " + dados[1]);
                    System.out.println("Email: " + dados[2]);
                    System.out.println("Endereço Completo: " + dados[3]);
                    hospedeEncontrado = true;
                    break;
                }
            }

            if (!hospedeEncontrado) {
                System.out.println("Hóspede não encontrado com o CPF informado.");
            }
        } catch (IOException e) {
            throw new IOException("Erro ao consultar hóspede: " + e.getMessage());
        }
        return null;
    }

    // Método listar que lista todos os hóspedes do arquivo e imprime os dados no console
    public void listar() throws IOException {
        ArrayList<Hospede> hospedes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                Hospede hospede = new Hospede();
                hospede.setCpf(dados[0]);
                hospede.setNome(dados[1]);
                hospede.setEmail(dados[2]);
                hospede.setEnderecoCompleto(dados[3]);
                hospedes.add(hospede);
            }
        } catch (IOException e) {
            throw new IOException("Erro ao listar hóspedes: " + e.getMessage());
        }

        // Imprime os hóspedes no console
        for (Hospede hospede : hospedes) {
            System.out.println("CPF: " + hospede.getCpf());
            System.out.println("Nome: " + hospede.getNome());
            System.out.println("Email: " + hospede.getEmail());
            System.out.println("Endereço Completo: " + hospede.getEnderecoCompleto());
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return getCpf() + ";" + getNome() + ";" + getEmail() + ";" + enderecoCompleto;
    }
}
