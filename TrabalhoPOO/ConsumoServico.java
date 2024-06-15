import java.io.*;
import java.sql.Date;
import java.util.ArrayList;

public class ConsumoServico {
    private Servico servico;
    private Reserva reserva;
    private int quantidadeSolicitada;
    private Date dataServico;
    
    public ConsumoServico(Servico servico, Reserva reserva, int quantidadeSolicitada, Date dataServico) {
        this.servico = servico;
        this.reserva = reserva;
        this.quantidadeSolicitada = quantidadeSolicitada;
        this.dataServico = dataServico;
    }

    public ConsumoServico() {}

    // Getters e Setters
    public int getQuantidadeSolicitada() {
        return quantidadeSolicitada;
    }

    public void setQuantidadeSolicitada(int quantidadeSolicitada) {
        this.quantidadeSolicitada = quantidadeSolicitada;
    }

    public Date getDataServico() {
        return dataServico;
    }

    public void setDataServico(Date dataServico) {
        this.dataServico = dataServico;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    @Override
public String toString() {
    return "ConsumoServico{" +
            "servico=" + servico +
            ", reserva=" + reserva +
            ", quantidadeSolicitada=" + quantidadeSolicitada +
            ", dataServico=" + dataServico +
            '}';
}


    // Método cadastrar que salva em arquivo
    public boolean cadastrar(ConsumoServico consumoServico) {
        try (BufferedReader reader = new BufferedReader(new FileReader("ConsumoServico.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts[0].equals(String.valueOf(consumoServico.getServico().getCodigo())) && Integer.parseInt(parts[1]) == consumoServico.getReserva().getCodigo()) {
                    System.out.println("Já existe um cadastro para este serviço na reserva.");
                    return false;
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("ConsumoServico.txt", true))) {
            writer.println(
                    consumoServico.getServico().getCodigo() + ";" +
                    consumoServico.getReserva().getCodigo() + ";" +
                    consumoServico.getServico().getDescricao() + ";" +
                    consumoServico.getQuantidadeSolicitada() + ";" +
                    consumoServico.getDataServico()
            );
            System.out.println("Cadastro realizado com sucesso.");
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao escrever o arquivo: " + e.getMessage());
            return false;
        }
    }

    // Método editar que edita atributos específicos de um consumo
    public boolean editar(ConsumoServico consumoServico, Servico novoServico, Reserva novaReserva, int novaQuantidade, Date novaDataServico) {
        try {
            File inputFile = new File("ConsumoServico.txt");
            File tempFile = new File("tempConsumoServico.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts[0].equals(String.valueOf(consumoServico.getServico().getCodigo())) && Integer.parseInt(parts[1]) == consumoServico.getReserva().getCodigo()) {
                    writer.write(novoServico.getCodigo() + ";" +
                            novaReserva.getCodigo() + ";" +
                            novoServico.getDescricao() + ";" +
                            novaQuantidade + ";" +
                            novaDataServico);
                    found = true;
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }

            reader.close();
            writer.close();

            if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                System.err.println("Erro: Não foi possível editar o consumo de serviço.");
                return false;
            }

            if (!found) {
                System.out.println("Consumo de serviço não encontrado.");
                return false;
            }

            System.out.println("Consumo de serviço editado com sucesso.");
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao editar ConsumoServico: " + e.getMessage());
            return false;
        }
    }

    // Método consultar que consulta em arquivo usando um objeto ConsumoServico
    public ConsumoServico consultar(ConsumoServico consumoServico) {
        try (BufferedReader reader = new BufferedReader(new FileReader("ConsumoServico.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts[0].equals(String.valueOf(consumoServico.getServico().getCodigo())) && Integer.parseInt(parts[1]) == consumoServico.getReserva().getCodigo()) {
                    Servico servico = new Servico(Integer.parseInt(parts[0]), parts[2], 0.0);
                    Reserva reserva = new Reserva(Integer.parseInt(parts[1]), null, null, null, null, null, null, null, null, 0.0, 0.0);
                    System.out.println("Consumo de serviço encontrado:");
                    System.out.println("Serviço Código: " + parts[0]);
                    System.out.println("Reserva Código: " + parts[1]);
                    System.out.println("Descrição Serviço: " + parts[2]);
                    System.out.println("Quantidade Solicitada: " + parts[3]);
                    System.out.println("Data Serviço: " + parts[4]);

                    return new ConsumoServico(servico, reserva, Integer.parseInt(parts[3]), Date.valueOf(parts[4]));
                }
            }
            System.out.println("Consumo de serviço não encontrado.");
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return null;
    }

    // Método listar que lista todos os consumos de serviços do arquivo e imprime os dados no console
    public ArrayList<ConsumoServico> listar() {
        ArrayList<ConsumoServico> consumosServicos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("ConsumoServico.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                Servico servico = new Servico(Integer.parseInt(parts[0]), parts[2], 0.0);
                Reserva reserva = new Reserva(Integer.parseInt(parts[1]), null, null, null, null, null, null, null, null, 0.0, 0.0);
                consumosServicos.add(new ConsumoServico(servico, reserva, Integer.parseInt(parts[3]), Date.valueOf(parts[4])));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        // Imprime os consumos de serviços no console
        for (ConsumoServico consumoServico : consumosServicos) {
            System.out.println("Serviço Código: " + consumoServico.getServico().getCodigo());
            System.out.println("Reserva Código: " + consumoServico.getReserva().getCodigo());
            System.out.println("Descrição Serviço: " + consumoServico.getServico().getDescricao());
            System.out.println("Quantidade Solicitada: " + consumoServico.getQuantidadeSolicitada());
            System.out.println("Data Serviço: " + consumoServico.getDataServico());
            System.out.println("--------------------------");
        }

        return consumosServicos;
    }
}
