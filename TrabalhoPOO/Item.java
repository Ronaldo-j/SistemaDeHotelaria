import java.io.*;
import java.util.ArrayList;

public class Item {
    private int codigo;
    private double valor;
    private String descricao;
    private static final String FILE_PATH = "Item.txt"; // Define o caminho do arquivo

    public Item(int codigo, String descricao, double valor) {
        this.codigo = codigo;
        this.valor = valor;
        this.descricao = descricao;
    }

    public Item() {}

    // Getters e Setters
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
    public String cadastrar(Item i) throws IOException {
        // Validações
        if (i.codigo < 0) {
            return "Erro: Código deve ser positivo.";
        }
        if (i.descricao == null || i.descricao.trim().isEmpty()) {
            return "Erro: Descrição não pode estar vazia.";
        }
        if (i.valor <= 0) {
            return "Erro: Valor deve ser positivo.";
        }

        FileWriter fw = new FileWriter(FILE_PATH, true);
        BufferedWriter bw = new BufferedWriter(fw);

        FileReader fr = new FileReader(FILE_PATH);
        BufferedReader br = new BufferedReader(fr);

        String linha = null;
        boolean itemExiste = false;

        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(";");
            if (Integer.parseInt(dados[0]) == i.codigo) {
                itemExiste = true;
                break;
            }
        }
        if (!itemExiste) {
            bw.write(i.codigo + ";" + i.descricao + ";" + i.valor);
            bw.newLine();
            bw.close();
            return "Item cadastrado com sucesso.";
        } else {
            bw.close();
            return "Erro: Item com esse código já existe.";
        }
    }

    // Método editar que edita em arquivo
    public void editar(Item itemParaEditar, int novoCodigoItem, String novaDescricaoItem, double novoValorItem) throws IOException {
        File inputFile = new File(FILE_PATH);
        
        // Carrega todo o conteúdo do arquivo
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] dados = currentLine.split(";");
                int codigo = Integer.parseInt(dados[0]);

                if (codigo == itemParaEditar.getCodigo()) {
                    lines.add(novoCodigoItem + ";" + novaDescricaoItem + ";" + novoValorItem);
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

    // Método consultar que consulta em arquivo usando um objeto Item e imprime os dados no console
    public void consultar(Item ItemConsultado) throws IOException {
        int codigoConsultado = ItemConsultado.getCodigo();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            boolean itemEncontrado = false;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (Integer.parseInt(dados[0]) == codigoConsultado) {
                    System.out.println("Item encontrado:");
                    System.out.println("Código: " + dados[0]);
                    System.out.println("Descrição: " + dados[1]);
                    System.out.println("Valor: " + dados[2]);
                    itemEncontrado = true;
                    break;
                }
            }

            if (!itemEncontrado) {
                System.out.println("");
            }
        } catch (IOException e) {
            throw new IOException("Erro ao consultar Item: " + e.getMessage());
        }
    }

    // Método listar que lista todos os itens do arquivo e imprime os dados no console
    public void listar() throws IOException {
        ArrayList<Item> itens = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                Item i = new Item();
                i.setCodigo(Integer.parseInt(dados[0]));
                i.setDescricao(dados[1]);
                i.setValor(Double.parseDouble(dados[2]));
                itens.add(i);
            }
        } catch (IOException e) {
            throw new IOException("Erro ao listar itens: " + e.getMessage());
        }

        // Imprime os itens no console
        for (Item i : itens) {
            System.out.println("Código: " + i.getCodigo());
            System.out.println("Descrição: " + i.getDescricao());
            System.out.println("Valor: " + i.getValor());
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return codigo + ";" + descricao + ";" + valor;
    }

    public void fromString(String string) {
        // Implementar lógica para criar um objeto Item a partir de uma string
        String[] dados = string.split(";");
        this.codigo = Integer.parseInt(dados[0]);
        this.descricao = dados[1];
        this.valor = Double.parseDouble(dados[2]);
    }
}
