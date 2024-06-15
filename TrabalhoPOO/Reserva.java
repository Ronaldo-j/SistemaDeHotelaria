import java.io.*;
import java.sql.Date;
import java.util.ArrayList;

public class Reserva {
    private int codigo;
    private Hospede hospede;
    private Quarto quarto;
    private Funcionario funcionarioReserva;
    private Funcionario funcionarioFechamento;
    private Date dataEntradaReserva;
    private Date dataSaidaReserva;
    private Date dataCheckin;
    private Date dataCheckout;
    private double valorReserva;
    private double valorPago;

    public Reserva(int codigo, Hospede hospede, Quarto quarto, Funcionario funcionarioReserva, Funcionario funcionarioFechamento, Date dataEntradaReserva, Date dataSaidaReserva, Date dataCheckin, Date dataCheckout, double valorReserva, double valorPago) {
        this.codigo = codigo;
        this.hospede = hospede;
        this.quarto = quarto;
        this.funcionarioReserva = funcionarioReserva;
        this.funcionarioFechamento = funcionarioFechamento;
        this.dataEntradaReserva = dataEntradaReserva;
        this.dataSaidaReserva = dataSaidaReserva;
        this.dataCheckin = dataCheckin;
        this.dataCheckout = dataCheckout;
        this.valorReserva = valorReserva;
        this.valorPago = valorPago;
    }
    
    public Reserva() {}
    // Getters e Setters
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public Hospede getHospede() {
        return hospede;
    }
    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }
    public Quarto getQuarto() {
        return quarto;
    }
    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }
    public Funcionario getFuncionarioReserva() {
        return funcionarioReserva;
    }
    public void setFuncionarioReserva(Funcionario funcionarioReserva) {
        this.funcionarioReserva = funcionarioReserva;
    }
    public Funcionario getFuncionarioFechamento() {
        return funcionarioFechamento;
    }
    public void setFuncionarioFechamento(Funcionario funcionarioFechamento) {
        this.funcionarioFechamento = funcionarioFechamento;
    }
    public Date getDataEntradaReserva() {
        return dataEntradaReserva;
    }
    public void setDataEntradaReserva(Date dataEntradaReserva) {
        this.dataEntradaReserva = dataEntradaReserva;
    }
    public Date getDataSaidaReserva() {
        return dataSaidaReserva;
    }
    public void setDataSaidaReserva(Date dataSaidaReserva) {
        this.dataSaidaReserva = dataSaidaReserva;
    }
    public Date getDataCheckin() {
        return dataCheckin;
    }
    public void setDataCheckin(Date dataCheckin) {
        this.dataCheckin = dataCheckin;
    }
    public Date getDataCheckout() {
        return dataCheckout;
    }
    public void setDataCheckout(Date dataCheckout) {
        this.dataCheckout = dataCheckout;
    }
    public double getValorReserva() {
        return valorReserva;
    }
    public void setValorReserva(double valorReserva) {
        this.valorReserva = valorReserva;
    }
    public double getValorPago() {
        return valorPago;
    }
    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "codigo=" + codigo +
                ", hospede=" + hospede.getCpf() +
                ", quarto=" + quarto.getCodigo() +
                ", funcionarioReserva=" + funcionarioReserva.getCpf() +
                ", funcionarioFechamento=" + funcionarioFechamento.getCpf() +
                ", dataEntradaReserva=" + dataEntradaReserva +
                ", dataSaidaReserva=" + dataSaidaReserva +
                ", dataCheckin=" + dataCheckin +
                ", dataCheckout=" + dataCheckout +
                ", valorReserva=" + valorReserva +
                ", valorPago=" + valorPago +
                '}';
    }

    // Método cadastrar que salva em arquivo
    public boolean cadastrar(Reserva reserva) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Reserva.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (Integer.parseInt(parts[0]) == reserva.getCodigo()) {
                    System.out.println("Já existe um cadastro para este código de reserva.");
                    return false;
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("Reserva.txt", true))) {
            writer.println(
                    reserva.getCodigo() + ";" +
                    reserva.getHospede().getCpf() + ";" +
                    reserva.getQuarto().getCodigo() + ";" +
                    reserva.getFuncionarioReserva().getCpf() + ";" +
                    reserva.getFuncionarioFechamento().getCpf() + ";" +
                    reserva.getDataEntradaReserva() + ";" +
                    reserva.getDataSaidaReserva() + ";" +
                    reserva.getDataCheckin() + ";" +
                    reserva.getDataCheckout() + ";" +
                    reserva.getValorReserva() + ";" +
                    reserva.getValorPago()
            );
            System.out.println("Cadastro realizado com sucesso.");
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao escrever o arquivo: " + e.getMessage());
            return false;
        }
    }

    // Método editar que edita atributos específicos de uma reserva
    public boolean editar(Reserva reserva, int novoCodigo, Hospede novoHospede, Quarto novoQuarto, Funcionario novoFuncionarioReserva, Funcionario novoFuncionarioFechamento, Date novaDataEntradaReserva, Date novaDataSaidaReserva, Date novaDataCheckin, Date novaDataCheckout, double novoValorReserva, double novoValorPago) {
        try {
            File inputFile = new File("Reserva.txt");
            File tempFile = new File("temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (Integer.parseInt(parts[0]) == reserva.getCodigo()) {
                    writer.write(novoCodigo + ";" +
                            novoHospede.getCpf() + ";" +
                            novoQuarto.getCodigo() + ";" +
                            novoFuncionarioReserva.getCpf() + ";" +
                            novoFuncionarioFechamento.getCpf() + ";" +
                            novaDataEntradaReserva + ";" +
                            novaDataSaidaReserva + ";" +
                            novaDataCheckin + ";" +
                            novaDataCheckout + ";" +
                            novoValorReserva + ";" +
                            novoValorPago);
                    found = true;
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }

            reader.close();
            writer.close();

            if (!inputFile.delete() || !tempFile.renameTo(inputFile)) {
                System.err.println("Erro: Não foi possível editar a Reserva.");
                return false;
            }

            if (!found) {
                System.out.println("Reserva não encontrada.");
                return false;
            }

            System.out.println("Reserva editada com sucesso.");
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao editar Reserva: " + e.getMessage());
            return false;
        }
    }

    // Método consultar que consulta em arquivo usando um código de reserva
