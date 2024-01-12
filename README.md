# DESAFIO - Clínica de Consultas Ágil

Neste desafio, foi proposto a criação de um sistema ágil para uma clínica, incluindo o cadastro de pacientes, agendamento e cancelamento de consultas, e uma opção para sair. Além disso, foi necessário implementar tratamento de erros para evitar duplicidade de cadastros e consultas, como pacientes não podendo ser cadastrados duas vezes e consultas não podendo ser marcadas em horários já agendados, entre outras restrições.

## Funcionalidades

1. **Cadastrar Paciente:** Permite o cadastro de novos pacientes, exigindo informações como nome e telefone, com validação para evitar duplicidade.

2. **Marcações de Consultas:** Possibilita o agendamento de consultas para pacientes cadastrados, com verificação de disponibilidade de horários.

3. **Cancelamento de Consultas:** Permite o cancelamento de consultas marcadas, removendo-as da lista de consultas ativas.

4. **Listar Pacientes:** Opção para visualizar a lista de todos os pacientes cadastrados, incluindo informações de nome e telefone.

5. **Sair:** Encerra o programa.

## Tratamento de Erros

- Pacientes não podem ser cadastrados duas vezes, e a duplicidade é evitada ao exigir que o número de telefone seja único entre os cadastros.
- Consultas não podem ser marcadas em dias e horários já agendados, e datas retroativas não são permitidas.

## Simulação do Banco de Dados

Para salvar os dados dos pacientes e as consultas marcadas, foi implementada uma simulação de banco de dados. A conexão entre os dados e o código principal é realizada por meio de operações de entrada e saída, utilizando as classes `ObjectOutputStream` e `ObjectInputStream`. Essas classes são responsáveis por serializar os objetos em bytes e desserializá-los, permitindo a persistência dos dados em arquivos de texto (.txt).

## Execução do Programa

Ao executar o programa, você terá acesso a um menu interativo que oferece as opções mencionadas acima. As operações são executadas em tempo real, e os dados são salvos automaticamente para garantir a persistência entre diferentes execuções do programa.

---
**Observação:** Certifique-se de seguir as instruções de uso e instalação do sistema para garantir seu funcionamento adequado.

