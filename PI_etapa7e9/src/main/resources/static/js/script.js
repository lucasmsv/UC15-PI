document.getElementById("cadastroForm").addEventListener("submit", function (event) {
    event.preventDefault();

    var nome = document.getElementById("nome").value;
    var documento = document.getElementById("documento").value;
    var tipoVeiculo = document.getElementById("tipoVeiculo").value;
    var placa = document.getElementById("placa").value;
    var dataHora = document.getElementById("dataHora").value;

    console.log("Nome:", nome);
    console.log("Documento:", documento);
    console.log("Tipo de Veículo:", tipoVeiculo);
    console.log("Placa:", placa);
    console.log("Data/Hora do Cadastro:", dataHora);

    this.reset();
});
