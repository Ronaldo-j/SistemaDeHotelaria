import java.io.*;
import java.util.ArrayList;

public class Categoria {
    private int codigo;
    private String descricao;
    private double valor;
    private static final String FILE_PATH = "Categoria.txt"; // Define o caminho do arquivo

    // Construtor
    public Categoria(int codigo, String descricao, double valor) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Categoria() {}

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
    public String cadastrar(Categoria c) throws IOException {
        // Validações
        if (c.codigo < 0) {
            return "Erro: Código deve ser positivo.";
        }
        if (c.descricao == null || c.descricao.trim().isEmpty()) {
            return "Erro: Descrição não pode estar vazia.";
        }
        if (c.valor <= 0) {
            return "Erro: Valor deve ser positivo.";
        }

        FileWriter fw = new FileWriter(FILE_PATH, true);
        BufferedWriter bw = new BufferedWriter(fw);

        FileReader fr = new FileReader(FILE_PATH);
        BufferedReader br = new BufferedReader(fr);

        String linha = null;
        boolean categoriaExiste = false;

        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(";");
            if (Integer.parseInt(dados[0]) == c.codigo) {
                categoriaExiste = true;
                break;
            }
        }
        if (!categoriaExiste) {
            bw.write(c.codigo + ";" + c.descricao + ";" + c.valor);
            bw.newLine();
            bw.close();
            return "Categoria cadastrada com sucesso.";
        } else {
            bw.close();
            return "Erro: Categoria com esse código já existe.";
        }
    }

    // Método editar que edita em arquivo
    public void editar(Categoria categoriaParaEditar, int novoCodigo, String novaDescricao, double novoValor) throws IOException {
        File inputFile = new File(FILE_PATH);

        // Carrega todo o conteúdo do arquivo
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] dados = currentLine.split(";");
                int codigo = Integer.parseInt(dados[0]);

                if (codigo == categoriaParaEditar.getCodigo()) {
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

    // Método auxiliar para carregar todas as categorias do arquivo
    private ArrayList<Categoria> carregarCategorias() throws IOException {
        ArrayList<Categoria> categorias = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                Categoria categoria = new Categoria();
                categoria.setCodigo(Integer.parseInt(dados[0]));
                categoria.setDescricao(dados[1]);
                categoria.setValor(Double.parseDouble(dados[2]));
                categorias.add(categoria);
            }
        } catch (IOException e) {
            throw new IOException("Erro ao carregar categorias: " + e.getMessage());
        }

        return categorias;
    }

    // Método consultar que consulta em arquivo usando um objeto Categoria e imprime os dados no console
    public void consultar(Categoria categoriaConsultada) throws IOException {
        int codigoConsultado = categoriaConsultada.getCodigo();
        boolean categoriaEncontrada = false;

        ArrayList<Categoria> categorias = carregarCategorias();
        for (Categoria categoria : categorias) {
            if (categoria.getCodigo() == codigoConsultado) {
                System.out.println("Categoria encontrada:");
                System.out.println("Código: " + categoria.getCodigo());
                System.out.println("Descrição: " + categoria.getDescricao());
                System.out.println("Valor: " + categoria.getValor());
                categoriaEncontrada = true;
                break;
            }
        }

        if (!categoriaEncontrada) {
            System.out.println("Categoria com código " + codigoConsultado + " não encontrada.");
        }
    }

    // Método listar que lista todas as categorias do arquivo e imprime os dados no console
    public void listar() throws IOException {
        ArrayList<Categoria> categorias = carregarCategorias();

        // Imprime as categorias no console
        for (Categoria categoria : categorias) {
            System.out.println("Código: " + categoria.getCodigo());
            System.out.println("Descrição: " + categoria.getDescricao());
            System.out.println("Valor: " + categoria.getValor());
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return codigo + ";" + descricao + ";" + valor;
    }

    public static void main(String[] args) {
        // Testes simples para verificar o funcionamento dos métodos
        try {
            Categoria cat1 = new Categoria(1, "Eletrônicos", 300.00);
            Categoria cat2 = new Categoria(2, "Móveis", 500.00);

            Categoria categoriaManager = new Categoria();

            // Teste de cadastro
            System.out.println(categoriaManager.cadastrar(cat1));
            System.out.println(categoriaManager.cadastrar(cat2));

            // Teste de consulta
            categoriaManager.consultar(cat1);

            // Teste de edição
            categoriaManager.editar(cat1, 1, "Eletrônicos Atualizados", 350.00);
            categoriaManager.consultar(cat1);

            // Teste de listagem
            categoriaManager.listar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
