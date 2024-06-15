import java.io.*;
import java.sql.Date;
import java.util.ArrayList;

public class Consumo {
    private Item item;
    private Reserva reserva;
    private int quantidadeSolicitada;
    private Date dataConsumo;

    public Consumo(Item item, Reserva reserva, int quantidadeSolicitada, Date dataConsumo) {
        this.item = item;
        this.reserva = reserva;
        this.quantidadeSolicitada = quantidadeSolicitada;
        this.dataConsumo = dataConsumo;
    }

    public Consumo() {}

    // Getters e Setters
    public Date getDataConsumo() {
        return dataConsumo;
    }

    public void setDataConsumo(Date dataConsumo) {
        this.dataConsumo = dataConsumo;
    }

    public int getQuantidadeSolicitada() {
        return quantidadeSolicitada;
    }

    public void setQuantidadeSolicitada(int quantidadeSolicitada) {
        this.quantidadeSolicitada = quantidadeSolicitada;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    @Override
public String toString() {
    return "Consumo{" +
            "item=" + item +
            ", reserva=" + reserva +
            ", quantidadeSolicitada=" + quantidadeSolicitada +
            ", dataConsumo=" + dataConsumo +
            '}';
}

    // Método cadastrar que salva em arquivo
    public boolean cadastrar(Consumo consumo) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Consumo.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts[0].equals(String.valueOf(consumo.getItem().getCodigo())) && Integer.parseInt(parts[1]) == consumo.getReserva().getCodigo()) {
                    System.out.println("Já existe um cadastro para este item na reserva.");
                    return false;
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("Consumo.txt", true))) {
            writer.println(
                    consumo.getItem().getCodigo() + ";" +
                    consumo.getReserva().getCodigo() + ";" +
                    consumo.getQuantidadeSolicitada() + ";" +
                    consumo.getDataConsumo()
            );
            System.out.println("Cadastro realizado com sucesso.");
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao escrever o arquivo: " + e.getMessage());
            return false;
        }
    }

    // Método editar que edita atributos específicos de um consumo
    public boolean editar(Consumo consumo, Item novoItem, Reserva novaReserva, int novaQuantidade, Date novaDataConsumo) {
        try {
            File inputFile = new File("Consumo.txt");
            File tempFile = new File("tempConsumo.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts[0].equals(String.valueOf(consumo.getItem().getCodigo())) && Integer.parseInt(parts[1]) == consumo.getReserva().getCodigo()) {
                    writer.write(novoItem.getCodigo() + ";" +
                            novaReserva.getCodigo() + ";" +
                            novaQuantidade + ";" +
                            novaDataConsumo);
                    found = true;
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }

            reader.close();
            writer.close();

            if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                System.err.println("Erro: Não foi possível editar o consumo.");
                return false;
            }

            if (!found) {
                System.out.println("Consumo não encontrado.");
                return false;
            }

            System.out.println("Consumo editado com sucesso.");
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao editar Consumo: " + e.getMessage());
            return false;
        }
    }

    // Método consultar que consulta em arquivo usando um objeto Consumo
    public Consumo consultar(Consumo consumo) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Consumo.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts[0].equals(String.valueOf(consumo.getItem().getCodigo())) && Integer.parseInt(parts[1]) == consumo.getReserva().getCodigo()) {
                    Item item = new Item(Integer.parseInt(parts[0]), "", 0.0);
                    Reserva reserva = new Reserva(Integer.parseInt(parts[1]), null, null, null, null, null, null, null, null, 0.0, 0.0);
                    System.out.println("Consumo encontrado:");
                    System.out.println("Item Código: " + parts[0]);
                    System.out.println("Reserva Código: " + parts[1]);
                    System.out.println("Quantidade Solicitada: " + parts[2]);
                    System.out.println("Data Consumo: " + parts[3]);

                    return new Consumo(item, reserva, Integer.parseInt(parts[2]), Date.valueOf(parts[3]));
                }
            }
            System.out.println("Consumo não encontrado.");
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return null;
    }

    // Método listar que lista todos os consumos do arquivo e imprime os dados no console
    public ArrayList<Consumo> listar() {
        ArrayList<Consumo> consumos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Consumo.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                Item item = new Item(Integer.parseInt(parts[0]), "", 0.0);
                Reserva reserva = new Reserva(Integer.parseInt(parts[1]), null, null, null, null, null, null, null, null, 0.0, 0.0);
                consumos.add(new Consumo(item, reserva, Integer.parseInt(parts[2]), Date.valueOf(parts[3])));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        // Imprime os consumos no console
        for (Consumo consumo : consumos) {
            System.out.println("Item Código: " + consumo.getItem().getCodigo());
            System.out.println("Reserva Código: " + consumo.getReserva().getCodigo());
            System.out.println("Quantidade Solicitada: " + consumo.getQuantidadeSolicitada());
            System.out.println("Data Consumo: " + consumo.getDataConsumo());
            System.out.println("--------------------------");
        }

        return consumos;
    }
}
