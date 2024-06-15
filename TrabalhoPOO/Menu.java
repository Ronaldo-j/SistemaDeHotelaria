import java.io.*;
import java.sql.Date;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Categoria categoriaManager = new Categoria();
        Item itemManager = new Item();
        Funcionario funcionarioManager = new Funcionario();
        Hospede hospedeManager = new Hospede();
        Servico servicoManager = new Servico();
        Quarto quartoManager = new Quarto();
        CategoriaItem categoriaItemManager = new CategoriaItem();
        Consumo consumoManager = new Consumo();
        ConsumoServico consumoServicoManager = new ConsumoServico();
        Reserva reservaManager = new Reserva();
        
        while (true) {
            System.out.println("Selecione a entidade desejada:");
            System.out.println("1. Categoria");
            System.out.println("2. Item");
            System.out.println("3. Funcionario");
            System.out.println("4. Hospede");
            System.out.println("5. Servico");
            System.out.println("6. Quarto");
            System.out.println("7. CategoriaItem");
            System.out.println("8. Reserva");
            System.out.println("9. Consumo");
            System.out.println("10. ConsumoServico");
            System.out.println("0. Sair");
            int opcaoEntidade = scanner.nextInt();
            scanner.nextLine();  // Consumir a linha

            if (opcaoEntidade == 0) {
                System.out.println("Saindo...");
                scanner.close();
                break;
            }

            switch (opcaoEntidade) {
                case 1:
                    System.out.println("Operações de Categoria:");
                    categoriaOperations(scanner, categoriaManager);
                    break;
                    case 2:
                    System.out.println("Operações de Item:");
                    itemOperations(scanner, itemManager);
                    break;
                    case 3:
                    System.out.println("Operações de Funcionario:");
                    funcionarioOperations(scanner, funcionarioManager);
                    break;
                    case 4:
                    System.out.println("Operações de Hóspede:");
                    hospedeOperations(scanner, hospedeManager);
                    break;
                    case 5:
                    System.out.println("Operações de Serviço:");
                    servicoOperations(scanner, servicoManager);
                    break;
                    case 6:
                    System.out.println("Operações de Quarto:");
                    quartoOperations(scanner, quartoManager, categoriaManager);
                    break;
                    case 7:
                    System.out.println("Operações de CategoriaItem:");
                    categoriaItemOperations(scanner, categoriaItemManager, itemManager, categoriaManager);
                    break;
                    case 8:
                    System.out.println("Operações de Reserva:");
                    reservaOperations(scanner, reservaManager, hospedeManager, quartoManager, funcionarioManager);
                    break;
                    case 9:
                    System.out.println("Operações de Consumo:");
                    consumoOperations(scanner, consumoManager, itemManager, reservaManager);
                    break;
                    case 10:
                    System.out.println("Operações de Consumo de Serviço:");
                    consumoServicoOperations(scanner, consumoServicoManager, servicoManager, reservaManager);
                    break;
                

                // Adicione cases para outras entidades conforme necessário
                // Por exemplo:
                // case 2:
                //     System.out.println("Operações de Item:");
                //     itemOperations(scanner, itemManager);
                //     break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    private static void categoriaOperations(Scanner scanner, Categoria categoriaManager) throws IOException {
        while (true) {
            System.out.println("1. Cadastrar Categoria");
            System.out.println("2. Editar Categoria");
            System.out.println("3. Consultar Categoria");
            System.out.println("4. Listar Categorias");
            System.out.println("0. Voltar ao menu principal");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a linha

            if (opcao == 0) {
                break;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Digite o código da categoria: ");
                    int codigoCategoria = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
                    System.out.print("Digite a descrição da categoria: ");
                    String descricaoCategoria = scanner.nextLine();
                    System.out.print("Digite o valor da categoria: ");
                    double valorCategoria = scanner.nextDouble();
                    scanner.nextLine();  // Consumir a linha
                    Categoria categoria = new Categoria(codigoCategoria, descricaoCategoria, valorCategoria);
                    System.out.println(categoriaManager.cadastrar(categoria));
                    break;
                case 2:
                    System.out.print("Digite o código da categoria a editar: ");
                    int codigoEditar = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
                    System.out.print("Digite a nova descrição da categoria: ");
                    String novaDescricao = scanner.nextLine();
                    System.out.print("Digite o novo valor da categoria: ");
                    double novoValor = scanner.nextDouble();
                    scanner.nextLine();  // Consumir a linha
                    Categoria categoriaParaEditar = new Categoria();
                    categoriaParaEditar.setCodigo(codigoEditar);
                    categoriaManager.editar(categoriaParaEditar, codigoEditar, novaDescricao, novoValor);
                    System.out.println("Categoria editada com sucesso.");
                    break;
                case 3:
                    System.out.print("Digite o código da categoria a consultar: ");
                    int codigoConsultar = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
                    Categoria categoriaParaConsultar = new Categoria();
                    categoriaParaConsultar.setCodigo(codigoConsultar);
                    categoriaManager.consultar(categoriaParaConsultar);
                    break;
                case 4:
                    categoriaManager.listar();
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
    private static void itemOperations(Scanner scanner, Item itemManager) throws IOException {
        while (true) {
            System.out.println("1. Cadastrar Item");
            System.out.println("2. Editar Item");
            System.out.println("3. Consultar Item");
            System.out.println("4. Listar Itens");
            System.out.println("0. Voltar ao menu principal");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a linha
    
            if (opcao == 0) {
                break;
            }
    
            switch (opcao) {
                case 1:
                    System.out.print("Digite o código do item: ");
                    int codigoItem = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
                    System.out.print("Digite a descrição do item: ");
                    String descricaoItem = scanner.nextLine();
                    System.out.print("Digite o valor do item: ");
                    double valorItem = scanner.nextDouble();
                    scanner.nextLine();  // Consumir a linha
                    Item item = new Item(codigoItem, descricaoItem, valorItem);
                    System.out.println(itemManager.cadastrar(item));
                    break;
                case 2:
                    System.out.print("Digite o código do item a editar: ");
                    int codigoEditar = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
                    System.out.print("Digite a nova descrição do item: ");
                    String novaDescricao = scanner.nextLine();
                    System.out.print("Digite o novo valor do item: ");
                    double novoValor = scanner.nextDouble();
                    scanner.nextLine();  // Consumir a linha
                    Item itemParaEditar = new Item();
                    itemParaEditar.setCodigo(codigoEditar);
                    itemManager.editar(itemParaEditar, codigoEditar, novaDescricao, novoValor);
                    System.out.println("Item editado com sucesso.");
                    break;
                case 3:
                    System.out.print("Digite o código do item a consultar: ");
                    int codigoConsultar = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
                    Item itemParaConsultar = new Item();
                    itemParaConsultar.setCodigo(codigoConsultar);
                    itemManager.consultar(itemParaConsultar);
                    break;
                case 4:
                    itemManager.listar();
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }    
    private static void funcionarioOperations(Scanner scanner, Funcionario funcionarioManager) throws IOException {
        while (true) {
            System.out.println("1. Cadastrar Funcionario");
            System.out.println("2. Editar Funcionario");
            System.out.println("3. Consultar Funcionario");
            System.out.println("4. Listar Funcionarios");
            System.out.println("0. Voltar ao menu principal");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a linha
    
            if (opcao == 0) {
                break;
            }
    
            switch (opcao) {
                case 1:
                    System.out.print("Digite o CPF do funcionario: ");
                    String cpfFuncionario = scanner.nextLine();
                    System.out.print("Digite o nome do funcionario: ");
                    String nomeFuncionario = scanner.nextLine();
                    System.out.print("Digite o email do funcionario: ");
                    String emailFuncionario = scanner.nextLine();
                    System.out.print("Digite o setor do funcionario: ");
                    String setorFuncionario = scanner.nextLine();
                    Funcionario funcionario = new Funcionario(cpfFuncionario, nomeFuncionario, emailFuncionario, setorFuncionario);
                    System.out.println(funcionarioManager.cadastrar(funcionario));
                    break;
                case 2:
                    System.out.print("Digite o CPF do funcionario a editar: ");
                    String cpfEditar = scanner.nextLine();
                    System.out.print("Digite o novo CPF do funcionario: ");
                    String novoCpf = scanner.nextLine();
                    System.out.print("Digite o novo nome do funcionario: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Digite o novo email do funcionario: ");
                    String novoEmail = scanner.nextLine();
                    System.out.print("Digite o novo setor do funcionario: ");
                    String novoSetor = scanner.nextLine();
                    Funcionario funcionarioParaEditar = new Funcionario();
                    funcionarioParaEditar.setCpf(cpfEditar);
                    funcionarioManager.editar(funcionarioParaEditar, novoCpf, novoNome, novoEmail, novoSetor);
                    System.out.println("Funcionario editado com sucesso.");
                    break;
                case 3:
                    System.out.print("Digite o CPF do funcionario a consultar: ");
                    String cpfConsultar = scanner.nextLine();
                    Funcionario funcionarioParaConsultar = new Funcionario();
                    funcionarioParaConsultar.setCpf(cpfConsultar);
                    funcionarioManager.consultar(cpfConsultar);
                    break;
                case 4:
                    funcionarioManager.listar();
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
    private static void hospedeOperations(Scanner scanner, Hospede hospedeManager) throws IOException {
        while (true) {
            System.out.println("1. Cadastrar Hóspede");
            System.out.println("2. Editar Hóspede");
            System.out.println("3. Consultar Hóspede");
            System.out.println("4. Listar Hóspedes");
            System.out.println("0. Voltar ao menu principal");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a linha
    
            if (opcao == 0) {
                break;
            }
    
            switch (opcao) {
                case 1:
                    System.out.print("Digite o CPF do hóspede: ");
                    String cpfHospede = scanner.nextLine();
                    System.out.print("Digite o nome do hóspede: ");
                    String nomeHospede = scanner.nextLine();
                    System.out.print("Digite o email do hóspede: ");
                    String emailHospede = scanner.nextLine();
                    System.out.print("Digite o endereço completo do hóspede: ");
                    String enderecoHospede = scanner.nextLine();
                    Hospede hospede = new Hospede(cpfHospede, nomeHospede, emailHospede, enderecoHospede);
                    System.out.println(hospedeManager.cadastrar(hospede));
                    break;
                case 2:
                    System.out.print("Digite o CPF do hóspede a editar: ");
                    String cpfEditar = scanner.nextLine();
                    System.out.print("Digite o novo CPF do hóspede: ");
                    String novoCpf = scanner.nextLine();
                    System.out.print("Digite o novo nome do hóspede: ");
                    String novoNome = scanner.nextLine();
                    System.out.print("Digite o novo email do hóspede: ");
                    String novoEmail = scanner.nextLine();
                    System.out.print("Digite o novo endereço completo do hóspede: ");
                    String novoEndereco = scanner.nextLine();
                    Hospede hospedeParaEditar = new Hospede();
                    hospedeParaEditar.setCpf(cpfEditar);
                    hospedeManager.editar(hospedeParaEditar, novoCpf, novoNome, novoEmail, novoEndereco);
                    System.out.println("Hóspede editado com sucesso.");
                    break;
                case 3:
                    System.out.print("Digite o CPF do hóspede a consultar: ");
                    String cpfConsultar = scanner.nextLine();
                    Hospede hospedeParaConsultar = new Hospede();
                    hospedeParaConsultar.setCpf(cpfConsultar);
                    hospedeManager.consultar(cpfConsultar);
                    break;
                case 4:
                    hospedeManager.listar();
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
    private static void servicoOperations(Scanner scanner, Servico servicoManager) throws IOException {
        while (true) {
            System.out.println("1. Cadastrar Serviço");
            System.out.println("2. Editar Serviço");
            System.out.println("3. Consultar Serviço");
            System.out.println("4. Listar Serviços");
            System.out.println("0. Voltar ao menu principal");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a linha
    
            if (opcao == 0) {
                break;
            }
    
            switch (opcao) {
                case 1:
                    System.out.print("Digite o código do serviço: ");
                    int codigoServico = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
                    System.out.print("Digite a descrição do serviço: ");
                    String descricaoServico = scanner.nextLine();
                    System.out.print("Digite o valor do serviço: ");
                    double valorServico = scanner.nextDouble();
                    scanner.nextLine();  // Consumir a linha
                    Servico servico = new Servico(codigoServico, descricaoServico, valorServico);
                    System.out.println(servicoManager.cadastrar(servico));
                    break;
                case 2:
                    System.out.print("Digite o código do serviço a editar: ");
                    int codigoEditar = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
                    System.out.print("Digite o novo código do serviço: ");
                    int novoCodigo = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
                    System.out.print("Digite a nova descrição do serviço: ");
                    String novaDescricao = scanner.nextLine();
                    System.out.print("Digite o novo valor do serviço: ");
                    double novoValor = scanner.nextDouble();
                    scanner.nextLine();  // Consumir a linha
                    Servico servicoParaEditar = new Servico(codigoEditar, "", 0); // Criando um objeto Servico apenas com o código para utilizar no método editar
                    servicoManager.editar(servicoParaEditar, novoCodigo, novaDescricao, novoValor);
                    System.out.println("Serviço editado com sucesso.");
                    break;
                case 3:
                    System.out.print("Digite o código do serviço a consultar: ");
                    int codigoConsultar = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
                    Servico servicoParaConsultar = new Servico(codigoConsultar, "", 0); // Criando um objeto Servico apenas com o código para utilizar no método consultar
                    servicoManager.consultar(servicoParaConsultar);
                    break;
                case 4:
                    servicoManager.listar();
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
    private static void quartoOperations(Scanner scanner, Quarto quartoManager, Categoria categoriaManager) throws IOException {
        while (true) {
            System.out.println("1. Cadastrar Quarto");
            System.out.println("2. Editar Quarto");
            System.out.println("3. Consultar Quarto");
            System.out.println("4. Listar Quartos");
            System.out.println("0. Voltar ao menu principal");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a linha
    
            if (opcao == 0) {
                break;
            }
    
            switch (opcao) {
                case 1:
                    System.out.print("Digite o código do quarto: ");
                    int codigoQuarto = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
    
                    // Listar categorias disponíveis para escolha
                    System.out.println("Categorias disponíveis:");
                    categoriaManager.listar();
    
                    System.out.print("Digite o código da categoria do quarto: ");
                    int codigoCategoria = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
    
                    // Aqui você precisa buscar a categoria pelo código informado
                    Categoria categoriaSelecionada = new Categoria(codigoCategoria, "", 0); // Substitua "" e 0 pelos getters corretos da Categoria
    
                    System.out.print("Digite o status do quarto (Disponível/Indisponível): ");
                    String statusQuarto = scanner.nextLine();
                    Quarto quarto = new Quarto(codigoQuarto, categoriaSelecionada, statusQuarto);
                    System.out.println(quartoManager.cadastrar(quarto, categoriaSelecionada));
                    break;
                case 2:
                    System.out.print("Digite o código do quarto a editar: ");
                    int codigoEditar = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
    
                    System.out.print("Digite o novo código do quarto: ");
                    int novoCodigo = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
    
                    System.out.print("Digite o novo status do quarto (Disponível/Indisponível): ");
                    String novoStatus = scanner.nextLine();
    
                    // Listar categorias disponíveis para escolha
                    System.out.println("Categorias disponíveis:");
                    categoriaManager.listar();
    
                    System.out.print("Digite o novo código da categoria do quarto: ");
                    int novoCodigoCategoria = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
    
                    // Aqui você precisa buscar a categoria pelo código informado
                    Categoria novaCategoria = new Categoria(novoCodigoCategoria, "", 0); // Substitua "" e 0 pelos getters corretos da Categoria
    
                    System.out.println(quartoManager.editar(new Quarto(codigoEditar, null, ""), novoCodigo, novoStatus, novoCodigoCategoria, novaCategoria.getDescricao(), novaCategoria.getValor()));
                    break;
                case 3:
                    System.out.print("Digite o código do quarto a consultar: ");
                    int codigoConsultar = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
    
                    Quarto quartoParaConsultar = new Quarto(codigoConsultar, null, "");
                    quartoManager.consultar(codigoConsultar);
                    break;
                case 4:
                    quartoManager.listar();
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
    private static void categoriaItemOperations(Scanner scanner, CategoriaItem categoriaItemManager, Item itemManager, Categoria categoriaManager) throws IOException {
        while (true) {
            System.out.println("1. Cadastrar CategoriaItem");
            System.out.println("2. Editar CategoriaItem");
            System.out.println("3. Consultar CategoriaItem");
            System.out.println("4. Listar CategoriaItem");
            System.out.println("0. Voltar ao menu principal");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a linha
    
            if (opcao == 0) {
                break;
            }
    
            switch (opcao) {
                case 1:
                    // Listar itens disponíveis para escolha
                    System.out.println("Itens disponíveis:");
                    itemManager.listar();
    
                    System.out.print("Digite o código do item: ");
                    int codigoItem = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
    
                    // Aqui você precisa buscar o item pelo código informado
                    Item itemSelecionado = new Item(codigoItem, "", 0); // Substitua "" e 0 pelos getters corretos do Item
    
                    // Listar categorias disponíveis para escolha
                    System.out.println("Categorias disponíveis:");
                    categoriaManager.listar();
    
                    System.out.print("Digite o código da categoria: ");
                    int codigoCategoria = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
    
                    // Aqui você precisa buscar a categoria pelo código informado
                    Categoria categoriaSelecionada = new Categoria(codigoCategoria, "", 0); // Substitua "" e 0 pelos getters corretos da Categoria
    
                    System.out.print("Digite a quantidade: ");
                    int quantidade = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
    
                    CategoriaItem categoriaItem = new CategoriaItem(itemSelecionado, categoriaSelecionada, quantidade);
                    System.out.println(categoriaItemManager.cadastrar(categoriaItem));
                    break;
                case 2:
                    System.out.print("Digite o código do item a editar: ");
                    int codigoEditarItem = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
    
                    System.out.print("Digite o código da categoria do item a editar: ");
                    int codigoEditarCategoria = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
    
                    // Listar itens disponíveis para escolha
                    System.out.println("Itens disponíveis:");
                    itemManager.listar();
    
                    System.out.print("Digite o novo código do item: ");
                    int novoCodigoItem = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
    
                    // Aqui você precisa buscar o item pelo código informado
                    Item novoItem = new Item(novoCodigoItem, "", 0); // Substitua "" e 0 pelos getters corretos do Item
    
                    // Listar categorias disponíveis para escolha
                    System.out.println("Categorias disponíveis:");
                    categoriaManager.listar();
    
                    System.out.print("Digite o novo código da categoria: ");
                    int novoCodigoCategoria = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
    
                    // Aqui você precisa buscar a categoria pelo código informado
                    Categoria novaCategoria = new Categoria(novoCodigoCategoria, "", 0); // Substitua "" e 0 pelos getters corretos da Categoria
    
                    System.out.print("Digite a nova quantidade: ");
                    int novaQuantidade = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
    
                    CategoriaItem categoriaItemParaEditar = new CategoriaItem(new Item(codigoEditarItem, "", 0), new Categoria(codigoEditarCategoria, "", 0), 0);
                    System.out.println(categoriaItemManager.editar(categoriaItemParaEditar, novoItem, novaCategoria, novaQuantidade));
                    break;
                case 3:
                    System.out.print("Digite o código do item a consultar: ");
                    int codigoConsultarItem = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
    
                    System.out.print("Digite o código da categoria do item a consultar: ");
                    int codigoConsultarCategoria = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
    
                    CategoriaItem categoriaItemParaConsultar = new CategoriaItem(new Item(codigoConsultarItem, "", 0), new Categoria(codigoConsultarCategoria, "", 0), 0);
                    categoriaItemManager.consultar(categoriaItemParaConsultar);
                    break;
                case 4:
                    categoriaItemManager.listar();
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
    private static void reservaOperations(Scanner scanner, Reserva reservaManager, Hospede hospedeManager, Quarto quartoManager, Funcionario funcionarioManager) throws IOException {
        while (true) {
            System.out.println("1. Realizar Reserva");
            System.out.println("2. Editar Reserva");
            System.out.println("3. Consultar Reserva");
            System.out.println("4. Listar Reservas");
            System.out.println("0. Voltar ao menu principal");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a linha
    
            if (opcao == 0) {
                break;
            }
    
            switch (opcao) {
                case 1:
                    System.out.print("Digite o código da reserva: ");
                    int codigoReserva = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
                    
                    System.out.print("Digite o CPF do hóspede: ");
                    String cpfHospede = scanner.nextLine();
                    Hospede hospede = new Hospede();
                    hospede.setCpf(cpfHospede);

                    System.out.print("Digite o código do quarto: ");
                    int codigoQuarto = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
                    Quarto quarto = new Quarto();
                    quarto.setCodigo(codigoQuarto);

                    System.out.print("Digite o CPF do funcionário reserva: ");
                    String cpfFuncionario = scanner.nextLine();
                    Funcionario funcionarioReserva = new Funcionario();
                    funcionarioReserva.setCpf(cpfFuncionario);

                    System.out.print("Digite o CPF do funcionário fechamento: ");
                    String cpfFuncionarioFechamento = scanner.nextLine();
                    Funcionario funcionarioFechamento = new Funcionario();
                    funcionarioReserva.setCpf(cpfFuncionarioFechamento);

                    System.out.print("Digite a data de entrada (formato YYYY-MM-DD): ");
                    String dataEntradaStr = scanner.nextLine();
                    Date dataEntrada = Date.valueOf(dataEntradaStr);
    
                    System.out.print("Digite a data de saída (formato YYYY-MM-DD): ");
                    String dataSaidaStr = scanner.nextLine();
                    Date dataSaida = Date.valueOf(dataSaidaStr);

                    System.out.print("Digite a data de checkin (formato YYYY-MM-DD): ");
                    String dataChekinStr = scanner.nextLine();
                    Date dataChekin = Date.valueOf(dataChekinStr);

                    System.out.print("Digite a data de checkout (formato YYYY-MM-DD): ");
                    String dataCheckoutStr = scanner.nextLine();
                    Date dataCheckout = Date.valueOf(dataCheckoutStr);

                    System.out.println("Digite o valor da reserva");
                    double valorReserva = scanner.nextDouble();
                    
   
                    System.out.println("Digite o valor da pago");
                    double valorPago = scanner.nextDouble();

                    Reserva reserva = new Reserva(codigoReserva,hospede,quarto,funcionarioReserva, funcionarioFechamento, dataEntrada, dataSaida, dataChekin, dataCheckout, valorReserva, valorPago);
                    System.out.println(reservaManager.cadastrar(reserva));
                    break;
                case 2:
                    System.out.print("Digite o código da reserva a editar: ");
                    int codigoEditar = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha

                    System.out.print("Digite o novo CPF do hóspede: ");
                    String novoCpfHospede = scanner.nextLine();
                    Hospede novoHospede = new Hospede();
                    novoHospede.setCpf(novoCpfHospede);

                    System.out.print("Digite o novo código do quarto: ");
                    int novoCodigoQuarto = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
                    Quarto novoQuarto = new Quarto();
                    novoQuarto.setCodigo(novoCodigoQuarto);

                    System.out.print("Digite o novo CPF do funcionário reserva: ");
                    String novoCpfFuncionario = scanner.nextLine();
                    Funcionario novoFuncionarioReserva = new Funcionario();
                    novoFuncionarioReserva.setCpf(novoCpfFuncionario);

                    System.out.print("Digite o novo CPF do funcionário fechamento: ");
                    String novoCpfFuncionarioFechamento = scanner.nextLine();
                    Funcionario novoFuncionarioFechamento = new Funcionario();
                    novoFuncionarioReserva.setCpf(novoCpfFuncionarioFechamento);

                    System.out.print("Digite a nova data de entrada (formato YYYY-MM-DD): ");
                    String novaDataEntradaStr = scanner.nextLine();
                    Date novaDataEntrada = Date.valueOf(novaDataEntradaStr);
    
                    System.out.print("Digite a nova data de saída (formato YYYY-MM-DD): ");
                    String novaDataSaidaStr = scanner.nextLine();
                    Date novaDataSaida = Date.valueOf(novaDataSaidaStr);
                    
                    System.out.print("Digite a nova data de checkin (formato YYYY-MM-DD): ");
                    String novaDataChekinStr = scanner.nextLine();
                    Date novaDataChekin = Date.valueOf(novaDataChekinStr);

                    System.out.print("Digite a nova data de checkout (formato YYYY-MM-DD): ");
                    String novaDataCheckoutStr = scanner.nextLine();
                    Date novaDataCheckout = Date.valueOf(novaDataCheckoutStr);
    
                    System.out.println("Digite o novo valor da reserva");
                    double novoValorReserva = scanner.nextDouble();
                    
                    System.out.println("Digite o novo valor da pago");
                    double novoValorPago = scanner.nextDouble();
    
                    Reserva reservaParaEditar = new Reserva(codigoEditar, novoHospede, novoQuarto, novoFuncionarioReserva, novoFuncionarioFechamento, novaDataEntrada, novaDataSaida,novaDataChekin,novaDataCheckout, novoValorReserva, novoValorPago); // Apenas para utilizar o método editar
                    System.out.println(reservaManager.editar(reservaParaEditar,codigoEditar, novoHospede, novoQuarto, novoFuncionarioReserva, novoFuncionarioFechamento, novaDataEntrada, novaDataSaida,novaDataChekin,novaDataCheckout, novoValorReserva, novoValorPago));
                    break;
                case 3:
                    System.out.print("Digite o código da reserva a consultar: ");
                    int codigoConsultar = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
    
                    reservaManager.consultar(codigoConsultar);
                    break;
                case 4:
                    reservaManager.listar();
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
    private static void consumoOperations(Scanner scanner, Consumo consumoManager, Item itemManager, Reserva reservaManager) throws IOException {
        while (true) {
            System.out.println("1. Cadastrar Consumo");
            System.out.println("2. Editar Consumo");
            System.out.println("3. Consultar Consumo");
            System.out.println("4. Listar Consumos");
            System.out.println("0. Voltar ao menu principal");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a linha
    
            if (opcao == 0) {
                break;
            }
    
            switch (opcao) {
                case 1:
                    System.out.print("Digite o código do item a consumir: ");
                    int codigoItem = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
                    Item item = new Item(codigoItem, "", 0.0);  // Pode mudar para buscar o item no gerenciador de item
                    System.out.print("Digite o código da reserva relacionada ao consumo: ");
                    int codigoReserva = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
                    Reserva reserva = new Reserva(codigoReserva, null, null, null, null, null, null, null, null, 0.0, 0.0);  // Pode mudar para buscar a reserva no gerenciador de reserva
                    System.out.print("Digite a quantidade solicitada: ");
                    int quantidadeSolicitada = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
                    System.out.print("Digite a data do consumo (AAAA-MM-DD): ");
                    String dataConsumoStr = scanner.nextLine();
                    Date dataConsumo = Date.valueOf(dataConsumoStr);
                    Consumo novoConsumo = new Consumo(item, reserva, quantidadeSolicitada, dataConsumo);
                    System.out.println(consumoManager.cadastrar(novoConsumo));
                    break;
                case 2:
                    System.out.print("Digite o código do item do consumo a editar: ");
                    int codigoItemEditar = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
                    System.out.print("Digite o código da reserva do consumo a editar: ");
                    int codigoReservaEditar = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
                    Consumo consumoParaEditar = new Consumo();
                    consumoParaEditar.setItem(new Item(codigoItemEditar, "", 0.0));
                    consumoParaEditar.setReserva(new Reserva(codigoReservaEditar, null, null, null, null, null, null, null, null, 0.0, 0.0));
                    System.out.print("Digite a nova quantidade solicitada: ");
                    int novaQuantidade = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
                    System.out.print("Digite a nova data do consumo (AAAA-MM-DD): ");
                    String novaDataConsumoStr = scanner.nextLine();
                    Date novaDataConsumo = Date.valueOf(novaDataConsumoStr);
                    consumoManager.editar(consumoParaEditar, null, null, novaQuantidade, novaDataConsumo);
                    System.out.println("Consumo editado com sucesso.");
                    break;
                case 3:
                    System.out.print("Digite o código do item do consumo a consultar: ");
                    int codigoItemConsultar = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
                    System.out.print("Digite o código da reserva do consumo a consultar: ");
                    int codigoReservaConsultar = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
                    Consumo consumoParaConsultar = new Consumo();
                    consumoParaConsultar.setItem(new Item(codigoItemConsultar, "", 0.0));
                    consumoParaConsultar.setReserva(new Reserva(codigoReservaConsultar, null, null, null, null, null, null, null, null, 0.0, 0.0));
                    consumoManager.consultar(consumoParaConsultar);
                    break;
                case 4:
                    consumoManager.listar();
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
    private static void consumoServicoOperations(Scanner scanner, ConsumoServico consumoServicoManager, Servico servicoManager, Reserva reservaManager) throws IOException {
        while (true) {
            System.out.println("1. Cadastrar Consumo de Serviço");
            System.out.println("2. Editar Consumo de Serviço");
            System.out.println("3. Consultar Consumo de Serviço");
            System.out.println("4. Listar Consumos de Serviço");
            System.out.println("0. Voltar ao menu principal");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a linha
    
            if (opcao == 0) {
                break;
            }
    
            switch (opcao) {
                case 1:
                    System.out.print("Digite o código do serviço a consumir: ");
                    int codigoServico = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
                    Servico servico = new Servico(codigoServico, "", 0.0);  // Pode mudar para buscar o serviço no gerenciador de serviço
                    System.out.print("Digite o código da reserva relacionada ao consumo de serviço: ");
                    int codigoReserva = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
                    Reserva reserva = new Reserva(codigoReserva, null, null, null, null, null, null, null, null, 0.0, 0.0);  // Pode mudar para buscar a reserva no gerenciador de reserva
                    System.out.print("Digite a quantidade solicitada: ");
                    int quantidadeSolicitada = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
                    System.out.print("Digite a data do serviço (AAAA-MM-DD): ");
                    String dataServicoStr = scanner.nextLine();
                    Date dataServico = Date.valueOf(dataServicoStr);
                    ConsumoServico novoConsumoServico = new ConsumoServico(servico, reserva, quantidadeSolicitada, dataServico);
                    System.out.println(consumoServicoManager.cadastrar(novoConsumoServico));
                    break;
                case 2:
                    System.out.print("Digite o código do serviço do consumo de serviço a editar: ");
                    int codigoServicoEditar = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
                    System.out.print("Digite o código da reserva do consumo de serviço a editar: ");
                    int codigoReservaEditar = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
                    ConsumoServico consumoServicoParaEditar = new ConsumoServico();
                    consumoServicoParaEditar.setServico(new Servico(codigoServicoEditar, "", 0.0));
                    consumoServicoParaEditar.setReserva(new Reserva(codigoReservaEditar, null, null, null, null, null, null, null, null, 0.0, 0.0));
                    System.out.print("Digite a nova quantidade solicitada: ");
                    int novaQuantidade = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
                    System.out.print("Digite a nova data do serviço (AAAA-MM-DD): ");
                    String novaDataServicoStr = scanner.nextLine();
                    Date novaDataServico = Date.valueOf(novaDataServicoStr);
                    consumoServicoManager.editar(consumoServicoParaEditar, null, null, novaQuantidade, novaDataServico);
                    System.out.println("Consumo de serviço editado com sucesso.");
                    break;
                case 3:
                    System.out.print("Digite o código do serviço do consumo de serviço a consultar: ");
                    int codigoServicoConsultar = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
                    System.out.print("Digite o código da reserva do consumo de serviço a consultar: ");
                    int codigoReservaConsultar = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha
                    ConsumoServico consumoServicoParaConsultar = new ConsumoServico();
                    consumoServicoParaConsultar.setServico(new Servico(codigoServicoConsultar, "", 0.0));
                    consumoServicoParaConsultar.setReserva(new Reserva(codigoReservaConsultar, null, null, null, null, null, null, null, null, 0.0, 0.0));
                    consumoServicoManager.consultar(consumoServicoParaConsultar);
                    break;
                case 4:
                    consumoServicoManager.listar();
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
    
    
}
