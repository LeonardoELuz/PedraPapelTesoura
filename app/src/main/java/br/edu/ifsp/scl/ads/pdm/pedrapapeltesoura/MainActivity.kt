package br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura.databinding.ActivityMainBinding
import br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura.model.ConfiguracaoFirebase
import java.util.*
import kotlin.concurrent.schedule
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var geradorRandomico: Random
    private var configuracaoAuxiliar: Configuracao = Configuracao()
    val firebaseDb = ConfiguracaoFirebase()

    private lateinit var settingsActivityLauncher: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        geradorRandomico = Random(System.currentTimeMillis())

        //No clique do botão jogar
        activityMainBinding.jogarBt.setOnClickListener {

            val listaJogadas = resources.getStringArray(R.array.jogadas)
            var jogada = ""
            val jogadaAutomatica = listaJogadas.random()
            var jogadaAutomatica2 = listaJogadas.random()


            //Pega a jogada do usuário
            if (activityMainBinding.pedraBt.isChecked) jogada = "Pedra"
            else if (activityMainBinding.papelBt.isChecked) jogada = "Papel"
            else if (activityMainBinding.tesouraBt.isChecked) jogada = "Tesoura"

            //Calcula o vencedor
            var resultado: Int = -1
            if (jogada == jogadaAutomatica) {
                resultado = 0
            }
            else if (jogada == "Pedra") {
                if (jogadaAutomatica == "Papel") {
                    resultado = 2
                }
                else if (jogadaAutomatica == "Tesoura") {
                    resultado = 1
                }
            }
            else if (jogada == "Papel") {
                if (jogadaAutomatica == "Tesoura") {
                    resultado = 2
                }
                else if (jogadaAutomatica == "Pedra") {
                    resultado = 1
                }
            }
            else if (jogada == "Tesoura") {
                if (jogadaAutomatica == "Pedra") {
                    resultado = 2
                }
                else if (jogadaAutomatica == "Papel") {
                    resultado = 1
                }
            }

            //Caso haja dois jogadores
            if (configuracaoAuxiliar.numeroJogadores == 2) {

                //Partida empata quando todas as mãos saem em uma partida com mais de 2 jogadores
                if (jogada == jogadaAutomatica && jogadaAutomatica == jogadaAutomatica2) {
                    resultado = 0
                }
                else if (jogada == "Pedra" && jogadaAutomatica == "Papel" && jogadaAutomatica2 == "Tesoura") {
                    resultado = 0
                }
                else if (jogada == "Pedra" && jogadaAutomatica == "Tesoura" && jogadaAutomatica2 == "Papel") {
                    resultado = 0
                }
                else if (jogada == "Papel" && jogadaAutomatica == "Pedra" && jogadaAutomatica2 == "Tesoura") {
                    resultado = 0
                }
                else if (jogada == "Papel" && jogadaAutomatica == "Tesoura" && jogadaAutomatica2 == "Pedra") {
                    resultado = 0
                }
                else if (jogada == "Tesoura" && jogadaAutomatica == "Pedra" && jogadaAutomatica2 == "Papel") {
                    resultado = 0
                }
                else if (jogada == "Tesoura" && jogadaAutomatica == "Papel" && jogadaAutomatica2 == "Pedra") {
                    resultado = 0
                }

                //Caso o vencedor do primeiro turno seja o jogador 1
                else if (resultado == 1) {
                    if (jogada == jogadaAutomatica2) {
                        resultado = 0
                    }
                    else if (jogada == "Pedra") {
                        if (jogadaAutomatica2 == "Papel") {
                            resultado = 3
                        }
                        else if (jogadaAutomatica2 == "Tesoura") {
                            resultado = 1
                        }
                    }
                    else if (jogada == "Papel") {
                        if (jogadaAutomatica2 == "Tesoura") {
                            resultado = 3
                        }
                        else if (jogadaAutomatica2 == "Pedra") {
                            resultado = 1
                        }
                    }
                    else if (jogada == "Tesoura") {
                        if (jogadaAutomatica2 == "Pedra") {
                            resultado = 3
                        }
                        else if (jogadaAutomatica2 == "Papel") {
                            resultado = 1
                        }
                    }
                }
                //Caso o vencedor seja o computador 1
                else if (resultado == 2) {
                    if (jogadaAutomatica == jogadaAutomatica2) {
                        resultado = 0
                    }
                    else if (jogadaAutomatica == "Pedra") {
                        if (jogadaAutomatica2 == "Papel") {
                            resultado = 3
                        }
                        else if (jogadaAutomatica2 == "Tesoura") {
                            resultado = 2
                        }
                    }
                    else if (jogadaAutomatica == "Papel") {
                        if (jogadaAutomatica2 == "Tesoura") {
                            resultado = 3
                        }
                        else if (jogadaAutomatica2 == "Pedra") {
                            resultado = 2
                        }
                    }
                    else if (jogadaAutomatica == "Tesoura") {
                        if (jogadaAutomatica2 == "Pedra") {
                            resultado = 3
                        }
                        else if (jogadaAutomatica2 == "Papel") {
                            resultado = 2
                        }
                    }
                }
                //Caso seja um empate
                else if (resultado == 0){
                    if (jogada == jogadaAutomatica2){
                        resultado = 0
                    }
                    else if (jogada == "Pedra") {
                        if (jogadaAutomatica2 == "Papel") {
                            resultado = 3
                        }
                        else if (jogadaAutomatica2 == "Tesoura") {
                            resultado = 0
                        }
                    }
                    else if (jogada == "Papel") {
                        if (jogadaAutomatica2 == "Tesoura") {
                            resultado = 3
                        }
                        else if (jogadaAutomatica2 == "Pedra") {
                            resultado = 0
                        }
                    }
                    else if (jogada == "Tesoura") {
                        if (jogadaAutomatica2 == "Pedra") {
                            resultado = 3
                        }
                        else if (jogadaAutomatica2 == "Papel") {
                            resultado = 0
                        }
                    }
                }
            }


//----MANIPULAÇÃO DO LAYOUT
            //Exibindo as jogadas dos computadores
            val statusComputador1 = activityMainBinding.statusComputador1Tv
            val imagemComputador1 = activityMainBinding.computador1Im

            val statusComputador2 = activityMainBinding.statusComputador2Tv
            val imagemComputador2 = activityMainBinding.computador2Im

            val vencedor = activityMainBinding.vencedorTv

            activityMainBinding.resultadoLy.visibility = View.GONE

            //Reseta o layout do computador 1
            imagemComputador1.visibility = View.GONE
            //Exibe o carregamento do jogador 1
            statusComputador1.visibility = View.VISIBLE
            statusComputador1.text = "Computador 1 planejando..."

            //Reseta o layout do computador 2
            imagemComputador2.visibility = View.GONE
            //Exibe o carregamento do jogador 1
            statusComputador2.visibility = View.VISIBLE
            statusComputador2.text = "Computador 2 planejando..."


            Timer().schedule(2000) {
                runOnUiThread(Runnable {

                    //Retira o carregamento
                    statusComputador1.visibility = View.GONE
                    //Insere a jogada do computador
                    imagemComputador1.visibility = View.VISIBLE

                    if (jogadaAutomatica == "Pedra") {
                        imagemComputador1.setImageResource(
                            resources.getIdentifier("pedra", "mipmap", packageName)
                        )
                    } else if (jogadaAutomatica == "Papel") {
                        imagemComputador1.setImageResource(
                            resources.getIdentifier("papel", "mipmap", packageName)
                        )
                    } else if (jogadaAutomatica == "Tesoura") {
                        imagemComputador1.setImageResource(
                            resources.getIdentifier("tesoura", "mipmap", packageName)
                        )
                    }

                    //Retira o carregamento
                    statusComputador2.visibility = View.GONE
                    //Insere a jogada do computador
                    imagemComputador2.visibility = View.VISIBLE

                    if (jogadaAutomatica2 == "Pedra") {
                        imagemComputador2.setImageResource(
                            resources.getIdentifier("pedra", "mipmap", packageName)
                        )
                    } else if (jogadaAutomatica2 == "Papel") {
                        imagemComputador2.setImageResource(
                            resources.getIdentifier("papel", "mipmap", packageName)
                        )
                    } else if (jogadaAutomatica2 == "Tesoura") {
                        imagemComputador2.setImageResource(
                            resources.getIdentifier("tesoura", "mipmap", packageName)
                        )
                    }


                   activityMainBinding.resultadoLy.visibility = View.VISIBLE

                    if (resultado == 0) {
                        vencedor.text = "Partida empatada!"
                    } else if (resultado == 1) {
                        vencedor.text = "Jogador venceu!!"
                    } else if (resultado == 2) {
                        vencedor.text = "Computador 1 venceu!"
                    } else if (resultado == 3) {
                        vencedor.text = "Computador 2 venceu!"
                    }

                })

            }

        }

        //-----Manipulando o layout de acordo com o firebase
        //Gambiarrinha porque o firebase estava demorando para concluir o acesso
        Timer().schedule(1000) {
            runOnUiThread(Runnable {

                //---Caso exista uma configuração no firebase
                val configuracaoFirebase = firebaseDb.recuperaConfiguracao()

                if (configuracaoFirebase != null) {
                    //Reseta o layout
                    activityMainBinding.resultadoLy.visibility = View.GONE
                    activityMainBinding.computador1Im.visibility = View.INVISIBLE
                    activityMainBinding.statusComputador1Tv.text = "Aguardando jogador..."
                    activityMainBinding.computador2Im.visibility = View.INVISIBLE
                    activityMainBinding.statusComputador2Tv.text = "Aguardando jogador..."

                    if (configuracaoFirebase == 1) {
                        activityMainBinding.computador2Ly.visibility = View.GONE
                    } else if (configuracaoFirebase == 2) {
                        activityMainBinding.computador2Ly.visibility = View.VISIBLE
                    }
                    configuracaoAuxiliar.numeroJogadores = configuracaoFirebase
                }
            })
        }

            //-----Manipulando o layout de acordo com as configurações escolhidas pelo usuário
            settingsActivityLauncher =
                registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                    if (result.resultCode == RESULT_OK) {
                        if (result.data != null) {
                            val configuracao: Configuracao? =
                                result.data?.getParcelableExtra<Configuracao>(Intent.EXTRA_USER)
                            if (configuracao != null) {
                                //Reseta o layout
                                activityMainBinding.resultadoLy.visibility = View.GONE
                                activityMainBinding.computador1Im.visibility = View.INVISIBLE
                                activityMainBinding.statusComputador1Tv.visibility = View.VISIBLE
                                activityMainBinding.statusComputador2Tv.visibility = View.VISIBLE
                                activityMainBinding.statusComputador1Tv.text = "Aguardando jogador..."
                                activityMainBinding.computador2Im.visibility = View.INVISIBLE
                                activityMainBinding.statusComputador2Tv.text = "Aguardando jogador..."
                                if (configuracao.numeroJogadores == 1) {
                                    activityMainBinding.computador2Ly.visibility = View.GONE
                                } else if (configuracao.numeroJogadores == 2) {
                                    activityMainBinding.computador2Ly.visibility = View.VISIBLE
                                }
                                configuracaoAuxiliar = configuracao
                            }
                        }
                    }
                }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.settingMi) {
            val settingsIntent = Intent(this, SettingsActivity::class.java)
            settingsActivityLauncher.launch(settingsIntent)
            return true
        }
        return false
    }

}