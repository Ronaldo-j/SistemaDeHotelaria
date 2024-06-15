import java.io.*;
import java.util.ArrayList;

public class Quarto {
    private int codigo;
    private Categoria categoria;
    private String status;

    public Quarto(int codigo, Categoria categoria, String status) {
        this.codigo = codigo;
        this.categoria = categoria;
        this.status = status;
    }

    public Quarto() {}

    // Getters e Setters
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    // Método cadastrar que salva em arquivo
    public boolean cadastrar(Quarto quarto, Categoria categoria) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Quarto.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (Integer.parseInt(parts[0]) == quarto.getCodigo()) {
                    System.out.println("Já existe um cadastro para este código de quarto.");
                    return false;
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("Quarto.txt", true))) {
            writer.println(
                    quarto.getCodigo() + ";" +
                    categoria.getCodigo() + ";" +
                    categoria.getDescricao() + ";" +
                    categoria.getValor() + ";" +
                    quarto.getStatus()
            );
            System.out.println("Cadastro realizado com sucesso.");
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao escrever o arquivo: " + e.getMessage());
            return false;
        }
    }

    // Método editar que edita atributos específicos de um quarto e sua categoria
    public boolean editar(Quarto quarto, int novoCodigo, String novoStatus, int novoCodCategoria, String novaDescricao, double novoValor) {
        try {
            File inputFile = new File("Quarto.txt");
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (Integer.parseInt(parts[0]) == quarto.getCodigo()) {
                    writer.write(novoCodigo + ";" +
                        novoCodCategoria + ";" +
                        novaDescricao + ";" +
                        novoValor + ";" +
                        novoStatus);
                    found = true;
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }

            reader.close();
            writer.close();

            if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                System.err.println("Erro: Não foi possível editar o Quarto.");
                return false;
            }

            if (!found) {
                System.out.println("");
                return false;
            }

            System.out.println("Quarto editado com sucesso.");
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao editar Quarto: " + e.getMessage());
            return false;
        }
    }

    // Método consultar que consulta em arquivo usando um objeto Quarto
    public Quarto consultar(int codigoQuarto) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Quarto.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (Integer.parseInt(parts[0]) == codigoQuarto) {
                    Categoria categoria = new Categoria(Integer.parseInt(parts[1]), parts[2], Double.parseDouble(parts[3]));
                    return new Quarto(Integer.parseInt(parts[0]), categoria, parts[4]);
                }
            }
            System.out.println("Quarto não encontrado.");
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return null;
    }


    // Método listar que lista todos os quartos do arquivo e imprime os dados no console
    public ArrayList<Quarto> listar() {
        ArrayList<Quarto> quartos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Quarto.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                Categoria categoria = new Categoria(Integer.parseInt(parts[1]), parts[2], Double.parseDouble(parts[3]));
                quartos.add(new Quarto(Integer.parseInt(parts[0]), categoria, parts[4]));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        // Imprime os quartos no console
        for (Quarto quarto : quartos) {
            System.out.println("Código: " + quarto.getCodigo());
            System.out.println("Categoria Código: " + quarto.getCategoria().getCodigo());
            System.out.println("Categoria Descrição: " + quarto.getCategoria().getDescricao());
            System.out.println("Categoria Valor: " + quarto.getCategoria().getValor());
            System.out.println("Status: " + quarto.getStatus());
            System.out.println();
        }
        return quartos;
    }

    public String getDescricao() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDescricao'");
    }

    public double getValor() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getValor'");
    }

    @Override
    public String toString() {
        return "Quarto{" +
                "codigo=" + codigo +
                ", categoria=" + categoria +
                ", status='" + status + '\'' +
                '}';
    }

}