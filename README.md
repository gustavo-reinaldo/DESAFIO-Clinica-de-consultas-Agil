#DESAFIO-Clinica de consultas agil
Neste desafio, foi proposto a criação de um sistema ágil de uma clínica, com um cadastro de paciente, agendamento de consulta, cancelamento de consulta e uma opção para sair, 
também foi proposto o tratamento de erros, onde o paciente não pode ser cadastrado duas vezes, e, para evitar a duplicidade, é exigido que o numero de telefone seja diferente entre
os cadastros, pacientes também não podem marcar consultas em dias e horarios que já hajam algum agendamento prévio, e as consultas não podem ser marcadas antes da data atual.

para salvar os dados dos usuários e as consultas marcadas, foi proposto a simulação de um banco de dados. 
O Fluxo de dados que escolhi usar foi com uma conexão entre arquivos de texto(.txt) e o meu código main, usando operações de entrada e saida, no caso,
as classes "ObjectOutputStream" e "ObjectInputStream", que são usadas para serializar os objetos em bytes e desserializar os mesmos, respectivamente.
