package br.edu.ifsp.scl.sdm.dicesdm

object ConfigSingleton{
    val config: Config = Config()

    //Usado para determinar qual o Fragment preenche a tela principal
    object modos {
        val MODO_GRAFICO = "MODO_GRAFICO"
        val MODO_TEXTO = "MODO_TEXTO"
    }

}