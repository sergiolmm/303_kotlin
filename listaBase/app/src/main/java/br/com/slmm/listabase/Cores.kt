package br.com.slmm.listabase

class Cores (nome: String, valor: String){
    var nome: String = ""
        private set
    var valor: String = ""
        private set

    init {
        require(nome.trim().length > 2){
            println("Informe um nome")
        }
        require( valor.trim().length > 2){
            println("informe um valor")
        }
        this.nome = nome
        this.valor = valor
    }
    fun teste(): Int {
        return 10
    }

}