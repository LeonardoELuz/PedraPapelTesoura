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
import br.edu.ifsp.scl.ads.pdm.pedrapapeltesoura.model.Jogadores
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.concurrent.schedule
import kotlin.random.Random
import kotlin.random.nextInt

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

        activityMainBinding.jogarBt.setOnClickListener {

            val listaJogadas = resources.getStringArray(R.array.jogadas)
            val jogadaAutomatica = listaJogadas.random()
            var jogada = ""
            var jogada2 = ""

            if (activityMainBinding.pedraBt.isChecked) jogada = "Pedra"
            else if (activityMainBinding.papelBt.isChecked) jogada = "Papel"
            else if (activityMainBinding.tesouraBt.isChecked) jogada = "Tesoura"

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

                //Pega a jogada do segundo computador
                if (activityMainBinding.pedra2Bt.isChecked) {
                    jogada2 = "Pedra"
                }
                else if (activityMainBinding.papel2Bt.isChecked) {
                    jogada2 = "Papel"
                }
                else if (activityMainBinding.tesoura2Bt.isChecked) {
                    jogada2 = "Tesoura"
                }

                //Partida empata quando todas as mãos saem em uma partida com mais de 2 jogadores
                if (jogada == jogadaAutomatica && jogadaAutomatica == jogada2) {
                    resultado = 0
                }
                else if (jogada == "Pedra" && jogadaAutomatica == "Papel" && jogada2 == "Tesoura") {
                    resultado = 0
                }
                else if (jogada == "Pedra" && jogadaAutomatica == "Tesoura" && jogada2 == "Papel") {
                    resultado = 0
                }
                else if (jogada == "Papel" && jogadaAutomatica == "Pedra" && jogada2 == "Tesoura") {
                    resultado = 0
                }
                else if (jogada == "Papel" && jogadaAutomatica == "Tesoura" && jogada2 == "Pedra") {
                    resultado = 0
                }
                else if (jogada == "Tesoura" && jogadaAutomatica == "Pedra" && jogada2 == "Papel") {
                    resultado = 0
                }
                else if (jogada == "Tesoura" && jogadaAutomatica == "Papel" && jogada2 == "Pedra") {
                    resultado = 0
                }

                //Caso o vencedor do primeiro turno seja o jogador 1
                else if (resultado == 1) {
                    if (jogada == jogada2) {
                        resultado = 0
                    }
                    else if (jogada == "Pedra") {
                        if (jogada2 == "Papel") {
                            resultado = 3
                        }
                        else if (jogada2 == "Tesoura") {
                            resultado = 1
                        }
                    }
                    else if (jogada == "Papel") {
                        if (jogada2 == "Tesoura") {
                            resultado = 3
                        }
                        else if (jogada2 == "Pedra") {
                            resultado = 1
                        }
                    }
                    else if (jogada == "Tesoura") {
                        if (jogada2 == "Pedra") {
                            resultado = 3
                        }
                        else if (jogada2 == "Papel") {
                            resultado = 1
                        }
                    }
                }
                //Caso o vencedor seja o computador
                else if (resultado == 2) {
                    if (jogadaAutomatica == jogada2) {
                        resultado = 0
                    }
                    else if (jogadaAutomatica == "Pedra") {
                        if (jogada2 == "Papel") {
                            resultado = 3
                        }
                        else if (jogada2 == "Tesoura") {
                            resultado = 2
                        }
                    }
                    else if (jogadaAutomatica == "Papel") {
                        if (jogada2 == "Tesoura") {
                            resultado = 3
                        }
                        else if (jogada2 == "Pedra") {
                            resultado = 2
                        }
                    }
                    else if (jogadaAutomatica == "Tesoura") {
                        if (jogada2 == "Pedra") {
                            resultado = 3
                        }
                        else if (jogada2 == "Papel") {
                            resultado = 2
                        }
                    }
                }
                //Caso seja um empate
                else if (resultado == 0){
                    if (jogada == jogada2){
                        resultado = 0
                    }
                    else if (jogada == "Pedra") {
                        if (jogada2 == "Papel") {
                            resultado = 3
                        }
                        else if (jogada2 == "Tesoura") {
                            resultado = 0
                        }
                    }
                    else if (jogada == "Papel") {
                        if (jogada2 == "Tesoura") {
                            resultado = 3
                        }
                        else if (jogada2 == "Pedra") {
                            resultado = 0
                        }
                    }
                    else if (jogada == "Tesoura") {
                        if (jogada2 == "Pedra") {
                            resultado = 3
                        }
                        else if (jogada2 == "Papel") {
                            resultado = 0
                        }
                    }
                }
            }


