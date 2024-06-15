import java.io.*;
import java.util.ArrayList;

public class CategoriaItem {
    private Item item;
    private Categoria categoria;
    private int quantidade;

    public CategoriaItem(Item item, Categoria categoria, int quantidade) {
        this.item = item;
        this.categoria = categoria;
        this.quantidade = quantidade;
    }

    public CategoriaItem() {}

    public boolean cadastrar(CategoriaItem categoriaItem) {
        try (BufferedReader reader = new BufferedReader(new FileReader("CategoriaItem.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (Integer.parseInt(parts[0]) == categoriaItem.getItem().getCodigo() &&
                        Integer.parseInt(parts[3]) == categoriaItem.getCategoria().getCodigo()) {
                    System.out.println("Já existe um cadastro para este Item nesta Categoria.");
                    return false;
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("CategoriaItem.txt", true))) {
            writer.println(
                    categoriaItem.getItem().getCodigo() + ";" +
                            categoriaItem.getItem().getDescricao() + ";" +
                            categoriaItem.getItem().getValor() + ";" +
                            categoriaItem.getCategoria().getCodigo() + ";" +
                            categoriaItem.getCategoria().getDescricao() + ";" +
                            categoriaItem.getCategoria().getValor() + ";" +
                            categoriaItem.getQuantidade()
            );
            System.out.println("Cadastro realizado com sucesso.");
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao escrever o arquivo: " + e.getMessage());
            return false;
        }
    }

    public boolean editar(CategoriaItem categoriaItem, Item newItem, Categoria newCategoria, int newQuantidade) {
        try {
            File inputFile = new File("CategoriaItem.txt");
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean encontrado = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (Integer.parseInt(parts[0]) == categoriaItem.getItem().getCodigo() && Integer.parseInt(parts[3]) == categoriaItem.getCategoria().getCodigo()) {
                    writer.write(
                            newItem.getCodigo() + ";" +
                                    newItem.getDescricao() + ";" +
                                    newItem.getValor() + ";" +
                                    newCategoria.getCodigo() + ";" +
                                    newCategoria.getDescricao() + ";" +
                                    newCategoria.getValor() + ";" +
                                    newQuantidade
                    );
                    writer.newLine();
                    encontrado = true;
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }

            reader.close();
            writer.close();

            if (!encontrado) {
                System.err.println("CategoriaItem não encontrado para editar.");
                return false;
            }

            if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                System.err.println("Erro: Não foi possível editar CategoriaItem.");
                return false;
            }

            // Atualiza o item e a categoria no objeto categoriaItem
            categoriaItem.setItem(newItem);
            categoriaItem.setCategoria(newCategoria);
            categoriaItem.setQuantidade(newQuantidade);

            System.out.println("CategoriaItem editado com sucesso.");
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao editar CategoriaItem: " + e.getMessage());
            return false;
        }
    }

    public String consultar(CategoriaItem categoriaItem) {
        try (BufferedReader reader = new BufferedReader(new FileReader("CategoriaItem.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (Integer.parseInt(parts[0]) == categoriaItem.getItem().getCodigo() &&
                    Integer.parseInt(parts[3]) == categoriaItem.getCategoria().getCodigo()) {
                    
                    // Monta a mensagem com os dados encontrados
                    StringBuilder sb = new StringBuilder();
                    sb.append("CategoriaItem encontrado:\n");
                    sb.append("Item: Código - ").append(parts[0]).append(", Descrição - ").append(parts[1]).append(", Valor - ").append(parts[2]).append("\n");
                    sb.append("Categoria: Código - ").append(parts[3]).append(", Descrição - ").append(parts[4]).append(", Valor - ").append(parts[5]).append("\n");
                    sb.append("Quantidade: ").append(parts[6]);

                    // Imprime os dados encontrados no console
                    System.out.println(sb.toString());

                    // Retorna os dados como uma string
                    return sb.toString();
                }
            }
            System.out.println("CategoriaItem não encontrado.");
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return null; // Retorna null se o CategoriaItem não for encontrado
    }

    public ArrayList<CategoriaItem> listar() {
        ArrayList<CategoriaItem> categoriaItems = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("CategoriaItem.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                Item item = new Item(Integer.parseInt(parts[0]), parts[1], Double.parseDouble(parts[2]));
                Categoria categoria = new Categoria(Integer.parseInt(parts[3]), parts[4], Double.parseDouble(parts[5]));
                int quantidade = Integer.parseInt(parts[6]);
                categoriaItems.add(new CategoriaItem(item, categoria, quantidade));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return categoriaItems;
    }

    // Getters e Setters
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
public String toString() {
    return "Item: Código - " + item.getCodigo() +
           ", Descrição - " + item.getDescricao() +
           ", Valor - " + item.getValor() + "\n" +
           "Categoria: Código - " + categoria.getCodigo() +
           ", Descrição - " + categoria.getDescricao() +
           ", Valor - " + categoria.getValor() + "\n" +
           "Quantidade: " + quantidade;
}

}