public Reserva consultar(int codigo) {
    try (BufferedReader reader = new BufferedReader(new FileReader("Reserva.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            if (Integer.parseInt(parts[0]) == codigo) {
                Hospede hospede = new Hospede(); // Precisa carregar o Hospede completo do arquivo
                hospede.setCpf(parts[1]);
                Quarto quarto = new Quarto(); // Precisa carregar o Quarto completo do arquivo
                quarto.setCodigo(Integer.parseInt(parts[2]));
                Funcionario funcionarioReserva = new Funcionario(); // Precisa carregar o Funcionario completo do arquivo
                funcionarioReserva.setCpf(parts[3]);
                Funcionario funcionarioFechamento = new Funcionario(); // Precisa carregar o Funcionario completo do arquivo
                funcionarioFechamento.setCpf(parts[4]);

                Reserva reservaEncontrada = new Reserva(Integer.parseInt(parts[0]), hospede, quarto, funcionarioReserva, funcionarioFechamento,
                        Date.valueOf(parts[5]), Date.valueOf(parts[6]), Date.valueOf(parts[7]), Date.valueOf(parts[8]),
                        Double.parseDouble(parts[9]), Double.parseDouble(parts[10]));

                // Imprime os dados da reserva encontrada
                System.out.println("Reserva encontrada:");
                System.out.println("Código: " + reservaEncontrada.getCodigo());
                System.out.println("Hospede CPF: " + reservaEncontrada.getHospede().getCpf());
                System.out.println("Quarto Código: " + reservaEncontrada.getQuarto().getCodigo());
                System.out.println("Funcionario Reserva CPF: " + reservaEncontrada.getFuncionarioReserva().getCpf());
                System.out.println("Funcionario Fechamento CPF: " + reservaEncontrada.getFuncionarioFechamento().getCpf());
                System.out.println("Data Entrada Reserva: " + reservaEncontrada.getDataEntradaReserva());
                System.out.println("Data Saida Reserva: " + reservaEncontrada.getDataSaidaReserva());
                System.out.println("Data Checkin: " + reservaEncontrada.getDataCheckin());
                System.out.println("Data Checkout: " + reservaEncontrada.getDataCheckout());
                System.out.println("Valor Reserva: " + reservaEncontrada.getValorReserva());
                System.out.println("Valor Pago: " + reservaEncontrada.getValorPago());
                System.out.println("--------------------------");

                return reservaEncontrada;
            }
        }
        System.out.println("Reserva não encontrada.");
    } catch (IOException e) {
        System.err.println("Erro ao ler o arquivo: " + e.getMessage());
    }
    return null;
}


    // Método listar que lista todas as reservas do arquivo e imprime os dados no console
    public ArrayList<Reserva> listar() {
        ArrayList<Reserva> reservas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Reserva.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                Hospede hospede = new Hospede(); // Precisa carregar o Hospede completo do arquivo
                hospede.setCpf(parts[1]);
                Quarto quarto = new Quarto(); // Precisa carregar o Quarto completo do arquivo
                quarto.setCodigo(Integer.parseInt(parts[2]));
                Funcionario funcionarioReserva = new Funcionario(); // Precisa carregar o Funcionario completo do arquivo
                funcionarioReserva.setCpf(parts[3]);
                Funcionario funcionarioFechamento = new Funcionario(); // Precisa carregar o Funcionario completo do arquivo
                funcionarioFechamento.setCpf(parts[4]);

                reservas.add(new Reserva(Integer.parseInt(parts[0]), hospede, quarto, funcionarioReserva, funcionarioFechamento, Date.valueOf(parts[5]), Date.valueOf(parts[6]), Date.valueOf(parts[7]), Date.valueOf(parts[8]), Double.parseDouble(parts[9]), Double.parseDouble(parts[10])));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        // Imprime as reservas no console
        for (Reserva reserva : reservas) {
            System.out.println("Código: " + reserva.getCodigo());
            System.out.println("Hospede CPF: " + reserva.getHospede().getCpf());
            System.out.println("Quarto Código: " + reserva.getQuarto().getCodigo());
            System.out.println("Funcionario Reserva CPF: " + reserva.getFuncionarioReserva().getCpf());
            System.out.println("Funcionario Fechamento CPF: " + reserva.getFuncionarioFechamento().getCpf());
            System.out.println("Data Entrada Reserva: " + reserva.getDataEntradaReserva());
            System.out.println("Data Saida Reserva: " + reserva.getDataSaidaReserva());
            System.out.println("Data Checkin: " + reserva.getDataCheckin());
            System.out.println("Data Checkout: " + reserva.getDataCheckout());
            System.out.println("Valor Reserva: " + reserva.getValorReserva());
            System.out.println("Valor Pago: " + reserva.getValorPago());
            System.out.println("--------------------------");
        }

        return reservas;
    }

    public void pagarReserva(){
        if(getValorReserva() == getValorPago()){
            System.out.println("Reserva paga");
        }else if (getValorReserva() < getValorPago()) {
            double troco = getValorPago() - getValorReserva();
            System.out.println("Troco: " + troco);
        }else if(getValorReserva() > getValorPago()){
            System.out.println("Saldo insuficiente");
        }
    }
}