//----JOGADA DO COMPUTADOR E RESULTADOS
                val statusComputador = activityMainBinding.statusComputadorTv
                val imagemComputador = activityMainBinding.computadorIm
                val resultadoTv = activityMainBinding.resultadoTv

                //Reseta o layout
                imagemComputador.visibility = View.GONE
                resultadoTv.visibility = View.GONE
                //Exibe o carregamento
                statusComputador.visibility = View.VISIBLE
                statusComputador.text = "Computador planejando..."


                Timer().schedule(2000) {
                    runOnUiThread(Runnable {

                        //Retira o carregamento
                        statusComputador.visibility = View.GONE
                        //Insere a jogada do computador
                        imagemComputador.visibility = View.VISIBLE

                        if (jogadaAutomatica == "Pedra") {
                            imagemComputador.setImageResource(
                                resources.getIdentifier("pedra", "mipmap", packageName)
                            )
                        } else if (jogadaAutomatica == "Papel") {
                            imagemComputador.setImageResource(
                                resources.getIdentifier("papel", "mipmap", packageName)
                            )
                        } else if (jogadaAutomatica == "Tesoura") {
                            imagemComputador.setImageResource(
                                resources.getIdentifier("tesoura", "mipmap", packageName)
                            )
                        }

                        resultadoTv.visibility = View.VISIBLE

                        if (resultado == 0) {
                            resultadoTv.text = "Partida empatada!"
                        } else if (resultado == 1) {
                            resultadoTv.text = "Jogador 1 venceu!!"
                        } else if (resultado == 2) {
                            resultadoTv.text = "Computador venceu!"
                        } else if (resultado == 3) {
                            resultadoTv.text = "Jogador 2 venceu!"
                        }

                    })

                }

        }

//-----Firebase
        //Gambiarrinha porque o firebase estava demorando para concluir o acesso
        Timer().schedule(1000) {
            runOnUiThread(Runnable {

                //---Caso exista uma configuração no firebase
                val configuracaoFirebase = firebaseDb.recuperaConfiguracao()

                if (configuracaoFirebase != null) {
                    Log.d("TAG", configuracaoFirebase.toString());
                    activityMainBinding.computadorIm.visibility = View.GONE
                    activityMainBinding.resultadoTv.visibility = View.GONE
                    if (configuracaoFirebase == 1) {
                        activityMainBinding.jogador2Ly.visibility = View.GONE
                    } else if (configuracaoFirebase == 2) {
                        activityMainBinding.jogador2Ly.visibility = View.VISIBLE
                    }
                    configuracaoAuxiliar.numeroJogadores = configuracaoFirebase
                }
            })
        }


//-----INTENTS E MENUS

            settingsActivityLauncher =
                registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                    if (result.resultCode == RESULT_OK) {
                        if (result.data != null) {
                            val configuracao: Configuracao? =
                                result.data?.getParcelableExtra<Configuracao>(Intent.EXTRA_USER)
                            if (configuracao != null) {
                                activityMainBinding.computadorIm.visibility = View.GONE
                                activityMainBinding.resultadoTv.visibility = View.GONE
                                if (configuracao.numeroJogadores == 1) {
                                    activityMainBinding.jogador2Ly.visibility = View.GONE
                                } else if (configuracao.numeroJogadores == 2) {
                                    activityMainBinding.jogador2Ly.visibility = View.VISIBLE
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